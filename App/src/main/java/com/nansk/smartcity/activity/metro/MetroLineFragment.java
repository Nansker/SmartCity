package com.nansk.smartcity.activity.metro;

import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.metro.MetroCityImgBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.view.ImageTouchView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 11:35
 * @description 城市地铁-线路图
 */

public class MetroLineFragment extends Fragment {
    private View view;

    private Handler handler;
    private ImageTouchView imageIv;
    private LinearLayout floatBar;

    private int[] barIcons;
    private String[] barNames;
    private TextView loadingBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_metro_line, container, false);
        initView();
        getCity();
        initFloatBar();
        return view;
    }

    /**
     * @Create 2021/10/9 16:01
     * @Role 设置浮动功能栏
     * @Param
     */
    private void initFloatBar() {
        for (int i = 0; i < 3; i++) {
            View view = getBarItemView(i);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setBarAction(finalI);
                }
            });
            floatBar.addView(view);
        }

    }

    /**
     * @Create 2021/10/9 16:51
     * @Role 悬浮工具栏点击事件
     * @Param
     */
    private void setBarAction(int i){
        switch (i){
            //打开图例
            case 0:
                break;
                //切换语言
            case 1:
                break;
                //打开线路图
            case 2:
                break;
        }
    }

    private View getBarItemView(int i) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_service_item, null);
        ImageView icon = view.findViewById(R.id.icon_iv);
        TextView name = view.findViewById(R.id.name_tv);
        icon.setImageResource(barIcons[i]);
        name.setText(barNames[i]);
        name.setTextSize(13);
        name.setTextColor(Color.parseColor("#333333"));
        icon.setColorFilter(Color.parseColor("#ca062c"));

        ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
        layoutParams.width = 70;
        layoutParams.height = 70;
        icon.setLayoutParams(layoutParams);

        return view;
    }

    /**
     * @Create 2021/10/6 22:46
     * @Role 获取城市地铁线路总览图
     * @Param
     */
    private void getCity() {
        String url = ConnectInfo.getUrl(ConnectInfo.METRO_CITY, "");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final MetroCityImgBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroCityImgBean.class);


                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            MetroCityImgBean.DataBean data = jsonObj.getData();
                            String imgUrl = MyApplication.getIP(getContext()) + data.getImgUrl();
                            Glide.with(getContext()).load(imgUrl).into(imageIv);
                        } else {
                            Toast.makeText(getContext(), jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    private void initView() {
        handler = new Handler();
        imageIv = (ImageTouchView) view.findViewById(R.id.image_iv);
        floatBar = (LinearLayout) view.findViewById(R.id.float_bar);
        loadingBtn = (TextView) view.findViewById(R.id.loading_btn);

        barIcons = new int[]{R.drawable.metro_tuli, R.drawable.metro_en, R.drawable.metro_line};
        barNames = new String[]{"图例", "EN/中文", "线路"};

        //
        loadingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Matrix matrix = new Matrix();
                matrix.postScale((float)1.0,(float)1.0);
                imageIv.setImageMatrix(matrix);

                getCity();
            }
        });
    }
}