package mexercise.com.recycleviewseries.adapter;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mexercise.com.recycleviewseries.R;
import mexercise.com.recycleviewseries.base.BaseRVAdapter;
import mexercise.com.recycleviewseries.base.CommonViewHolder;
import mexercise.com.recycleviewseries.utils.PX2DPUtil;

/**
 * 简单使用的适配器
 *
 *
 * @author lcl
 * @time 2017/10/23 10:54
 * @version
 */

public class SimpleUseAdapter extends BaseRVAdapter {

    /**
     * 是否是瀑布流
     */
    private boolean isMasonry;


    public SimpleUseAdapter(Context context, List<String> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    protected void fillDatas(CommonViewHolder holder, int position) {
        if (isMasonry){
            TextView idTvDesc = holder.getView(R.id.id_tv_desc);
            LinearLayout.LayoutParams tvParams = (LinearLayout.LayoutParams) idTvDesc.getLayoutParams();
            int hTmp = PX2DPUtil.dip2px(mContext, 50);
            tvParams.height = hTmp + (int) (Math.random() * hTmp);
            idTvDesc.setLayoutParams(tvParams);
        }
        holder.setText(R.id.id_tv_desc, ((List<String>)mDatas).get(position));
    }

    public void setMasonry(boolean masonry) {
        isMasonry = masonry;
    }
}
