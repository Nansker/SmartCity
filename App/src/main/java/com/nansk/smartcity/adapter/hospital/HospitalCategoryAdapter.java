package com.nansk.smartcity.adapter.hospital;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.hospital.HospitalConfirmActivity;
import com.nansk.smartcity.beans.hospital.HospitalCategoryBean;
import com.nansk.smartcity.beans.hospital.PatientListBean;
import com.nansk.smartcity.beans.hospital.ReservationsBean;

import java.util.List;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/17 19:56
 * @Description
 */

public class HospitalCategoryAdapter extends RecyclerView.Adapter<HospitalCategoryAdapter.BodyViewHolder> {
    private Context context;
    private List<HospitalCategoryBean.RowsBean> data;
    private PatientListBean.RowsBean patientObj;

    public HospitalCategoryAdapter(Context context, List<HospitalCategoryBean.RowsBean> data, PatientListBean.RowsBean patientObj) {
        this.context = context;
        this.data = data;
        this.patientObj = patientObj;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hospital_category, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        holder.categoryNameTv.setText(data.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryNameTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryNameTv = (TextView) itemView.findViewById(R.id.categoryName_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HospitalCategoryBean.RowsBean rowsBean = data.get(getLayoutPosition());

                    //生成预约单对象
                    ReservationsBean reservationsBean = new ReservationsBean();
                    reservationsBean.setCategoryId(rowsBean.getId());
                    reservationsBean.setMoney(rowsBean.getMoney());
                    reservationsBean.setType(rowsBean.getType());
                    reservationsBean.setPatientName(patientObj.getName());
                    reservationsBean.setCategoryName(rowsBean.getCategoryName());

                    Intent intent = new Intent(context, HospitalConfirmActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("reservaObj",reservationsBean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
