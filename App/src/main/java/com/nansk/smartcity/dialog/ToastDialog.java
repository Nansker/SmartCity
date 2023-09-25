package com.nansk.smartcity.dialog;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/10/15 10:29
 * @Description
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.nansk.smartcity.R;

public class ToastDialog extends PopupWindow {
    private Context context;
    private View contentView;
    private TextView messageTv;

    public ToastDialog(Context context) {
        super(context);
        this.context = context;
        initDialog();
    }


    private void initDialog() {
        contentView = LayoutInflater.from(context).inflate(R.layout.main_toast_dialog, null);
        messageTv = contentView.findViewById(R.id.message_tv);

        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setOutsideTouchable(false);
        setFocusable(false);

        setBackgroundDrawable(new ColorDrawable(0x000000));
    }

    public void showReveal(String msg) {
        if (contentView == null) {
            Toast.makeText(context, "发出错误，请稍后再试！", Toast.LENGTH_SHORT).show();
        } else {
            messageTv.setText(msg);
            showAtLocation(contentView, Gravity.CENTER, 0, 0);
        }
    }

    public void setMsg(String msg){
        messageTv.setText(msg);
    }


}
