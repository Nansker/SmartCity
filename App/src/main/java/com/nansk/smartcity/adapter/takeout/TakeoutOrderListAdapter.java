package com.nansk.smartcity.adapter.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/24 10:01
 * @Description
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.takeout.TakeoutOrderDetailsActivity;
import com.nansk.smartcity.beans.takeout.TakeoutOrderListBean;
import com.nansk.smartcity.utils.MyApplication;

import java.util.List;

public class TakeoutOrderListAdapter extends RecyclerView.Adapter<TakeoutOrderListAdapter.BodyViewHolder> {
    private Context context;
    private List<TakeoutOrderListBean.RowsBean> data;
    private OnItemCallBack onItemCallBack;
    public TakeoutOrderListAdapter(Context context, List<TakeoutOrderListBean.RowsBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_takeout_order_list, parent, false);
        BodyViewHolder holder = new BodyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {
        final TakeoutOrderListBean.RowsBean rowsBean = data.get(position);
        final TakeoutOrderListBean.RowsBean.SellerInfoBean sellerInfo = rowsBean.getSellerInfo();
        final TakeoutOrderListBean.RowsBean.OrderInfoBean orderInfo = rowsBean.getOrderInfo();
        List<TakeoutOrderListBean.RowsBean.OrderInfoBean.OrderItemListBean> orderItemList = orderInfo.getOrderItemList();
        //店家信息
        Glide.with(context).load(MyApplication.IP + sellerInfo.getImgUrl()).placeholder(R.drawable.ic_baseline_insert_photo_24).into(holder.logoIv);
        holder.sellerNameTv.setText(sellerInfo.getName());

        //菜品列表
        TakeoutOrderListItemAdapter orderListItemAdapter = new TakeoutOrderListItemAdapter(context, orderItemList);
        holder.itemListContainer.setAdapter(orderListItemAdapter);

        //订单信息
        holder.amountTv.setText("￥" + orderInfo.getAmount().toString());
        holder.payTimeTv.setText("下单时间：" + orderInfo.getPayTime());
        holder.orderNoTv.setText("订单号：" + orderInfo.getOrderNo());
        holder.paymentTypeTv.setText("支付方式：" + orderInfo.getPaymentType());

        //订单状态信息
        String status = orderInfo.getStatus();
        holder.statusTv.setText(status);

        switch (orderInfo.getStatus()) {
            case "待支付":
                holder.payBtn.setVisibility(View.VISIBLE);
                holder.commentBtn.setVisibility(View.GONE);
                holder.refoundBtn.setVisibility(View.GONE);
                holder.buyBtn.setVisibility(View.GONE);
                //跳转到支付页面
                holder.payBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemCallBack.setItemCallBack(rowsBean,"去支付");
                    }
                });
                break;
            case "待评价":
                holder.payBtn.setVisibility(View.GONE);
                holder.commentBtn.setVisibility(View.VISIBLE);
                holder.refoundBtn.setVisibility(View.VISIBLE);
                holder.buyBtn.setVisibility(View.GONE);
                holder.commentBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemCallBack.setItemCallBack(rowsBean,"评价");
                    }
                });

                holder.refoundBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemCallBack.setItemCallBack(rowsBean,"退款");
                    }
                });

                break;
            case "完成":
                holder.payBtn.setVisibility(View.GONE);
                holder.commentBtn.setVisibility(View.GONE);
                holder.refoundBtn.setVisibility(View.GONE);
                holder.buyBtn.setVisibility(View.VISIBLE);

                //跳转店家详情页
                holder.buyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemCallBack.setItemCallBack(rowsBean,"再来一单");
                    }
                });
                break;
            case "退款":
                holder.payBtn.setVisibility(View.GONE);
                holder.commentBtn.setVisibility(View.GONE);
                holder.refoundBtn.setVisibility(View.GONE);
                holder.buyBtn.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemCallBack {
        void setItemCallBack(TakeoutOrderListBean.RowsBean orderObj,String action);
    }
    public void setItemCallBack(OnItemCallBack callBack){
        this.onItemCallBack = callBack;
    }

    class BodyViewHolder extends RecyclerView.ViewHolder {
        private ImageView logoIv;
        private TextView sellerNameTv;
        private TextView statusTv;
        private RecyclerView itemListContainer;
        private TextView amountTv;
        private TextView orderNoTv;
        private TextView payTimeTv;
        private TextView paymentTypeTv;

        private Button payBtn;
        private Button commentBtn;
        private Button refoundBtn;
        private Button buyBtn;

        public BodyViewHolder(@NonNull View itemView) {
            super(itemView);
            logoIv = (ImageView) itemView.findViewById(R.id.logo_iv);
            sellerNameTv = (TextView) itemView.findViewById(R.id.sellerName_tv);
            statusTv = (TextView) itemView.findViewById(R.id.status_tv);
            itemListContainer = (RecyclerView) itemView.findViewById(R.id.itemList_container);
            amountTv = (TextView) itemView.findViewById(R.id.amount_tv);
            orderNoTv = (TextView) itemView.findViewById(R.id.orderNo_tv);
            payTimeTv = (TextView) itemView.findViewById(R.id.payTime_tv);
            paymentTypeTv = (TextView) itemView.findViewById(R.id.paymentType_tv);
            payBtn = (Button) itemView.findViewById(R.id.pay_btn);
            commentBtn = (Button) itemView.findViewById(R.id.comment_btn);
            refoundBtn = (Button) itemView.findViewById(R.id.refound_btn);
            buyBtn = (Button) itemView.findViewById(R.id.buy_btn);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            itemListContainer.setLayoutManager(linearLayoutManager);
            itemListContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    outRect.left = 10;
                    outRect.right = 10;
                }
            });
        }
    }
}
