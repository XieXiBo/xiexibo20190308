package com.bwie.xiexibo20190308.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.xiexibo20190308.JsonBean;
import com.bwie.xiexibo20190308.R;
import com.bwie.xiexibo20190308.adapter.ChildAdapter;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 14:31:55
 * @Description:
 */
public class CustomView extends LinearLayout implements View.OnClickListener {

    private TextView jian;
    private TextView add;
    private EditText ed_num;
    private int number = 1;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_view, this);
        //控件
        jian = findViewById(R.id.jian);
        add = findViewById(R.id.add);
        ed_num = findViewById(R.id.ed_num);
        //点击监听
        jian.setOnClickListener(this);
        add.setOnClickListener(this);
        ed_num.setText("" + number);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                number++;
                ed_num.setText("" + number);
                if (onChangeNum!=null){
                    onChangeNum.onChange(number);
                }
                break;
            case R.id.jian:

                if (number>1){
                    number--;
                }else{
                    Toast.makeText(getContext(),"不能小于1",Toast.LENGTH_SHORT).show();
                }
                ed_num.setText("" + number);
                if (onChangeNum!=null){
                    onChangeNum.onChange(number);
                }
                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        ed_num.setText(number + "");
    }


    public interface onChangeNum {
        void onChange(int number);
    }

    public onChangeNum onChangeNum;

    public void setOnChangeNum(CustomView.onChangeNum onChangeNum) {
        this.onChangeNum = onChangeNum;
    }
}
