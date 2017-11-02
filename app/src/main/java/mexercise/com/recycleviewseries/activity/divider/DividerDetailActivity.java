package mexercise.com.recycleviewseries.activity.divider;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mexercise.com.recycleviewseries.R;
import mexercise.com.recycleviewseries.adapter.SimpleHomeAdapter;
import mexercise.com.recycleviewseries.adapter.SimpleUseAdapter;
import mexercise.com.recycleviewseries.base.BaseActivity;
import mexercise.com.recycleviewseries.bean.DecorationSectionBean;
import mexercise.com.recycleviewseries.bean.MainItemBean;
import mexercise.com.recycleviewseries.listener.RVDecorationOnGroupCallBack;
import mexercise.com.recycleviewseries.listener.RVOnItemClickListener;
import mexercise.com.recycleviewseries.utils.ArraysHelper;
import mexercise.com.recycleviewseries.utils.PX2DPUtil;
import mexercise.com.recycleviewseries.view.ItemDecoration.GridDividerItemDecoration;
import mexercise.com.recycleviewseries.view.ItemDecoration.HeadBottomItemDecoration;
import mexercise.com.recycleviewseries.view.ItemDecoration.SectionDecoration;


/**
 * ItemDecoration的详细介绍和各种种类的演示
 *
 * @author lcl
 * @time 2017/10/30 11:24
 */
public class DividerDetailActivity extends BaseActivity {


    @BindView(R.id.id_rv_type_divier)
    RecyclerView idRvTypeDivier;

    @BindView(R.id.id_rv_demo_divier)
    RecyclerView idRvDemoDivier;

    private List<MainItemBean> mDatas;
    private List<String> mDescs;

    private SimpleHomeAdapter mSimpleHomeAdapter;
    private SimpleUseAdapter mSimpleUseAdapter;

    private LayoutManager mLayoutManager;

    private int spanCount;

    private SparseArray<DecorationSectionBean> mGroups;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divider_detail);
        ButterKnife.bind(this);


        mDatas = new ArrayList<>();
        mGroups = new SparseArray<>();
        //获取列表展示的数据
        if (ArraysHelper.getArrayContent(mContext, R.array.text_name_itemdecoration, R.array.text_cla_path_itemdecoration, mDatas)) return;

        //公共适配器的抽取
        mSimpleHomeAdapter = new SimpleHomeAdapter(mContext, mDatas, R.layout.item_main);
        idRvTypeDivier.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        idRvTypeDivier.setAdapter(mSimpleHomeAdapter);

        mDescs = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDescs.add("" + (char) i);
        }
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mSimpleUseAdapter = new SimpleUseAdapter(mContext, mDescs, R.layout.item_simple_use);
        idRvDemoDivier.setLayoutManager(mLayoutManager);
        idRvDemoDivier.setAdapter(mSimpleUseAdapter);

        mSimpleHomeAdapter.setRvOnItemClickListener(new RVOnItemClickListener() {
            @Override
            public <T> void onItemClick(T... params) {
                // TODO: 2017/10/30   每个item的点击事件
                int pos = (Integer) params[1];
                int orient = -1;
                if (pos==0){
                    //粘性头部的前身
                    List<String> datas = new ArrayList<>();
                    int j = 1;
                    int groupIndex = -1;
                    for (int i = 0; i < 26; i++) {
                        datas.add(i+"test");
                        DecorationSectionBean decorationSectionBean = new DecorationSectionBean();
                        if (i==0){
                            groupIndex = 0 ;
                        }else if (i/5==j && i>0){
                            Log.i(TAG, "每组的第一个索引: groupIndex===" + i);
                            groupIndex = i ;
                            j++;
                        }
                        Log.i(TAG, "每个对应的索引位置 i===" + i + "===groupIndex==" + groupIndex + "====" + j);
                        decorationSectionBean.setGroupIndex(groupIndex);
                        decorationSectionBean.setGroupName(String.valueOf(j));
                        mGroups.put(i, decorationSectionBean);
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    SectionDecoration sectionDecoration = new SectionDecoration(mContext, new RVDecorationOnGroupCallBack() {
                        @Override
                        public int onGroupIndex(int itemPos) {
                            return mGroups.get(itemPos).getGroupIndex();
                        }

                        @Override
                        public String onGroupName(int itemPos) {
                            return mGroups.get(itemPos).getGroupName();
                        }
                    });
                    idRvDemoDivier.addItemDecoration(sectionDecoration);
                    idRvDemoDivier.setLayoutManager(linearLayoutManager);
                    mSimpleUseAdapter = new SimpleUseAdapter(mContext, datas, R.layout.item_simple_use);
                    idRvDemoDivier.setAdapter(mSimpleUseAdapter);

                }else if (pos==1 || pos==2 || pos==3){
                    spanCount = 3 ;
                    GridDividerItemDecoration gdItemDecor = null;
                    switch (pos){
                        case 0:
                            gdItemDecor = new GridDividerItemDecoration(mContext, spanCount, GridDividerItemDecoration.VERTICAL_GRID, 0);
                            orient = GridLayoutManager.VERTICAL;
                            break;
                        case 1:
                            gdItemDecor = new GridDividerItemDecoration(mContext, spanCount, GridDividerItemDecoration.HORIZONTAL_GRID, 0);
                            orient = GridLayoutManager.HORIZONTAL;
                            break;
                        case 2:
                            gdItemDecor = new GridDividerItemDecoration(mContext, spanCount, GridDividerItemDecoration.VERTICAL_GRID, 1);
                            orient = GridLayoutManager.VERTICAL;
                            break;
                        case 3:
                            gdItemDecor = new GridDividerItemDecoration(mContext, spanCount, GridDividerItemDecoration.HORIZONTAL_GRID, 1);
                            orient = GridLayoutManager.HORIZONTAL;
                            break;
                    }
                    mLayoutManager = new GridLayoutManager(mContext, spanCount, orient, false);
                    idRvDemoDivier.addItemDecoration(gdItemDecor);
                    idRvDemoDivier.setLayoutManager(mLayoutManager);
                    idRvDemoDivier.setAdapter(mSimpleUseAdapter);
                }else if (pos==4 || pos==5){
                    switch (pos){
                        case 4:
                            orient = LinearLayoutManager.VERTICAL;
                            break;
                        case 5:
                            orient = LinearLayoutManager.HORIZONTAL;
                            break;
                    }
                    mLayoutManager = new LinearLayoutManager(mContext, orient, false);
                    int hTmp = PX2DPUtil.dip2px(mContext, 50);
                    idRvDemoDivier.addItemDecoration(
                            new HeadBottomItemDecoration(mContext, orient, Color.RED, hTmp, hTmp));
                    idRvDemoDivier.setLayoutManager(mLayoutManager);
                    idRvDemoDivier.setAdapter(mSimpleUseAdapter);
                }

            }
        });

    }
}
