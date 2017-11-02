package mexercise.com.recycleviewseries.listener;

/**
 * 每个继承baseActivity的activity要实现这个接口  为了更方便的跳转自己
 *
 *  说白了就是 每次我们跳转到其他的页面 都是在自己的页面中写跳转逻辑，但是我们不知道我们的跳转参数
 *  如果是自己开发的话 可以； 不是自己开发的就要去询问自己的同事，所以我们约定每个activity中预留一个跳转的方法
 *
 *
 *
 * @author lcl
 * @time 2017/10/20 15:53
 * @version
 */

public interface IToSelfActivity {

    /**
     * 跳转自己页面的方法
     *
     * @param params
     * @param <T>
     */
    <T> void toSelfAty(T... params);
}
