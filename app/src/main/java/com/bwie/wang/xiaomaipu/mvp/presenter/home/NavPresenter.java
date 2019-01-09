package com.bwie.wang.xiaomaipu.mvp.presenter.home;

import com.bwie.wang.xiaomaipu.base.BasePresenter;
import com.bwie.wang.xiaomaipu.mvp.model.home.HomeModel;
import com.bwie.wang.xiaomaipu.mvp.model.home.NavModel;
import com.bwie.wang.xiaomaipu.mvp.view.home.NavView;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavBean;
import com.bwie.wang.xiaomaipu.my.bean.home.nav.NavChildBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/7.
 *
 * @author 王丙均
 */

public class NavPresenter extends BasePresenter<NavView> {
    private final NavModel navModel;
    public NavPresenter(){
        navModel=new NavModel();
    }

    //首页导航
    public void getNavBea() {
        navModel.getNavBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NavBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(NavBean navBean) {

                        if (navBean!=null){
                            getView().onNavBeanSuccess(navBean);
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
    //首页导航的孩子
    public void getNavChildBea(String firstCategoryId) {
        navModel.getNavChildBean(firstCategoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NavChildBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(NavChildBean navChildBean) {

                        if (navChildBean!=null){
//                            List<NavChildBean.ResultBean> result = navChildBean.getResult();
                            getView().onNavChildBeanSuccess(navChildBean);
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
    //首页导航的孩子们
    public void getNavChildsBea(String categoryId) {
        navModel.getNavChildsBean(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NavChildsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(NavChildsBean navChildsBean) {

                        if (navChildsBean!=null){
//                            List<NavChildBean.ResultBean> result = navChildBean.getResult();
                            getView().onNavChildsBeanSuccess(navChildsBean);
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
