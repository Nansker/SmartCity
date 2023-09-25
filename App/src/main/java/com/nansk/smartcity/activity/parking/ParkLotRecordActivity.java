package com.nansk.smartcity.activity.parking;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.base.BaseActivity;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.parking.ParkRecordListAdapter;
import com.nansk.smartcity.beans.park.ParkRecordListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 14:23
 * @Description 停车记录
 */

public class ParkLotRecordActivity extends BaseActivity implements View.OnClickListener {


    private TextView indateTv;
    private TextView intimeTv;
    private TextView outdateTv;
    private TextView outtimeTv;
    private Button searchBtn;
    private Button moreBtn;
    private RecyclerView bodyContainer;
    private CardView tipsCard;

    private int pageSize = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView(R.layout.activity_park_lot_record);
        setToolBarTitle("停车记录");
        initView();
        fillData("?pageNum=1&pageSize=5");
    }

    private void initView() {

        indateTv = (TextView) findViewById(R.id.indate_tv);
        intimeTv = (TextView) findViewById(R.id.intime_tv);
        outdateTv = (TextView) findViewById(R.id.outdate_tv);
        outtimeTv = (TextView) findViewById(R.id.outtime_tv);
        searchBtn = (Button) findViewById(R.id.search_btn);
        moreBtn = (Button) findViewById(R.id.more_btn);

        bodyContainer = (RecyclerView) findViewById(R.id.body_container);
        tipsCard = (CardView) findViewById(R.id.tips_card);


        indateTv.setOnClickListener(this);
        intimeTv.setOnClickListener(this);
        outdateTv.setOnClickListener(this);
        outtimeTv.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        moreBtn.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ParkLotRecordActivity.this);
        bodyContainer.setLayoutManager(linearLayoutManager);
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
                outRect.bottom = 20;
                outRect.right = 40;
                outRect.left = 40;
            }
        });
    }

    /**
     * @Create 2021/9/18 14:51
     * @Role 查询停车记录列表
     * @Param
     */
    private void fillData(String condition) {
        String url = ConnectInfo.getUrl(ConnectInfo.PARK_RECORD_LIST, condition);
        OkHttpUtil.doGet(url, MyApplication.getToken(this), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ParkRecordListBean recordListBean = MyApplication.gson.fromJson(response.body().string(), ParkRecordListBean.class);
                if (recordListBean.code == 200) {
                    List<ParkRecordListBean.RowsBean> rows = recordListBean.getRows();
                    if (rows.size() > 0) {
                        final ParkRecordListAdapter adapter = new ParkRecordListAdapter(ParkLotRecordActivity.this, rows);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bodyContainer.setVisibility(View.VISIBLE);
                                moreBtn.setVisibility(View.VISIBLE);
                                tipsCard.setVisibility(View.GONE);
                                bodyContainer.setAdapter(adapter);
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bodyContainer.setVisibility(View.GONE);
                                tipsCard.setVisibility(View.VISIBLE);
                            }
                        });
                    }

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ParkLotRecordActivity.this, recordListBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    /**
     * @Create 2021/9/18 15:43
     * @Role 查询记录
     * @Param
     */
    private void query() {
        if (!indateTv.getText().toString().equals("日期") && !outdateTv.getText().toString().equals("日期") && !outtimeTv.getText().toString().equals("时间") && !intimeTv.getText().toString().equals("时间")) {
            String entryTime = indateTv.getText().toString() + " " + intimeTv.getText().toString();
            String outTime = outdateTv.getText().toString() + " " + outtimeTv.getText().toString();
            fillData("?entryTime=" + entryTime + "&" + "outTime=" + outTime);
        } else {
            Toast.makeText(this, "请选择完整的时间段！", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //打开时间选择器
            case R.id.indate_tv:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String desc = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
                        indateTv.setText(desc);
                    }
                },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                pickerDialog.show();
                break;
            case R.id.intime_tv:
                Calendar calendar1 = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String desc = String.format("%d:%d", hourOfDay, minute);
                        intimeTv.setText(desc);
                    }
                },
                        calendar1.get(Calendar.HOUR_OF_DAY),
                        calendar1.get(Calendar.MINUTE), true
                );
                timePickerDialog.show();
                break;
            case R.id.outdate_tv:
                Calendar calendar2 = Calendar.getInstance();
                DatePickerDialog pickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String desc = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
                        outdateTv.setText(desc);
                    }
                },
                        calendar2.get(Calendar.YEAR),
                        calendar2.get(Calendar.MONTH),
                        calendar2.get(Calendar.DAY_OF_MONTH)
                );
                pickerDialog1.show();
                break;
            case R.id.outtime_tv:
                Calendar calendar3 = Calendar.getInstance();
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String desc = String.format("%d:%d", hourOfDay, minute);
                        outtimeTv.setText(desc);
                    }
                },
                        calendar3.get(Calendar.HOUR_OF_DAY),
                        calendar3.get(Calendar.MINUTE), true
                );
                timePickerDialog1.show();
                break;
            //搜索记录
            case R.id.search_btn:
                query();
                break;
            //查看更多
            case R.id.more_btn:
                pageSize = pageSize + 3;
                fillData("?pageNum=1&pageSize=" + pageSize);
                break;
        }
    }


}