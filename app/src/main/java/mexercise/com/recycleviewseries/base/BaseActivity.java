package mexercise.com.recycleviewseries.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * activity的基类
 *
 * @author lcl
 * @time 2017/8/29 17:35
 * @version
 */

public abstract class BaseActivity extends FragmentActivity{

    protected BaseActivity mActivity;
    protected Context mContext;

    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = mActivity;
        Log.i(TAG, "onCreate: ==" + TAG);

    }

}
