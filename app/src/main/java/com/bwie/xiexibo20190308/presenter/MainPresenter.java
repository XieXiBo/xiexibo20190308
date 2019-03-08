package com.bwie.xiexibo20190308.presenter;

import com.bwie.xiexibo20190308.model.MainModel;
import com.bwie.xiexibo20190308.view.MainView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 13:56:57
 * @Description:p层
 */
public class MainPresenter<T> {
    private String urlMy = "http://172.17.8.100/ks/product/getCarts?uid=51";
    private final MainModel mainModel;
    private final MainView mainView;
    private Reference<T> tReference;

    public MainPresenter(MainView view) {
        mainModel = new MainModel();
        mainView = view;
        mainModel.attechView(this);
    }

    public void onRelated() {
        mainModel.getHttpData(urlMy);
        //model接口监听
        mainModel.setModelListener(new MainModel.onModelListener() {
            @Override
            public void onResult(String json) {
                //传值到view
                mainView.getViewData(json);
            }
        });
    }

    public void attechView(T t) {
        //软引用控制外部类
        tReference = new WeakReference<T>(t);
    }

    public void detchView() {
        if (tReference != null) {
            tReference.clear();
            tReference = null;
        }
    }
}
