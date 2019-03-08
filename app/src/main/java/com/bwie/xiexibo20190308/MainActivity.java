package com.bwie.xiexibo20190308;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bwie.xiexibo20190308.fragment.MineFragment;
import com.bwie.xiexibo20190308.fragment.ShopCarFragment;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private FragmentManager manager;
    private ShopCarFragment carFragment;
    private MineFragment mineFragment;

    //http://172.17.8.100/ks/product/getCarts?uid=51
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //控件
        radioGroup = findViewById(R.id.radiogroup);
        radioGroup.check(radioGroup.getChildAt(0).getId());
        //管理者
        manager = getSupportFragmentManager();
        //实例化
        carFragment = new ShopCarFragment();
        mineFragment = new MineFragment();
        //事务
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment,carFragment);
        transaction.add(R.id.fragment,mineFragment);
        transaction.show(carFragment).hide(mineFragment).commit();

        //按钮切换
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio0:
                        fragmentTransaction.show(carFragment).hide(mineFragment);
                        break;
                    case R.id.radio1:
                        fragmentTransaction.show(mineFragment).hide(carFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }
}
