package com.bwie.xiexibo20190308.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.xiexibo20190308.JsonBean;
import com.bwie.xiexibo20190308.R;
import com.bwie.xiexibo20190308.wiget.CustomView;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 14:56:44
 * @Description:商品适配器
 */
public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.MyViewHolder> {
    private Context context;
    private List<JsonBean.ChildBean> list;

    public ChildAdapter(Context context, List<JsonBean.ChildBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_child, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.ck_child.setChecked(list.get(i).isChildChecked());
        myViewHolder.title_child.setText(list.get(i).getTitle());
        myViewHolder.price_child.setText("¥：" + list.get(i).getPrice());
        list.get(i).setNumber(1);
        Glide.with(context).load(list.get(i).getImages()).into(myViewHolder.img_child);
        //商品状态改变
        myViewHolder.ck_child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(i).setChildChecked(isChecked);
                if (childListener != null) {
                    myViewHolder.group_child.setOnChangeNum(new CustomView.onChangeNum() {
                        @Override
                        public void onChange(int number) {
                            list.get(i).setNumber(number);
                            childListener.onChild(list);
                        }
                    });
                    childListener.onChild(list);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setCheckAllChild(boolean checked) {
        for (JsonBean.ChildBean childBean : list) {
            childBean.setChildChecked(checked);
        }
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox ck_child;
        private final ImageView img_child;
        private final TextView title_child;
        private final TextView price_child;
        private final CustomView group_child;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //找控件
            ck_child = itemView.findViewById(R.id.ck_child);
            img_child = itemView.findViewById(R.id.img_child);
            title_child = itemView.findViewById(R.id.title_child);
            price_child = itemView.findViewById(R.id.price_child);
            group_child = itemView.findViewById(R.id.group_child);

        }
    }

    public interface onChildListener {
        void onChild(List<JsonBean.ChildBean> list);
    }

    public onChildListener childListener;

    public void setChildListener(onChildListener childListener) {
        this.childListener = childListener;
    }
}

