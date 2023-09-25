package com.nansk.smartcity.activity.press;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.base.BaseAdapter;
import com.nansk.smartcity.base.BaseViewHolder;
import com.nansk.smartcity.beans.press.PressListBean;
import com.nansk.smartcity.model.impl.OnItemClickListener;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 16:22
 * @Description
 */

public class PressSearchActivity extends BaseActivity implements SearchView.OnQueryTextListener {


    private LinearLayout searchContainer;
    private SearchView searchView;
    private Button cancelBtn;
    private TextView tipsTv;
    private RecyclerView bodyContainer;

    private String query;
    private Gson gson;

    private BaseAdapter<PressListBean.RowsBean> pressListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_press_search);
        setToolBarTitle("新闻搜索");
        initView();
        initObject();

    }

    private void initView() {

        searchContainer = (LinearLayout) findViewById(R.id.search_container);
        searchView = (SearchView) findViewById(R.id.search_view);
        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);

    }

    private void initObject() {

        cancelBtn.setVisibility(View.GONE);
        //        搜索框焦点获取监听
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cancelBtn.setVisibility(View.VISIBLE);
                } else {
                    cancelBtn.setVisibility(View.GONE);
                }
            }
        });

        searchView.setOnQueryTextListener(this);

        //搜索框取消按钮点击事件
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索框清除内容和焦点
                searchView.setQuery("", false);
                searchView.clearFocus();
                WindowMangerUtils.closeKeyboard(PressSearchActivity.this, searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });

        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        gson = new Gson();

        if (query != "") {
            searchView.setQuery(query, true);
        } else {
            initPressList("");
        }

        bodyContainer.setLayoutManager(new LinearLayoutManager(PressSearchActivity.this));
        bodyContainer.addItemDecoration(new DividerItemDecoration(PressSearchActivity.this, DividerItemDecoration.VERTICAL));

        //        新闻列表
        pressListAdapter = new BaseAdapter<PressListBean.RowsBean>(PressSearchActivity.this, R.layout.item_press_list) {
            @Override
            protected void onConvert(BaseViewHolder holder, PressListBean.RowsBean item, int position) {
                holder.getTextView(R.id.title_tv).setText(item.getTitle());
                Glide.with(PressSearchActivity.this).load(MyApplication.getIP(PressSearchActivity.this) + item.getCover()).into(holder.getImageView(R.id.cover_iv));
                holder.getTextView(R.id.time_tv).setText(item.getPublishDate());
                holder.getTextView(R.id.readNum_tv).setText("阅读人数：" + item.getReadNum());
                holder.getTextView(R.id.likeNum_tv).setText("点赞人数：" + item.getLikeNum());
            }
        };

        pressListAdapter.setOnItemCallBack(new OnItemClickListener<PressListBean.RowsBean>() {
            @Override
            public void OnItemCallBack(int position, PressListBean.RowsBean obj) {
                super.OnItemCallBack(position, obj);
                Intent intent = new Intent(PressSearchActivity.this, PressDetailsActivity.class);
                intent.putExtra("pressId", obj.getId());
                startActivity(intent);
            }
        });

    }

    /**
     * @Create 2021/9/9 16:21
     * @Role 获取新闻列表
     * @Param
     */
    public void initPressList(String condition) {
        String url = ConnectInfo.getUrl(ConnectInfo.PRESS_LIST, "?title=" + condition);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PressListBean pressJsonObj = gson.fromJson(response.body().string(), PressListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (pressJsonObj.getCode() == 200) {
                            List<PressListBean.RowsBean> rows = pressJsonObj.getRows();
                            if (rows.size() > 0) {
                                tipsTv.setVisibility(View.GONE);
                                pressListAdapter.setData(rows);
                                bodyContainer.setAdapter(pressListAdapter);
                            }
                        } else {
                            showToast(pressJsonObj.getMsg());
                        }
                    }
                });

            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        initPressList(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}