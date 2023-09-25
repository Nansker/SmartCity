package com.nansk.smartcity.base;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/24 19:08
 * @description
 */

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private Context context;

    public BaseViewHolder(@NonNull Context context ,View itemView) {
        super(itemView);
        this.context = context;
        views = new SparseArray<>();
    }

    public static BaseViewHolder getViewHolder(Context context, View itemView) {
        return new BaseViewHolder(context, itemView);
    }

    public TextView getTextView(int viewId) {
        return findView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return findView(viewId);
    }

    public Button getButton(int viewId) {
        return findView(viewId);
    }

    public View getView(int viewId) {
        return findView(viewId);
    }

    protected <T extends View> T findView(int viewId) {
        View view = itemView.findViewById(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
