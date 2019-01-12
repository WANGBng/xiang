package com.bwie.wang.xiaomaipu.my.activity.loginregister;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class EventBusMassage {
    String phone;
    String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public EventBusMassage(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }
}
