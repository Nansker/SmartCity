package com.nansk.smartcity.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 16:38
 * @Description 外卖退款弹出窗
 */

public class TakeoutOrderRefoundDialog extends BottomSheetDialogFragment {
    private Dialog dialog;
    private View view;

    private EditText bodyEt;
    private RatingBar scoreBar;
    private Button submitBtn;

    private CommentDialog commentDialog;
    private TextView dialogTitle;

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
        dialogTitle = (TextView) view.findViewById(R.id.dialog_title);
        scoreBar.setVisibility(View.GONE);

        dialogTitle.setText("退款");
        ViewGroup.LayoutParams layoutParams = bodyEt.getLayoutParams();
        layoutParams.height = 200;
        bodyEt.setLayoutParams(layoutParams);
        bodyEt.setHint("退款理由...");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.CommentDialog(bodyEt);
            }
        });
    }
    
    //开放接口
    public interface CommentDialog {
        void CommentDialog(EditText bodyEt);
    }

    public void CommentDialog(CommentDialog dialog) {
        this.commentDialog = dialog;
    }

}
