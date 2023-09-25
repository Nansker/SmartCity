package com.nansk.smartcity.base;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nansk.smartcity.model.impl.OnItemClickListener;

import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 19:34
 * @description
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private int layoutId;
    private List<T> data;

    private OnItemClickListener<T> onItemClickListener;

    public BaseAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
    }

    public List<T> getData() {
        Log.i("test", "getData" + this.data.size());
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
        Log.i("test", "setData--->" + this.data.size());
    }

    protected T getItem(int position) {
        if (data != null) {
            return data.get(position);
        }
        return null;
    }

    protected abstract void onConvert(BaseViewHolder holder, T item, int position);

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return BaseViewHolder.getViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, final int position) {
        final T item = getItem(position);
        onConvert(holder, item, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemCallBack(position,item);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            data.size();
        }
        return data.size();
    }

    public void setOnItemCallBack(OnItemClickListener<T> listener){
        this.onItemClickListener = listener;
    }
}
