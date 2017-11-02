package mexercise.com.recycleviewseries.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.List;

import mexercise.com.recycleviewseries.R;
import mexercise.com.recycleviewseries.base.BaseRVAdapter;
import mexercise.com.recycleviewseries.base.CommonViewHolder;
import mexercise.com.recycleviewseries.bean.MainItemBean;

/**
 * 简单用法的适配器
 *
 *  通过这个适配器抽取公共的适配器和viewholder
 *
 *  1、就是发现我们重复代码  就是生成onCreateViewHolder， 所以要抽取这个里面的代码
 *  2、在抽取的过程中我们发现我们需要一个CommonViewHolder 也就是需要一个公共的viewholder 我们只用给view就行了
 *  3、CommonViewHolder抽取后，我们就抽取onCreateViewHolder，因为是要抽取 所以新建一个基类
 *  4、优化抽取的代码 最好泛型、更广泛
 *
 *
 * @author lcl
 * @time 2017/10/20 10:25
 * @version
 */

public class SimpleHomeAdapter extends BaseRVAdapter {


    public SimpleHomeAdapter(Context context, List<MainItemBean> datas, int itemViewResId) {
        super(context, datas, itemViewResId);
    }

    @Override
    protected void fillDatas(CommonViewHolder holder, final int position) {
        Log.i(TAG, "fillDatas: ");
        final MainItemBean mainItemBean = ((List<MainItemBean>)mDatas).get(position);
        holder.setText(R.id.id_btn_item, mainItemBean.getName());
        holder.setOnClickListener(R.id.id_btn_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRvOnItemClickListener != null) {
                    mRvOnItemClickListener.onItemClick(mainItemBean.getToClaPath(), position);
                }
            }
        });
    }
}
