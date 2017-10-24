package ustc.var.com.myapplication001.util;

import android.content.Context;

/**
 * Created by GRY on 2017/10/17.
 *
 */

public class ToolUtils {
    public static final int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }
}
