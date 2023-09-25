package com.nansk.smartcity.adapter.hospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.hospital.HospitalAddPatientActivity;
import com.nansk.smartcity.activity.hospital.HospitalRegActivity;
import com.nansk.smartcity.beans.hospital.PatientListBean;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 19:56
 * @Description
 */

public class HospitalCardListAdapter extends RecyclerView.Adapter<HospitalCardListAdapter.BodyViewHolder> {
    private Context context;
    private List<PatientListBean.RowsBean> data;


    public HospitalCardListAdapter(Context context, List<PatientListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hospital_card, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final PatientListBean.RowsBean rowsBean = data.get(position);
        holder.nameTv.setText(rowsBean.getName());
        holder.cardIdTv.setText("身份证号："+rowsBean.getCardId());
        holder.telTv.setText("手机号："+rowsBean.getTel());

        holder.modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HospitalAddPatientActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cardObj",rowsBean);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
//        Glide.with(context).load(ConnectInfo.IP+"/prod-api/api" +rowsBean.getImgUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.avatarIv);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarIv;
        private TextView nameTv;
        private ImageButton modifyBtn;
        private TextView cardIdTv;
        private TextView telTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarIv = (ImageView) itemView.findViewById(R.id.avatar_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);
            modifyBtn = (ImageButton) itemView.findViewById(R.id.modify_btn);
            cardIdTv = (TextView) itemView.findViewById(R.id.cardId_tv);
            telTv = (TextView) itemView.findViewById(R.id.tel_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PatientListBean.RowsBean rowsBean = data.get(getLayoutPosition());
                    Intent intent = new Intent(context, HospitalRegActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("patientObj",rowsBean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }
}
