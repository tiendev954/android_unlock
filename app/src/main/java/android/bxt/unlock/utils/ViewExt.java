package android.bxt.unlock.utils;

import android.app.Activity;

import androidx.core.view.WindowInsetsControllerCompat;

public class ViewExt {

    public static void setLightNavStatusBar(Activity activity, boolean isLight) {
        WindowInsetsControllerCompat windowInsert = new WindowInsetsControllerCompat(
                activity.getWindow(),
                activity.getWindow().getDecorView()
        );
        windowInsert.setAppearanceLightStatusBars(isLight);
        windowInsert.setAppearanceLightNavigationBars(isLight);
    }
}

