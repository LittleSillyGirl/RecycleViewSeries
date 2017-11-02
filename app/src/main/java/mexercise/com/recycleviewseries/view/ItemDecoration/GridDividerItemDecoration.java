package mexercise.com.recycleviewseries.view.ItemDecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


/**
 * 网状的itemDecoration的装饰效果
 *
 * 
 * @author lcl
 * @time 2017/10/30 14:38
 * @version 
 */ 

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "GridDividerItemDecorati";

    public static final int HORIZONTAL_GRID = GridLayoutManager.HORIZONTAL;

    public static final int VERTICAL_GRID = GridLayoutManager.VERTICAL;

    /**
     * 思路：
     *
     *      1、不管设么类型的 都要有方向 即：横竖
     *      2、装饰的drawable
     *      3、考虑你要画到层的上面还是下面 同时考虑你是否需要给每个item设置padding
     */

    /**
     * 我们先添加RecycleView的方向
     */
    private int mOrient;

    /**
     * 设置装饰的drawable
     */
    private Drawable mDivider;

    /**
     * ItemDecoration的type 也就是你要走ondraw还是ondrawOver方法
     *      0 ： ondraw  1：ondrawOver
     *
     */
    private int type;

    /**
     *  网状的列数
     *
     */
    private int spanCount;

    public GridDividerItemDecoration(Context context, int spanNum, int orient, int type) {
        final TypedArray a = context.obtainStyledAttributes(new int[]{
                android.R.attr.listDivider
        });
        mDivider = a.getDrawable(0);
        a.recycle();
        this.spanCount = spanNum;
        this.type = type;
        setOrientation(orient);
    }

    public void setOrientation(int orient) {
        if (orient != HORIZONTAL_GRID && orient != VERTICAL_GRID) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrient = orient;
    }

    /**
     * 给每个item设置偏移量也就是所谓的padding， 不设置的话 就要用onDrawOver  不要用哪个onDraw
     *   不然你的ItemDecoration其实是无效的 因为它被Item覆盖了
     *
     *
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        if (type==0){
            // 这个是走ondraw方法  所以getItemOffSets 一定要设置偏移量 不然没效果
            // 因为onDraw是优先于drawChildren执行的 后者是覆盖在前者上面的 如果不设置偏移量的话 就完全覆盖了看不到装饰线了

//        }else if (type==1){
            // 这个是走onDrawOver方法 可以设置偏移量也可以不设置
            // 通过ui发现 如果不设置这个偏移量的话 最后一行会消失
           /**
            * 思路是： spanCount 的除数为0  这是最右边的  不需要leftPadding
            *
            *
            */
            int layoutPos = parent.getChildLayoutPosition(view);
            int pos = parent.getChildAdapterPosition(view);
        Log.i(TAG, "getItemOffsets: itempso==" + pos +"==layoutPos===" + layoutPos);
            int childCount = parent.getAdapter().getItemCount();
        Log.i(TAG, "getItemOffsets: chi===" + childCount +"===" + parent.getLayoutManager().getItemCount() + "===child==" + parent.getLayoutManager().getChildCount());

            if (mOrient==VERTICAL_GRID){
                if (!isLastRawOrColum(pos, childCount)){
                    if (pos%spanCount==(spanCount-1)){
                        outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
                    }else{
                        outRect.set(0, 0, mDivider.getIntrinsicHeight(), mDivider.getIntrinsicHeight());
                    }
                }else{
                    if (pos%spanCount!=(spanCount-1)){
                        outRect.set(0, 0, mDivider.getIntrinsicHeight(), 0);
                    }
                }
            }else if (mOrient==HORIZONTAL_GRID){
                //当然也可以不用设置这个偏移量  根据需求来定 正常来说两个边是不用设置偏移量的
                if (!isLastRawOrColum(pos, childCount)){
                    outRect.set(0, 0, mDivider.getIntrinsicHeight(), mDivider.getIntrinsicHeight());
                }


//                if (pos<(parent.getChildCount()-3)){
//                    outRect.set(0, 0, mDivider.getIntrinsicHeight(), mDivider.getIntrinsicHeight());
//                }else{
//                    outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
//                }
            }

