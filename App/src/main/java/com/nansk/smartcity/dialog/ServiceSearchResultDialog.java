package com.nansk.smartcity.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.ServiceItemAdapter;
import com.nansk.smartcity.beans.service.ServiceJsonRows;
import com.nansk.smartcity.utils.ServiceLinkListener;

import java.util.List;
/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/13 11:00
 * @Description 搜索结果底部弹窗
 */

public class ServiceSearchResultDialog extends BottomSheetDialogFragment {
    private Dialog dialog;
    private View view;

    private TextView tipsTv;
    private RecyclerView resultContainer;
    private Button cancelBtn;

    private List<ServiceJsonRows> rows;

    public ServiceSearchResultDialog(List<ServiceJsonRows> rows) {
        this.rows = rows;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_service_search_result, null);
        dialog.setContentView(view);
        initView();
        return dialog;
    }

    private void initView() {
        tipsTv = (TextView) view.findViewById(R.id.tips_tv);
        resultContainer = (RecyclerView) view.findViewById(R.id.result_container);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        resultContainer.setLayoutManager(gridLayoutManager);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (rows.size() > 0) {
                    tipsTv.setVisibility(View.GONE);
                    ServiceItemAdapter adapter = new ServiceItemAdapter(getContext(), rows);
                    resultContainer.setAdapter(adapter);
                    adapter.setOnItemCallback(new ServiceItemAdapter.OnItemCallback() {
                        @Override
                        public void OnItemCallback(View view, int position, ServiceJsonRows rows) {
                            ServiceLinkListener.setLinkListener(getContext(),rows);
                        }
                    });
                }
            }
    });

}
}
