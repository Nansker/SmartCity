package com.nansk.smartcity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.utils.MyApplication;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/26 19:23
 * @Description 网络设置页面
 */

public class NetworkSettingsActivity extends BaseActivity {

    private EditText ipEt;
    private Button saveBtn;
    private TextView publicTv;
    private TextView schoolTv;
    private TextView currentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_network_settings);
        setToolBarTitle("网络设置");
        initView();
    }

    private void initView() {
        ipEt = (EditText) findViewById(R.id.ip_et);
        saveBtn = (Button) findViewById(R.id.save_btn);
        currentTv = (TextView) findViewById(R.id.current_tv);

        publicTv = (TextView) findViewById(R.id.public_tv);
        schoolTv = (TextView) findViewById(R.id.school_tv);


        if (MyApplication.getIP(NetworkSettingsActivity.this).length() != 0){
            currentTv.setText(MyApplication.getIP(NetworkSettingsActivity.this));
        }else {
            currentTv.setText("暂未设置网络");
        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipEt.getText().toString().trim();
                if (!ip.isEmpty()) {
                    /**
                     * @Create 2021/10/8 18:12
                     * @Role 待优化项-需要检测自定义的IP地址是否能成功连接到网络
                     * @Param
                     */
                    MyApplication.setIP(NetworkSettingsActivity.this, ip);
                    showToast("保存成功！",1020);
                    MyApplication.setIsLogin(NetworkSettingsActivity.this, false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }, 1000);
                } else {
                    showToast("服务器IP地址不能为空！",1200);
                }
            }
        });

        publicTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipEt.setText("124.93.196.45:10001");
            }
        });

        schoolTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipEt.setText("192.168.10.162:10001");
            }
        });

    }
}