//        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (type==0){
            //起效果
            drawDecor(c, parent, state);
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (type==1){
            //起效果
            drawDecor(c, parent, state);
        }

    }

    /**
     *  绘制装饰线
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawDecor(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrient==VERTICAL_GRID){
            drawVertDecor(c, parent, state);
        }else if (mOrient==HORIZONTAL_GRID){
            drawHorizDecor(c, parent, state);
        }
    }

    /**
     *
     *  绘制竖直的装饰线
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawVertDecor(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (type==0){

            //这里有两种方案  一种你就是你绘制的比item的多出一点 然后他覆盖；一种是你只绘制多出的那点它覆盖
            // 个人比较倾向于后者
            drawDecoration(c, parent);
        }else if (type==1){
            drawDecoration(c, parent);
        }
    }

    /**
     * 绘制装饰线
     *
     * @param c
     * @param parent
     */
    private void drawDecoration(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int childH = child.getMeasuredHeight();
            int childW = child.getMeasuredWidth();

            Log.i(TAG, "drawVertDecor:  child的宽高==" + childW +"===高===" + childH);
            int left = child.getLeft();
            int top = child.getTop();
            int right = child.getRight();
            int bottom = child.getBottom();
            Log.i(TAG, i + "==drawVertDecor: v相对于父容器的四个坐标 left==" + left + "=top=" + top +"=right=" + right + "=bottom=" + bottom);
            Log.i(TAG, "drawVertDecor: 宽度==" + (right-left) + "==高度===" + (bottom-top));

            //这次我们在绘制装饰线的时候 为了更好的展示效果 我们绘制的装饰线是半个
            //这里其实left和right都不用绘制 只是为了更好的展示效果
            //top+childH  其实就是bottom 所以这里可以有多种写法
            //这里就牵扯到margin和padding了  我们是否想覆盖这些 正常情况下都是覆盖的
            int drawableH = mDivider.getIntrinsicHeight();
            RecyclerView.LayoutParams childParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            mDivider.setBounds((left+50), bottom + childParams.bottomMargin, right -50, bottom + drawableH);
            mDivider.draw(c);
            //这种方法就是绘制二次
            mDivider.setBounds(right + childParams.rightMargin, top + 50, right + drawableH, bottom - 50);
            mDivider.draw(c);
            //还有一种方法就是旋转，当然了还有其他的方法
//                c.save();
//                c.rotate(90, right, bottom + drawableH);
//                mDivider.draw(c);
//                c.restore();

        }
    }

    /**
     * 绘制横向的装饰线
     *
     * @param c
     * @param parent
     * @param state
     */
    private void drawHorizDecor(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (type==0){
            drawDecoration(c, parent);
        }else if (type==1){
            drawDecoration(c, parent);
        }
    }


    /**
     * 用来判断当前位置是否是最后一排或者最后一列  通用
     *      原理是： 不管横着排还是竖着排 最后一列或者一排都是最后三个  所以我们就除余
     *              1、余数为0 就是说明整除了  那么最后一排或者一列的第一个位置就是 总数-列数或者排数-1；
     *              2、余数不为0 那最后一排或者一列的第一个位置就是 总数-余数-1；
     *
     *
     * @param pos
     * @param childCount
     * @return
     */
    private boolean isLastRawOrColum(int pos, int childCount){
        int remainder = childCount % spanCount;
        int lastRawOrColumPos = -1;
        if (remainder == 0){
            lastRawOrColumPos = childCount - spanCount -1 ;
        }else{
            lastRawOrColumPos = childCount - remainder - 1;
        }
        Log.i(TAG, "isLastRawOrColum: lastRawOrColumPos==" + lastRawOrColumPos);
        if (pos>lastRawOrColumPos){
            return  true;
        }else{
            return false;
        }
    }


    //这是自己开始想的方法 但是感觉不对 网上也有

    /**
     * 是否是最后一排
     *
     * @param pos
     * @param childCount
     * @return
     */
    private boolean isLastRow(int pos, int childCount){
        //childCount - childCount%spanCount 这个表示最后一排的第一个
            Log.i(TAG, childCount + "==isLastRow: pos==" + pos + "==" + (childCount - childCount%spanCount));
        if (pos > (childCount - childCount%spanCount)){
            Log.i(TAG, "isLastRow: 最后一排===" + pos);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 是否是最后一列
     *
     * @param pos
     * @param childCount
     * @return
     */
    private boolean isLastColum(int pos, int childCount){
        //childCount/spanCount 最后一列
        if ((pos/spanCount - childCount/spanCount)==0){
            return true;
        }else{
            return false;
        }
    }
}
