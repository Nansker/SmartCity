package com.nansk.smartcity.design.party;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.PartyAdviceListAdapter;
import com.nansk.smartcity.beans.party.PartyAdviceBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 09:44
 * @description
 */

public class PartyAdviceActivity extends BaseActivity {
    private int PUBLISH_ADVICE = 121;
    private int UPDATE_ADVICE = 120;

    private RecyclerView bodyContainer;
    private PartyAdviceListAdapter adviceAdapter;
    private Button publishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_advice);
        setToolBarTitle("建言献策");
        setToolBarBackground(getResources().getString(R.string.theme_party));
        initView();
        getAdviceList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==UPDATE_ADVICE){
            if (data != null){
                if (data.getBooleanExtra("isUpdate",false)){
                    getAdviceList();
                }
            }
        }
    }

    /**
     * @Create 2021/10/24 10:40
     * @Role
     * @Param
     */
    private void getAdviceList() {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        String format = time.format(date);

        List<PartyAdviceBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PartyAdviceBean adviceBean = new PartyAdviceBean();
            adviceBean.setTitle("留言测试标题"+(i+1));
            adviceBean.setContent("留言测试内容");
            adviceBean.setName("南山客");
            adviceBean.setDate(format);
            list.add(adviceBean);
        }

        String record = (String) SharedPreferencesUtils.getRecord(PartyAdviceActivity.this, "party_advice", "");

        if (!record.equals("")) {
            List<PartyAdviceBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<PartyAdviceBean>>() {

            }.getType());
            for (int i = 0; i < json.size(); i++) {
                PartyAdviceBean advice = json.get(i);
                list.add(0, advice);
                adviceAdapter.setData(list);
            }
        } else {
            adviceAdapter.setData(list);
        }
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        publishBtn = (Button) findViewById(R.id.publish_btn);

        adviceAdapter = new PartyAdviceListAdapter(PartyAdviceActivity.this);
        bodyContainer.setLayoutManager(new LinearLayoutManager(PartyAdviceActivity.this));
        bodyContainer.setAdapter(adviceAdapter);

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
            }
        });

        publishBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party),100));

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PartyAdviceActivity.this,PartyAdvicePublishActivity.class),PUBLISH_ADVICE);
            }
        });

    }


}