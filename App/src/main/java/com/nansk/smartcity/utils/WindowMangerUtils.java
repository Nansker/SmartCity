package com.nansk.smartcity.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/07 18:23
 * @Description 系统服务配置工具类
 */
public class WindowMangerUtils {

    /**
     * @Create 2021/9/7 18:25
     * @Role 设置系统状态栏样式, 内容延伸到系统状态栏
     * @Param style（0 深色字体，1 浅色字体）
     * 此外还需要到values中的系统样式中设置状态栏背景颜色为透明
     */
    public final static void setSysStatusBar(Activity activity, int style) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (style == 0) {
                activity.getWindow().getDecorView().setSystemUiVisibility
                        (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else if (style == 1) {
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }

    }

    /**
     * @Create 2021/9/7 18:25
     * @Role 获取屏幕宽高
     * @Param brim（0 宽width，1 高height）
     */
    public final static int getWindowSize(Context context, int brim) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (brim == 0) {
            return displayMetrics.widthPixels;
        } else if (brim == 1) {
            return displayMetrics.heightPixels;
        }
        return displayMetrics.heightPixels;
    }

    /**
     * @Create 2021/9/10 23:29
     * @Role 关闭系统软键盘
     * @Param
     */
    public final static void closeKeyboard(Context context,View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

}
