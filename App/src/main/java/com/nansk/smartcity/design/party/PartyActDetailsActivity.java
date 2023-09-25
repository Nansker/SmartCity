package com.nansk.smartcity.design.party;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.DesignCommentAdapter;
import com.nansk.smartcity.beans.DesignCommentBean;
import com.nansk.smartcity.beans.party.PartyTextBean;
import com.nansk.smartcity.dialog.CommentDialog;
import com.nansk.smartcity.utils.BannerLoader;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/22 10:49
 * @description 活动详情、报名、评论
 */

public class PartyActDetailsActivity extends BaseActivity {
    private PartyTextBean object;
    private Banner banner;
    private TextView nameTv;
    private TextView contentTv;
    private TextView commentBtn;
    private RecyclerView commentContainer;
    private Button applyBtn;

    private DesignCommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_act_details);
        setToolBarTitle("活动详情");
        initView();
        initObject();
        getCommentList();
    }

    //活动报名
    @SuppressLint("InflateParams")
    private void apple() {
        if (!applyBtn.getText().toString().equals("已报名")){

        final AlertDialog dialog = new AlertDialog.Builder(PartyActDetailsActivity.this).create();
        View view = LayoutInflater.from(PartyActDetailsActivity.this).inflate(R.layout.dialog_tips_model, null);
        TextView titleTv = view.findViewById(R.id.title_tv);
        TextView tipsTv = view.findViewById(R.id.tips_tv);
        Button confirmBtn = view.findViewById(R.id.confirm_btn);
        titleTv.setText("活动报名");
        tipsTv.setText(object.getTitle());
        confirmBtn.setText("确认报名");
        confirmBtn.setTextColor(Color.parseColor(getResources().getString(R.string.theme_party)));

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyBtn.setBackground(getDrawable("#a6F23D3D",100));
                applyBtn.setText("已报名");
                dialog.dismiss();
                showToast("活动报名成功！");
            }
        });
        dialog.setView(view);
        dialog.show();
        }else {
            showToast("该活动已经报名~");
        }
    }
    /**
     * @Create 2021/10/22 10:03
     * @Role
     * @Param
     */
    private void comment(String body) {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        String format = time.format(date);

        DesignCommentBean commentBean = new DesignCommentBean();
        commentBean.setContent(body);
        commentBean.setNickName(UserInfoUtils.getUserInfo(PartyActDetailsActivity.this).getNickName());
        commentBean.setTime(format);
        List<DesignCommentBean> list = new ArrayList<>();
        list.add(commentBean);

        String record = (String) SharedPreferencesUtils.getRecord(PartyActDetailsActivity.this, "party_activity_comment", "");

        if (!record.equals("")) {
            List<DesignCommentBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            for (int i = 0; i < json.size(); i++) {
                DesignCommentBean designCommentBean = json.get(i);
                list.add(designCommentBean);
            }
        }
        SharedPreferencesUtils.addRecord(PartyActDetailsActivity.this, "party_activity_comment", MyApplication.gson.toJson(list));
        getCommentList();
        showToast("评论成功！");
    }

    /**
     * @Create 2021/10/22 9:41
     * @Role
     * @Param
     */
    private void getCommentList() {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        String format = time.format(date);
        DesignCommentBean commentBean = new DesignCommentBean();
        commentBean.setContent("评论测试");
        commentBean.setNickName("南山客");
        commentBean.setTime(format);

        List<DesignCommentBean> list = new ArrayList<>();
        list.add(commentBean);

        String record = (String) SharedPreferencesUtils.getRecord(PartyActDetailsActivity.this, "party_activity_comment", "");

        if (!record.equals("")) {
            List<DesignCommentBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            for (int i = 0; i < json.size(); i++) {
                DesignCommentBean designCommentBean = json.get(i);
                list.add(0, designCommentBean);
                commentAdapter.setData(list);
            }
        } else {
            commentAdapter.setData(list);
        }
    }

    /**
     * @Create 2021/10/22 11:25
     * @Role
     * @Param
     */
    private void initObject() {
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.party_press1);
        images.add(R.drawable.party_press2);
        images.add(R.drawable.party_press3);
        images.add(R.drawable.party_press4);

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.isAutoPlay(false);
        banner.setImageLoader(new BannerLoader());
        banner.setImages(images);
        banner.start();

        object = (PartyTextBean) getIntent().getSerializableExtra("obj");

        commentBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party), 100));
        applyBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party), 100));

        nameTv.setText(object.getTitle());
        contentTv.setText(object.getContent());

        commentContainer.setLayoutManager(new LinearLayoutManager(PartyActDetailsActivity.this));
        commentAdapter = new DesignCommentAdapter(PartyActDetailsActivity.this);
        commentContainer.setAdapter(commentAdapter);

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommentDialog();
            }
        });

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apple();
            }
        });
    }


    private void openCommentDialog() {
        final CommentDialog dialog = new CommentDialog("活动留言", getDrawable(getResources().getString(R.string.theme_party), 100), false);
        dialog.DialogCallBack(new CommentDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(TextView dialogTitle, EditText bodyEt, RatingBar scoreBar, Button submitBtn) {
                dialog.dismiss();
                comment(bodyEt.getText().toString().trim());
                getCommentList();
            }
        });
        dialog.show(getSupportFragmentManager(),"dialog");

    }

    private void initView() {
        banner = (Banner) findViewById(R.id.banner);
        nameTv = (TextView) findViewById(R.id.name_tv);
        contentTv = (TextView) findViewById(R.id.content_tv);
        commentBtn = (TextView) findViewById(R.id.comment_btn);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        applyBtn = (Button) findViewById(R.id.apply_btn);
    }
}