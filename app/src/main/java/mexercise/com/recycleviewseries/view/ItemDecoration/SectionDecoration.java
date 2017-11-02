package mexercise.com.recycleviewseries.view.ItemDecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import mexercise.com.recycleviewseries.listener.RVDecorationOnGroupCallBack;
import mexercise.com.recycleviewseries.utils.PX2DPUtil;

/**
 * 粘性头部的前身 只是带着每个种类的条目
 *
 *  思路： 这里就是每组种类的第一个条目上有一个标题区分
 *
 *
 * @author lcl
 * @time 2017/11/2 14:38
 * @version
 */

public class SectionDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "SectionDecoration";

    /**
     *  文本的画笔
     */
    private TextPaint mTextPaint;
    /**
     *  标题栏的画笔
     */
    private Paint mPaint;

    private Drawable mDivider;

    //这里为了简单 都不设置那么多的属性

    private int sectionH;

    private RVDecorationOnGroupCallBack mRvDecorationOnGroupCallBack;


    public SectionDecoration(Context context, RVDecorationOnGroupCallBack decorationOnGroupCallBack) {

        this.sectionH = PX2DPUtil.dip2px(context, 50);
        this.mRvDecorationOnGroupCallBack = decorationOnGroupCallBack;

        final TypedArray a = context.obtainStyledAttributes(new int[]{
                android.R.attr.listDivider
        });
        mDivider = a.getDrawable(0);
        a.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);

        mTextPaint = new TextPaint();
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(60);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        if (itemCount<=0) return;
        int itemPos = parent.getChildAdapterPosition(view);
        int drawableH = mDivider.getIntrinsicHeight();
        if (mRvDecorationOnGroupCallBack != null) {
            if (itemPos==0){
                //第一个默认就是有分组标题
                outRect.set(0, sectionH, 0, drawableH);
            }else{
                int preGroupIndex = mRvDecorationOnGroupCallBack.onGroupIndex(itemPos-1);
                int groupIndex = mRvDecorationOnGroupCallBack.onGroupIndex(itemPos);
                if (groupIndex != preGroupIndex){
                    //说明是下一组的分组第一个了
                    outRect.set(0, sectionH, 0, drawableH);
                }else{
                    //说明是同一分组的了
                    outRect.set(0, 0, 0, drawableH);
                }
            }

        }else{
            //正常的分割线
            outRect.set(0, 0, 0, drawableH);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View v = parent.getChildAt(i);
            int left = v.getLeft();
            int top = v.getTop();
            int right = v.getRight();
            int bottom = v.getBottom();

            if (mRvDecorationOnGroupCallBack != null) {
                String groupName = mRvDecorationOnGroupCallBack.onGroupName(i);
                if (i==0){
                    //绘制第一个分组标题
                    drawGroupName(c, left, top, right, bottom, groupName);
                    drawDivider(c, left, right, bottom);
                }else{
                    int preGroupIndex = mRvDecorationOnGroupCallBack.onGroupIndex(i-1);
                    int groupIndex = mRvDecorationOnGroupCallBack.onGroupIndex(i);
                    Log.i(TAG, i + "===onDraw: preGroupIndex===" + preGroupIndex + "==groupIndex==" + groupIndex);
                    if (groupIndex != preGroupIndex){
                        //说明是下一组的分组第一个了
                        drawGroupName(c, left, top, right, bottom, groupName);
                    }
                    drawDivider(c, left, right, bottom);
                }

            }else{
                //正常的分割线
                drawDivider(c, left, right, bottom);
            }
        }
    }

    /**
     * 绘制每组的名称和背景
     *
     * @param c
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param groupName
     */
    private void drawGroupName(Canvas c, int left, int top, int right, int bottom, String groupName) {
        c.drawRect(left, 0, right, top, mPaint);
        c.drawText(groupName, left + 30, top/2, mTextPaint);
    }

    /**
     * 绘制正常的分割线
     *
     * @param c
     * @param left
     * @param right
     * @param bottom
     */
    private void drawDivider(Canvas c, int left, int right, int bottom) {
        mDivider.setBounds(left, bottom, right, bottom + mDivider.getIntrinsicHeight());
        mDivider.draw(c);
    }
}
