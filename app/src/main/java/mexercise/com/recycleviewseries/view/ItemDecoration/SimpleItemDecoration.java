package mexercise.com.recycleviewseries.view.ItemDecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 就是一般的常见的decoration（装饰）
 *
 * 
 * @author lcl
 * @time 2017/10/24 16:03
 * @version 
 */ 

public class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "SimpleItemDecoration";

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
