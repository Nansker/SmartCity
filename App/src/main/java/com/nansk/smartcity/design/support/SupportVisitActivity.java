package com.nansk.smartcity.design.support;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 21:10
 * @description 入户走访
 */

public class SupportVisitActivity extends BaseActivity {

    private LinearLayout menuContainer;
    private TextView starTimeTv;
    private TextView endTimeTv;
    private EditText purposeEt;
    private Button submitBtn;
    private TextView nameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_support_visit);
        setToolBarTitle("入户走访");
        initView();
    }

    private void initView() {
        menuContainer = (LinearLayout) findViewById(R.id.menu_container);
        starTimeTv = (TextView) findViewById(R.id.starTime_tv);
        endTimeTv = (TextView) findViewById(R.id.endTime_tv);
        purposeEt = (EditText) findViewById(R.id.purpose_et);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        nameTv = (TextView) findViewById(R.id.name_tv);

        purposeEt.setBackground(getDrawable("#ffffff", 20, 5, "#f2f2f2"));
        submitBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party), 100));
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        for (int i = 0; i < menuContainer.getChildCount(); i++) {
            final int finalI = i;
            menuContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    switch (finalI) {
                        case 0:
                            changeMaster();
                            break;
                        case 1:

                            DatePickerDialog pickerDialog = new DatePickerDialog(SupportVisitActivity.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    starTimeTv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                }
                            }, calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                            );
                            pickerDialog.show();
                            break;
                        case 2:
                            DatePickerDialog datePickerDialog = new DatePickerDialog(SupportVisitActivity.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    endTimeTv.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                }
                            }, calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                            );
                            datePickerDialog.show();
                            break;
                    }
                }
            });
        }


    }

    /**
     * @Create 2021/10/21 12:02
     * @Role 提交
     * @Param
     */
    private void submit() {
        if (!nameTv.getText().toString().equals("请选择要走访的户主")){
            if (!starTimeTv.getText().toString().equals("")){
                if (!endTimeTv.getText().toString().equals("")){
                    if (!purposeEt.getText().toString().trim().equals("")){
                        showToast("申请提交成功！，请耐心对待审核");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1200);
                    }else {
                        showToast("请填写走访的目的");
                    }
                }else {
                    showToast("请选择走访的结束时间");
                }
            }else {
                showToast("请选择走访的开始时间");
            }
        }else {
            showToast("请先选择要走访的户主");
        }
    }

    /**
     * @Create 2021/10/20 21:44
     * @Role 选择户主
     * @Param
     */
    private void changeMaster() {
        final List<String> names = new ArrayList<>();
        names.add("张爱梅");
        names.add("牛爱花");
        names.add("张三");

        View view = LayoutInflater.from(SupportVisitActivity.this).inflate(R.layout.dialog_support_change_master, null);
        LinearLayout container = view.findViewById(R.id.body_container);

        for (int i = 0; i < names.size(); i++) {
            View itemView = LayoutInflater.from(SupportVisitActivity.this).inflate(R.layout.item_pension_service, null);
            TextView nameTv = itemView.findViewById(R.id.name_tv);
            String s = names.get(i);
            nameTv.setText(s);
            container.addView(itemView);
        }


        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(view);
        dialog.show();

        for (int i = 0; i < container.getChildCount(); i++) {
            final int finalI = i;
            container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nameTv.setText(names.get(finalI));
                    dialog.dismiss();
                }
            });

        }

    }
}