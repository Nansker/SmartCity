package com.nansk.smartcity.design.pension;

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
import com.nansk.smartcity.beans.pension.PensionOrgBean;
import com.nansk.smartcity.design.DesignResources;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.UserInfoUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 19:37
 * @description 养老机构详情+在线预约
 */

public class PensionOrgDetailsActivity extends BaseActivity {
    private PensionOrgBean object;
    private ImageView imageIv;
    private TextView nameTv;
    private TextView introductionTv;
    private RecyclerView commentContainer;
    private EditText commentEt;
    private Button commentBtn;

    private DesignCommentAdapter commentAdapter;
    private TextView tipsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_pension_org_details);
        setBarDivider(false, "");
        setToolBarTitle("机构详情");
        setToolBarBackground("#b07eef");
        initView();
        getCommentList();
    }

    private void addComment() {
        DesignCommentBean bean = new DesignCommentBean();
        bean.setNickName(UserInfoUtils.getUserInfo(PensionOrgDetailsActivity.this).getNickName());
        bean.setTime("2021-12-13");
        bean.setContent(commentEt.getText().toString().trim());

        String record = (String) SharedPreferencesUtils.getRecord(PensionOrgDetailsActivity.this, "design_comment", "");
        if (!record.equals("")) {
            List<DesignCommentBean> oldRecord = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            if (oldRecord.size() > 0) {
                oldRecord.add(0, bean);
                SharedPreferencesUtils.addRecord(PensionOrgDetailsActivity.this, "design_comment", MyApplication.gson.toJson(oldRecord));
                getCommentList();
            } else {
                ArrayList<DesignCommentBean> beans = new ArrayList<>();
                beans.add(bean);
                SharedPreferencesUtils.addRecord(PensionOrgDetailsActivity.this, "design_comment", MyApplication.gson.toJson(beans));
                getCommentList();
            }
        } else {
            ArrayList<DesignCommentBean> beans = new ArrayList<>();
            beans.add(bean);
            SharedPreferencesUtils.addRecord(PensionOrgDetailsActivity.this, "design_comment", MyApplication.gson.toJson(beans));
            getCommentList();
        }
    }

    private void getCommentList() {
        String record = (String) SharedPreferencesUtils.getRecord(PensionOrgDetailsActivity.this, "design_comment", "");
        if (!record.equals("")) {
            List<DesignCommentBean> list = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            if (list.size() > 0) {
                tipsTv.setVisibility(View.GONE);
                commentAdapter.setData(list);
            }

        }
    }

    private void initView() {

        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        commentEt = (EditText) findViewById(R.id.comment_et);
        commentBtn = (Button) findViewById(R.id.comment_btn);
        imageIv = (ImageView) findViewById(R.id.image_iv);
        nameTv = (TextView) findViewById(R.id.name_tv);
        introductionTv = (TextView) findViewById(R.id.introduction_tv);

        object = (PensionOrgBean) getIntent().getSerializableExtra("obj");

        nameTv.setText(object.getName());
        introductionTv.setText("\u3000" + object.getIntroduce());
        imageIv.setImageResource(DesignResources.getPensionOrgImage(object.getId()));

        commentBtn.setBackground(getDrawable("#b07eef", 100));

        commentContainer.setLayoutManager(new LinearLayoutManager(PensionOrgDetailsActivity.this));
        commentAdapter = new DesignCommentAdapter(PensionOrgDetailsActivity.this);
        commentContainer.setAdapter(commentAdapter);
        tipsTv = (TextView) findViewById(R.id.tips_tv);

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!commentEt.getText().toString().isEmpty()) {
                    addComment();
                    commentEt.setText("");
                    commentEt.clearFocus();
                    WindowMangerUtils.closeKeyboard(PensionOrgDetailsActivity.this,commentEt);
                    showToast("评论成功！");
                } else {
                    showToast("请输入评论内容！");
                }
            }
        });

    }
}