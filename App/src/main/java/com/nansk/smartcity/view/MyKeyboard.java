package com.nansk.smartcity.view;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/03 18:34
 * @description
 */

import android.content.Context;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.view.View;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.traffic.TrafficAddCardActivity;

public class MyKeyboard {
    private Context context;
    private KeyboardView keyboardView;

    public MyKeyboard(Context context, KeyboardView keyboardView) {
        this.context = context;
        this.keyboardView = keyboardView;

        setKeyboardType(0);
        keyboardView.setVisibility(View.GONE);

        keyboardView.setBackgroundColor(Color.WHITE);
        keyboardView.setEnabled(true);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(listener);
    }

    KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {

        }

        @Override
        public void onRelease(int primaryCode) {

        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {

            Editable editable = TrafficAddCardActivity.plateNumEt.getText();
            int start = TrafficAddCardActivity.plateNumEt.getSelectionStart();
            if (primaryCode > 0) {
                editable.insert(0, getProvincesString(primaryCode-1,primaryCode));
                //转字母数字键盘
                hideKeyBoard();

            } else if (primaryCode == -3) {
                if (start > 0) {
                    editable.delete(start - 1, start);
                }
            } else if (primaryCode == -2) {
                hideKeyBoard();
            }


        }

        @Override
        public void onText(CharSequence text) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    public void setKeyboardType(int type) {
        Keyboard keyboard;
        switch (type) {
            case 0:
               keyboard = new Keyboard(context, R.layout.main_change_province_keyboard);
               keyboardView.setKeyboard(keyboard);
               break;
        }
    }

    public void showKeyBoard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyBoard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
    }

    private String getProvincesString(int start,int end){
        String provinces = "京津渝沪冀晋辽吉黑苏浙皖闽赣鲁豫鄂湘粤琼川贵云陕甘青蒙贵宁新藏使领警学港澳";
        return provinces.substring(start,end);
    }

}
