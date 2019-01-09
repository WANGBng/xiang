package com.bwie.wang.xiaomaipu.mvp.view.home;

import com.bwie.wang.xiaomaipu.base.BaseView;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeBannerBean;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeCommodityBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public interface HomeView extends BaseView {

    void onHomeBannerSuccess(HomeBannerBean bannerBean);
    void onHomeCommoditySuccess(HomeCommodityBean.ResultBean homeCommodityBean);

    void onFailed(Throwable t);
}
