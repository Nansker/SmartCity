package com.nansk.smartcity.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 16:38
 * @Description 外卖评论弹出窗
 */

public class TakeoutOrderCommentDialog extends BottomSheetDialogFragment {
    private Dialog dialog;
    private View view;

    private EditText bodyEt;
    private RatingBar scoreBar;
    private Button submitBtn;

    private CommentDialog commentDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_takeout_order_comment, null);
        dialog.setContentView(view);
        initView();
        return dialog;
    }

    private void initView() {
        bodyEt = (EditText) view.findViewById(R.id.body_et);
        scoreBar = (RatingBar) view.findViewById(R.id.score_bar);
        submitBtn = (Button) view.findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.CommentDialog(bodyEt,scoreBar);
            }
        });
    }

    //开放接口
    public interface CommentDialog{
        void CommentDialog(EditText bodyEt,RatingBar scoreBar);
    }

    public void CommentDialog(CommentDialog dialog){
        this.commentDialog = dialog;
    }

}
