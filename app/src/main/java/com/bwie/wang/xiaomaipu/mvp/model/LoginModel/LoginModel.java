package com.bwie.wang.xiaomaipu.mvp.model.LoginModel;

import com.bwie.wang.xiaomaipu.my.bean.loginorregister.LoginBean;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.RegisterBean;
import com.bwie.wang.xiaomaipu.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class LoginModel {
    public Observable<LoginBean> getLoginB(String phone, String pwd){
        Observable<LoginBean> login = HttpUtils.getInstance().api.getLogin(phone, pwd);
        return login;
    }
    public Observable<RegisterBean> getRegisterB(String phone, String pwd){
        Observable<RegisterBean> register = HttpUtils.getInstance().api.getRegister(phone, pwd);
        return register;
    }
}
