package com.bwie.wang.xiaomaipu.mvp.view;

import com.bwie.wang.xiaomaipu.base.BaseView;
import com.bwie.wang.xiaomaipu.my.bean.home.ProFragmentBean;

/**
 * date:2019/1/2.
 *
 * @author 王丙均
 */

public interface ProFragmentView extends BaseView {
    void OnSuccess(ProFragmentBean proFragmentBean);
    void OnFailed(String msg);
//    void OnSyncShoppingBeanSuccess(SyncShoppingBean syncShoppingBean);
//    void OnSyncShoppingBeanFailed(String msg);

}
