package com.bwie.wang.xiaomaipu.mvp.model.home;

import com.bwie.wang.xiaomaipu.mvp.presenter.home.NavChildsBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavChildBean;
import com.bwie.wang.xiaomaipu.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * date:2019/1/7.
 *
 * @author 王丙均
 */

public class NavModel {
    //首页导航的
    public Observable<NavBean> getNavBean(){
        Observable<NavBean> navList = HttpUtils.getInstance().api.getNavList();
        return navList;
    }
    //首页导航的孩子
    public Observable<NavChildBean> getNavChildBean(String firstCategoryId){
        Observable<NavChildBean> navTwoList = HttpUtils.getInstance().api.getNavTwoList(firstCategoryId);
        return navTwoList;
    }
    //首页导航的孩子们
    public Observable<NavChildsBean> getNavChildsBean(String categoryId){
        Observable<NavChildsBean> navTwosList = HttpUtils.getInstance().api.getNavTwosList(categoryId);
        return navTwosList;
    }
}
