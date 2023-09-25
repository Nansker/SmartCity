package com.nansk.smartcity.activity.metro;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.metro.MetroCodeInfoBean;
import com.nansk.smartcity.dialog.MetroRemoveCardDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 11:36
 * @description 城市地铁-乘车码
 */

public class MetroCardFragment extends Fragment {
    private View view;
    private TextView titleTv;
    private ImageView imageIv;
    private Button handleBtn;

    private Handler handler;
    private TextView tipsTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_metro_code, container, false);
        initView();
        getCardInfo();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCardInfo();
    }

    private void getCardInfo() {
        //判断用户是否登录
        if (MyApplication.isLogin(getContext())){
            String url = ConnectInfo.getUrl(ConnectInfo.METRO_CARD, "");
            OkHttpUtil.doGet(url, MyApplication.getToken(getContext()), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final MetroCodeInfoBean jsonObj = MyApplication.gson.fromJson(response.body().string(), MetroCodeInfoBean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200 && jsonObj.getData() != null) {
                                imageIv.setImageResource(R.drawable.metro_code);
                                tipsTv.setVisibility(View.GONE);
                                handleBtn.setText("注销此卡");
                                handleBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        removeCard(jsonObj.getData());
                                    }
                                });
                            } else {
                                tipsTv.setText(jsonObj.getMsg());
                                imageIv.setImageResource(R.drawable.metro_card);
                                handleBtn.setText("领取乘车码");        handleBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (MyApplication.isLogin(getContext())){
                                            Intent intent = new Intent(getContext(), MetroGetTermsActivity.class);
                                            startActivity(intent);
                                        }else {
                                            Intent intent = new Intent(getContext(), LoginActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }
                    });

                }
            });
        }else {
            tipsTv.setText("用户未登录！");
        }

    }

    /**
     * @Create 2021/10/6 21:08
     * @Role 注销乘车卡卡
     * @Param
     */
    private void removeCard(final MetroCodeInfoBean.DataBean cardObj) {
        final MetroRemoveCardDialog dialog = new MetroRemoveCardDialog();
        dialog.DialogCallBack(new MetroRemoveCardDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(TextView userNameTv, TextView cardNumTv, TextView idCardTv, Button confirmBtn) {
                userNameTv.setText(cardObj.getUserName());
                cardNumTv.setText(cardObj.getCardNum());
                idCardTv.setText(cardObj.getIdCard().substring(0, 2) + "************" + cardObj.getIdCard().substring(cardObj.getIdCard().length()-4));

                confirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String url = ConnectInfo.getUrl(ConnectInfo.METRO_CARD, "/"+cardObj.getId());
                        OkHttpUtil.delete(url, MyApplication.getToken(getContext()),"", new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, final Response response) throws IOException {
                                final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                   if (jsonObj.getCode() == 200){
                                       getCardInfo();
                                       tipsTv.setVisibility(View.VISIBLE);

                                       handler.postDelayed(new Runnable() {
                                           @Override
                                           public void run() {
                                               tipsTv.setText("此乘车卡已经注销");
                                           }
                                       }, 600);

                                       Toast.makeText(getContext(), "乘车卡已成功注销！", Toast.LENGTH_SHORT).show();
                                       dialog.dismiss();
                                   }else {
                                       Toast.makeText(getContext(), jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                                   }
                                    }
                                });

                            }
                        });
                    }
                });

            }
        });
        dialog.show(getFragmentManager(),"dialog");
    }


    private void initView() {
        titleTv = (TextView) view.findViewById(R.id.title_tv);
        imageIv = (ImageView) view.findViewById(R.id.image_iv);
        handleBtn = (Button) view.findViewById(R.id.handle_btn);
        tipsTv = (TextView) view.findViewById(R.id.tips_tv);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        gradientDrawable.setColor(Color.parseColor("#ca062c"));
        handleBtn.setBackground(gradientDrawable);

        handler = new Handler();

    }

}