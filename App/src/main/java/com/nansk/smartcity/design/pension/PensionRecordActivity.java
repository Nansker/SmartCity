package com.nansk.smartcity.design.pension;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.dialog.CommentDialog;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 11:11
 * @description 巡检记录
 */

public class PensionRecordActivity extends BaseActivity {

    private LinearLayout bodyContainer;
    private RatingBar ratingBar1;
    private Button btn1;
    private RatingBar ratingBar2;
    private Button btn2;
    private RatingBar ratingBar3;
    private Button btn3;
    private RatingBar ratingBar4;
    private Button bt4;
    private RatingBar ratingBar5;
    private Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension_record);
        setToolBarTitle("巡检记录");
        setToolBarBackground("#b07eef");
        initView();
    }

    private void initView() {
        bodyContainer = (LinearLayout) findViewById(R.id.body_container);
        ratingBar1 = (RatingBar) findViewById(R.id.rating_bar1);
        btn1 = (Button) findViewById(R.id.btn_1);
        ratingBar2 = (RatingBar) findViewById(R.id.rating_bar2);
        btn2 = (Button) findViewById(R.id.btn_2);
        ratingBar3 = (RatingBar) findViewById(R.id.rating_bar3);
        btn3 = (Button) findViewById(R.id.btn_3);
        ratingBar4 = (RatingBar) findViewById(R.id.rating_bar4);
        bt4 = (Button) findViewById(R.id.bt_4);
        ratingBar5 = (RatingBar) findViewById(R.id.rating_bar5);
        btn5 = (Button) findViewById(R.id.btn_5);

        final List<RatingBar> ratingBars = new ArrayList<>();
        ratingBars.add(ratingBar1);
        ratingBars.add(ratingBar2);
        ratingBars.add(ratingBar3);
        ratingBars.add(ratingBar4);
        ratingBars.add(ratingBar5);

        List<Button> buttons = new ArrayList<>();
        buttons.add(btn1);
        buttons.add(btn2);
        buttons.add(btn3);
        buttons.add(bt4);
        buttons.add(btn5);

        for (int i = 0; i < buttons.size(); i++) {
            Button button =  buttons.get(i);
            final RatingBar ratingBar = ratingBars.get(i);
            button.setBackground(getDrawable("#b07eef",100));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CommentDialog dialog = new CommentDialog("服务评价", getDrawable("#b07eef", 100), true);
                    dialog.DialogCallBack(new CommentDialog.DialogCallBack() {
                        @Override
                        public void DialogCallBack(TextView dialogTitle, EditText bodyEt, RatingBar scoreBar, Button submitBtn) {
                            ratingBar.setRating(scoreBar.getRating());
                            dialog.dismiss();
                            showToast("评价成功！");
                        }
                    });
                    dialog.show(getSupportFragmentManager(),"dialog");
                }
            });
        }


    }

}