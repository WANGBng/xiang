package com.bwie.wang.xiaomaipu.mvp.view.circle;

import com.bwie.wang.xiaomaipu.base.BaseView;
import com.bwie.wang.xiaomaipu.my.bean.circle.CircleBean;

/**
 * date:2019/1/4.
 *
 * @author 王丙均
 */

public interface CircleView extends BaseView {
    void onCircleSuccess(CircleBean circleBean);
    void OnCircleFailed(Throwable throwable);
}
