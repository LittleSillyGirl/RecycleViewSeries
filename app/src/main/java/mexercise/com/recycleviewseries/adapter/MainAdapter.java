package mexercise.com.recycleviewseries.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import mexercise.com.recycleviewseries.R;
import mexercise.com.recycleviewseries.bean.MainItemBean;
import mexercise.com.recycleviewseries.utils.UIHelper;

/**
 * main中的适配器
 *
 *
 * @author lcl
 * @time 2017/10/19 10:04
 * @version
 */

public class MainAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private static final String TAG = "MainAdapter";

    private List<MainItemBean> mDatas;
    private Context mContext;

    public MainAdapter(List<MainItemBean> datas, Context mContext) {
        this.mDatas = datas;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemContent = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new MyViewHolder(itemContent);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final MainItemBean mainItemBean = mDatas.get(position);
        Log.i(TAG, "onBindViewHolder: ===" + mainItemBean.getName());
        holder.idBtnItem.setText(mainItemBean.getName());
        holder.idBtnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i(TAG, "onClick: 点击事件");
                    UIHelper.showActivity(mContext, (Class<? extends Activity>) Class.forName(mainItemBean.getToClaPath()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}

class MyViewHolder extends RecyclerView.ViewHolder{
    public Button idBtnItem;
    public MyViewHolder(View view) {
        super(view);
        idBtnItem = (Button) view.findViewById(R.id.id_btn_item);
    }
}
