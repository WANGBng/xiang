package com.bwie.wang.xiaomaipu.mvp.presenter.home;

import android.util.Log;

import com.bwie.wang.xiaomaipu.base.BasePresenter;
import com.bwie.wang.xiaomaipu.mvp.view.InfoView;
import com.bwie.wang.xiaomaipu.my.bean.home.InfoBean;
import com.bwie.wang.xiaomaipu.my.utils.HttpUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/3.
 *
 * @author 王丙均
 */

public class InfoPresenter extends BasePresenter<InfoView>{
    public void loadData(int commodityId){
        HttpUtils.getInstance().api.queryGoodsByPid(commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(InfoBean infoBean) {
                        if (infoBean!=null){
                            try {
                                getView().OnSuccess(infoBean);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            Log.e("TAG", "onNext: "+infoBean.toString().length() );
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
