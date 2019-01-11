package com.bwie.wang.xiaomaipu.mvp.model.circle;

import android.util.Log;

import com.bwie.wang.xiaomaipu.my.bean.circle.CircleBean;
import com.bwie.wang.xiaomaipu.my.utils.HttpUtils;

import io.reactivex.Observable;

/**
 * date:2019/1/4.
 *
 * @author 王丙均
 */

public class CircleModel {
    /**
     * 圈子
     */
    public Observable<CircleBean> getCircleB(){
        Observable<CircleBean> circle = HttpUtils.getInstance().api.getCircle();
        return circle;
    }
}
