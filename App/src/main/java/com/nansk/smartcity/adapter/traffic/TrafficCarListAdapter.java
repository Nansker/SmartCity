package com.nansk.smartcity.adapter.traffic;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/04 18:34
 * @description 绑定车辆列表适配器
 */

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.traffic.TrafficCarListBean;
import com.nansk.smartcity.beans.traffic.TrafficIllegalListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;
import com.nansk.smartcity.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TrafficCarListAdapter extends RecyclerView.Adapter<TrafficCarListAdapter.BodyViewHolder> {
    private Context context;
    private List<TrafficCarListBean.RowsBean> data;
    private SharedPreferencesUtils preferencesUtils;
    private String token;
    private Gson gson;
    Handler handler = new Handler();

    private OnItemCallBack onItemCallBack;

    public TrafficCarListAdapter(Context context, List<TrafficCarListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
        token = MyApplication.getToken(context);
        gson = MyApplication.gson;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_traffic_car, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final TrafficCarListBean.RowsBean bean = data.get(position);
        holder.plateNoTv.setText(getValue(bean.getPlateNo()));
        holder.userNameTv.setText(getValue(bean.getUserName()));
        getCarStatus(bean.getPlateNo(),holder.disposeStateTv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemCallBack{
        void OnItemCallBack(int position,TrafficCarListBean.RowsBean obj);
    }
    public void OnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack =callBack;
    }

    private String getValue(String value){
        if (value != "" && value != null){
            return value;
        }
        return "暂无数据";
    }
    /**
     * @Create 2021/10/4 19:57
     * @Role 获取车辆状态
     * @Param
     */
    private void getCarStatus(String plateNum, final TextView disposeStateTv){
        String url = ConnectInfo.getUrl(ConnectInfo.TRAFFIC_ILLEGAL, "list/?licencePlate=" + plateNum);
        OkHttpUtil.doGet(url, token, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final TrafficIllegalListBean jsonObj = gson.fromJson(response.body().string(), TrafficIllegalListBean.class);
                if (jsonObj.getCode() == 200){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getTotal() == 0){
                                disposeStateTv.setText("正常");
                                disposeStateTv.setTextColor(Color.parseColor("#4CAF50"));
                                disposeStateTv.setBackgroundColor(Color.parseColor("#264CAF50"));
                            }else {
                                disposeStateTv.setText("违法未处理");
                                disposeStateTv.setTextColor(Color.parseColor("#F44336"));
                                disposeStateTv.setBackgroundColor(Color.parseColor("#0DCC0000"));
                            }
                        }
                    });
                }

            }
        });
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView plateNoTv;
        private TextView disposeStateTv;
        private TextView userNameTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            plateNoTv = (TextView) itemView.findViewById(R.id.plateNo_tv);
            disposeStateTv = (TextView) itemView.findViewById(R.id.disposeState_tv);
            userNameTv = (TextView) itemView.findViewById(R.id.userName_tv);
        }
    }
}
