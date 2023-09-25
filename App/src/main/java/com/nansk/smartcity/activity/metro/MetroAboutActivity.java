package com.nansk.smartcity.activity.metro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.utils.WindowMangerUtils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 17:28
 * @description 关于
 */


public class MetroAboutActivity extends BaseActivity {

    private ImageView logoIv;
    private TextView visionTv;
    private LinearLayout tabMenu;
    private LinearLayout logoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro_about);
        setToolBarTitle("关于我们");

        initView();
    }

    private void initView() {
        logoIv = (ImageView) findViewById(R.id.logo_iv);
        visionTv = (TextView) findViewById(R.id.vision_tv);
        tabMenu = (LinearLayout) findViewById(R.id.tab_menu);
        logoBox = (LinearLayout) findViewById(R.id.logo_box);

        int windowSize = WindowMangerUtils.getWindowSize(MetroAboutActivity.this, 1);
        ViewGroup.LayoutParams layoutParams = logoBox.getLayoutParams();
        layoutParams.height = windowSize / 2;
        logoBox.setLayoutParams(layoutParams);

        //纵向tab
        for (int i = 0; i < tabMenu.getChildCount(); i++) {
            final int finalI = i;
            tabMenu.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    Bundle bundle;
                    switch (finalI) {
                        case 0:

                            break;
                        case 1:
                            break;
                        case 2:
                            intent = new Intent(MetroAboutActivity.this, MetroNoticeActivity.class);
                            bundle = new Bundle();
                            bundle.putInt("dataType", 5);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}