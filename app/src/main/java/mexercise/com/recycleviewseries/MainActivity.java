package mexercise.com.recycleviewseries;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mexercise.com.recycleviewseries.adapter.MainAdapter;
import mexercise.com.recycleviewseries.base.BaseActivity;
import mexercise.com.recycleviewseries.bean.MainItemBean;
import mexercise.com.recycleviewseries.utils.ArraysHelper;

/**
 * RecycleView的简单使用
 *
 * 
 * @author lcl
 * @time 2017/10/19 9:57
 * @version 
 */ 
public class MainActivity extends BaseActivity {

    @BindView(R.id.id_rv_main)
    RecyclerView idRvMain;

    private List<MainItemBean> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.setDebug(true);
        ButterKnife.bind(mActivity);

        mDatas = new ArrayList<>();
        //获取列表展示的数据
        if (ArraysHelper.getArrayContent(mContext, R.array.text_name_main, R.array.text_cla_path_main, mDatas)) return;

        /**
         * 简单使用步骤
         *
         *   //首先设置RecyclerView的布局管理模式
         *    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         *    mAdapter = new MyAdapter(getData());
         *
         *    //设置Item项的UI装饰器
         *    mRecyclerView.addItemDecoration(new RecyclerItemDecoration(LinearLayoutManager.VERTICAL));
         *
         *    //设置Item项的不同操作的动画
         *    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
         *
         *    //设置数据开始装配
         *    mRecyclerView.setAdapter(mAdapter);
         *
         */

        idRvMain.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        //item项的间隔和动画 默认
        MainAdapter mainAdapter = new MainAdapter(mDatas, mContext);
        idRvMain.setAdapter(mainAdapter);
    }

}
