package com.bwie.wang.xiaomaipu.mvp.presenter.circle;

import android.util.Log;

import com.bwie.wang.xiaomaipu.base.BasePresenter;
import com.bwie.wang.xiaomaipu.mvp.model.circle.CircleModel;
import com.bwie.wang.xiaomaipu.mvp.view.circle.CircleView;
import com.bwie.wang.xiaomaipu.my.bean.circle.CircleBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2019/1/4.
 *
 * @author 王丙均
 */

public class CirclePresenter extends BasePresenter<CircleView> {
    private static final String TAG = "CirclePresenter";
    private final CircleModel circleModel;
    public CirclePresenter(){
        circleModel= new CircleModel();
    }

    public void getCir(){
        circleModel.getCircleB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CircleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(CircleBean circleBean) {
                        getView().onCircleSuccess(circleBean);
                        Log.e(TAG, "onNext: "+circleBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().OnCircleFailed(e);
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
