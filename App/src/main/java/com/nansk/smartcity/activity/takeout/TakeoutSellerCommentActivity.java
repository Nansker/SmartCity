package com.nansk.smartcity.activity.takeout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.takeout.TakeoutCommentAdapter;
import com.nansk.smartcity.beans.takeout.TakeoutSellerCommentBean;
import com.nansk.smartcity.beans.takeout.TakeoutSellerDetailsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 14:17
 * @Description 商家评论
 */

public class TakeoutSellerCommentActivity extends Fragment {
    private View view;

    private Gson gson;
    private TakeoutSellerDetailsBean.DataBean sellerObj;

    private TextView scoreTv;
    private RatingBar scoreBar;
    private RecyclerView bodyContainer;
    private TextView tipsTv;

    public TakeoutSellerCommentActivity(TakeoutSellerDetailsBean.DataBean sellerObj) {
        this.sellerObj = sellerObj;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_takeout_seller_comment, container, false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        scoreTv = (TextView) view.findViewById(R.id.score_tv);
        scoreBar = (RatingBar) view.findViewById(R.id.score_bar);
        bodyContainer = (RecyclerView) view.findViewById(R.id.body_container);

        bodyContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        scoreTv.setText(Double.toString(sellerObj.getScore()));

        scoreBar.setRating((float) sellerObj.getScore());

        gson = new Gson();

        tipsTv = (TextView) view.findViewById(R.id.tips_tv);
    }

    /**
     * @Create 2021/9/22 17:10
     * @Role 获取评论列表
     * @Param
     */
    private void initData() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_COMMENT, "?sellerId=" + sellerObj.getId());
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeoutSellerCommentBean jsonObj = gson.fromJson(response.body().string(), TakeoutSellerCommentBean.class);
                if (jsonObj.getCode() == 200) {
                    List<TakeoutSellerCommentBean.RowsBean> rows = jsonObj.getRows();
                    if (rows.size() > 0) {
                        final TakeoutCommentAdapter adapter = new TakeoutCommentAdapter(getContext(), rows);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tipsTv.setVisibility(View.GONE);
                                bodyContainer.setAdapter(adapter);
                            }
                        });
                    }
                }
            }
        });
    }

}