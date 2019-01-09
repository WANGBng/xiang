package com.bwie.wang.xiaomaipu.mvp.model.home;

import com.bwie.wang.xiaomaipu.my.bean.home.HomeBannerBean;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeCommodityBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;
import com.bwie.wang.xiaomaipu.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class HomeModel {
    public Observable<HomeBannerBean> getBannerBean(){
        Observable<HomeBannerBean> banner = HttpUtils.getInstance().api.getBanner();
        return banner;
    }//首页展示的
    public Observable<HomeCommodityBean> getCommodity(){
        Observable<HomeCommodityBean> commodity = HttpUtils.getInstance().api.getCommodityList();
        return commodity;
    }




}
