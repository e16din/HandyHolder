package com.e16din.handyholder;

import android.util.TypedValue;

public final class Utils {
    public static int dpToPx(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                HandyHolder.getContext().getResources().getDisplayMetrics());
    }
}
