package com.bwie.xiexibo20190308.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.xiexibo20190308.JsonBean;
import com.bwie.xiexibo20190308.R;
import com.bwie.xiexibo20190308.adapter.ParentAdapter;
import com.bwie.xiexibo20190308.presenter.MainPresenter;
import com.bwie.xiexibo20190308.view.MainView;
import com.google.gson.Gson;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 13:48:19
 * @Description:购物车fragment
 */
public class ShopCarFragment extends Fragment implements MainView {
    private View view;
    private RecyclerView rlv_car;
    private CheckBox checkAll;
    private TextView priceAll;
    private TextView numAll;
    private MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopcar, null, false);
        //控件
        rlv_car = view.findViewById(R.id.rlv_car);
        checkAll = view.findViewById(R.id.checkAll);
        priceAll = view.findViewById(R.id.priceAll);
        numAll = view.findViewById(R.id.numAll);
        //实例p
        presenter = new MainPresenter(this);
        //关联
        presenter.onRelated();
        return view;
    }

    @Override
    public void getViewData(String json) {
        //解析
        Gson gson = new Gson();
        JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
        final List<JsonBean.DataBean> data = jsonBean.getData();
        // Log.i("xxx", "getViewData: "+data.toString());
        //适配器
        if (data.size() != 0) {
            rlv_car.setLayoutManager(new LinearLayoutManager(getActivity()));
            final ParentAdapter parentAdapter = new ParentAdapter(getActivity(), data);
            rlv_car.setAdapter(parentAdapter);
            /**
             * 全选全不选
             */
            checkAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox) v).isChecked()) {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setParentChecked(true);
                            List<JsonBean.ChildBean> list = data.get(i).getList();
                            for (int j = 0; j < list.size(); j++) {
                                list.get(j).setChildChecked(true);
                            }
                        }
                    } else {
                        for (int i = 0; i < data.size(); i++) {
                            data.get(i).setParentChecked(false);
                            List<JsonBean.ChildBean> list = data.get(i).getList();
                            for (int j = 0; j < list.size(); j++) {
                                list.get(j).setChildChecked(false);
                            }
                        }
                    }
                    parentAdapter.notifyDataSetChanged();
                }
            });
            /**
             * 商品控制全选
             */
            parentAdapter.setParentListener(new ParentAdapter.onParentListener() {
                @Override
                public void onParent(List<JsonBean.DataBean> list, List<JsonBean.ChildBean> goodslist) {
                    int num1 = 0;
                    int num2 = 0;
                    for (int i = 0; i < list.size(); i++) {
                        List<JsonBean.ChildBean> beans = list.get(i).getList();
                        for (int j = 0; j < beans.size(); j++) {
                            num1++;
                            if (beans.get(j).isChildChecked()) {
                                num2++;
                            }
                        }
                    }
                    if (num1 == num2) {
                        checkAll.setChecked(true);
                    } else {
                        checkAll.setChecked(false);
                    }

                    int sum1 = 0;
                    Double sum2 = 0.0;
                    for (int i = 0; i < goodslist.size(); i++) {
                        boolean childChecked = goodslist.get(i).isChildChecked();
                        if (childChecked) {
                            sum1++;
                            Double price = goodslist.get(i).getPrice();
                            Double num = Double.valueOf(goodslist.get(i).getNum());
                            sum2 = price * num;
                        }
                    }
                    priceAll.setText(sum2+"");
                    numAll.setText("去结算（"+sum1+"）");
                }
            });
        }
    }
}
