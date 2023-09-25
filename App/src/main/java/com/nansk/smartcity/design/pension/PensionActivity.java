package com.nansk.smartcity.design.pension;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.PensionOrgListAdapter;
import com.nansk.smartcity.beans.pension.PensionOrgBean;
import com.nansk.smartcity.design.DesignResources;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;
import com.youth.banner.Banner;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 21:16
 * @description 智慧养老
 */

public class PensionActivity extends BaseActivity {

    private ImageButton backBtn;
    private LinearLayout searchBox;
    private Button searchBtn;
    private Banner banner;
    private LinearLayout menuContainer1;
    private GridLayout menuContainer2;
    private RecyclerView recommendContainer;

    private PensionOrgListAdapter adapter;
    private EditText searchEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension);
        setToolBarBackground("#b07eef");
        setToolbarIsShow(false);
        initView();
        initObject();
        getRecommendList();
    }

    /**
     * @Create 2021/10/19 11:43
     * @Role
     * @Param
     */
    private void getRecommendList() {
        String data = FileReadUtils.getData(PensionActivity.this, R.raw.pension_org);
        List<PensionOrgBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<PensionOrgBean>>() {
        }.getType());
        adapter.setData(json);
    }

    /**
     * @Create 2021/10/20 13:01
     * @Role
     * @Param
     */
    private void initObject() {
        backBtn.setColorFilter(Color.parseColor("#ffffff"));
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchBox.setBackground(getDrawable("#26ffffff", 100));
        BannerSetUtils.setBannerStyle(PensionActivity.this, banner);
        banner.setImages(DesignResources.getPensionBannerPagers());
        banner.start();
        recommendContainer.setLayoutManager(new LinearLayoutManager(PensionActivity.this));
        adapter = new PensionOrgListAdapter(PensionActivity.this);
        recommendContainer.setAdapter(adapter);
        recommendContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 10;
                outRect.left = 10;
                outRect.top = 10;
                outRect.bottom = 10;
            }
        });

        adapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                final PensionOrgBean object = (PensionOrgBean) obj;
                Intent intent = new Intent(PensionActivity.this, PensionOrgDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("obj", object);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        for (int i = 0; i < menuContainer1.getChildCount(); i++) {
            final int finalI = i;
            menuContainer1.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            startActivity(new Intent(PensionActivity.this, PensionHealthDataActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(PensionActivity.this, PensionRecordActivity.class));
                            break;
                        case 2:
                            startActivity(new Intent(PensionActivity.this, PensionFocusActivity.class));
                            break;
                    }
                }
            });
        }

        for (int i = 0; i < menuContainer2.getChildCount(); i++) {
            final int finalI = i;
            menuContainer2.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (finalI) {
                        case 0:
                            startActivity(new Intent(PensionActivity.this, PensionQueryOrgActivity.class));
                            break;
                        case 1:
                            startActivity(new Intent(PensionActivity.this, PensionAssessActivity.class));
                            break;
                        case 2:
                            break;
                    }
                }
            });
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard(searchEt);
                if (!searchEt.getText().toString().trim().equals("")){
                    Intent intent = new Intent(PensionActivity.this, PensionQueryOrgActivity.class);
                    intent.putExtra("searchObj",searchEt.getText().toString().trim());
                    startActivity(intent);
                    searchEt.setText("");
                }else {
                    showToast("请先输入要查找的机构名称！");
                }
            }
        });

    }

    private void initView() {

        backBtn = (ImageButton) findViewById(R.id.back_btn);
        searchBox = (LinearLayout) findViewById(R.id.search_box);
        searchBtn = (Button) findViewById(R.id.search_btn);
        banner = (Banner) findViewById(R.id.banner);
        menuContainer1 = (LinearLayout) findViewById(R.id.menu_container1);
        menuContainer2 = (GridLayout) findViewById(R.id.menu_container2);
        recommendContainer = (RecyclerView) findViewById(R.id.recommend_container);

        searchEt = (EditText) findViewById(R.id.search_et);
    }
}