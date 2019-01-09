package com.bwie.wang.xiaomaipu.mvp.presenter.home;

import com.bwie.wang.xiaomaipu.base.BasePresenter;
import com.bwie.wang.xiaomaipu.mvp.model.home.HomeModel;
import com.bwie.wang.xiaomaipu.mvp.view.home.HomeView;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeBannerBean;
import com.bwie.wang.xiaomaipu.my.bean.home.HomeCommodityBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class HomeBannerPresenter extends BasePresenter<HomeView>{

    private final HomeModel homeModel;
    public HomeBannerPresenter(){
        homeModel=new HomeModel();
    }//轮播图
    public void getHomeBanner(){
        homeModel.getBannerBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        if (homeBannerBean!=null){
                            getView().onHomeBannerSuccess(homeBannerBean);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
    //首页数据
    public void getHomeCommodity(){
        homeModel.getCommodity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeCommodityBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(HomeCommodityBean homeCommodityBean) {
                        if (homeCommodityBean!=null){
                            HomeCommodityBean.ResultBean result = homeCommodityBean.getResult();
                            getView().onHomeCommoditySuccess(result);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

}