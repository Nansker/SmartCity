package com.nansk.smartcity.activity.takeout;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/23 16:11
 * @Description 外卖-提交订单-选择收货地址弹窗
 */

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.takeout.TakeoutAddressAdapter;
import com.nansk.smartcity.beans.takeout.TakeoutAddressListBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TakeoutChangeAddressDialog extends BottomSheetDialogFragment {
    private Dialog dialog;
    private View view;
    private RecyclerView bodyContainer;
    private ImageView closeBtn;
    private Button addAddressBtn;

    public DialogCallBack dialogCallBack;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_takeout_change_address, null);
        dialog.setContentView(view);
        initView();
        fillData();
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        fillData();
    }

    private void initView() {
        bodyContainer = (RecyclerView) view.findViewById(R.id.body_container);

        bodyContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        closeBtn = (ImageView) view.findViewById(R.id.close_btn);
        addAddressBtn = (Button) view.findViewById(R.id.addAddress_btn);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TakeoutNewAddressActivity.class);
                getContext().startActivity(intent);
            }
        });
    }

    /**
     * @Create 2021/9/23 14:36
     * @Role 获取收货地址
     * @Param
     */
    private void fillData() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ADDRESS_LIST, "");
        OkHttpUtil.doGet(url, MyApplication.getToken(getContext()), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeoutAddressListBean jsonObj = MyApplication.gson.fromJson(response.body().string(), TakeoutAddressListBean.class);
                if (jsonObj.code == 200) {
                    List<TakeoutAddressListBean.DataBean> data = jsonObj.getData();
                    if (data.size() > 0) {
                        final TakeoutAddressAdapter adapter = new TakeoutAddressAdapter(getContext(), data);

                        adapter.OnItemCallBack(new TakeoutAddressAdapter.OnItemCallBack() {
                            @Override
                            public void OnItemCallBack(int position, TakeoutAddressListBean.DataBean obj) {
                                dialogCallBack.DialogCallBack(obj);
                            }
                        });

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bodyContainer.setAdapter(adapter);
                            }
                        });
                    }
                }
            }
        });

    }

    public interface DialogCallBack{
        void DialogCallBack(TakeoutAddressListBean.DataBean obj);
    }

    public void DialogCallBack(DialogCallBack callBack){
        this.dialogCallBack = callBack;
    }

}
