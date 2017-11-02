package mexercise.com.recycleviewseries.listener;

/**
 * decoration的头部分组的回调函数
 *
 *   这个接口主要是 给ItemDecoration 回传每组的标题
 *                                  每个item对应的decoration对应的分组索引
 *
 *
 * @author lcl
 * @time 2017/11/2 16:44
 * @version
 */

public interface RVDecorationOnGroupCallBack {

    /**
     * 根据每个Item的position获取对应的分组索引 groupIndex
     *
     * @param itemPos
     * @return
     */
    int onGroupIndex(int itemPos);

    /**
     * 根据每个Item的position获取对应的分组名称 groupName
     *
     * @param itemPos
     * @return
     */
    String onGroupName(int itemPos);
}
