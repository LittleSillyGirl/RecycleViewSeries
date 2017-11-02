package mexercise.com.recycleviewseries.listener;

/**
 * RecycleView的item点击事件
 *
 *
 * @author lcl
 * @time 2017/10/20 15:25
 * @version
 */

public interface RVOnItemClickListener {

    /** item的点击事件, 但是不知道回调什么参数  所以用这个表示 */
    <T>void onItemClick(T... params);
}
