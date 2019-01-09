package com.bwie.wang.xiaomaipu.base;

/**
 * date:2018/12/28.
 *
 * @author 王丙均
 */

public class BasePresenter<V extends BaseView> {
    private V iv;
    public void attachView(V iv){
        this.iv = iv;
    }
    public void detachView(){
        this.iv = null;
    }
    public V getView(){
       return iv;
    }
}
