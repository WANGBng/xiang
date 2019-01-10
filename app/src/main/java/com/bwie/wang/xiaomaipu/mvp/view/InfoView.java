package com.bwie.wang.xiaomaipu.mvp.view;

import com.bwie.wang.xiaomaipu.base.BaseView;
import com.bwie.wang.xiaomaipu.my.bean.GoodsList.SyncShoppingBean;
import com.bwie.wang.xiaomaipu.my.bean.home.InfoBean;

import java.io.IOException;

/**
 * date:2019/1/2.
 *
 * @author 王丙均
 */

public interface InfoView extends BaseView {
    void OnSuccess(InfoBean infoBean);
    void OnFailed(String msg);
//    void OnSyncShoppingBeanSuccess(SyncShoppingBean syncShoppingBean);
//    void OnSyncShoppingBeanFailed(String msg);

}
