package com.nansk.smartcity.design.community;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.DesignCommentAdapter;
import com.nansk.smartcity.beans.DesignCommentBean;
import com.nansk.smartcity.beans.community.CommunityTiebaBean;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 14:36
 * @description
 */

public class CommunitySocialDetailsActivity extends BaseActivity {

    private ImageView iconIv;
    private TextView nickNameTv;
    private TextView createTimeTv;
    private TextView titleTv;
    private TextView contentTv;
    private GridLayout imageContainer;
    private TextView readNumTv;
    private TextView likeTv;
    private TextView commentNumTv;
    private RecyclerView commentContainer;
    private EditText commentEt;
    private Button commentBtn;

    private int windowWidth;
    private TextView tipsTv;

    private DesignCommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_community_social_details);
        setToolBarTitle("动态详情");
        initView();
        fillData();
        getCommentList();
    }

    /**
     * @Create 2021/10/18 15:17
     * @Role
     * @Param
     */
    private void getCommentList() {
        String record = (String) SharedPreferencesUtils.getRecord(CommunitySocialDetailsActivity.this, "community_comment", "");
        if (!record.equals("")) {
            List<DesignCommentBean> list = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            if (list.size()>0){
                tipsTv.setVisibility(View.GONE);
                commentAdapter.setData(list);
            }

        }
    }

    private void addComment() {
        DesignCommentBean bean = new DesignCommentBean();
        bean.setNickName(UserInfoUtils.getUserInfo(CommunitySocialDetailsActivity.this).getNickName());
        bean.setTime("2021-12-13");
        bean.setContent(commentEt.getText().toString().trim());

        String record = (String) SharedPreferencesUtils.getRecord(CommunitySocialDetailsActivity.this, "community_comment", "");
        if (!record.equals("")) {
            List<DesignCommentBean> oldRecord = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            if (oldRecord.size() > 0) {
                oldRecord.add(0, bean);
                SharedPreferencesUtils.addRecord(CommunitySocialDetailsActivity.this, "community_comment", MyApplication.gson.toJson(oldRecord));
                getCommentList();
            } else {
                ArrayList<DesignCommentBean> beans = new ArrayList<>();
                beans.add(bean);
                SharedPreferencesUtils.addRecord(CommunitySocialDetailsActivity.this, "community_comment", MyApplication.gson.toJson(beans));
                getCommentList();
            }
        } else {
            ArrayList<DesignCommentBean> beans = new ArrayList<>();
            beans.add(bean);
            SharedPreferencesUtils.addRecord(CommunitySocialDetailsActivity.this, "community_comment", MyApplication.gson.toJson(beans));
            getCommentList();
        }
    }

    /**
     * @Create 2021/10/18 14:51
     * @Role
     * @Param
     */
    private void fillData() {
        CommunityTiebaBean object = (CommunityTiebaBean) getIntent().getSerializableExtra("obj");
        nickNameTv.setText(object.getNickName());
        createTimeTv.setText(object.getTime());
        titleTv.setText(object.getTitle());
        contentTv.setText(object.getContent());
        readNumTv.setText(Integer.toString(object.getReadNum()) + "人浏览\u3000");
        likeTv.setText(Integer.toString(object.getLikeNum()) + "人点赞");
        if (object.getImages() != null) {
            if (object.getImages().size() > 0) {
                createImage(object.getImages());
            }
        }

    }

    /**
     * @Create 2021/10/18 14:15
     * @Role 设置照片
     * @Param
     */
    private void createImage(List<String> images) {
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(CommunitySocialDetailsActivity.this);
            Glide.with(CommunitySocialDetailsActivity.this).load(images.get(i)).placeholder(R.drawable.default_img).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            layoutParams.topMargin = 5;
            layoutParams.bottomMargin = 5;

            layoutParams.width = windowWidth / 3;
            layoutParams.height = windowWidth / 3;
            imageContainer.addView(imageView, layoutParams);

        }

        ViewGroup.LayoutParams imagesParams = imageContainer.getLayoutParams();

        switch (images.size()) {
            case 1:
                imagesParams.height = windowWidth / 3;
                break;
            case 4:
                imagesParams.height = windowWidth * 2 / 3;
                break;
            case 7:
                imagesParams.height = windowWidth;
                break;
        }
        imageContainer.setLayoutParams(imagesParams);

    }

    private void initView() {
        iconIv = (ImageView) findViewById(R.id.icon_iv);
        nickNameTv = (TextView) findViewById(R.id.nickName_tv);
        createTimeTv = (TextView) findViewById(R.id.createTime_tv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        contentTv = (TextView) findViewById(R.id.content_tv);
        imageContainer = (GridLayout) findViewById(R.id.image_container);
        readNumTv = (TextView) findViewById(R.id.readNum_tv);
        likeTv = (TextView) findViewById(R.id.like_tv);
        commentNumTv = (TextView) findViewById(R.id.commentNum_tv);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        commentEt = (EditText) findViewById(R.id.comment_et);
        commentBtn = (Button) findViewById(R.id.comment_btn);
        tipsTv = (TextView) findViewById(R.id.tips_tv);
        commentBtn.setBackground(getDrawable("#FF9800", 100));

        windowWidth = WindowMangerUtils.getWindowSize(CommunitySocialDetailsActivity.this, 0) - 100;

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!commentEt.getText().toString().isEmpty()) {
                    addComment();

                } else {
                    showToast("请输入评论内容！");
                }
            }
        });


        commentContainer.setLayoutManager(new LinearLayoutManager(CommunitySocialDetailsActivity.this));
        commentAdapter = new DesignCommentAdapter(CommunitySocialDetailsActivity.this);
        commentContainer.setAdapter(commentAdapter);
    }

}