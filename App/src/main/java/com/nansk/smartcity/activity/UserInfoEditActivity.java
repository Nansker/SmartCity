package com.nansk.smartcity.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.MyCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.ToUserInfoEditObj;
import com.nansk.smartcity.beans.UserInfoBean;
import com.nansk.smartcity.utils.InputMatchUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 14:51
 * @description 用户相信修改页面
 */

public class UserInfoEditActivity extends BaseActivity {
    private int MODIFY_INFO= 120;
    private EditText inputEt;
    private TextView tipsTv;

    private ToUserInfoEditObj editObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_user_info_edit);
        setToolBarRightButton(true, "保存", new MyCallBack() {
            @Override
            public void CallBack() {
                judgeInfo();
            }
        });
        initView();
        initObject();

    }

    /**
     * @Create 2021/10/12 15:27
     * @Role 判断用户输入是否合法
     * @Param
     */
    private void judgeInfo() {
        WindowMangerUtils.closeKeyboard(UserInfoEditActivity.this,inputEt);
        inputEt.clearFocus();
        tipsTv.setText("");

        String[] tips = new String[]{"昵称长度不能少于2","请输入长度为15或18位的身份证号", "请输入正确格式的手机号码"};
        String regex;
        String info = inputEt.getText().toString().trim();
        switch (editObj.getKey()) {
            case "nickName":
                if (info.length()>=2) {
                    saveInfo();
                } else {
                    tipsTv.setText(tips[0]);
                }
                break;
            case "email":
                    saveInfo();
                break;
            case "idCard":
                regex = "^\\d{15}|\\d{18}$";
                if (InputMatchUtils.isMatches(regex, info)) {
                    saveInfo();
                } else {
                    tipsTv.setText(tips[1]);
                }
                break;
            case "phonenumber":
                regex = "^\\d{11}$";
                if (InputMatchUtils.isMatches(regex, info)) {
                    saveInfo();
                } else {
                    tipsTv.setText(tips[2]);
                }
                break;
        }

    }

    /**
     * @Create 2021/10/12 15:37
     * @Role 保存用户修改的信息
     * @Param
     */
    private void saveInfo() {
        String info = inputEt.getText().toString().trim();
        UserInfoBean.UserBean userInfo = UserInfoUtils.getUserInfo(UserInfoEditActivity.this);
        switch (editObj.getKey()){
            case "nickName":
                userInfo.setNickName(info);
                break;
            case "email":
                userInfo.setEmail(info);
                break;
            case "idCard":
                userInfo.setIdCard(info);
                break;
            case "phonenumber":
                userInfo.setPhonenumber(info);
                break;
        }
        SharedPreferencesUtils.addRecord(UserInfoEditActivity.this,"userInfo", MyApplication.gson.toJson(userInfo));
        setResult(MODIFY_INFO);
        finish();
    }

    private void initObject() {
        editObj = (ToUserInfoEditObj) getIntent().getSerializableExtra("editObj");
        inputEt.setText(editObj.getValue());
        switch (editObj.getKey()) {
            case "nickName":
                setLeftTitleBar("修改昵称");
                break;
                case "email":
                    setLeftTitleBar("修改邮箱地址");
                break;
            case "idCard":
                setLeftTitleBar("修改证件号");
                break;
            case "phonenumber":
                setLeftTitleBar("修改手机号");
                break;
        }
    }

    private void initView() {
        inputEt = (EditText) findViewById(R.id.input_et);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
    }


}