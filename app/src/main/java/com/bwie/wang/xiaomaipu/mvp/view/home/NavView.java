package com.bwie.wang.xiaomaipu.mvp.view.home;

import com.bwie.wang.xiaomaipu.base.BaseView;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.NavChildsBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavChildBean;

/**
 * date:2019/1/7.
 *导航的
 * @author 王丙均
 */

public interface NavView extends BaseView {
    void onNavBeanSuccess(NavBean navBean);
    void onNavBeanFailed(String navMsg);
    void onNavChildBeanSuccess(NavChildBean navChildBean);
    void onNavChildBeanFailed(String navCMsg);
    void onNavChildsBeanSuccess(NavChildsBean navChildsBean);
    void onNavChildsBeanFailed(String navCMsg);
}
