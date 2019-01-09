package com.bwie.wang.xiaomaipu.mvp.view.loginview;

import com.bwie.wang.xiaomaipu.base.BaseView;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.LoginBean;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.RegisterBean;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public interface LoginView extends BaseView {
    void onLoginSuccess(LoginBean loginBean);
    void onLoginFailed(String msg);

    void onRegisterSuccess(RegisterBean registerBean);
    void onRegisterFailed(String msg);
}
