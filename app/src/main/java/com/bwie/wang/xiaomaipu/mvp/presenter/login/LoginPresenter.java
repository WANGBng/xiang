package com.bwie.wang.xiaomaipu.mvp.presenter.login;

import com.bwie.wang.xiaomaipu.base.BasePresenter;
import com.bwie.wang.xiaomaipu.mvp.model.LoginModel.LoginModel;
import com.bwie.wang.xiaomaipu.mvp.view.loginview.LoginView;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.LoginBean;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    private final LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    //登陆
    public void getLogin(String phone, String pwd) {
        loginModel.getLoginB(phone, pwd)
                //发射事件
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
//                        判断bean是否为空
                        if (loginBean != null) {
                            getView().onLoginSuccess(loginBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().onLoginFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    //注册
    public void getRegister(String phone, String pwd) {
        loginModel.getRegisterB(phone, pwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (registerBean != null) {
                            getView().onRegisterSuccess(registerBean);
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