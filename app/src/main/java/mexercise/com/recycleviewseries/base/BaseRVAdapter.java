package mexercise.com.recycleviewseries.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

import mexercise.com.recycleviewseries.listener.RVOnClickListener;
import mexercise.com.recycleviewseries.listener.RVOnItemClickListener;

/**
 * 抽取的RecycleView的适配器基类
 *      1、首先定义需要的变量
 *          Context、集合(这里通常用的是List 只是为了更好的封装 我们用Collection)、布局id(因为是抽取所以肯定不能写死)
 *
 *      2、其次是点击事件  包括长按
 *
 *
 *
 * @author lcl
 * @time 2017/10/20 14:59
 * @version
 */

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    protected final String TAG = this.getClass().getSimpleName();

    protected Context mContext;

    /** 填充的数据 */
    protected Collection<T> mDatas;

    /** 每个item的布局id */
    protected int itemLayoutId;

    /** item的点击事件 */
    protected RVOnItemClickListener mRvOnItemClickListener;

    /**  view的点击事件 */
    protected RVOnClickListener mRvOnClickListener;

    public BaseRVAdapter(Context context, Collection<T> datas, int itemLayoutId) {
        this.mContext = context;
        this.mDatas = datas;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getInstanse(mContext, parent, false, itemLayoutId, viewType);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        /** 填充数据 留给子类实现 */
        fillDatas(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 每个子类实现自己的数据填充和逻辑实现
     *
     * @param holder
     * @param position
     */
    protected abstract void fillDatas(CommonViewHolder holder, int position);

    public void setRvOnItemClickListener(RVOnItemClickListener rvOnItemClickListener) {
        this.mRvOnItemClickListener = rvOnItemClickListener;
    }

    public void setRvOnClickListener(RVOnClickListener rvOnClickListener) {
        this.mRvOnClickListener = rvOnClickListener;
    }
}
