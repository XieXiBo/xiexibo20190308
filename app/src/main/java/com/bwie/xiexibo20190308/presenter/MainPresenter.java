package com.bwie.xiexibo20190308.presenter;

import com.bwie.xiexibo20190308.model.MainModel;
import com.bwie.xiexibo20190308.view.MainView;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 13:56:57
 * @Description:
 */
public class MainPresenter {
    private String urlMy = "http://172.17.8.100/ks/product/getCarts?uid=51";
    private final MainModel mainModel;
    private final MainView mainView;

    public MainPresenter(MainView view) {
        mainModel = new MainModel();
        mainView = view;
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


}
