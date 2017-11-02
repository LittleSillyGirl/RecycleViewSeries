package mexercise.com.recycleviewseries.view.ItemDecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 通过ItemDecoration来实现RecycleView的添加头部view和底部view
 *
 *      区别于以前的通过itemType或者装饰着设计模式实现的
 *
 *      这里还有一种方案 就是解析layout布局 绘制在第一个Item上  这个方案也是不错的
 *      链接：http://blog.csdn.net/xyq046463/article/details/53009635?locationNum=3&fps=1
 *
 *
 *
 * @author lcl
 * @time 2017/11/1 11:04
 * @version
 */

public class HeadBottomItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "HeadBottomItemDecoratio";


    /**
     *  表示RecycleView的方向的
     */
    private int mOrient;

    /**
     * 表示ItemDecoration的资源
     * drawable 是怎么绘制是通过canvas  所以 你想绘制什么都行
     *
     *
     */
    private Drawable mDivider;

    /**
     *  绘制装饰线的画笔
     */
    private Paint mPaint;

    private int mColor;

    private int headH;
    private int bottomH;

    private final int VERTICAL_LINELAYOUT = LinearLayoutManager.VERTICAL;

    private final int HORIZONTAL_LINELAYOUT = LinearLayoutManager.HORIZONTAL;

    public HeadBottomItemDecoration(Context context, int orient, int colorId, int headH, int bottomH) {

        //这里是为了简单，用系统的listDIvider了 正常开发我们可以随时设置drawable
        final TypedArray a = context.obtainStyledAttributes(new int[]{
                android.R.attr.listDivider
        });
        mDivider = a.getDrawable(0);
        a.recycle();

        //这里我们初始化我们要绘制头部和底部的画笔
        initPaint(colorId);


        this.mOrient = orient;
        this.mColor = colorId;
        this.headH = headH;
        this.bottomH = bottomH;
    }

    /**
     * 初始化head和bottom的画笔  这里简单点 就只有一个画笔 正常是头部和底部的要区分开 类似我们headH和bottomH
     *
     * @param colorId
     */
    private void initPaint(int colorId) {
        //打开锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(colorId);
        //画笔是填充的
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //这里我们只要给第一个和最后一个设置itemDecoration
        //注意区分getChildLayoutPosition()和getChildAdapterPosition这两个方法
        //还有就是parent.getChildCount() 这个是指的屏幕上可见的view总数， 不是item的实际总数
        //我们要用这个获得总数 parent.getAdapter().getItemCount()

        int pos = parent.getChildAdapterPosition(view);
        int childCount = parent.getAdapter().getItemCount();
        if (pos==0){
            //第一个位置的
            if (mOrient==VERTICAL_LINELAYOUT){
                outRect.set(0, headH, 0, mDivider.getIntrinsicHeight());
            }else if (mOrient==HORIZONTAL_LINELAYOUT){
                outRect.set(headH, 0, mDivider.getIntrinsicHeight(), 0);
            }
        }else if (pos == childCount-1){
            //最后一个位置的
            if (mOrient==VERTICAL_LINELAYOUT){
                outRect.set(0, 0, 0, bottomH);
            }else if (mOrient==HORIZONTAL_LINELAYOUT){
                outRect.set(0, 0, bottomH, 0);
            }
        }else{
            //中间的就是正常的分割线
            if (mOrient==VERTICAL_LINELAYOUT){
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }else if (mOrient==HORIZONTAL_LINELAYOUT){
                outRect.set(0, 0, mDivider.getIntrinsicHeight(), 0);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) v.getLayoutParams();

            int left = v.getLeft();
            int top = v.getTop();
            int right = v.getRight();
            int bottom = v.getBottom();

            int vH = params.height;
            int vW = params.width;

            // TODO: 2017/11/1 这里你要考虑的还有就是性能 当不要展示的话 就不要绘制
            // 如果第一个item已经被回收,没有显示在recycler view上,则不需要draw header
            if (parent.getChildAdapterPosition(v) != 0) {
                return;
            }



            if (i<=0){
                // 绘制第一个 也就是头部
                if (mOrient==VERTICAL_LINELAYOUT){
                    c.drawRect(left, 0, right, top, mPaint);
                    drawDividerVert(c, params, left, right, bottom);
                }else if (mOrient==HORIZONTAL_LINELAYOUT){
                    c.drawRect(0, 0, left, bottom, mPaint);
                    drawDividerHor(c, params, right, bottom);
                }

            }else if (i<(childCount-1)){
                if (mOrient==VERTICAL_LINELAYOUT){
                    drawDividerVert(c, params, left, right, bottom);
                }else if (mOrient==HORIZONTAL_LINELAYOUT){
                    drawDividerHor(c, params, right, bottom);
                }
            }else{
                if (mOrient==VERTICAL_LINELAYOUT){
                    c.drawRect(left, bottom, right, bottom + bottomH, mPaint);
                }else if (mOrient==HORIZONTAL_LINELAYOUT){
                    c.drawRect(right, 0, bottomH, bottom, mPaint);
                }
            }
        }

    }

    /**
     * 绘制横着方向的分割线0
     *
     * @param c
     * @param params
     * @param right
     * @param vH
     */
    private void drawDividerHor(Canvas c, RecyclerView.LayoutParams params, int right, int vH) {
        mDivider.setBounds(right + params.rightMargin, 0,
                right + params.rightMargin + mDivider.getIntrinsicHeight(),
                vH + params.bottomMargin);
        mDivider.draw(c);
    }

    /**
     * 绘制 竖直方向的分割线
     *
     * @param c
     * @param params
     * @param left
     * @param right
     * @param bottom
     */
    private void drawDividerVert(Canvas c, RecyclerView.LayoutParams params, int left, int right, int bottom) {
        mDivider.setBounds(left + params.leftMargin, bottom + params.bottomMargin,
                right, bottom + params.bottomMargin + mDivider.getIntrinsicHeight());
        mDivider.draw(c);
    }
}
