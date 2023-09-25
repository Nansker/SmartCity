package com.nansk.smartcity.design.community;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 17:36
 * @description 物业服务
 */

public class CommunityPropertyActivity extends BaseActivity {

    private GridLayout tabContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_community_property);
        setToolBarTitle("物业服务");
        initView();
    }

    private void initView() {
        tabContainer = (GridLayout) findViewById(R.id.tab_container);
        String[] colors = new String[]{"#5badf6", "#f19f77", "#b07eef", "#7dd193", "#5fc3e7","#5593e0"};
        final String[] tels = new String[]{"010-6525472","010-65252000","010-64192079","010-63984662","010-63073111"};
        for (int i =0;i<tabContainer.getChildCount();i++){
            tabContainer.getChildAt(i).setBackground(getDrawable(colors[i],30));
            final int finalI = i;
            tabContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI !=5){
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tels[finalI]));
                        startActivity(intent);
                    }else {
                        startActivity(new Intent(CommunityPropertyActivity.this,CommunityFeedbackActivity.class));
                    }
                }
            });
        }
    }
}