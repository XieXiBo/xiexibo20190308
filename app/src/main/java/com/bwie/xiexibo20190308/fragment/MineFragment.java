package com.bwie.xiexibo20190308.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwie.xiexibo20190308.R;
import com.bwie.xiexibo20190308.wiget.CreateView;

import java.lang.reflect.Parameter;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 13:48:19
 * @Description:我的页面fragment
 */
public class MineFragment extends Fragment {

    private CreateView view_mine;
    private ImageView img_mine;
    private LinearLayout.LayoutParams p;
    LinearLayout.LayoutParams lp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null, false);
        //控件
       view_mine = view.findViewById(R.id.view_mine);
        img_mine = view.findViewById(R.id.img_mine);
        lp= new LinearLayout.LayoutParams(-2, -2);
        lp.gravity= Gravity.CENTER;
        view_mine.setOnCreateView(new CreateView.onCreateView() {
            @Override
            public void ongetY(float y) {
                lp.setMargins(0,-20,0, (int) (y+2));

            }
        });
        img_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(img_mine,"translationY",0,300f);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(img_mine,"alpha",1.0f,0.3f);
                AnimatorSet animatorSet =new AnimatorSet();
                animatorSet.play(objectAnimator1).with(objectAnimator2);
                animatorSet.setDuration(3000);
                animatorSet.start();
            }
        });
        return view;
    }
}
