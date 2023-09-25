package com.nansk.smartcity.dialog;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/28 21:26
 * @Description 垃圾分类选择弹窗
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

public class ChangeRubbishDialog extends BottomSheetDialogFragment{
    private Dialog dialog;
    private View view;


    private DialogCallBack dialogCallBack;
    private TextView dialogTitle;
    private LinearLayout tabContainer;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_change_rubbish, null, false);
        dialog.setContentView(view);
        initView();
        return dialog;
    }

    private void initView() {
        dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
        tabContainer = (LinearLayout) view.findViewById(R.id.tab_container);

        final String[] names = new String[]{"废纸","塑料","玻璃","金属物","布料"};
        for (int i  =0;i<tabContainer.getChildCount();i++){
            final int finalI = i;
            tabContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogCallBack.DialogCallBack(names[finalI]);
                }
            });
        }
    }

    public interface DialogCallBack {
        void DialogCallBack(String classX);
    }

    public void DialogCallBack(DialogCallBack callBack) {
        this.dialogCallBack = callBack;
    }
}
