package com.nansk.smartcity.design.community;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.CommunityCarAdapter;
import com.nansk.smartcity.beans.community.CommunityCarBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 15:42
 * @description
 */

public class CommunityCarManageActivity extends BaseActivity {

    private RecyclerView bodyContainer;
    private LinearLayout addBox;

    private CommunityCarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_community_car_manage);
        setToolBarTitle("我的车辆");

        initView();
        getCarList();
    }

    /**
     * @Create 2021/10/18 16:49
     * @Role
     * @Param
     */
    private void getCarList() {
        String record = (String) SharedPreferencesUtils.getRecord(CommunityCarManageActivity.this, "community_car", "");

        if (!record.equals("")) {
            List<CommunityCarBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<CommunityCarBean>>() {
            }.getType());
            if (json.size()>0){
                adapter.setData(json);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 121){
                boolean isUpdate = data.getBooleanExtra("isUpdate", false);
                if (isUpdate){
                    getCarList();
            }
        }
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        addBox = (LinearLayout) findViewById(R.id.add_box);

        addBox.setBackground(getDrawable("#f2f2f2",20,4,"#E1E1E1"));
        addBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CommunityCarManageActivity.this,CommunityCarEditActivity.class),121);
            }
        });

        bodyContainer.setLayoutManager(new LinearLayoutManager(CommunityCarManageActivity.this));
        adapter = new CommunityCarAdapter(CommunityCarManageActivity.this);
        bodyContainer.setAdapter(adapter);

        adapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                CommunityCarBean object = (CommunityCarBean) obj;
                Intent intent = new Intent(CommunityCarManageActivity.this, CommunityCarEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj",object);
                intent.putExtras(bundle);
                startActivityForResult(intent,121);
            }
        });

        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

                outRect.top = 15;
                outRect.bottom = 15;
                outRect.left = 30;
                outRect.right = 30;
            }
        });
    }
}