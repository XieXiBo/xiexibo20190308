package com.bwie.xiexibo20190308.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.xiexibo20190308.JsonBean;
import com.bwie.xiexibo20190308.R;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 14:37:54
 * @Description:
 */
public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {
    private Context context;
    private List<JsonBean.DataBean> list;

    public ParentAdapter(Context context, List<JsonBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_parent, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.name_parent.setText(list.get(i).getSellerName());
        myViewHolder.ck_parent.setChecked(list.get(i).isParentChecked());
        final List<JsonBean.ChildBean> data = this.list.get(i).getList();
        //设置子适配器
        myViewHolder.rlv_parent.setLayoutManager(new LinearLayoutManager(context));
        final ChildAdapter childAdapter = new ChildAdapter(context, data);
        myViewHolder.rlv_parent.setAdapter(childAdapter);
        /**
         * 商品控制商家
         */
        childAdapter.setChildListener(new ChildAdapter.onChildListener() {
            @Override
            public void onChild(List<JsonBean.ChildBean> goodslist) {
                if (parentListener!=null){
                    parentListener.onParent(list,goodslist);
                }
                boolean isChecked=true;
                for (int j = 0; j < data.size(); j++) {
                    if (!data.get(j).isChildChecked()){
                        isChecked=false;
                    }
                }
                //根据遍历结果设置商家状态
                myViewHolder.ck_parent.setChecked(isChecked);
                ParentAdapter.this.list.get(i).setParentChecked(isChecked);
            }
        });
        /**
         * 商家控制商品
         */
        myViewHolder.ck_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.ck_parent.setChecked(myViewHolder.ck_parent.isChecked());
                childAdapter.setCheckAllChild(myViewHolder.ck_parent.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name_parent;
        private final CheckBox ck_parent;
        private final RecyclerView rlv_parent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //找控件
            name_parent = itemView.findViewById(R.id.name_parent);
            ck_parent = itemView.findViewById(R.id.ck_parent);
            rlv_parent = itemView.findViewById(R.id.rlv_parent);
        }
    }
    public interface onParentListener{
        void onParent(List<JsonBean.DataBean> list, List<JsonBean.ChildBean> goodslist);
    }
    public onParentListener parentListener;

    public void setParentListener(onParentListener parentListener) {
        this.parentListener = parentListener;
    }
}
