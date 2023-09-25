package com.nansk.smartcity.activity.metro;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.metro.MetroStepLinesAdapter;
import com.nansk.smartcity.beans.BannerBean;
import com.nansk.smartcity.beans.metro.MetroStepInfoBean;
import com.nansk.smartcity.beans.metro.MetroStepLisBean;
import com.nansk.smartcity.utils.BannerLinkListener;
import com.nansk.smartcity.utils.BannerSetUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.WindowMangerUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 11:14
 * @description 站点详情
 */

public class MetroStepDetailsActivity extends BaseActivity {

    private Banner banner;

    private MetroStepLisBean.DataBean stepObj;
    private TextView stepNameTv;
    private RecyclerView bodyContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_metro_step_details);
        setToolBarTitle("站点详情");
        initView();
        initObject();
        initBanner();
        fillData();
    }

    /**
     * @Create 2021/10/11 13:23
     * @Role 获取数据
     * @Param
     */
    private void fillData() {

        //获取当前站点全部所属线路ID
        List<MetroStepLisBean.DataBean.LinesBean> lines = stepObj.getLines();
        ArrayList<Integer> lineIds = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            lineIds.add(lines.get(i).getLineId());
        }

        StringBuffer sb = new StringBuffer();

        for (int id : lineIds) {
            sb.append(id).append(",");
        }

        String ids = sb.deleteCharAt(sb.length() - 1).toString();

        String url = ConnectInfo.getUrl(ConnectInfo.METRO_LINE, "?lineIds=" + ids + "&name=" + stepObj.getName());

        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroStepInfoBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroStepInfoBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            MetroStepInfoBean.DataBean data = jsonObj.getData();

                            MetroStepLinesAdapter adapter = new MetroStepLinesAdapter(MetroStepDetailsActivity.this, data);
                            bodyContainer.setAdapter(adapter);
                        } else {
                            Toast.makeText(MetroStepDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    /**
     * @Create 2021/10/6 13:09
     * @Role 设置轮播图
     * @Param
     */
    private void initBanner() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_ROTATION, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final BannerBean jsonObj = MyApplication.gson.fromJson(response.body().string(), BannerBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (jsonObj.getCode() == 200) {
                            final List<BannerBean.RowsBean> rows = jsonObj.getRows();
                            List<String> pagers = new ArrayList<>();
                            List<String> titles = new ArrayList<>();
                            for (int i = 0; i < rows.size(); i++) {
                                pagers.add(MyApplication.IP + rows.get(i).getAdvImg());
                                titles.add(rows.get(i).getAdvTitle());
                            }
                            banner.setImages(pagers);
                            banner.setBannerTitles(titles);
                            banner.start();
                            banner.setOnBannerListener(new OnBannerListener() {
                                @Override
                                public void OnBannerClick(int i) {
                                    BannerLinkListener.setBannerLink(MetroStepDetailsActivity.this, banner, rows);
                                }
                            });
                        } else {
                            Toast.makeText(MetroStepDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private void initObject() {

        bodyContainer.setLayoutManager(new LinearLayoutManager(MetroStepDetailsActivity.this));

        BannerSetUtils.setBannerStyle(MetroStepDetailsActivity.this, banner);

        int windowHeight = WindowMangerUtils.getWindowSize(MetroStepDetailsActivity.this, 1);
        ViewGroup.LayoutParams bannerLayoutParams = banner.getLayoutParams();
        bannerLayoutParams.height = windowHeight / 4;
        banner.setLayoutParams(bannerLayoutParams);

        stepObj = (MetroStepLisBean.DataBean) getIntent().getSerializableExtra("stepObj");
        stepNameTv.setText(stepObj.getName());
    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        stepNameTv = (TextView) findViewById(R.id.stepName_tv);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
    }
}