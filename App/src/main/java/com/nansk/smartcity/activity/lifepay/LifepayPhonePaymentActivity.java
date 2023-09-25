package com.nansk.smartcity.activity.lifepay;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.lifepay.LifepayAmuntChangeAdapter;
import com.nansk.smartcity.beans.lifepay.LifepayPhoneRuleBean;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.lifepay.LifepayPhoneBalanceBean;
import com.nansk.smartcity.beans.lifepay.LifepayPhoneRechargeBean;
import com.nansk.smartcity.beans.lifepay.OftenUserPhoneBean;
import com.nansk.smartcity.dialog.PaymentTypeChangeDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/29 11:05
 * @Description 手机缴费页
 */

public class LifepayPhonePaymentActivity extends BaseActivity implements View.OnClickListener {
    private LifepayPhoneBalanceBean.DataBean phoneObj;

    private TextView phonenumberTv;
    private TextView addressTv;
    private TextView titleTv;
    private RecyclerView amountContainer;
    private Button paymentBtn;
    private TextView operatorTv;
    private TextView balanceTv;

    private TextView ruleNameTv;

    private int[] amounts;
    private LifepayPhoneRechargeBean rechargeObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_lifepay_phone_payment);
        setToolBarTitle("话费充值");


        initView();
        initObject();
        setToolBarRightButton(true, "添加常用", new MyCallBack() {
            @Override
            public void CallBack() {
                addOftenUsePhone();
            }
        });
    }

    private void initView() {
        phonenumberTv = (TextView) findViewById(R.id.phonenumber_tv);
        addressTv = (TextView) findViewById(R.id.address_tv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        amountContainer = (RecyclerView) findViewById(R.id.amount_container);
        paymentBtn = (Button) findViewById(R.id.payment_btn);
        operatorTv = (TextView) findViewById(R.id.operator_tv);
        balanceTv = (TextView) findViewById(R.id.balance_tv);

        paymentBtn.setOnClickListener(this);

        ruleNameTv = (TextView) findViewById(R.id.ruleName_tv);
    }

    private void initObject() {

        Intent intent = getIntent();
        phoneObj = (LifepayPhoneBalanceBean.DataBean) intent.getSerializableExtra("phoneObj");
        rechargeObj = new LifepayPhoneRechargeBean();

        amountContainer.setLayoutManager(new GridLayoutManager(this, 3));
        amounts = new int[]{50, 100, 200};
        final LifepayAmuntChangeAdapter adapter = new LifepayAmuntChangeAdapter(this, amounts);
        amountContainer.setAdapter(adapter);
        amountContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 15;
                outRect.bottom = 15;
                outRect.left = 15;
                outRect.right = 15;
            }
        });

        adapter.OnItemCallBack(new LifepayAmuntChangeAdapter.OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position) {
                adapter.setPosition(position);
                adapter.notifyDataSetChanged();
                //获取相关优惠政策
                getRule(amounts[position]);
            }
        });

        getRule(50);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                operatorTv.setText(phoneObj.getOperator());
                balanceTv.setText(phoneObj.getBalance().toString());
                phonenumberTv.setText(phoneObj.getPhonenumber());
            }
        });
    }

    /**
     * @Create 2021/9/29 14:41
     * @Role 获取优惠政策
     * @Param
     */
    private void getRule(final int amount) {
        String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_PHONE_QUERY, "/rule/list");
        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final LifepayPhoneRuleBean jsonObj = MyApplication.gson.fromJson(response.body().string(), LifepayPhoneRuleBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<LifepayPhoneRuleBean.DataBean> data = jsonObj.getData();
                            if (data.size() > 0) {
                                for (int i = 0; i < data.size(); i++) {
                                    if (data.get(i).getRechargeAmount().intValue() == amount) {
                                        int newAmount = amount - data.get(i).getBackAmount().intValue();
                                        rechargeObj.setRuleId(data.get(i).getRuleId());
                                        rechargeObj.setType(Integer.toString(data.get(i).getRuleId()));
                                        paymentBtn.setText("立即支付：￥" + newAmount);
                                        ruleNameTv.setText("冲" + data.get(i).getRechargeAmount().intValue() + "返" + data.get(i).getBackAmount().intValue() + "话费券");
                                    }
                                }
                                rechargeObj.setRechargeAmount(amount);
                            } else {
                                rechargeObj.setRechargeAmount(amount);
                                rechargeObj.setType("1");
                                paymentBtn.setText("立即支付：￥" + amount);
                                ruleNameTv.setText("无");
                            }
                        } else {
                            rechargeObj.setRechargeAmount(amount);
                            rechargeObj.setType("1");
                            paymentBtn.setText("立即支付：￥" + amount);
                            ruleNameTv.setText("无");
                            Toast.makeText(LifepayPhonePaymentActivity.this, "获取优惠政策失败\n" + jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }


    /**
     * @Create 2021/9/29 13:32
     * @Role 支付话费
     * @Param
     */
    private void payment() {
        final PaymentTypeChangeDialog dialog = new PaymentTypeChangeDialog();
        dialog.DialogCallBack(new PaymentTypeChangeDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(String paymentType) {

                rechargeObj.setPaymentType(paymentType);
                rechargeObj.setPhonenumber(phoneObj.getPhonenumber());
                String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_PHONE_QUERY, "/recharge");
                OkHttpUtil.doPost(url, MyApplication.getToken(LifepayPhonePaymentActivity.this), rechargeObj, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (jsonObj.getCode() == 200) {
                                    dialog.dismiss();
                                    Toast.makeText(LifepayPhonePaymentActivity.this, "充值成功！", Toast.LENGTH_SHORT).show();
                                    //充值成功，重新获取话费余额
                                    updateBalance();
                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(LifepayPhonePaymentActivity.this, "充值失败，请稍后再试！\n"+jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });
            }
        });

        dialog.show(getSupportFragmentManager(), "dialog");

    }

    /**
     * @Create 2021/9/29 16:21
     * @Role 更新话费余额
     * @Param
     */
    private void updateBalance() {
        String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_PHONE_QUERY, "?operator=" + phoneObj.getOperator() + "&phonenumber=" + phoneObj.getPhonenumber());
        OkHttpUtil.doGet(url, MyApplication.getToken(LifepayPhonePaymentActivity.this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                final LifepayPhoneBalanceBean jsonObj = MyApplication.gson.fromJson(response.body().string(), LifepayPhoneBalanceBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            balanceTv.setText(jsonObj.getData().getBalance().toString());
                        } else {
                            Toast.makeText(LifepayPhonePaymentActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/9/29 16:46
     * @Role 加入常用电话
     * @Param
     */
    private boolean addOftenUsePhone() {
        //内部储存的key名
        String spName = "OFTEN_USER_PHONE";

        //获取当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String format = dateFormat.format(date);

        //新增电话对象
        OftenUserPhoneBean phoneBean = new OftenUserPhoneBean();
        phoneBean.setPhoneNumber(phoneObj.getPhonenumber());
        phoneBean.setOperator(phoneObj.getOperator());
        phoneBean.setCreateTime(format);

        //先读取储存中的常用电话集合
        String oftenUserPhone = (String) SharedPreferencesUtils.getRecord(this,spName,"");
        List<OftenUserPhoneBean> phoneBeanList;
        //储存中没有数据
        if (oftenUserPhone == "") {
            phoneBeanList = new ArrayList<>();
            phoneBeanList.add(phoneBean);
            //储存中有其他号码
        } else {
            phoneBeanList = MyApplication.gson.fromJson(oftenUserPhone, new TypeToken<List<OftenUserPhoneBean>>() {
            }.getType());

            if (phoneBeanList == null){
                phoneBeanList = new ArrayList<>();
                //将新号码加入集合
                phoneBeanList.add(phoneBean);
            }else {
                //判断当前手机号是否已经加入过常用手机号
                for (int i=0;i<phoneBeanList.size();i++){
                    if (!phoneBeanList.get(i).getPhoneNumber().equals(phoneBean.getPhoneNumber())){
                        //与所有结果比对完
                        if (i==phoneBeanList.size()-1){
                            //将新号码加入集合
                            phoneBeanList.add(phoneBean);
                        }
                    }else {
                        Toast.makeText(this, "该号码已经加入过常用，无须重复添加！", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
            
        }
        //将新集合对象序列化
        String newObj = MyApplication.gson.toJson(phoneBeanList);
        //更新储存数据
        SharedPreferencesUtils.addRecord(this,spName,newObj);

        Toast.makeText(this, "加入成功！", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.payment_btn:
                payment();
                break;
        }
    }


}