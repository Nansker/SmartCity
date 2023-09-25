package com.nansk.smartcity.design.party;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.PartyStudyAdapter;
import com.nansk.smartcity.beans.party.PartyTextBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/22 10:16
 * @description 党员学习
 */

public class PartyStudyActivity extends BaseActivity {

    private LinearLayout categoryBox;
    private RecyclerView bodyContainer;
    private PartyStudyAdapter studyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_study);
        setToolBarTitle("党员学习");
        setToolBarBackground(getResources().getString(R.string.theme_party));
        initView();
        getStudyList();
    }

    /**
     * @Create 2021/10/22 13:44
     * @Role
     * @Param
     */
    private void getStudyList() {
        String data = FileReadUtils.getData(PartyStudyActivity.this, R.raw.party_text);
        List<PartyTextBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<PartyTextBean>>() {
        }.getType());
        studyAdapter.setData(json);
    }

    private void initView() {

        categoryBox = (LinearLayout) findViewById(R.id.category_box);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);


        for (int i = 0; i < categoryBox.getChildCount(); i++) {
            final int finalI = i;
            Button button = (Button) categoryBox.getChildAt(i);
            button.setTextColor(Color.parseColor(getResources().getString(R.string.theme_party)));
            button.setBackground(getDrawable("#ffffff",10,2,getResources().getString(R.string.theme_party)));

            if (i==0){
                button.setBackground( getDrawable(getResources().getString(R.string.theme_party),10));
                button.setTextColor(Color.parseColor("#ffffff"));
            }

            categoryBox.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button) categoryBox.getChildAt(finalI);
                    for (int j = 0; j < categoryBox.getChildCount(); j++) {
                        Button button = (Button) categoryBox.getChildAt(j);
                        button.setTextColor(Color.parseColor(getResources().getString(R.string.theme_party)));
                        button.setBackground(getDrawable("#ffffff",10,2,getResources().getString(R.string.theme_party)));
                    }
                    btn.setBackground( getDrawable(getResources().getString(R.string.theme_party),10));
                    btn.setTextColor(Color.parseColor("#ffffff"));
                }
            });
        }

        studyAdapter = new PartyStudyAdapter(PartyStudyActivity.this);
        bodyContainer.setAdapter(studyAdapter);
        bodyContainer.setLayoutManager(new LinearLayoutManager(PartyStudyActivity.this));
        bodyContainer.addItemDecoration(new DividerItemDecoration(PartyStudyActivity.this,DividerItemDecoration.VERTICAL));

        studyAdapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                PartyTextBean object = (PartyTextBean) obj;
                Intent intent = new Intent(PartyStudyActivity.this, PartyStudyDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("studyObj",object);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}