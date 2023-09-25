package com.nansk.smartcity.design.pension;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.pension.PensionAssessBean;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 13:40
 * @description 健康评估
 */

public class PensionAssessActivity extends BaseActivity {

    private Button submitBtn;
    private GridLayout optionsContainer1;
    private GridLayout optionsContainer2;
    private GridLayout optionsContainer3;
    private GridLayout optionsContainer4;

    private PensionAssessBean assessObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension_assess);
        setToolBarTitle("评估资料填写");
        setToolBarBackground("#b07eef");
        initView();
    }


    /**
     * @Create 2021/10/19 15:57
     * @Role 提交结果
     * @Param
     */
    private void submit() {
        showToast("自动生成评估结果中...",800);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PensionAssessActivity.this, PensionAssessResultsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj",assessObj);
                intent.putExtras(bundle);
                startActivity(intent);
//                finish();
            }
        }, 800);
    }

    /**
     * @Create 2021/10/19 14:28
     * @Role
     * @Param
     */
    private void initView() {

        submitBtn = (Button) findViewById(R.id.submit_btn);
        submitBtn.setBackground(getDrawable("#b07eef", 100));
        assessObj = new PensionAssessBean();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (assessObj.getBasicStatus() != null && assessObj.getMindStatus()!= null && assessObj.getActivityPower() != null &&  assessObj.getAbility() != null ){
                    submit();
                }else {
                    showToast("请先将选项选择完整！");
                }

            }
        });



        optionsContainer1 = (GridLayout) findViewById(R.id.options_container1);
            optionsContainer2 = (GridLayout) findViewById(R.id.options_container2);
        optionsContainer3 = (GridLayout) findViewById(R.id.options_container3);
        optionsContainer4 = (GridLayout) findViewById(R.id.options_container4);

        final GridLayout[] options = {optionsContainer1, optionsContainer2, optionsContainer3, optionsContainer4};

        for (int i = 0; i < options.length; i++) {
            for (int j = 0; j < options[i].getChildCount(); j++) {
                options[i].getChildAt(j).setBackground(getDrawable("#f2f2f2", 10));
                final int finalI = i;
                final int finalJ = j;
                options[i].getChildAt(j).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int k = 0; k < options[finalI].getChildCount(); k++) {
                            TextView textView = (TextView) options[finalI].getChildAt(k);
                            options[finalI].getChildAt(k).setBackground(getDrawable("#f2f2f2", 10));
                            textView.setTextColor(Color.parseColor("#666666"));
                        }
                        TextView textView = (TextView) options[finalI].getChildAt(finalJ);
                        textView.setTextColor(Color.parseColor("#b07eef"));
                        options[finalI].getChildAt(finalJ).setBackground(getDrawable("#33b07eef", 10));

                        //设置对象
                        switch (finalI){
                            //基本情况
                            case 0:
                                assessObj.setBasicStatus(textView.getText().toString());
                                break;
                                //精神状态
                            case 1:
                                assessObj.setMindStatus(textView.getText().toString());
                                break;
                                //活动能力
                            case 2:
                                assessObj.setActivityPower(textView.getText().toString());
                                break;
                                //生活能力
                            case 3:
                                assessObj.setAbility(textView.getText().toString());
                                break;
                        }

                    }
                });
            }
        }

    }

}