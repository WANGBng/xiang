package com.bwie.wang.xiaomaipu.mvp.presenter.home;

import android.util.Log;

import com.bwie.wang.xiaomaipu.base.BasePresenter;
import com.bwie.wang.xiaomaipu.mvp.view.ProFragmentView;
import com.bwie.wang.xiaomaipu.my.bean.home.ProFragmentBean;
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

public class ProFragmentPresenter extends BasePresenter<ProFragmentView>{
    public void loadData(int commodityId){
        HttpUtils.getInstance().api.queryGoodsByPid(commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProFragmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ProFragmentBean proFragmentBean) {
                        if (proFragmentBean!=null){
                            try {
                                getView().OnSuccess(proFragmentBean);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            Log.e("TAG", "onNext: "+proFragmentBean.toString().length() );
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getView().OnFailed(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
