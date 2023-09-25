package com.nansk.smartcity.design.party;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.design.DesignCommentAdapter;
import com.nansk.smartcity.beans.DesignCommentBean;
import com.nansk.smartcity.beans.party.PartyTextBean;
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
 * @create 2021/10/22 14:09
 * @description 课程内容
 */

public class PartyStudyDetailsActivity extends BaseActivity implements SurfaceHolder.Callback, MediaPlayer.OnBufferingUpdateListener {
    private PartyTextBean studyObj;
    private SurfaceView surfaceView;

    private MediaPlayer mediaPlayer;
    private ImageView playIv;
    private ProgressBar progressBar;
    private TextView currentTv;
    private TextView totalTv;
    private ImageView maxIv;

    private Handler handler;
    private TextView titleTv;
    private EditText commentEt;
    private TextView commentBtn;
    private RecyclerView bodyContainer;

    private DesignCommentAdapter commentAdapter;
    private RecyclerView commentContainer;

    private MediaPlayer voice;
    private ImageView voicePlay;
    private ProgressBar voiceProgress;
    private TextView voiceTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_party_study_details);
        setToolBarTitle("课程内容");
        initView();
        initObject();
        initPlay();
        initVoice();
        getCommentList();
    }

    /**
     * @Create 2021/10/22 16:20
     * @Role 课程语音
     * @Param
     */
    private void initVoice() {

        voice = MediaPlayer.create(PartyStudyDetailsActivity.this, R.raw.voice);
        voiceProgress.setMax(voice.getDuration());

        voice.setAudioStreamType(AudioManager.STREAM_MUSIC);
        voice.setLooping(true);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(voice.getDuration());
        String format = simpleDateFormat.format(date);
        voiceTime.setText(format);

        voicePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playIv.setImageResource(R.drawable.icon_play);
                }
                if (voice.isPlaying()) {
                    voice.pause();
                    voicePlay.setImageResource(R.drawable.icon_play);
                } else {
                    voicePlay.setImageResource(R.drawable.icon_start);
                    voice.start();
                }

            }
        });
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
        commentBean.setNickName(UserInfoUtils.getUserInfo(PartyStudyDetailsActivity.this).getNickName());
        commentBean.setTime(format);
        List<DesignCommentBean> list = new ArrayList<>();
        list.add(commentBean);

        String record = (String) SharedPreferencesUtils.getRecord(PartyStudyDetailsActivity.this, "party_study_comment", "");

        if (!record.equals("")) {
            List<DesignCommentBean> json = MyApplication.gson.fromJson(record, new TypeToken<List<DesignCommentBean>>() {
            }.getType());
            for (int i = 0; i < json.size(); i++) {
                DesignCommentBean designCommentBean = json.get(i);
                list.add(designCommentBean);
            }
        }
        SharedPreferencesUtils.addRecord(PartyStudyDetailsActivity.this, "party_study_comment", MyApplication.gson.toJson(list));
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

        String record = (String) SharedPreferencesUtils.getRecord(PartyStudyDetailsActivity.this, "party_study_comment", "");

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

    private void initPlay() {
        int[] raws = new int[]{R.raw.video01, R.raw.video02, R.raw.video03};
        mediaPlayer = MediaPlayer.create(getApplicationContext(), raws[studyObj.getId() % 3]);
        mediaPlayer.setOnBufferingUpdateListener(this);
        surfaceView.getHolder().addCallback(this);
        //循环播放
        mediaPlayer.setLooping(true);
        progressBar.setMax(mediaPlayer.getDuration());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(mediaPlayer.getDuration());
        String format = simpleDateFormat.format(date);
        totalTv.setText("/" + format);
    }

    private void initObject() {
        commentBtn.setBackground(getDrawable(getResources().getString(R.string.theme_party), 100));
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    Date date = new Date(mediaPlayer.getCurrentPosition());
                    String format = simpleDateFormat.format(date);
                    currentTv.setText(format);
                    progressBar.setProgress(mediaPlayer.getCurrentPosition());

                }
                if (voice != null && voice.isPlaying()) {
                    voiceProgress.setProgress(voice.getCurrentPosition());
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
        playIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (voice.isPlaying()) {
                    voice.pause();
                    voicePlay.setImageResource(R.drawable.icon_play);
                }

                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        playIv.setImageResource(R.drawable.icon_play);
                    } else {
                        mediaPlayer.start();
                        playIv.setImageResource(R.drawable.icon_start);
                    }
                } else {
                    showToast("播放资源出错了！");
                }
            }
        });

        commentAdapter = new DesignCommentAdapter(PartyStudyDetailsActivity.this);
        commentContainer.setAdapter(commentAdapter);
        commentContainer.setLayoutManager(new LinearLayoutManager(PartyStudyDetailsActivity.this));
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!commentEt.getText().toString().trim().equals("")) {
                    closeKeyboard(commentEt);
                    comment();
                    commentEt.setText("");
                } else {
                    showToast("请输入评价内容！");
                }
            }
        });
    }

    private void initView() {
        studyObj = (PartyTextBean) getIntent().getSerializableExtra("studyObj");
        playIv = (ImageView) findViewById(R.id.play_iv);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        currentTv = (TextView) findViewById(R.id.current_tv);
        totalTv = (TextView) findViewById(R.id.total_tv);
        maxIv = (ImageView) findViewById(R.id.max_iv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        titleTv.setText(studyObj.getTitle());
        commentEt = (EditText) findViewById(R.id.comment_et);
        commentBtn = (TextView) findViewById(R.id.comment_btn);
        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        commentContainer = (RecyclerView) findViewById(R.id.comment_container);
        voicePlay = (ImageView) findViewById(R.id.voice_play);
        voiceProgress = (ProgressBar) findViewById(R.id.voice_progress);
        voiceTime = (TextView) findViewById(R.id.voice_time);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(holder);
        mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (voice.isPlaying()) {
            voice.release();
            voice = null;
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }
}