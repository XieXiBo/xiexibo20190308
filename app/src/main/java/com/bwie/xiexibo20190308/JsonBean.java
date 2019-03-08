package com.bwie.xiexibo20190308;

import java.util.List;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/8 14:21:00
 * @Description:
 */
public class JsonBean {


    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {

        private String sellerName;
        private String sellerid;
        private boolean parentChecked = false;
        private List<ChildBean> list;

        public boolean isParentChecked() {
            return parentChecked;
        }

        public void setParentChecked(boolean parentChecked) {
            this.parentChecked = parentChecked;
        }


        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
        }

        public String getSellerid() {
            return sellerid;
        }

        public void setSellerid(String sellerid) {
            this.sellerid = sellerid;
        }

        public List<ChildBean> getList() {
            return list;
        }

        public void setList(List<ChildBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "sellerName='" + sellerName + '\'' +
                    ", sellerid='" + sellerid + '\'' +
                    ", parentChecked=" + parentChecked +
                    ", list=" + list +
                    '}';
        }
    }

    public class ChildBean {
        private String title;
        private Double price;
        private String images;
        private boolean childChecked=false;
        private int num = 1;

        public boolean isChildChecked() {
            return childChecked;
        }

        public void setChildChecked(boolean childChecked) {
            this.childChecked = childChecked;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "ChildBean{" +
                    "title='" + title + '\'' +
                    ", price=" + price +
                    ", images='" + images + '\'' +
                    ", num=" + num +
                    '}';
        }
    }
}
