package com.nansk.smartcity.base;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/10/14 22:23
 * @Description
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.dialog.ToastDialog;


/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 19:34
 * @description
 */

public class BaseActivity extends AppCompatActivity {


    private RelativeLayout toolbar;
    private ImageButton backBar;
    private TextView leftTitleBar;
    private TextView titleBar;
    private TextView funBar;
    private FrameLayout contentView;
    private View dividerBar;

    private ToastDialog toastDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();

        toastDialog = new ToastDialog(this);

    }

    /**
     * @Create 2021/10/15 8:26
     * @Role
     * @Param
     */
    public void setCustomView(int layoutId) {
        View v = LayoutInflater.from(this).inflate(layoutId, null);
        contentView.addView(v);
    }

    public void setToolBarTitle(String title) {
        titleBar.setText(title);
    }

    public void setLeftTitleBar(String title) {
        leftTitleBar.setText(title);
    }

    /**
     * @Create 2021/10/15 8:33
     * @Role 设置标题栏颜色
     * @Param
     */
    public void setToolBarBackground(String backgroundColor) {
        toolbar.setBackgroundColor(Color.parseColor(backgroundColor));
        getWindow().setStatusBarColor(Color.parseColor(backgroundColor));
        dividerBar.setVisibility(View.GONE);
        //背景色不是白色，系统系统状态栏文字，标题栏返回键，文本颜色改为深色
        //一般情况下,系统状态栏颜色跟随标题栏
        if (backgroundColor.equals("#FFFFFF") || backgroundColor.equals("#ffffff")) {
            backBar.setColorFilter(Color.parseColor("#333333"));
            titleBar.setTextColor(Color.parseColor("#333333"));
            leftTitleBar.setTextColor(Color.parseColor("#333333"));
            funBar.setTextColor(Color.parseColor("#333333"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        } else {
            backBar.setColorFilter(Color.parseColor("#FFFFFF"));
            titleBar.setTextColor(Color.parseColor("#FFFFFF"));
            leftTitleBar.setTextColor(Color.parseColor("#FFFFFF"));
            funBar.setTextColor(Color.parseColor("#FFFFFF"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        }
    }
    public void setToolBarBackground(String backgroundColor,int style) {
        dividerBar.setVisibility(View.GONE);
        toolbar.setBackgroundColor(Color.parseColor(backgroundColor));
        getWindow().setStatusBarColor(Color.parseColor(backgroundColor));
        //背景色不是白色，系统系统状态栏文字，标题栏返回键，文本颜色改为深色
        //一般情况下,系统状态栏颜色跟随标题栏
        if (style == 0) {
            backBar.setColorFilter(Color.parseColor("#333333"));
            titleBar.setTextColor(Color.parseColor("#333333"));
            leftTitleBar.setTextColor(Color.parseColor("#333333"));
            funBar.setTextColor(Color.parseColor("#333333"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (style == 1){
            backBar.setColorFilter(Color.parseColor("#FFFFFF"));
            titleBar.setTextColor(Color.parseColor("#FFFFFF"));
            leftTitleBar.setTextColor(Color.parseColor("#FFFFFF"));
            funBar.setTextColor(Color.parseColor("#FFFFFF"));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
    /**
     * @Create 2021/10/15 10:01
     * @Role 系统状态栏下的分割线
     * @Param
     */
    public void setBarDivider(boolean isShow, String color) {
        if (isShow) {
            dividerBar.setVisibility(View.VISIBLE);
            dividerBar.setBackgroundColor(Color.parseColor(color));
        } else {
            dividerBar.setVisibility(View.GONE);
        }
    }

    public void setToolbarIsShow(boolean show) {
        if (show) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
            dividerBar.setVisibility(View.GONE);
        }
    }

    public void setBackBarIsShow(boolean show) {
        if (show) {
            backBar.setVisibility(View.VISIBLE);
        } else {
            backBar.setVisibility(View.GONE);
        }
    }

    public void setToolBarRightButton(boolean show, String text, final MyCallBack callBack) {
        if (show) {
            funBar.setText(text);
            funBar.setVisibility(View.VISIBLE);
            funBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.CallBack();
                }
            });
        } else {
            funBar.setVisibility(View.GONE);
        }
    }
    @SuppressLint("ResourceType")
    public void setToolBarRightButton(boolean show, Drawable drawable, String textColor, String text, final MyCallBack callBack) {
        funBar.setBackground(drawable);
        funBar.setTextColor(Color.parseColor(textColor));
        if (show) {
            funBar.setText(text);
            funBar.setVisibility(View.VISIBLE);
            funBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.CallBack();
                }
            });
        } else {
            funBar.setVisibility(View.GONE);
        }
    }

    public void showToast(String msg) {
        toastDialog.showReveal(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toastDialog.dismiss();
            }
        }, 1200);
    }
    public void showToast(String msg, long delayMills) {
        toastDialog.showReveal(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toastDialog.dismiss();
            }
        }, delayMills);
    }

    /**
     * @Create 2021/10/17 8:04
     * @Role 获取Drawable对象
     * @Param
     */
    public Drawable getDrawable(String background, float radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(background));
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    public Drawable getDrawable(String background, float radius, int lineWidth, String lineColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(background));
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setStroke(lineWidth, Color.parseColor(lineColor));
        return gradientDrawable;
    }

    /**
     * @Create 2021/10/20 13:19
     * @Role 关闭键盘
     * @Param
     */
    public void closeKeyboard(View view){
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    private void initView() {
        toolbar = (RelativeLayout) findViewById(R.id.toolbar);
        backBar = (ImageButton) findViewById(R.id.back_bar);
        leftTitleBar = (TextView) findViewById(R.id.leftTitle_bar);
        titleBar = (TextView) findViewById(R.id.title_bar);
        funBar = (TextView) findViewById(R.id.fun_bar);
        contentView = (FrameLayout) findViewById(R.id.content_view);
        dividerBar = (View) findViewById(R.id.divider_bar);

        backBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //默认全屏显示
        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
