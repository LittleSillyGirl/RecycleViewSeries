package mexercise.com.recycleviewseries.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * px 、dp(dip) 、sp 之间的区别和转换
 * <p>
 * 首先来看一下他们的基本概念：
 * px   ：是屏幕的像素点
 * dp   ：一个基于density的抽象单位，如果一个160dpi的屏幕，1dp=1px
 * dip  ：等同于dp
 * sp   ：同dp相似，但还会根据用户的字体大小偏好来缩放(建议使用sp作为文本的单位，其它用dip)
 *
 * @author lcl
 * @time 2017/10/24 10:18
 */

public class PX2DPUtil {
    /**
     * dp与px换算公式：
     *    pixs =dips * (densityDpi/160). 
     *    dips=(pixs*160)/densityDpi
     *    但是我们在代码里面进行转化的时候还需要有一个偏移值：0.5f
     *
     */

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param context
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     *  同时系统也提供了TypedValue类帮助我们转换
     */

    /**
     * 将dp转换成px  但是是通过TypedValue类帮助的
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2pxTypedValue(Context context, float dpValue){
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, displayMetrics);
    }

    /**
     * 将px转换成dp  但是是通过TypedValue类帮助的
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2pxTypedValue(Context context,float spValue){
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, displayMetrics);
    }

}
