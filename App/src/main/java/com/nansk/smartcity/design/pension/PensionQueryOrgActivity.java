package com.nansk.smartcity.design.pension;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.traffic.PensionOrgQueryAdapter;
import com.nansk.smartcity.beans.pension.PensionOrgBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 19:38
 * @description  机构查询
 */

public class PensionQueryOrgActivity extends BaseActivity {
    private PensionOrgQueryAdapter adapter;
    private RecyclerView bodyContainer;
    private LinearLayout searchBox;
    private ImageView searchIv;
    private Button searchBtn;
    private EditText searchEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension_query_org);
        setToolBarBackground("#b07eef");
        setToolBarTitle("养老机构查询");
        initView();
        getRecommendList();
    }

    /**
     * @Create 2021/10/19 11:43
     * @Role
     * @Param
     */
    private void getRecommendList() {
        String data = FileReadUtils.getData(PensionQueryOrgActivity.this, R.raw.pension_org);
        List<PensionOrgBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<PensionOrgBean>>() {
        }.getType());
        adapter.setData(json);
    }

    private void initView() {
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        searchBox = (LinearLayout) findViewById(R.id.search_box);
        searchIv = (ImageView) findViewById(R.id.search_iv);
        searchBtn = (Button) findViewById(R.id.search_btn);
        searchEt = (EditText) findViewById(R.id.search_et);

        searchIv.setColorFilter(Color.parseColor("#99B07EEF"));

        searchBox.setBackground(getDrawable("#f2f2f2", 100, 4, "#b07eef"));

        bodyContainer.setLayoutManager(new LinearLayoutManager(PensionQueryOrgActivity.this));
        adapter = new PensionOrgQueryAdapter(PensionQueryOrgActivity.this);
        bodyContainer.setAdapter(adapter);
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 15;
                outRect.left = 15;
                outRect.top = 15;
                outRect.bottom = 15;
            }
        });


        adapter.setMakeCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, final Object obj) {
                final PensionOrgBean object = (PensionOrgBean) obj;
                showToast("成功预约到" + object.getName());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(PensionQueryOrgActivity.this, PensionOrgDetailsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("obj", object);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }, 1200);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard(searchEt);
                if (!searchEt.getText().toString().trim().equals("")){
                    showToast("推荐机构已更新！");
                    getRecommendList();
                }else {
                    showToast("请先输入要查找的机构名称！");
                }
            }
        });

    }
}