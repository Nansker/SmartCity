package com.nansk.smartcity.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.nansk.smartcity.R;
import com.nansk.smartcity.activity.LoginActivity;
import com.nansk.smartcity.activity.takeout.TakeoutPlayActivity;
import com.nansk.smartcity.activity.takeout.TakeoutSellerDetailsActivity;
import com.nansk.smartcity.adapter.takeout.TakeoutOrderListAdapter;
import com.nansk.smartcity.beans.RequestResultBean;
import com.nansk.smartcity.beans.TakeoutOrderRefoundBean;
import com.nansk.smartcity.beans.takeout.TakeoutOrderCommentObj;
import com.nansk.smartcity.beans.takeout.TakeoutOrderListBean;
import com.nansk.smartcity.dialog.TakeoutOrderCommentDialog;
import com.nansk.smartcity.dialog.TakeoutOrderRefoundDialog;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.MyApplication;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/18 17:25
 * @Description
 */

public class TakeoutOrderFragment extends Fragment {
    private View view;

    private TabLayout tabMenu;
    private RecyclerView bodyContainer;

    private Gson gson;
    private String token;
    private TextView tipsTv;
    private String[] tabNames;

    private Handler handler = new Handler();
    private LinearLayout body;
    private RelativeLayout loginBox;
    private Button loginBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_take_out_order, container, false);

        initView();
        initTabMenu();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fillData();
    }

    private void initView() {
        tipsTv = (TextView) view.findViewById(R.id.tips_tv);
        tabMenu = (TabLayout) view.findViewById(R.id.tab_menu);
        bodyContainer = (RecyclerView) view.findViewById(R.id.body_container);
        body = (LinearLayout) view.findViewById(R.id.body);
        loginBox = (RelativeLayout) view.findViewById(R.id.login_box);
        loginBtn = (Button) view.findViewById(R.id.login_btn);

        gson = MyApplication.gson;
        token = MyApplication.getToken(getContext());

        bodyContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        bodyContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 20;
                outRect.top = 20;
                outRect.right = 40;
                outRect.left = 40;
            }
        });

        tabNames = getResources().getStringArray(R.array.takeout_order_menu_names);

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100);
        gradientDrawable.setColor(Color.parseColor("#FFC107"));
        loginBtn.setBackground(gradientDrawable);

    }

    /**
     * @Create 2021/9/23 21:27
     * @Role 初始化tabLayout
     * @Param
     */
    private void initTabMenu() {
        tabMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fillData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        for (int i = 0; i < tabNames.length; i++) {
            tabMenu.addTab(tabMenu.newTab().setText(tabNames[i]));
        }
    }

    /**
     * @Create 2021/9/23 21:41
     * @Role 获取订单列表
     * @Param
     */
    public void fillData() {
        if (MyApplication.isLogin(getContext())) {
            body.setVisibility(View.VISIBLE);
            loginBox.setVisibility(View.GONE);
            int position = tabMenu.getSelectedTabPosition();
            String status;
            if (position == 0) {
                status = "";
            } else {
                status = tabNames[position];
            }

            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ORDER_LIST, "?status=" + status);
            OkHttpUtil.doGet(url, token, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final TakeoutOrderListBean jsonObj = gson.fromJson(response.body().string(), TakeoutOrderListBean.class);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonObj.getCode() == 200) {
                                final List<TakeoutOrderListBean.RowsBean> rows = jsonObj.getRows();
                                if (rows.size() > 0) {
                                    final TakeoutOrderListAdapter adapter = new TakeoutOrderListAdapter(getContext(), rows);

                                    //Item按钮点击事件
                                    adapter.setItemCallBack(new TakeoutOrderListAdapter.OnItemCallBack() {
                                        @Override
                                        public void setItemCallBack(final TakeoutOrderListBean.RowsBean orderObj, String action) {
                                            Intent intent;
                                            Bundle bundle;
                                            switch (action) {
                                                case "去支付":
                                                    intent = new Intent(getContext(), TakeoutPlayActivity.class);
                                                    bundle = new Bundle();
                                                    bundle.putString("orderNo", orderObj.getOrderInfo().getOrderNo());
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                    break;
                                                case "评价":
                                                    final TakeoutOrderCommentDialog dialog = new TakeoutOrderCommentDialog();
                                                    //回调接口
                                                    dialog.CommentDialog(new TakeoutOrderCommentDialog.CommentDialog() {
                                                        @Override
                                                        public void CommentDialog(EditText bodyEt, RatingBar scoreBar) {
                                                            comment(orderObj, bodyEt, scoreBar, dialog);
                                                        }
                                                    });
                                                    dialog.show(getFragmentManager(), "dialog");

                                                    break;
                                                case "再来一单":
                                                    intent = new Intent(getContext(), TakeoutSellerDetailsActivity.class);
                                                    bundle = new Bundle();
                                                    bundle.putString("sellerId", Integer.toString(orderObj.getSellerInfo().getId()));
                                                    intent.putExtras(bundle);
                                                    startActivity(intent);
                                                    break;
                                                case "退款":
                                                    final TakeoutOrderRefoundDialog refoundDialog = new TakeoutOrderRefoundDialog();
                                                    refoundDialog.CommentDialog(new TakeoutOrderRefoundDialog.CommentDialog() {
                                                        @Override
                                                        public void CommentDialog(EditText bodyEt) {
                                                            refound(orderObj, bodyEt, refoundDialog);
                                                        }
                                                    });
                                                    refoundDialog.show(getFragmentManager(), "dialog");
                                                    break;
                                            }
                                        }
                                    });
                                    tipsTv.setVisibility(View.GONE);
                                    bodyContainer.setAdapter(adapter);
                                } else {
                                    tipsTv.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });

                }
            });


        } else {
            body.setVisibility(View.GONE);
            loginBox.setVisibility(View.VISIBLE);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * @Create 2021/9/24 18:50
     * @Role 退款
     * @Param
     */
    public void refound(TakeoutOrderListBean.RowsBean orderObj, EditText bodyEt, final TakeoutOrderRefoundDialog refoundDialog) {
        String body = bodyEt.getText().toString();
        if (!body.isEmpty()) {
            String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_ORDER_REFOUND, "");
            TakeoutOrderRefoundBean refoundBean = new TakeoutOrderRefoundBean();
            refoundBean.setReason(body);
            refoundBean.setOrderNo(orderObj.getOrderInfo().getOrderNo());
            OkHttpUtil.doPost(url, token, refoundBean, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    RequestResultBean resultBean = gson.fromJson(response.body().string(), RequestResultBean.class);
                    if (resultBean.getCode() == 200) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                refoundDialog.dismiss();
                                fillData();
                            }
                        });
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "请填写退款原因！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Create 2021/9/24 17:21
     * @Role 评价
     * @Param
     */
    private void comment(TakeoutOrderListBean.RowsBean orderObj, EditText bodyEt, RatingBar scoreBar, final TakeoutOrderCommentDialog dialog) {
        String body = bodyEt.getText().toString();
        int rating = (int) scoreBar.getRating();
        if (!body.isEmpty()) {
            if (rating > 0) {
                TakeoutOrderCommentObj obj = new TakeoutOrderCommentObj();
                obj.setContent(body);
                obj.setScore(rating);
                obj.setOrderNo(orderObj.getOrderInfo().getOrderNo());

                String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_SELLER_COMMENT, "");
                OkHttpUtil.doPost(url, token, obj, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final RequestResultBean jsonObj = gson.fromJson(response.body().string(), RequestResultBean.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (jsonObj.getCode() == 200) {
                                    dialog.dismiss();
                                    fillData();
                                } else {
                                    Toast.makeText(getContext(), jsonObj.getMsg() + "", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

            } else {
                Toast.makeText(getContext(), "请添加评分！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "请填写评价内容！", Toast.LENGTH_SHORT).show();
        }

    }


}