package com.nansk.smartcity.design.party;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.adapter.design.PartyPressListAdapter;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.party.PartyTextBean;
import com.nansk.smartcity.utils.FileReadUtils;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/21 19:34
 * @description 党建动态
 */

public class PartyPressActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabMenu;
    private RecyclerView bodyContainer;

    private PartyPressListAdapter pressListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_press);
        setToolBarTitle("党建动态");
        setToolBarBackground(getResources().getString(R.string.theme_party));
        initView();

        fillData();
    }

    /**
     * @Create 2021/10/21 20:49
     * @Role
     * @Param
     */
    private void fillData() {
        String data = FileReadUtils.getData(PartyPressActivity.this, R.raw.party_text);
        List<PartyTextBean> json = MyApplication.gson.fromJson(data, new TypeToken<List<PartyTextBean>>() {
        }.getType());
        pressListAdapter.setData(json);
    }

    private void initView() {
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        String[] category = getResources().getStringArray(R.array.party_press_category);

        tabMenu.addOnTabSelectedListener(this);
        for (int i = 0; i < category.length; i++) {
            tabMenu.addTab(tabMenu.newTab().setText(category[i]));
        }

        bodyContainer.setLayoutManager(new LinearLayoutManager(PartyPressActivity.this));
        pressListAdapter = new PartyPressListAdapter(PartyPressActivity.this);
        bodyContainer.setAdapter(pressListAdapter);
        bodyContainer.addItemDecoration(new DividerItemDecoration(PartyPressActivity.this,DividerItemDecoration.VERTICAL));

        pressListAdapter.setOnItemCallBack(new OnItemCallBack() {
            @Override
            public void OnItemCallBack(int position, Object obj) {
                PartyTextBean object = (PartyTextBean) obj;
                Intent intent = new Intent(PartyPressActivity.this, PartyPressDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pressObj",object);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}