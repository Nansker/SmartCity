package com.nansk.smartcity.activity.press;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.base.BaseAdapter;
import com.nansk.smartcity.base.BaseViewHolder;
import com.nansk.smartcity.model.CheckLogin;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.PressCommentsAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.press.PressCommentListObj;
import com.nansk.smartcity.beans.press.PressCommentsBean;
import com.nansk.smartcity.beans.press.PressDetailsBean;
import com.nansk.smartcity.beans.press.PressListBean;
import com.nansk.smartcity.model.impl.OnItemClickListener;
import com.nansk.smartcity.model.impl.CheckLoginUtils;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/09 20:48
 * @Description 新闻详情页
 */

public class PressDetailsActivity extends BaseActivity implements View.OnClickListener {
    private int pressId;
    private Gson gson;
    private TextView titleTv;
    private TextView publishDateTv;
    private ImageView coverIv;
    private WebView webView;
    private TextView readNumTv;
    private LinearLayout likeBox;
    private TextView likeNumTv;
    private TextView commentNumTv;
    private TextView tipsTv;
    private RecyclerView commentContainer;
    private RecyclerView recommendContainer;
    private EditText commentEt;
    private Button commentBtn;

    private BaseAdapter<PressListBean.RowsBean> hotPressAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomView(R.layout.activity_press_details);
        setToolBarTitle("新闻详情");
        initView();
        initObject();
        fillData();
        getCommentList();
        getHotList();
    }


    /**
     * @Create 2021/9/9 16:21
     * @Role 获取新闻列表
     * @Param
     */
    public void getHotList() {
        String url = ConnectInfo.getUrl(ConnectInfo.PRESS_LIST, "?hot=Y&pageNum=1&pageSize=3");
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PressListBean jsonObj = gson.fromJson(response.body().string(), PressListBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonObj.getCode() == 200) {
                            List<PressListBean.RowsBean> rows = jsonObj.getRows();
                            hotPressAdapter.setData(rows);
                            recommendContainer.setAdapter(hotPressAdapter);
                        } else {
                            showToast(jsonObj.getMsg());
                        }
                    }
                });
            }
        });

    }

    /**
     * @Create 2021/9/10 9:35
     * @Role 根据新闻id获取数据
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.PRESS_DETAILS, pressId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final PressDetailsBean jsonObj = gson.fromJson(response.body().string(), PressDetailsBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (jsonObj.getCode() == 200) {
                            final PressDetailsBean.DataBean data = jsonObj.getData();
                            //标题
                            titleTv.setText(data.getTitle());
                            //图片
                            Glide.with(PressDetailsActivity.this).load(MyApplication.IP + data.getCover()).into(coverIv);
                            likeNumTv.setText(Integer.toString(data.getLikeNum()));
                            readNumTv.setText(Integer.toString(data.getReadNum()));
                            publishDateTv.setText(data.getPublishDate());
                            //内容
                            try {
                                webView.loadData(URLEncoder.encode(data.getContent(), "utf-8"), "text/html", "utf-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * @Create 2021/10/11 19:26
     * @Role 获取评论列表
     * @Param
     */
    private void getCommentList() {
        if (pressId != 0) {
            String url = ConnectInfo.getUrl(ConnectInfo.PRESS_COMMENTS_LIST, "?newsId=" + pressId);
            OkHttpUtil.doGet(url, "", new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final PressCommentListObj jsonObj = MyApplication.gson.fromJson(response.body().string(), PressCommentListObj.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200) {
                                if (jsonObj.getTotal() > 0) {
                                    commentNumTv.setText("评论 " + jsonObj.getTotal());
                                    final PressCommentsAdapter adapter = new PressCommentsAdapter(PressDetailsActivity.this, jsonObj.getRows());
                                    tipsTv.setVisibility(View.GONE);
                                    commentContainer.setAdapter(adapter);
                                } else {
                                    tipsTv.setVisibility(View.VISIBLE);
                                    tipsTv.setText("暂无评论");
                                }
                            } else {
                                Toast.makeText(PressDetailsActivity.this, jsonObj.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        } else {
            showToast("获取新闻详细信息失败！");
        }
    }

    /**
     * @Create 2021/10/11 19:23
     * @Role 评论新闻
     * @Param
     */
    private void comment() {
        closeKeyboard(commentEt);

        CheckLoginUtils.isLogin(PressDetailsActivity.this, true, new CheckLogin() {
            @Override
            public void onAlready() {

                String content = commentEt.getText().toString().trim();
                //判断评论内容是否为空
                if (!content.isEmpty()) {
                    //评论对象
                    PressCommentsBean commentsBean = new PressCommentsBean();
                    commentsBean.setContent(content);
                    commentsBean.setNewsId(pressId);
                    //路径
                    String url = ConnectInfo.getUrl(ConnectInfo.PRESS_COMMENTS, "");
                    //向服务器发送请求
                    OkHttpUtil.doPost(url, MyApplication.getToken(PressDetailsActivity.this), commentsBean, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //评论成功
                                    commentEt.setText("");
                                    showToast("评论发送成功！");
                                    getCommentList();
                                }
                            });
                        }
                    });

                } else {
                    showToast("评论的内容不能为空！");
                }
            }

            @Override
            public void onNone() {

            }
        });
    }

    private void initObject() {

        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

        commentBtn.setBackground(getDrawable("#007aff", 10));

        //获取传入的新闻信息
        Intent intent = getIntent();
        pressId = intent.getIntExtra("pressId", 0);
        gson = MyApplication.gson;

        commentContainer.setLayoutManager(new LinearLayoutManager(PressDetailsActivity.this));
        recommendContainer.setLayoutManager(new LinearLayoutManager(PressDetailsActivity.this));
        recommendContainer.addItemDecoration(new DividerItemDecoration(PressDetailsActivity.this, DividerItemDecoration.VERTICAL));

        hotPressAdapter = new BaseAdapter<PressListBean.RowsBean>(PressDetailsActivity.this,R.layout.item_press_list) {
            @Override
            protected void onConvert(BaseViewHolder holder, PressListBean.RowsBean item, int position) {
                holder.getTextView(R.id.title_tv).setText(item.getTitle());
                Glide.with(PressDetailsActivity.this).load(MyApplication.getIP(PressDetailsActivity.this) + item.getCover()).into(holder.getImageView(R.id.cover_iv));
                holder.getTextView(R.id.time_tv).setText(item.getPublishDate());
                holder.getTextView(R.id.readNum_tv).setText("阅读人数：" + item.getReadNum());
                holder.getTextView(R.id.likeNum_tv).setText("点赞人数：" + item.getLikeNum());
            }
        };

        hotPressAdapter.setOnItemCallBack(new OnItemClickListener<PressListBean.RowsBean>() {
            @Override
            public void OnItemCallBack(int position, PressListBean.RowsBean obj) {
                super.OnItemCallBack(position, obj);
                Intent intent = new Intent(PressDetailsActivity.this, PressDetailsActivity.class);
                intent.putExtra("pressId", obj.getId());
                startActivity(intent);
            }
        });

    }

    /**
     * @Create 2021/10/11 20:04
     * @Role 点赞新闻
     * @Param
     */
    private void likePress() {

        CheckLoginUtils.isLogin(PressDetailsActivity.this, true, new CheckLogin() {
            @Override
            public void onAlready() {
                String url = ConnectInfo.getUrl(ConnectInfo.PRESS, "press/like/" + pressId);
                OkHttpUtil.doPut(url, MyApplication.getToken(PressDetailsActivity.this), "", new Callback() {
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
                                    fillData();
                                    showToast("点赞+1");
                                } else {
                                    showToast(jsonObj.getMsg());
                                }
                            }
                        });
                    }
                });
            }

            @Override
            public void onNone() {

            }
        });
    }

    private void initView() {
        titleTv = (TextView) findViewById(R.id.title_tv);

        publishDateTv = (TextView) findViewById(R.id.publishDate_tv);
        coverIv = (ImageView) findViewById(R.id.cover_iv);
        webView = (WebView) findViewById(R.id.webView);

        readNumTv = (TextView) findViewById(R.id.readNum_tv);
        likeBox = (LinearLayout) findViewById(R.id.like_box);
        likeNumTv = (TextView) findViewById(R.id.likeNum_tv);
        commentNumTv = (TextView) findViewById(R.id.commentNum_tv);

        tipsTv = (TextView) findViewById(R.id.tips_tv);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        recommendContainer = (RecyclerView) findViewById(R.id.recommend_container);
        commentEt = (EditText) findViewById(R.id.comment_et);
        commentBtn = (Button) findViewById(R.id.comment_btn);

        likeBox.setOnClickListener(this);

        likeBox.setOnClickListener(this);
        commentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_btn:
                comment();
                break;
            case R.id.like_box:
                likePress();
                break;
        }
    }

}