package mexercise.com.recycleviewseries.bean;

/**
 * main对应的activity中的每个条目的bean对象
 *
 *
 * @author lcl
 * @time 2017/10/19 10:06
 * @version
 */

public class MainItemBean {

    /**
     * 要跳转的类名全路径
     */
    private String toClaPath;

    /**
     * item的名称
     */
    private String name;

    public String getToClaPath() {
        return toClaPath;
    }

    public void setToClaPath(String toClaPath) {
        this.toClaPath = toClaPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MainItemBean{" +
                "toClaPath='" + toClaPath + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
