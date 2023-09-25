package com.nansk.smartcity.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.model.CheckLogin;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.activity.ActivityCommentListAdapter;
import com.nansk.smartcity.adapter.activity.AcitvityListAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.activity.ActivityCommentBean;
import com.nansk.smartcity.beans.activity.ActivityCommentListBean;
import com.nansk.smartcity.beans.activity.ActivityDetailsBean;
import com.nansk.smartcity.beans.activity.ActivityListBean;
import com.nansk.smartcity.beans.activity.ActivitySifnupCheckBean;
import com.nansk.smartcity.beans.activity.ActivitySignup;
import com.nansk.smartcity.dialog.CommentDialog;
import com.nansk.smartcity.model.impl.CheckLoginUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/27 16:55
 * @Description 活动详情
 */

public class ActivityDetailsActivity extends BaseActivity implements View.OnClickListener {
    private int activityId;

    private ImageView imageIv;
    private TextView nameTv;
    private WebView webView;
    private TextView signupNumTv;
    private TextView likeNumTv;
    private TextView categoryNameTv;
    private TextView publishTimeTv;

    private TextView commentNumTv;
    private RecyclerView commentContainer;
    private RecyclerView recommentContainer;

    private Button commentBtn;
    private Button appleBtn;

    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_avtivity_details);
        setToolBarBackground("#007aff");
        initView();
        fillData();
        getCommentList();
        getRecommendAct();
        singnupStatus();
    }

    private void initView() {

        imageIv = (ImageView) findViewById(R.id.image_iv);
        nameTv = (TextView) findViewById(R.id.name_tv);
        webView = (WebView) findViewById(R.id.webView);
        signupNumTv = (TextView) findViewById(R.id.signupNum_tv);
        likeNumTv = (TextView) findViewById(R.id.likeNum_tv);
        categoryNameTv = (TextView) findViewById(R.id.categoryName_tv);
        publishTimeTv = (TextView) findViewById(R.id.publishTime_tv);

        commentNumTv = (TextView) findViewById(R.id.commentNum_tv);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        recommentContainer = (RecyclerView) findViewById(R.id.recomment_container);
        commentBtn = (Button) findViewById(R.id.comment_btn);
        appleBtn = (Button) findViewById(R.id.apple_btn);

        commentBtn.setOnClickListener(this);
        appleBtn.setOnClickListener(this);

        commentContainer.setLayoutManager(new LinearLayoutManager(ActivityDetailsActivity.this));
        recommentContainer.setLayoutManager(new LinearLayoutManager(ActivityDetailsActivity.this));
        recommentContainer.addItemDecoration(new DividerItemDecoration(ActivityDetailsActivity.this, DividerItemDecoration.VERTICAL));

        Intent intent = getIntent();
        activityId = intent.getIntExtra("activityId", 0);

        token = (String) SharedPreferencesUtils.getRecord(this, "token", "");

    }

    /**
     * @Create 2021/9/27 19:04
     * @Role 获取信息
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_DETAILS, activityId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ActivityDetailsBean jsonObj = MyApplication.gson.fromJson(response.body().string(), ActivityDetailsBean.class);
                if (jsonObj.getCode() == 200) {
                    final ActivityDetailsBean.DataBean data = jsonObj.getData();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(ActivityDetailsActivity.this).load(MyApplication.IP + data.getImgUrl()).into(imageIv);
                            setToolBarTitle(data.getName());
                            nameTv.setText(data.getName());
                            signupNumTv.setText(data.getSignupNum() + "人已报名");
                            likeNumTv.setText(data.getLikeNum() + "人已点赞");
                            categoryNameTv.setText("活动类型：" + data.getCategoryName());
                            publishTimeTv.setText("发布时间：" + data.getPublishTime());
                            try {
                                webView.loadData(URLEncoder.encode(data.getContent(), "UTF-8"), "text/html", "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });
    }

    /**
     * @Create 2021/9/27 19:39
     * @Role 获取评论列表
     * @Param
     */
    private void getCommentList() {
        String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_COMMENT, "/list?activityId=" + activityId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ActivityCommentListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), ActivityCommentListBean.class);
                if (jsonObj.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getTotal() > 0) {
                                List<ActivityCommentListBean.RowsBean> rows = jsonObj.getRows();
                                ActivityCommentListAdapter adapter = new ActivityCommentListAdapter(ActivityDetailsActivity.this, rows);
                                commentContainer.setAdapter(adapter);
                            }
                            commentNumTv.setText("评论(" + jsonObj.getTotal() + ")");
                        }
                    });
                }
            }
        });
    }

    /**
     * @Create 2021/9/27 20:02
     * @Role 推荐活动
     * @Param
     */
    private void getRecommendAct() {
        String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_LIST, "?recommend=Y");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ActivityListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), ActivityListBean.class);
                if (jsonObj.getCode() == 200) {
                    final List<ActivityListBean.RowsBean> data = jsonObj.getRows();
                    List<ActivityListBean.RowsBean> subDatas = new ArrayList<>();

                    //只保留三篇推荐文章
                    if (data.size() > 3) {
                        for (int i = 0; i < 3; i++) {
                            subDatas.add(data.get(i));
                        }
                    } else {
                        subDatas = data;
                    }

                    final List<ActivityListBean.RowsBean> finalSubDatas = subDatas;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AcitvityListAdapter adapter = new AcitvityListAdapter(ActivityDetailsActivity.this, finalSubDatas);
                            recommentContainer.setAdapter(adapter);
                        }
                    });
                }
            }
        });
    }

    /**
     * @Create 2021/9/27 20:30
     * @Role 设置用户报名状态
     * @Param
     */
    private void singnupStatus() {

        String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_SIGNUP, "/check?activityId=" + activityId);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ActivitySifnupCheckBean jsonObj = MyApplication.gson.fromJson(response.body().string(), ActivitySifnupCheckBean.class);
                if (jsonObj.getCode() == 200) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getMsg().equals("未报名")) {
                                appleBtn.setText("我要报名");
                            } else {
                                appleBtn.setText(jsonObj.getMsg());
                            }

                        }
                    });

                }
            }
        });
    }

    /**
     * @Create 2021/9/27 20:43
     * @Role 报名活动
     * @Param
     */
    private void appleActivity() {
        String tag = appleBtn.getText().toString();
        if (tag.equals("已报名")) {
            showToast("活动已经报名");
            //报名
        } else {

            ActivitySignup signup = new ActivitySignup();
            signup.setActivityId(activityId);

            String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_SIGNUP, "");
            OkHttpUtil.doPost(url, token, signup, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                    if (jsonObj.getCode() == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast("活动报名成功！");
                            }
                        });
                        singnupStatus();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(jsonObj.getMsg());
                            }
                        });

                    }
                }
            });
        }
    }

    /**
     * @Create 2021/9/27 21:54
     * @Role 评论
     * @Param
     */
    private void toComment() {
        final CommentDialog dialog = new CommentDialog("活动评论", null, false);
        dialog.DialogCallBack(new CommentDialog.DialogCallBack() {
            @Override
            public void DialogCallBack(TextView dialogTitle, EditText bodyEt, RatingBar scoreBar, Button submitBtn) {
                WindowMangerUtils.closeKeyboard(ActivityDetailsActivity.this, bodyEt);
                String content = bodyEt.getText().toString().trim();
                ActivityCommentBean commentObj = new ActivityCommentBean();
                commentObj.setActivityId(activityId);
                commentObj.setContent(content);

                String url = ConnectInfo.getUrl(ConnectInfo.ACTIVITY_COMMENT, "");
                OkHttpUtil.doPost(url, token, commentObj, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean jsonObj = MyApplication.gson.fromJson(response.body().string(), RequestResultBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (jsonObj.getCode() == 200) {
                                    //刷新评论列表
                                    getCommentList();
                                    //延时关闭弹窗
                                    dialog.dismiss();
                                } else {
                                    showToast(jsonObj.getMsg());
                                }
                            }
                        });

                    }
                });

            }
        });
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void onClick(final View v) {
        CheckLoginUtils.isLogin(ActivityDetailsActivity.this, true, new CheckLogin() {
            @Override
            public void onAlready() {
                switch (v.getId()) {
                    case R.id.comment_btn:
                        toComment();

                        break;
                    case R.id.apple_btn:
                        appleActivity();
                        break;
                }
            }
            @Override
            public void onNone() {

            }
        });

    }


}