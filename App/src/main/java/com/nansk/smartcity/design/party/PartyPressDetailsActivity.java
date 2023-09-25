package com.nansk.smartcity.design.party;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.DesignCommentAdapter;
import com.nansk.smartcity.beans.DesignCommentBean;
import com.nansk.smartcity.beans.party.PartyTextBean;
import com.nansk.smartcity.design.DesignResources;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/21 20:55
 * @description 动态详情
 */

public class PartyPressDetailsActivity extends BaseActivity {
    private PartyTextBean pressObj;
    private TextView titleTv;
    private TextView dateTv;
    private ImageView imageIv;
    private TextView contentTv;
    private RecyclerView commentContainer;
    private EditText commentEt;
    private Button commentBtn;

    private DesignCommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_press_dateils);
        setToolBarTitle("动态详情");
        initView();
        initObject();
        getCommentList();
    }

    /**
     * @Create 2021/10/22 10:03
     * @Role
     * @Param
     */
    private void comment() {
        long timeMillis = System.currentTimeMillis();
        Date date = new Date(timeMillis);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        String format = time.format(date);

        DesignCommentBean commentBean = new DesignCommentBean();
        commentBean.setContent(commentEt.getText().toString().trim());
        commentBean.setNickName(UserInfoUtils.getUserInfo(PartyPressDetailsActivity.this).getNickName());
        commentBean.setTime(format);
        List<DesignCommentBean> list = new ArrayList<>();
        list.add(commentBean);

        String record = (String) SharedPreferencesUtils.getRecord(PartyPressDetailsActivity.this, "party_press_comment", "");

        if (!record.equals("")){
            List<DesignCommentBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            for (int i = 0; i < json.size(); i++) {
                DesignCommentBean designCommentBean =  json.get(i);
                list.add(designCommentBean);
            }
        }
        SharedPreferencesUtils.addRecord(PartyPressDetailsActivity.this, "party_press_comment", MyApplication.gson.toJson(list));
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

        String record = (String) SharedPreferencesUtils.getRecord(PartyPressDetailsActivity.this, "party_press_comment", "");

        if (!record.equals("")){
            List<DesignCommentBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            for (int i = 0; i < json.size(); i++) {
                DesignCommentBean designCommentBean =  json.get(i);
                list.add(0,designCommentBean);
                commentAdapter.setData(list);
            }
        }else {
            commentAdapter.setData(list);
        }
    }

    /**
     * @Create 2021/10/22 9:11
     * @Role
     * @Param
     */
    private void initObject() {
        pressObj = (PartyTextBean) getIntent().getSerializableExtra("pressObj");
        titleTv.setText(pressObj.getTitle());
        dateTv.setText("2021-10-22");
        contentTv.setText(pressObj.getContent());
        imageIv.setImageResource(DesignResources.getPartyPressImage(pressObj.getId()));

        commentContainer.setLayoutManager(new LinearLayoutManager(PartyPressDetailsActivity.this));
        commentAdapter = new DesignCommentAdapter(PartyPressDetailsActivity.this);
        commentContainer.setAdapter(commentAdapter);
    }

    private void initView() {
        titleTv = (TextView) findViewById(R.id.title_tv);
        dateTv = (TextView) findViewById(R.id.date_tv);
        imageIv = (ImageView) findViewById(R.id.image_iv);
        contentTv = (TextView) findViewById(R.id.content_tv);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        commentEt = (EditText) findViewById(R.id.comment_et);
        commentBtn = (Button) findViewById(R.id.comment_btn);

        commentBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party), 100));
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!commentEt.getText().toString().trim().equals("")){
                    closeKeyboard(commentEt);
                    commentEt.setText("");
                    comment();
                }else {
                    showToast("请输入评论内容");
                }

            }
        });
    }

}