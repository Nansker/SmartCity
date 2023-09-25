package com.nansk.smartcity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.PrimaryServiceAdapter;
import com.nansk.smartcity.adapter.SecondaryServiceAdapter;
import com.nansk.smartcity.base.BaseAdapter;
import com.nansk.smartcity.base.BaseViewHolder;
import com.nansk.smartcity.beans.service.ServiceJsonObj;
import com.nansk.smartcity.beans.service.ServiceJsonRows;
import com.nansk.smartcity.dialog.ServiceSearchResultDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 13:06
 * @Description
 */

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/14 10:23
 * @Description 全部服务
 */

public class ServiceFragment extends Fragment implements SearchView.OnQueryTextListener {
    private View view;
    private Gson gson;

    private LinearLayout searchContainer;
    private SearchView searchView;
    private Button cancelBtn;
    private RecyclerView primaryMenu;
    private RecyclerView secondaryMenu;

    private String[] primaryNames;

    private BaseAdapter<ServiceJsonRows> baseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_service, container, false);
        initView();
        initObject();
        return view;
    }


    private void initObject() {
        primaryMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        secondaryMenu.setLayoutManager(new LinearLayoutManager(getContext()));

        //设置菜单数据适配器，添加分割线
        final PrimaryServiceAdapter primaryClassAdapter = new PrimaryServiceAdapter(getContext(), primaryNames);
        primaryMenu.setAdapter(primaryClassAdapter);
        primaryMenu.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        secondaryMenu.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //设置一级分类菜单点击事件
        primaryClassAdapter.setOnItemListener(new PrimaryServiceAdapter.OnItemListener() {
            @Override
            public void onClick(int position) {
                //更改当前Item背景色
                primaryClassAdapter.setPosition(position);
                primaryClassAdapter.notifyDataSetChanged();
            }
        });

        setSecondaryClassMenu();
        gson = MyApplication.gson;

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
                WindowMangerUtils.closeKeyboard(getContext(), searchView);
                cancelBtn.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        searchContainer = (LinearLayout) view.findViewById(R.id.search_container);
        searchView = (SearchView) view.findViewById(R.id.search_view);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        primaryMenu = (RecyclerView) view.findViewById(R.id.primary_menu);
        secondaryMenu = (RecyclerView) view.findViewById(R.id.secondary_menu);

        cancelBtn.setVisibility(View.GONE);
        primaryNames = getResources().getStringArray(R.array.service_primary_class);
    }

    /**
     * @Create 2021/9/10 23:19
     * @Role 设置二级分类菜单
     * @Param
     */
    private void setSecondaryClassMenu() {
        //根据服务类别分类的组
        final List<List<ServiceJsonRows>> serviceGroup = new ArrayList<>();

        for (int i = 0; i < primaryNames.length; i++) {
            String url = ConnectInfo.getUrl(ConnectInfo.SERVICE_LIST, "?serviceType=" + primaryNames[i]);

            OkHttpUtil.doGet(url,"", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ServiceJsonObj jsonObj = gson.fromJson(response.body().string(), new TypeToken<ServiceJsonObj>() {
                    }.getType());
                    List<ServiceJsonRows> rows = jsonObj.getRows();
                    serviceGroup.add(rows);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SecondaryServiceAdapter adapter = new SecondaryServiceAdapter(getContext(), primaryNames, serviceGroup);
                            secondaryMenu.setAdapter(adapter);
                        }
                    });
                }
            });
        }

    }

    /**
     * @Create 2021/9/13 10:08
     * @Role 搜索服务、打开搜索结果对话框
     * @Param
     */
    private void searchService(String query) {
        final String url = ConnectInfo.getUrl(ConnectInfo.SERVICE_LIST, "?serviceName=" + query.trim());

        OkHttpUtil.doGet(url,"", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ServiceJsonObj jsonObj = gson.fromJson(response.body().string(), new TypeToken<ServiceJsonObj>() {
                }.getType());
                final List<ServiceJsonRows> rows = jsonObj.getRows();
                showSearchResult(rows);
            }
        });
    }

    /**
     * @Create 2021/9/13 10:46
     * @Role 显示搜索结果
     * @Param
     */
    private void showSearchResult(List<ServiceJsonRows> rows) {
        ServiceSearchResultDialog resultDialog = new ServiceSearchResultDialog(rows);
        resultDialog.show(getFragmentManager(), "dialog");
    }

    //搜索框提交监听事件
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchService(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}