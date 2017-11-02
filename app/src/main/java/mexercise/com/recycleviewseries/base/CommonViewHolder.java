package mexercise.com.recycleviewseries.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * RecycleView的公共ViewHolder
 *      既然是公共的我们就不能特定
 *  1、ViewHolder是什么，说白了就是view的容器也就是view的集合，只不过这个集合比较特别罢了
 *  我们要根据resId来获取对应的view  就要用键值对 也就是map 但是因为key值是int类型 所以可以用 SparseArray这个特殊的map
 *
 *  2、然后可以封装一些常用的方法
 *      比如：根据resId获取view、setText、点击事件（长按事件）等
 *
 *  3、为了ViewHolder的对象减少 我们单列它
 *
 *
 *
 * @author lcl
 * @time 2017/10/20 10:31
 * @version
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "CommonViewHolder";

    /** view的容器 */
    private SparseArray<View> mViews;

    private CommonViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray();
    }

    public static CommonViewHolder getInstanse(Context ctx, ViewGroup parent, boolean attachToRoot, int layoutId, int viewType){
        return new CommonViewHolder(LayoutInflater.from(ctx).inflate(layoutId, parent, attachToRoot));
    }

    /**
     * 根据资源id获取对应的view
     *
     *  泛型下
     *
     * @param resId
     * @return
     */
    public <T extends View>T getView(int resId){
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    /**
     * 设置text
     *
     * @param viewResId
     * @param text
     */
    public void setText(int viewResId, String text){
        View view = getView(viewResId);
        if (view instanceof TextView){
            ((TextView)view).setText(text);
        }else{
            Log.e(TAG, "当前的控件不属于TextView, 请检查控件的属性。");
        }
    }

    /**
     * 每个控件对应的点击事件
     *
     * @param viewResId
     * @param onClickListener
     */
    public void setOnClickListener(int viewResId, OnClickListener onClickListener){
        getView(viewResId).setOnClickListener(onClickListener);
    }
}
