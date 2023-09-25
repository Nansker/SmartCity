package com.nansk.smartcity.dialog;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 21:26
 * @Description 支付方式选择弹窗
 */

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;

public class PaymentTypeChangeDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    private Dialog dialog;
    private View view;

    private LinearLayout dianziPlay;
    private LinearLayout weixinPlay;
    private LinearLayout zfbPlay;

    private DialogCallBack dialogCallBack;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_payment_type_change, null, false);
        dialog.setContentView(view);
        initView();
        return dialog;
    }

    private void initView() {

        dianziPlay = (LinearLayout) view.findViewById(R.id.dianzi_play);
        weixinPlay = (LinearLayout) view.findViewById(R.id.weixin_play);
        zfbPlay = (LinearLayout) view.findViewById(R.id.zfb_play);

        dianziPlay.setOnClickListener(this);
        weixinPlay.setOnClickListener(this);
        zfbPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dianzi_play:
                dialogCallBack.DialogCallBack("电子支付");
                break;
            case R.id.weixin_play:
                dialogCallBack.DialogCallBack("微信支付");
                break;
            case R.id.zfb_play:
                dialogCallBack.DialogCallBack("支付宝支付");
                break;
        }
    }

    public interface DialogCallBack{
        void DialogCallBack(String paymentType);
    }

    public void DialogCallBack(DialogCallBack callBack){
        this.dialogCallBack = callBack;
    }
}
