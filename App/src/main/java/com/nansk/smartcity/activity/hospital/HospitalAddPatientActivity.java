package com.nansk.smartcity.activity.hospital;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.hospital.AddPatientBean;
import com.nansk.smartcity.beans.hospital.PatientListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 16:07
 * @Description 新增就诊人
 */

public class HospitalAddPatientActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    private PatientListBean.RowsBean cardObj;

    private EditText nickNameEt;
    private RadioGroup sexRdg;
    private RadioButton maleRd;
    private RadioButton womanRd;
    private EditText idCardEt;
    private TextView dateTv;
    private EditText phoneEt;
    private EditText addressEt;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_hospital_add_patient);
        setToolBarTitle("新增就诊人");
        initView();
    }

    private void initView() {
        nickNameEt = (EditText) findViewById(R.id.nickName_et);
        sexRdg = (RadioGroup) findViewById(R.id.sex_rdg);
        maleRd = (RadioButton) findViewById(R.id.male_rd);
        womanRd = (RadioButton) findViewById(R.id.woman_rd);
        idCardEt = (EditText) findViewById(R.id.idCard_et);
        dateTv = (TextView) findViewById(R.id.date_tv);
        phoneEt = (EditText) findViewById(R.id.phone_et);
        addressEt = (EditText) findViewById(R.id.address_et);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);


        Intent intent = getIntent();
        cardObj = (PatientListBean.RowsBean) intent.getSerializableExtra("cardObj");
        if (cardObj != null) {
            setToolBarTitle("编辑就诊人信息");
            editInfo();
        }

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInfo();

            }
        });


        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog pickerDialog = new DatePickerDialog(HospitalAddPatientActivity.this, HospitalAddPatientActivity.this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                pickerDialog.show();
            }
        });

    }

    /**
     * @Create 2021/9/17 18:22
     * @Role 向页面显示就诊人信息
     * @Param
     */
    private void editInfo() {
        nickNameEt.setText(cardObj.getName());
        if (cardObj.getSex().equals("0")) {
            maleRd.setChecked(true);
        } else if (cardObj.getSex().equals("1")) {
            womanRd.setChecked(true);
        }

        idCardEt.setText(cardObj.getCardId());
        dateTv.setText(cardObj.getBirthday());
        phoneEt.setText(cardObj.getTel());
        addressEt.setText(cardObj.getAddress());

    }

    /**
     * @Create 2021/9/17 16:32
     * @Role 新增就诊人
     * @Param
     */
    private void submitInfo() {
        String name = nickNameEt.getText().toString().trim();
        String idCard = idCardEt.getText().toString().trim();
        String birthday = dateTv.getText().toString();
        String tel = phoneEt.getText().toString().trim();
        String address = addressEt.getText().toString().trim();
        String sex = null;
        if (maleRd.isChecked()) {
            sex = "0";
        } else if (womanRd.isChecked()) {
            sex = "1";
        }

        if (!name.isEmpty() && !idCard.isEmpty() && !birthday.equals("请选择") && !tel.isEmpty() && !address.isEmpty() && sex != null) {

            AddPatientBean patientBean = new AddPatientBean();
            patientBean.setName(name);
            patientBean.setCardId(idCard);
            patientBean.setBirthday(birthday);
            patientBean.setTel(tel);
            patientBean.setAddress(address);
            patientBean.setSex(sex);

            String url = ConnectInfo.getUrl(ConnectInfo.HOSPITAL_ADD_PATIENT, "");

            //添加就诊人信息
            if (cardObj == null) {
                OkHttpUtil.doPost(url, MyApplication.getToken(this), patientBean, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final RequestResultBean resultBean = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (resultBean.getCode() == 200) {
                                    showToast("就诊人添加成功！");
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    }, 1200);

                                } else {
                                    showToast(resultBean.getMsg());
                                }
                            }
                        });
                    }
                });
                //修改用户信息
            } else {
                OkHttpUtil.doPut(url, MyApplication.getToken(this), patientBean, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean resultBean = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (resultBean.getCode() == 200) {
                                    showToast("信息修改成功！");
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    }, 1200);
                                } else {
                                    showToast(resultBean.getMsg());
                                }
                            }
                        });
                    }
                });
            }
        } else {
            showToast("请将信息填写完整！");
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
        dateTv.setText(date);
    }
}