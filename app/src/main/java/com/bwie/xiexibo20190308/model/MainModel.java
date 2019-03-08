package com.bwie.xiexibo20190308.model;

import android.os.Handler;
import android.os.Message;

import com.bwie.xiexibo20190308.utils.OkHttpUtils;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 13:57:10
 * @Description:m层
 */
public class MainModel<T> {
    private Reference<T> tReference;

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


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
               String json= (String) msg.obj;
               //接受数据，回调
                if (modelListener!=null){
                    modelListener.onResult(json);
                }
            }
        }
    };
    public void getHttpData(String urlMy) {
        OkHttpUtils.getInstance().doGet(urlMy, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.what=0;
                message.obj=response.body().string();
                handler.sendMessage(message);
            }
        });
    }
    public interface onModelListener{
        void onResult(String json);
    }
    public onModelListener modelListener;

    public void setModelListener(onModelListener modelListener) {
        this.modelListener = modelListener;
    }
}
