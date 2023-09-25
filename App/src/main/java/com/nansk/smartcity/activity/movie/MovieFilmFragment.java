package com.nansk.smartcity.activity.movie;

import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.movie.MovieFilmListAdapter;
import com.nansk.smartcity.beans.movie.MovieFilmListBean;
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
 * @create 2021/10/13 08:42
 * @description 影片
 */

public class MovieFilmFragment extends Fragment implements SearchView.OnQueryTextListener {

    private TextView placeTv;
    private LinearLayout searchBox;
    private SearchView searchView;
    private Button cancelBtn;
    private RecyclerView filmContainer;
    private TextView tipsTv;

    private View view;
    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_movie_film, container, false);
        initView();
        getFilmList();
        return view;
    }

    /**
     * @Create 2021/10/13 14:12
     * @Role 获取影片列表
     * @Param
     */
    private void getFilmList() {
            String url = ConnectInfo.getUrl(ConnectInfo.MOVIE_FILM, "list");
            OkHttpUtil.doGet(url, "", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final MovieFilmListBean json = MyApplication.gson.fromJson(response.body().string(), MovieFilmListBean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (json.getCode() == 200){
                                if (json.getTotal()>0){
                                    tipsTv.setVisibility(View.GONE);
                                    List<MovieFilmListBean.RowsBean> rows = json.getRows();
                                    MovieFilmListAdapter adapter = new MovieFilmListAdapter(getContext(), rows);
                                    filmContainer.setAdapter(adapter);
                                    adapter.setOnItemCallBack(new MovieFilmListAdapter.OnItemCallBack() {
                                        @Override
                                        public void OnItemCallBack(int position, MovieFilmListBean.RowsBean obj) {
                                            Intent intent = new Intent(getContext(), MovieFilmDetailsActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("filmId",obj.getId());
                                            intent.putExtras(bundle);
                                            startActivity(intent);
                                        }
                                    });
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
        filmContainer = (RecyclerView) view.findViewById(R.id.film_container);

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

        filmContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        filmContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
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
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}