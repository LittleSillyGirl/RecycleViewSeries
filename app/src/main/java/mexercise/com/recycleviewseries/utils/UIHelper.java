package mexercise.com.recycleviewseries.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * activity也就是ui的辅助类
 *
 * @author lcl
 * @time 2017/10/20 16:05
 */

public class UIHelper {

    private static final String TAG = "UIHelper";

    private UIHelper() {
    }

    /**
     * 带参数activity跳转
     *
     * @param context
     * @param clazz
     * @param map
     */
    public static void showActivity(Context context, Class<? extends Activity> clazz, Map<String, Object> map) {
        if (clazz == null) {
            throw new NullPointerException("-->检查跳转的Activity");
        }
        Intent intent = getIntent(context, clazz, map);
        context.startActivity(intent);
    }

    /**
     * 无参数activity跳转
     *
     * @param context
     * @param clazz
     */
    public static void showActivity(Context context, Class<? extends Activity> clazz) {
        showActivity(context, clazz, null);
    }

    /**
     * 带参数activity跳转带返回结果的
     *
     * @param context
     * @param clazz
     * @param map
     * @param requestCode
     */
    public static void showActivityForResult(Context context,
                                             Class<? extends Activity> clazz, Map<String, Object> map, int requestCode) {
        if (clazz == null) {
            throw new NullPointerException("-->检查跳转的Activity");
        }
        Intent intent = getIntent(context, clazz, map);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 无参数activity跳转带返回结果的
     *
     * @param context
     * @param clazz
     * @param requestCode
     */
    public static void showActivityForResult(Context context,
                                             Class<? extends Activity> clazz, int requestCode) {
        showActivityForResult(context, clazz, requestCode);
    }

    /**
     * 获取intent
     *
     * @param clazz
     * @param map
     * @return
     */
    private static Intent getIntent(Context context, Class<? extends Activity> clazz, Map<String, Object> map) {
        Intent intent = new Intent(context, clazz);
        if (map == null) {
            return intent;
        }
        return setIntentExtras(map, intent);
    }

    /**
     * 给intent设置可传递的参数
     *
     * @param map
     * @param intent
     * @return
     */
    private static Intent setIntentExtras(Map<String, Object> map, Intent intent) {
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            Object value = entry.getValue();
            if (value instanceof String) {
                intent.putExtra(entry.getKey(), (String) value);
                continue;
            }
            if (value instanceof String[]) {
                intent.putExtra(entry.getKey(), (String[]) value);
                continue;
            }
            if (value instanceof Boolean) {
                intent.putExtra(entry.getKey(),
                        ((Boolean) value).booleanValue());
                continue;
            }
            if (value instanceof Boolean[]) {
                Boolean[] tvalues = (Boolean[]) value;
                boolean[] vals = new boolean[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].booleanValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof Bundle) {
                intent.putExtra(entry.getKey(), (Bundle) value);
                continue;
            }
            if (value instanceof Byte) {
                intent.putExtra(entry.getKey(), ((Byte) value).byteValue());
                continue;
            }
            if (value instanceof Byte[]) {
                Byte[] tvalues = (Byte[]) value;
                byte[] vals = new byte[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].byteValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof Character) {
                intent.putExtra(entry.getKey(), ((Character) value).charValue());
                continue;
            }
            if (value instanceof Character[]) {
                Character[] tvalues = (Character[]) value;
                char[] vals = new char[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].charValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof CharSequence) {
                intent.putExtra(entry.getKey(), (CharSequence) value);
                continue;
            }
            if (value instanceof CharSequence[]) {
                intent.putExtra(entry.getKey(), (CharSequence[]) value);
                continue;
            }
            if (value instanceof Double) {
                intent.putExtra(entry.getKey(), ((Double) value).doubleValue());
                continue;
            }
            if (value instanceof Double[]) {
                Double[] tvalues = (Double[]) value;
                double[] vals = new double[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].doubleValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof Float) {
                intent.putExtra(entry.getKey(), ((Float) value).floatValue());
                continue;
            }
            if (value instanceof Float[]) {
                Float[] tvalues = (Float[]) value;
                float[] vals = new float[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].floatValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof Integer) {
                intent.putExtra(entry.getKey(), ((Integer) value).intValue());
                continue;
            }
            if (value instanceof Integer[]) {
                Integer[] tvalues = (Integer[]) value;
                int[] vals = new int[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].intValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof Long) {
                intent.putExtra(entry.getKey(), ((Long) value).longValue());
                continue;
            }
            if (value instanceof Long[]) {
                Long[] tvalues = (Long[]) value;
                long[] vals = new long[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].longValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof Parcelable) {
                intent.putExtra(entry.getKey(), (Parcelable) value);
                continue;
            }
            if (value instanceof Parcelable[]) {
                intent.putExtra(entry.getKey(), (Parcelable[]) value);
                continue;
            }
            if (value instanceof Short) {
                intent.putExtra(entry.getKey(), ((Short) value).shortValue());
                continue;
            }
            if (value instanceof Short[]) {
                Short[] tvalues = (Short[]) value;
                short[] vals = new short[tvalues.length];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = tvalues[i].shortValue();
                }
                intent.putExtra(entry.getKey(), vals);
                continue;
            }
            if (value instanceof Serializable) {
                intent.putExtra(entry.getKey(), (Serializable) value);
                continue;
            }
        }
        return intent;
    }
}
