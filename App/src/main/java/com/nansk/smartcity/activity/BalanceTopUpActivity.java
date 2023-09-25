package com.nansk.smartcity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.UserInfoBean;
import com.nansk.smartcity.dialog.PaymentTypeChangeDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.UserInfoUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 10:37
 * @description 钱包充值
 */

public class BalanceTopUpActivity extends BaseActivity implements View.OnClickListener {

    private TextView userNameTv;
    private TextView balanceTv;
    private GridLayout changeContainer;
    private EditText moneyEt;
    private LinearLayout paymentBox;
    private TextView paymentTypeTv;
    private Button topUpBtn;
    private LinearLayout moneyEtBox;

    private int rechargeMoney;
    private int[] moneys = new int[]{50, 100, 200, 500, 1000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_wallet_top_up);
        setToolBarTitle("账号充值");

        initView();
        initObject();
        getUserInfo();

        setToolBarRightButton(true, "交易明细", new MyCallBack() {
            @Override
            public void CallBack() {
                Intent intent = new Intent(BalanceTopUpActivity.this, BalanceRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @Create 2021/10/12 20:03
     * @Role 充值
     * @Param
     */
    private void recharge(){
        String url = ConnectInfo.getUrl(ConnectInfo.BALANCE, "recharge?money="+rechargeMoney);
        OkHttpUtil.doPost(url, MyApplication.getToken(BalanceTopUpActivity.this), "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final RequestResultBean json = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200){
                            //更新用户信息
                            UserInfoUtils.updateUserInfo(BalanceTopUpActivity.this);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    getUserInfo();
                                }
                            }, 300);
                            showToast("充值成功！",1200);
                        }else {
                            Log.e("recharge",json.getMsg());
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/12 19:28
     * @Role 判断用户是否选择或输入金额
     * @Param 0    --------   VISIBLE    可见
     * 4    --------   INVISIBLE    不可见但是占用布局空间
     * 8    --------   GONE    不可见也不占用布局空间
     */
    private void judgeMoney() {
        int visibility = moneyEtBox.getVisibility();
        //自己选择金额
        if (visibility == 8) {
            if (rechargeMoney != 0) {
                recharge();
            } else {
                Toast.makeText(this, "请先选择要充值的金额！", Toast.LENGTH_SHORT).show();
            }
        } else if (visibility == 0) {
            WindowMangerUtils.closeKeyboard(BalanceTopUpActivity.this, moneyEt);
            moneyEt.clearFocus();
            String trim = moneyEt.getText().toString().trim();
            if (!trim.isEmpty()) {
                int money = Integer.parseInt(trim);
                if (money > 0) {
                    rechargeMoney = money;
                   recharge();
                } else {
                    Toast.makeText(this, "请输入大于0的金额！", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "请输入要充值的金额！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * @Create 2021/10/12 17:08
     * @Role 获取用户信息
     * @Param
     */
    private void getUserInfo() {
        UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(BalanceTopUpActivity.this);
        userNameTv.setText(userInfo.getNickName());
        balanceTv.setText(userInfo.getBalance().toString());
    }


    @SuppressLint("ResourceAsColor")
    private void initObject() {

        topUpBtn.setOnClickListener(this);
        paymentBox.setOnClickListener(this);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#007aff"));
        gradientDrawable.setCornerRadius(100);
        topUpBtn.setBackground(gradientDrawable);

        for (int i = 0; i < changeContainer.getChildCount(); i++) {
            GradientDrawable gradientDrawable1 = new GradientDrawable();
            gradientDrawable1.setCornerRadius(8);
            gradientDrawable1.setColor(Color.WHITE);
            gradientDrawable1.setStroke(2, Color.parseColor("#999999"));
            changeContainer.getChildAt(i).setBackground(gradientDrawable1);

            final int finalI = i;
            changeContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeMoneyStyle(finalI);

                    if (finalI == changeContainer.getChildCount() - 1) {
                        moneyEtBox.setVisibility(View.VISIBLE);
                    } else {
                        //设置金额
                        rechargeMoney = moneys[finalI];
                        moneyEtBox.setVisibility(View.GONE);
                        moneyEt.clearFocus();
                        moneyEt.setText("");
                    }
                }
            });
        }

    }

    /**
     * @Create 2021/10/12 20:21
     * @Role 选择支付方式
     * @Param
     */
    private void changePaymentType() {
        final PaymentTypeChangeDialog dialog = new PaymentTypeChangeDialog();
        dialog.DialogCallBack(new PaymentTypeChangeDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(String paymentType) {
                paymentTypeTv.setText(paymentType);
                dialog.dismiss();
            }
        });

        dialog.show(getSupportFragmentManager(),"dialog");

    }

    /**
     * @Create 2021/10/12 19:09
     * @Role 选择金额监听事件样式
     * @Param
     */
    private void changeMoneyStyle(int i) {
        for (int j = 0; j < changeContainer.getChildCount(); j++) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            if (i == j) {
                gradientDrawable.setCornerRadius(8);
                gradientDrawable.setColor(Color.parseColor("#007aff"));
                TextView textView = findViewById(changeContainer.getChildAt(j).getId());
                textView.setTextColor(Color.WHITE);
            } else {
                gradientDrawable.setCornerRadius(8);
                gradientDrawable.setColor(Color.WHITE);
                gradientDrawable.setStroke(2, Color.parseColor("#999999"));
                TextView textView = findViewById(changeContainer.getChildAt(j).getId());
                textView.setTextColor(Color.parseColor("#333333"));
            }
            changeContainer.getChildAt(j).setBackground(gradientDrawable);
        }
    }

    private void initView() {
        userNameTv = (TextView) findViewById(R.id.userName_tv);
        balanceTv = (TextView) findViewById(R.id.balance_tv);
        changeContainer = (GridLayout) findViewById(R.id.change_container);
        moneyEt = (EditText) findViewById(R.id.money_et);
        paymentBox = (LinearLayout) findViewById(R.id.payment_box);
        paymentTypeTv = (TextView) findViewById(R.id.paymentType_tv);
        topUpBtn = (Button) findViewById(R.id.topUp_btn);
        moneyEtBox = (LinearLayout) findViewById(R.id.money_et_box);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.payment_box:
                changePaymentType();
                break;
            case R.id.topUp_btn:
                judgeMoney();
                break;
        }
    }


}