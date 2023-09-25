package com.nansk.smartcity.dialog;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 10:00
 * @description 退出登录确框
 */

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;

public class LoginOutConfirmDialog extends BottomSheetDialogFragment {
    private View view;
    private Dialog dialog;
    private TextView dialogTitle;
    private LinearLayout tabMenu;

    private DialogCallBack dialogCallBack;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_login_out_confirm, null);
        dialog.setContentView(view);
        initView();
        return dialog;
    }

    private void initView() {
        dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
        tabMenu = (LinearLayout) view.findViewById(R.id.tab_menu);
        for (int i =0;i<tabMenu.getChildCount();i++){
            final int finalI = i;
            tabMenu.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogCallBack.DialogCallBack(finalI);
                }
            });
        }
    }


    public interface DialogCallBack{
        void DialogCallBack(int position);
    }

    public void DialogCallBack(DialogCallBack callBack){
        this.dialogCallBack = callBack;
    }

}
