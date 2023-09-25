package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 15:21
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.DesignCommentBean;

import java.util.List;

public class DesignCommentAdapter extends RecyclerView.Adapter<DesignCommentAdapter.BodyViewHolder> {
    private Context context;
    private List<DesignCommentBean> data;


    public DesignCommentAdapter(Context context) {
        this.context = context;
    }

    public List<DesignCommentBean> getData() {
        return data;
    }

    public void setData(List<DesignCommentBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_press_comment, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        DesignCommentBean bean = data.get(position);
        holder.userNameTv.setText(bean.getNickName());
        holder.dateTv.setText(bean.getTime());
        holder.contentTv.setText(bean.getContent());
    }

    @Override
    public int getItemCount() {
        if (data != null){
            return data.size();
        }
        return 0;
    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarIv;
        private TextView userNameTv;
        private TextView dateTv;
        private TextView contentTv;
        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarIv = (ImageView) itemView.findViewById(R.id.avatar_iv);
            userNameTv = (TextView) itemView.findViewById(R.id.userName_tv);
            dateTv = (TextView) itemView.findViewById(R.id.date_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
        }
    }
}
