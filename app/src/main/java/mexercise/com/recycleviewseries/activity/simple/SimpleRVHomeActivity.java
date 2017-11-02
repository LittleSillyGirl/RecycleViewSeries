package mexercise.com.recycleviewseries.activity.simple;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mexercise.com.recycleviewseries.R;
import mexercise.com.recycleviewseries.adapter.SimpleHomeAdapter;
import mexercise.com.recycleviewseries.base.BaseActivity;
import mexercise.com.recycleviewseries.bean.MainItemBean;
import mexercise.com.recycleviewseries.listener.RVOnItemClickListener;
import mexercise.com.recycleviewseries.utils.ArraysHelper;
import mexercise.com.recycleviewseries.utils.UIHelper;

/**
 * RecycleView的简单使用方法
 *
 * 1、比如最根本的 公共适配器的抽取
 *
 * @author lcl
 * @time 2017/10/20 8:48
 */
public class SimpleRVHomeActivity extends BaseActivity {

    @BindView(R.id.id_rv_simple)
    RecyclerView idRvSimple;

    private List<MainItemBean> mDatas;

    private SimpleHomeAdapter mSimpleHomeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rv_home);
        ButterKnife.bind(this);

        mDatas = new ArrayList<>();
        //获取列表展示的数据
        if (ArraysHelper.getArrayContent(mContext, R.array.text_name_simple, R.array.text_cla_path_simple, mDatas)) return;

        //公共适配器的抽取
        mSimpleHomeAdapter = new SimpleHomeAdapter(mContext, mDatas, R.layout.item_main);
        idRvSimple.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        idRvSimple.setAdapter(mSimpleHomeAdapter);

        mSimpleHomeAdapter.setRvOnItemClickListener(new RVOnItemClickListener() {
            @Override
            public <T> void onItemClick(T... params) {
                //每个item的点击事件
                try {
                    UIHelper.showActivity(mContext, (Class<? extends Activity>) Class.forName((String) params[0]));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
