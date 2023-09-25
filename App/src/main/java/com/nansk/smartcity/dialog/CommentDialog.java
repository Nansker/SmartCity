package com.nansk.smartcity.dialog;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 21:36
 * @Description 评论弹窗
 */

public class CommentDialog extends BottomSheetDialogFragment {
    private Dialog dialog;
    private View view;

    public EditText bodyEt;
    private Button submitBtn;

    private DialogCallBack dialogCallBack;
    private TextView dialogTitle;

    private String title;
    private Drawable buttonStyle;
    private RatingBar scoreBar;
    private boolean scoreBarIsShow;

    private ToastDialog toastDialog;

    public CommentDialog(String title, Drawable buttonStyle, boolean scoreBarIsShow) {
        super();
        this.title = title;
        this.buttonStyle = buttonStyle;
        this.scoreBarIsShow = scoreBarIsShow;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_comment_model, null);
        dialog.setContentView(view);
        toastDialog = new ToastDialog(getContext());
        initView();
        return dialog;
    }

    private void initView() {
        bodyEt = (EditText) view.findViewById(R.id.body_et);
        submitBtn = (Button) view.findViewById(R.id.submit_btn);
        dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
        scoreBar = (RatingBar) view.findViewById(R.id.score_bar);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bodyEt.getText().toString().trim().isEmpty()) {
                    if (scoreBarIsShow) {
                        if (scoreBar.getRating() > 0) {
                            dialogCallBack.DialogCallBack(dialogTitle, bodyEt, scoreBar, submitBtn);
                        } else {
                            showToast("请选择评分");
                        }
                    } else {
                        dialogCallBack.DialogCallBack(dialogTitle, bodyEt, scoreBar, submitBtn);
                    }

                } else {
                    showToast("输入的内容不能为空！");
                }

            }
        });

        if (!title.equals("")) {
            dialogTitle.setText(title);
        }
        if (buttonStyle != null) {
            submitBtn.setBackground(buttonStyle);
        }

        if (scoreBarIsShow) {
            scoreBar.setVisibility(View.VISIBLE);
        } else {
            scoreBar.setVisibility(View.GONE);
        }
    }

    private void showToast(String msg) {
        toastDialog.showReveal(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toastDialog.dismiss();
            }
        }, 1200);
    }


    //开放接口
    public interface DialogCallBack {
        void DialogCallBack(TextView dialogTitle, EditText bodyEt, RatingBar scoreBar, Button submitBtn);
    }

    public void DialogCallBack(DialogCallBack dialog) {
        this.dialogCallBack = dialog;
    }

}
