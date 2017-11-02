package mexercise.com.recycleviewseries.activity.simple;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mexercise.com.recycleviewseries.R;
import mexercise.com.recycleviewseries.adapter.SimpleUseAdapter;
import mexercise.com.recycleviewseries.base.BaseActivity;
import mexercise.com.recycleviewseries.view.ItemDecoration.DividerItemDecoration;


/**
 * RecycleView的简单用法  但是为了增加功能， 这里要先用不同布局
 *
 * @author lcl
 * @time 2017/10/20 16:39
 */
public class SimpleRVUseActivity extends BaseActivity {

    /**
     * RecycleView的各种简单用法的切换点击事件
     * <p>
     * 为了练习 我们用不同的布局来实现
     * <p>
     * <p>
     * <item>竖着线性</item>
     * <item>横着线性</item>
     * <item>竖着网状</item>
     * <item>横着网状</item>
     * <item>瀑布流</item>
     * 前面这五个是单独的不能共存的， 说白了就是用 单选按钮
     * <p>
     * <item>分割线</item>
     * <item>添加</item>长按某个就是删除
     * 就是一般的textview
     * <p>
     * <item>带动画</item>是否带动画
     * 这三个是可以互相叠加且可以和上面叠加  说白了就是用chebox
     */


    @BindView(R.id.id_rv_content_sim_use)
    RecyclerView idRvContentSimUse;

    @BindView(R.id.id_rb_line)
    RadioButton idRbLine;
    @BindView(R.id.id_rb_grid)
    RadioButton idRbGrid;
    @BindView(R.id.id_rb_masonry)
    RadioButton idRbMasonry;
    @BindView(R.id.id_rg_type)
    RadioGroup idRgType;

    @BindView(R.id.id_rb_v)
    RadioButton idRbV;
    @BindView(R.id.id_rb_h)
    RadioButton idRbH;
    @BindView(R.id.id_rg_orientation)
    RadioGroup idRgOrientation;

    @BindView(R.id.id_cb_decoration)
    CheckBox idCbDecoration;
    @BindView(R.id.id_cb_anim)
    CheckBox idCbAnim;

    private List<String> mDatas;

    private LayoutManager mLayoutManager;
    private SimpleUseAdapter mSimpleUseAdapter;
    private DividerItemDecoration mDividerItemDecoration;

    private int orient = 1;
    private int type = 0;
    private boolean isMasonry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rv_use);
        ButterKnife.bind(this);
        Resources res = mContext.getResources();
//        String[] types = res.getStringArray(R.array.text_name_sim_use);
//        mTypes = Arrays.asList(types);
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
        for (int i = 'a'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        //默认是竖着线性
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mDividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);
        mSimpleUseAdapter = new SimpleUseAdapter(mContext, mDatas, R.layout.item_simple_use);

        idRvContentSimUse.setLayoutManager(mLayoutManager);
        idRvContentSimUse.setAdapter(mSimpleUseAdapter);

        idRgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.id_rb_line:
                        //线性
                        type = 0;
                        isMasonry = false;
                        break;
                    case R.id.id_rb_grid:
                        //网状
                        type = 1;
                        isMasonry = false;
                        break;
                    case R.id.id_rb_masonry:
                        //瀑布流
                        type = 2;
                        isMasonry = true;
                        break;
                }
                //改变布局
                updateLayout();
            }
        });

        idRgOrientation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.id_rb_v:
                        //竖着
                        orient = 1;
                        mDividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);
                        break;
                    case R.id.id_rb_h:
                        //横着
                        orient = 0;
                        mDividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL_LIST);
                        break;
                }
                //改变布局
                updateLayout();
            }
        });

        idCbDecoration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateLayout();
            }
        });

    }

    /**
     * 改变布局
     */
    private void updateLayout() {
        switch (type) {
            case 0:
                //线性
                mLayoutManager = new LinearLayoutManager(mContext, orient, false);
                break;
            case 1:
                //网状
                mLayoutManager = new GridLayoutManager(mContext, 3, orient, false);
                break;
            case 2:
                //瀑布流
                mLayoutManager = new StaggeredGridLayoutManager(3, orient);
                break;
        }
        idRvContentSimUse.setLayoutManager(mLayoutManager);
        Log.i(TAG, type + " ===type====updateLayout: isMasonry===" + isMasonry);
        mSimpleUseAdapter.setMasonry(isMasonry);
//        mSimpleUseAdapter.notifyDataSetChanged();
        if (idCbDecoration.isChecked()){
            //官网的itemdecoration
            Log.i(TAG, "updateLayout: 添加条目");
            idRvContentSimUse.addItemDecoration(mDividerItemDecoration);
        }else{
            Log.i(TAG, "updateLayout: 删除条目");
            idRvContentSimUse.removeItemDecoration(mDividerItemDecoration);
        }
        idRvContentSimUse.setAdapter(mSimpleUseAdapter);
    }



}
