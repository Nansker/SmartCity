package com.nansk.smartcity.adapter.design;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 09:39
 * @description
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.model.OnItemCallBack;
import com.nansk.smartcity.R;
import com.nansk.smartcity.beans.community.CommunityTiebaBean;
import com.nansk.smartcity.utils.WindowMangerUtils;

import java.util.List;


public class CommunityTiebaListAdapter extends RecyclerView.Adapter<CommunityTiebaListAdapter.BodyViewHolder> {
    private Context context;
    private List<CommunityTiebaBean> data;

    private OnItemCallBack onItemCallBack;
    int windowWidth;
    public CommunityTiebaListAdapter(Context context) {
        this.context = context;
       windowWidth = WindowMangerUtils.getWindowSize(context, 0)-200;
    }


    public List<CommunityTiebaBean> getData() {
        return data;
    }

    public void setData(List<CommunityTiebaBean> data) {
        notifyDataSetChanged();
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_community_tieba, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, final int position) {
        final CommunityTiebaBean bean = data.get(position);
        holder.nickNameTv.setText(bean.getNickName());
        holder.createTimeTv.setText(bean.getTime());
        holder.titleTv.setText(bean.getTitle());
        holder.contentTv.setText(bean.getContent());
        holder.readNumTv.setText(Integer.toString(bean.getReadNum()) + "人浏览\u3000");
        holder.likeTv.setText(Integer.toString(bean.getLikeNum()) + "人点赞");
        if (bean.getImages() != null){
            if (bean.getImages().size() > 0) {
                createImage(bean.getImages(),holder);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCallBack.OnItemCallBack(position,bean);
            }
        });
    }

    public void setOnItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * @Create 2021/10/18 14:15
     * @Role 设置照片
     * @Param
     */
    private void createImage(List<String> images, BodyViewHolder holder) {
        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(images.get(i)).placeholder(R.drawable.default_img).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            layoutParams.topMargin = 5;
            layoutParams.bottomMargin = 5;

            layoutParams.width = windowWidth /3;
            layoutParams.height = windowWidth /3;
            holder.imageContainer.addView(imageView,layoutParams);

        }

        ViewGroup.LayoutParams imagesParams = holder.imageContainer.getLayoutParams();

        switch (images.size()){
            case 1:
                imagesParams.height = windowWidth/3;
                break;
            case 4:
                imagesParams.height = windowWidth*2/3;
                break;
            case 7:
                imagesParams.height = windowWidth;
                break;
        }
        holder.imageContainer.setLayoutParams(imagesParams);

    }


    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iconIv;
        private TextView titleTv;
        private TextView contentTv;
        private GridLayout imageContainer;
        private TextView readNumTv;
        private TextView likeTv;
        private TextView nickNameTv;
        private TextView createTimeTv;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconIv = (ImageView) itemView.findViewById(R.id.icon_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.content_tv);
            imageContainer = (GridLayout) itemView.findViewById(R.id.image_container);
            readNumTv = (TextView) itemView.findViewById(R.id.readNum_tv);
            likeTv = (TextView) itemView.findViewById(R.id.like_tv);
            nickNameTv = (TextView) itemView.findViewById(R.id.nickName_tv);
            createTimeTv = (TextView) itemView.findViewById(R.id.createTime_tv);
        }
    }
}
