package com.nansk.smartcity.activity.takeout;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nansk.smartcity.R;
import com.nansk.smartcity.adapter.takeout.TakeoutCategoryAdapter;
import com.nansk.smartcity.adapter.takeout.TakeoutProductAdapter;
import com.nansk.smartcity.beans.takeout.TakeoutCategoryBean;
import com.nansk.smartcity.beans.takeout.TakeoutOrderObj;
import com.nansk.smartcity.beans.takeout.TakeoutProductBean;
import com.nansk.smartcity.beans.takeout.TakeoutSellerDetailsBean;
import com.nansk.smartcity.utils.ConnectInfo;
import com.nansk.smartcity.utils.OkHttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/22 14:15
 * @Description 商家点菜单
 */

public class TakeoutSellerOrderActivity extends Fragment {
    private View view;
    private RecyclerView categoryContainer;
    private RecyclerView listContainer;

    private TakeoutOrderObj orderObj;
    private List<TakeoutOrderObj.OrderItemListBean> orderItemList;

    private TextView priceTv;
    private TextView numberTv;

    private Gson gson;
    private TakeoutSellerDetailsBean.DataBean sellerObj;

    public TakeoutSellerOrderActivity(TakeoutSellerDetailsBean.DataBean sellerObj, TakeoutOrderObj orderObj,TextView priceTv, TextView numberTv) {
        this.orderObj = orderObj;
        this.priceTv = priceTv;
        this.numberTv = numberTv;
        this.sellerObj = sellerObj;

        orderItemList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_takeout_seller_order, container, false);
        initView();
        initCategory();
        return view;
    }


    private void initView() {
        categoryContainer = (RecyclerView) view.findViewById(R.id.category_container);
        listContainer = (RecyclerView) view.findViewById(R.id.list_container);

        categoryContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        listContainer.setLayoutManager(new LinearLayoutManager(getContext()));

        listContainer.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
                outRect.bottom = 20;
            }
        });

        gson = new Gson();

    }

    /**
     * @Create 2021/9/22 14:37
     * @Role 获取店家菜品类别
     * @Param
     */
    private void initCategory() {
        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_CATEGORY, "?sellerId=" + sellerObj.getId());
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeoutCategoryBean jsonObj = gson.fromJson(response.body().string(), TakeoutCategoryBean.class);
                if (jsonObj.getCode() == 200) {
                    final List<TakeoutCategoryBean.DataBean> data = jsonObj.getData();
                    if (data.size() > 0) {
                        final TakeoutCategoryAdapter adapter = new TakeoutCategoryAdapter(getContext(), data);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                categoryContainer.setAdapter(adapter);
                                initProduct(sellerObj.getId(), data.get(0).getId());

                                //设置点击事件
                                adapter.OnItemClick(new TakeoutCategoryAdapter.OnItemClick() {
                                    @Override
                                    public void OnItemClick(int position, TakeoutCategoryBean.DataBean obj) {
                                        initProduct(sellerObj.getId(), obj.getId());
                                        adapter.setPosition(position);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });

    }

    /**
     * @Create 2021/9/22 15:36
     * @Role 菜品列表
     * @Param
     */
    private void initProduct(int sellerId, int categoryId) {

        String url = ConnectInfo.getUrl(ConnectInfo.TAKEOUT_PRODUCT, "?sellerId=" + sellerId + "&categoryId=" + categoryId);
        OkHttpUtil.doGet(url, "", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                TakeoutProductBean jsonObj = gson.fromJson(response.body().string(), TakeoutProductBean.class);
                if (jsonObj.getCode() == 200) {
                    List<TakeoutProductBean.DataBean> data = jsonObj.getData();
                    if (data.size() > 0) {
                        final TakeoutProductAdapter adapter = new TakeoutProductAdapter(getContext(), data);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listContainer.setAdapter(adapter);
                                adapter.setNumberCallback(numberCallback);
                            }
                        });
                    }

                }
            }
        });
    }

    TakeoutProductAdapter.NumberCallback numberCallback = new TakeoutProductAdapter.NumberCallback() {
        @Override
        public void numberAddLoad(int number, TakeoutProductBean.DataBean obj) {
            Double prices = (number * obj.getPrice()) + Double.valueOf(priceTv.getText().toString());
            int num = Integer.parseInt(numberTv.getText().toString());
            priceTv.setText(Double.toString(prices));
            numberTv.setText("" + (number + num));

            //菜品对象
            TakeoutOrderObj.OrderItemListBean itemListBean = new TakeoutOrderObj.OrderItemListBean();
            //加入菜品对象集合
            itemListBean.setProductId(obj.getId());
            itemListBean.setQuantity(num);
            itemListBean.setImgUrl(obj.getImgUrl());
            itemListBean.setProductName(obj.getName());
            itemListBean.setPrice(obj.getPrice());
            itemListBean.setNumber(itemListBean.getNumber()+1);
            itemListBean.setQuantity(1);

            orderItemList.add(itemListBean);

            orderObj.setNumber(number+num);
            orderObj.setAmount(prices);
            orderObj.setOrderItemList(orderItemList);
        }

        @Override
        public void numberSubLoad(int number, TakeoutProductBean.DataBean obj) {
            Double prices =Double.valueOf(priceTv.getText().toString()) - (number*obj.getPrice());
            int num = Integer.parseInt(numberTv.getText().toString());
            priceTv.setText(Double.toString((prices)));
            numberTv.setText(""+(num-number));

            for (int i =0;i<orderObj.getOrderItemList().size();i++){
                if (orderObj.getOrderItemList().get(i).getProductId() == obj.getId()){
                    orderObj.getOrderItemList().remove(i);
                }
            }
            orderObj.setNumber(num-number);
            orderObj.setAmount(prices);
            orderObj.setOrderItemList(orderItemList);

        }
    };

}