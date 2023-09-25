package com.nansk.smartcity.activity.traffic;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.traffic.TrafficAddCarBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.view.MyKeyboard;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/10/02 23:45
 * @Description 添加备案机动车
 */

public class TrafficAddCardActivity extends BaseActivity implements View.OnClickListener {

    private Button addCarBtn;
    private LinearLayout changeTypeBox;
    private TextView plateTypeTv;
    private KeyboardView keyBoardView;
    public static EditText plateNumEt;

    private Gson gson;
    private String token;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_traffic_add_car);
        setToolBarTitle("备案机动车");
        setToolBarBackground("#2c65a8");
        initView();
        initObject();
    }

    private void initView() {

        addCarBtn = (Button) findViewById(R.id.addCar_btn);
        changeTypeBox = (LinearLayout) findViewById(R.id.changeType_box);

        changeTypeBox.setOnClickListener(this);
        addCarBtn.setOnClickListener(this);
        plateTypeTv = (TextView) findViewById(R.id.plateType_tv);
        keyBoardView = (KeyboardView) findViewById(R.id.keyBoard_view);
        plateNumEt = (EditText) findViewById(R.id.plateNum_et);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
    }

    /**
     * @Create 2021/10/2 23:55
     * @Role
     * @Param
     */
    private void initObject() {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setColor(Color.parseColor("#2c65a8"));
        addCarBtn.setBackground(gradientDrawable);

        gson = MyApplication.gson;
        token = MyApplication.getToken(this);

        plateNumEt.setShowSoftInputOnFocus(false);
        final MyKeyboard myKeyboard = new MyKeyboard(this, keyBoardView);
        plateNumEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plateNumEt.getText().toString().length() == 0) {
                    myKeyboard.showKeyBoard();
                } else {
                    plateNumEt.setShowSoftInputOnFocus(true);
                }

            }
        });
        plateNumEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    myKeyboard.showKeyBoard();
                } else {
                    myKeyboard.hideKeyBoard();
                }
            }
        });

    }

    /**
     * @Create 2021/10/3 0:37
     * @Role 号牌种类选择弹窗
     * @Param
     */
    private void openPlateTypeDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_change_palte_type, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();

        LinearLayout typeContainer = view.findViewById(R.id.type_container);

        final String[] plateTypes = getResources().getStringArray(R.array.traffic_plate_type);

        for (int i = 0; i < plateTypes.length; i++) {
            TextView textView = new TextView(this);
            textView.setTextSize(16);
            if (plateTypeTv.getText().toString().equals(plateTypes[i])) {
                textView.setTextColor(Color.parseColor("#2c65a8"));
            } else {
                textView.setTextColor(Color.parseColor("#666666"));
            }
            textView.setText(plateTypes[i]);
            textView.setPadding(120, 30, 120, 30);
            typeContainer.addView(textView);

            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plateTypeTv.setText(plateTypes[finalI]);
                    dialog.dismiss();
                }
            });

        }
        dialog.show();
    }

    private void addCar() {
        String plateNum = plateNumEt.getText().toString();

        if (!plateNum.isEmpty() && plateNum.length() == 8) {
            TrafficAddCarBean addCarBean = new TrafficAddCarBean();
            addCarBean.setEngineNo("");
            addCarBean.setType(plateTypeTv.getText().toString());
            addCarBean.setPlateNo(plateNum);

            String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_CAR, "");
            OkHttpUtil.doPost(url, token, addCarBean, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final RequestResultBean jsoObj = gson.fromJson(response.body().string(), RequestResultBean.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsoObj.getCode() == 200) {
                                tipsTv.setVisibility(View.VISIBLE);
                                tipsTv.setText("添加机动车成功！");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 600);

                            } else {
                                tipsTv.setText("添加机动车出现问题！\n" + jsoObj.getMsg());
                            }
                        }
                    });
                }
            });
        } else {
            Toast.makeText(this, "请将车牌号信息填写完整！", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeType_box:
                openPlateTypeDialog();
                break;
            case R.id.addCar_btn:
                addCar();
                break;
        }
    }
}