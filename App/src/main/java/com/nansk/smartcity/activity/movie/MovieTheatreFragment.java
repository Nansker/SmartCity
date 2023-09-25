package com.nansk.smartcity.activity.movie;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.movie.MovieTheatreListAdapter;
import com.nansk.smartcity.beans.movie.MovieTheatreListBean;
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
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 08:40
 * @description 影院
 */

public class MovieTheatreFragment extends Fragment implements SearchView.OnQueryTextListener {

    private TextView placeTv;
    private LinearLayout searchBox;
    private SearchView searchView;
    private Button cancelBtn;
    private RecyclerView theatreContainer;

    private View view;
    private Handler handler;
    private TextView tipsTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie_theatre, container, false);
        initView();
        getTheatreList(0, "");

        return view;
    }

    /**
     * @Create 2021/10/13 12:34
     * @Role 获取影院列表
     * @Param action 获取或者搜索影院操作 0=获取全部影院 1=根据名称搜索影院
     */
    private void getTheatreList(final int action, String name) {
        String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_THEATRE, "list?name=" + name);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MovieTheatreListBean json = MyApplication.gson.fromJson(response.body().string(), MovieTheatreListBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (json.getCode() == 200) {
                            if (action == 0) {
                                if (json.getTotal() > 0) {
                                    tipsTv.setVisibility(View.GONE);
                                    List<MovieTheatreListBean.RowsBean> rows = json.getRows();
                                    MovieTheatreListAdapter adapter = new MovieTheatreListAdapter(getContext(), rows);
                                    theatreContainer.setAdapter(adapter);
                                }
                            } else if (action == 1) {
                                if (json.getTotal() > 0) {
                                    List<MovieTheatreListBean.RowsBean> rows = json.getRows();
                                    MovieTheatreListAdapter adapter = new MovieTheatreListAdapter(getContext(), rows);
                                    theatreContainer.setAdapter(adapter);
                                } else {
                                    Toast.makeText(getContext(), "没有查找到相关影院", Toast.LENGTH_SHORT).show();
                                    getTheatreList(0, "");
                                }
                            }


                        }
                    }
                });
            }
        });
    }

    private void initView() {
        handler = new Handler();

        tipsTv = (TextView) view.findViewById(R.id.tips_tv);
        placeTv = (TextView) view.findViewById(R.id.place_tv);
        searchBox = (LinearLayout) view.findViewById(R.id.search_box);
        searchView = (SearchView) view.findViewById(R.id.search_view);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        theatreContainer = (RecyclerView) view.findViewById(R.id.theatre_container);

        cancelBtn.setVisibility(View.GONE);
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

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowMangerUtils.closeKeyboard(getContext(), searchView);
                searchView.setQuery("", false);
                searchView.clearFocus();
            }
        });

        theatreContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        theatreContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 30;
                outRect.right = 30;
                outRect.top = 10;
                outRect.bottom = 10;
            }
        });

        GradientDrawable searchBck = new GradientDrawable();
        searchBck.setCornerRadius(100);
        searchBck.setColor(Color.parseColor("#f2f2f2"));
        searchBox.setBackground(searchBck);

        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        WindowMangerUtils.closeKeyboard(getContext(), searchView);
        searchView.clearFocus();
        getTheatreList(1,query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}