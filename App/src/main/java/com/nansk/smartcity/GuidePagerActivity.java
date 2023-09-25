package com.nansk.smartcity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.activity.MainActivity;
import com.nansk.smartcity.dialog.ToastDialog;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.SharedPreferencesUtils;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/07 19:42
 * @Description 引导页
 */

public class GuidePagerActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private Button nets_btn;
    private Button begin_btn;

    //    引导页
    private List<ImageView> imageViews;
    private ImageView imageView;
    private int[] images;

    //引导点
    private LinearLayout indicatorBox;
    private ImageView[] indicators;
    private int currentIndex; //当前位置

    ToastDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_pager);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getIP(GuidePagerActivity.this);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicatorBox = findViewById(R.id.indicator_box);
        nets_btn = (Button) findViewById(R.id.nets_btn);
        begin_btn = (Button) findViewById(R.id.begin_btn);

        nets_btn.setOnClickListener(this);
        begin_btn.setOnClickListener(this);

        //引导显示的图片
        images = new int[]{
                R.drawable.smartcity1,
                R.drawable.smartcity2,
                R.drawable.smartcity3,
                R.drawable.smartcity4,
                R.drawable.smartcity5};

        imageViews = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            imageView = new ImageView(GuidePagerActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(GuidePagerActivity.this).load(images[i]).into(imageView);
            imageViews.add(imageView);
        }

        //设置底部指示器
        initIndicator();

        //设置适配器
        viewPager.setAdapter(new GuidePagerAdapter());
        //先隐藏按钮
        begin_btn.setVisibility(View.GONE);
        nets_btn.setVisibility(View.GONE);

        //最后一张引导页显示按钮
        setEndPagerStyle();
       dialog = new ToastDialog(GuidePagerActivity.this);
    }

    /**
     * @Create 2021/9/7 23:02
     * @Role 初始化底部指示器
     * @Param
     */
    private void initIndicator() {
        indicators = new ImageView[imageViews.size()];
        for (int i = 0; i < imageViews.size(); i++) {
            indicators[i] = (ImageView) indicatorBox.getChildAt(i);
            indicators[i].setSelected(false); //都设为未选中状态
            indicators[i].setTag(i);
        }
        currentIndex = 0;
        indicators[currentIndex].setSelected(true); //设置为选中状态
    }

    /**
     * @Create 2021/9/7 23:28
     * @Role 设置当前位置引导点位置
     * @Param
     */
    private void setIndicator(int position) {
        indicators[position].setSelected(true);
        indicators[currentIndex].setSelected(false);//上一个引导点
        currentIndex = position;
    }


    /**
     * @Create 2021/9/7 20:40
     * @Role 按钮监听事件
     * @Param
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.nets_btn:
                intent = new Intent(GuidePagerActivity.this, NetworkSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.begin_btn:
                //进入主页
                if (MyApplication.getIP(GuidePagerActivity.this) != null && MyApplication.getIP(GuidePagerActivity.this) != ""){
                    intent = new Intent(GuidePagerActivity.this, MainActivity.class);
                    startActivity(intent);

                    //增加加一次登录次数
                    int loginCount = (int) SharedPreferencesUtils.getRecord(GuidePagerActivity.this, "loginCount", 0);
                    SharedPreferencesUtils.addRecord(GuidePagerActivity.this,"loginCount",loginCount+1);
                }else {
                    dialog.showReveal("请先配置服务器网络！");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    }, 1200);
                }
                break;
        }
    }


    /**
     * @Create 2021/9/7 20:46
     * @Role 设置最后一张引导页显示样式
     * @Param
     */
    private void setEndPagerStyle() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageViews.size() - 1) {
                    begin_btn.setVisibility(View.VISIBLE);
                    nets_btn.setVisibility(View.VISIBLE);
                } else {
                    begin_btn.setVisibility(View.GONE);
                    nets_btn.setVisibility(View.GONE);
                }
                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * @Create 2021/9/7 20:31
     * @Role 引导页适配器
     * @Param
     */
    public class GuidePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}