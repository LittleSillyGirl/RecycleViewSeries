package mexercise.com.recycleviewseries.utils;

import android.content.Context;
import android.content.res.Resources;

import java.util.List;

import mexercise.com.recycleviewseries.bean.MainItemBean;

/**
 * 获取数组的工具类 或者辅助类
 *
 *
 * @author lcl
 * @time 2017/10/20 10:15
 * @version
 */

public class ArraysHelper {

    private static final String TAG = "ArraysHelper";

    /**
     * 通过数组的配置 获取列表展示的内容
     *
     * @param ctx
     * @return
     */
    public static boolean getArrayContent(Context ctx, int aryId01, int aryId02, List<MainItemBean> datas) {
        Resources res = ctx.getResources();
        String[] names = res.getStringArray(aryId01);
        String[] claPaths = res.getStringArray(aryId02);
        String packageName = ctx.getPackageName();
        int nameLen = names.length;
        if (nameLen != claPaths.length) return true;
        for (int i = 0; i < nameLen; i++) {
            MainItemBean mainItemBean = new MainItemBean();
            mainItemBean.setName(names[i]);
            mainItemBean.setToClaPath(packageName + "." + claPaths[i]);
            datas.add(mainItemBean);
        }
        return false;
    }
}
