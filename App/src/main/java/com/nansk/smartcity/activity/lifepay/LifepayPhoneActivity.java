package com.nansk.smartcity.activity.lifepay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.lifepay.OftenUserPhoneAdapter;
import com.nansk.smartcity.beans.lifepay.LifepayPhoneBalanceBean;
import com.nansk.smartcity.beans.lifepay.OftenUserPhoneBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 20:45
 * @Description 手机缴费查询页
 */

public class LifepayPhoneActivity extends BaseActivity implements View.OnClickListener {

    private Spinner spinner;
    private EditText phoneEt;
    private Button queryBtn;
    private TextView tipsTv;
    private RecyclerView telContainer;

    private int categoryId;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_lifepay_phone_query);
        setToolBarTitle("手机缴费");

        initView();
        setToolBarRightButton(true, "缴费记录", new MyCallBack() {
            @Override
            public void CallBack() {
                Intent intent = new Intent(LifepayPhoneActivity.this, LifepayHistoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", categoryId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getOftenUserPhone();
    }

    private void initView() {

        spinner = (Spinner) findViewById(R.id.spinner);
        phoneEt = (EditText) findViewById(R.id.phone_et);
        queryBtn = (Button) findViewById(R.id.query_btn);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        telContainer = (RecyclerView) findViewById(R.id.tel_container);

        telContainer.setLayoutManager(new LinearLayoutManager(LifepayPhoneActivity.this));
        telContainer.addItemDecoration(new DividerItemDecoration(LifepayPhoneActivity.this,DividerItemDecoration.VERTICAL));
        queryBtn.setOnClickListener(this);

        final String[] operators = getResources().getStringArray(R.array.phone_operator);
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this, R.layout.main_spinner_tv, operators);
        spinner.setAdapter(arrayAdapter);


        Intent intent = getIntent();
        categoryId = intent.getIntExtra("categoryId", 0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                operator = operators[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * @Create 2021/9/29 13:45
     * @Role 查询手机号信息
     * @Param
     */
    private void query() {
        WindowMangerUtils.closeKeyboard(LifepayPhoneActivity.this,phoneEt);
        phoneEt.clearFocus();

        String phonenumber = phoneEt.getText().toString();
        if (!phonenumber.isEmpty()) {
            String url = ConnectInfo.getUrl(ConnectInfo.LIFEPAY_PHONE_QUERY, "?operator=" + operator + "&phonenumber=" + phonenumber);
            OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
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
                                final LifepayPhoneBalanceBean.DataBean data = jsonObj.getData();
                                if (data != null) {
                                            Intent intent = new Intent(LifepayPhoneActivity.this, LifepayPhonePaymentActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable("phoneObj", data);
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                } else {
                                    Toast.makeText(LifepayPhoneActivity.this, "没有查询到号码相关信息", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LifepayPhoneActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

        } else {
            Toast.makeText(this, "请输入要充值的手机号码", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * @Create 2021/9/29 17:27
     * @Role 获取常用手机号码
     * @Param
     */
    private void getOftenUserPhone(){
        String record = (String) SharedPreferencesUtils.getRecord(this,"OFTEN_USER_PHONE","");
        List<OftenUserPhoneBean> phoneList = MyApplication.gson.fromJson(record, new TypeToken<List<OftenUserPhoneBean>>() {
        }.getType());
        if (phoneList != null){
        if (phoneList.size()>0){
            tipsTv.setVisibility(View.GONE);
            OftenUserPhoneAdapter adapter = new OftenUserPhoneAdapter(LifepayPhoneActivity.this,phoneList);
            telContainer.setAdapter(adapter);

            adapter.OnItemCallBack(new OftenUserPhoneAdapter.OnItemCallBack() {
                @Override
                public void OnItemCallBack(OftenUserPhoneBean obj) {
                    WindowMangerUtils.closeKeyboard(LifepayPhoneActivity.this,phoneEt);
                    phoneEt.clearFocus();
                    phoneEt.setText(obj.getPhoneNumber());
                    switch (obj.getOperator()){
                        case "中国移动":
                            spinner.setSelection(0);
                            break;
                        case "中国联通":
                            spinner.setSelection(1);
                            break;
                        case "中国电信":
                            spinner.setSelection(2);
                            break;
                    }

                    query();

                }
            });

        }

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {

            //交费页
            case R.id.query_btn:
                query();
                break;
        }
    }
}