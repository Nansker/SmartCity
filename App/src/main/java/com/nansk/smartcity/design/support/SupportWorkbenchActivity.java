package com.nansk.smartcity.design.support;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 16:23
 * @description 工作台
 */

public class SupportWorkbenchActivity extends BaseActivity {

    private LinearLayout menuContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_workbench);
        setToolBarTitle("工作台");
        initView();
    }

    private void initView() {

        menuContainer = (LinearLayout) findViewById(R.id.menu_container);

        for (int i = 0; i < menuContainer.getChildCount(); i++) {
            final int finalI = i;
            menuContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (finalI){
                        case 0:
                            intent = new Intent(SupportWorkbenchActivity.this,SupportHelpActivity.class);
                            intent.putExtra("dataType","seekHelp");
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(SupportWorkbenchActivity.this,SupportHelpActivity.class);
                            intent.putExtra("dataType","help");
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(SupportWorkbenchActivity.this,SupportVisitActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(SupportWorkbenchActivity.this,SupportCasePublishedActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }

    }
}