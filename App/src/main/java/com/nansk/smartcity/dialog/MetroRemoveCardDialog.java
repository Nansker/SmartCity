package com.nansk.smartcity.dialog;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 21:11
 * @description 注销乘车卡底部弹窗
 */

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;

public class MetroRemoveCardDialog extends BottomSheetDialogFragment {
    private View view;
    private Dialog dialog;
    private TextView dialogTitle;
    private TextView userNameTv;
    private TextView cardNumTv;
    private TextView idCardTv;
    private Button confirmBtn;

    public DialogCallBack dialogCallBack;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_metro_remove_card, null);
        dialog.setContentView(view);
        initView();
        return dialog;

    }

    private void initView() {
        dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
        userNameTv = (TextView) view.findViewById(R.id.userName_tv);
        cardNumTv = (TextView) view.findViewById(R.id.cardNum_tv);
        idCardTv = (TextView) view.findViewById(R.id.idCard_tv);
        confirmBtn = (Button) view.findViewById(R.id.confirm_btn);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#ca062c"));
        gradientDrawable.setCornerRadius(10);
        confirmBtn.setBackground(gradientDrawable);

        dialogCallBack.DialogCallBack(userNameTv,cardNumTv,idCardTv,confirmBtn);

    }

    public interface DialogCallBack{
        void DialogCallBack(TextView userNameTv,TextView cardNumTv,TextView idCardTv,Button confirmBtn);
    }
    public void DialogCallBack(DialogCallBack callBack){
        this.dialogCallBack = callBack;
    }

}
