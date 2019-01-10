package com.bwie.wang.xiaomaipu.my.fragment.infotab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.InfoPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.InfoView;
import com.bwie.wang.xiaomaipu.my.bean.GoodsList.SyncShoppingBean;
import com.bwie.wang.xiaomaipu.my.bean.home.InfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/1/3.
 *
 * @author 王丙均
 */
//详情页面
public class DetailsFragment extends Fragment implements InfoView {
    View view;
    @BindView(R.id.web_view_details)
    WebView webView;

    InfoPresenter infoPresenter;

    WebSettings webViewSettings;
    Unbinder unbinder;
    String details;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.details_layout, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        unbinder = ButterKnife.bind(this, view);
        infoPresenter = new InfoPresenter();
        infoPresenter.attachView(this);

//      接受传值
        Intent intent = getActivity().getIntent();
        int commodityId = intent.getIntExtra("commodityId", 6);
//        加载传值
        infoPresenter.loadData(commodityId);

        return view;
    }

    @Override
    public void OnSuccess(InfoBean infoBean) {
        InfoBean.ResultBean result = infoBean.getResult();

        details = result.getDetails();
        webViewSettings = webView.getSettings();
        webViewSettings.setLoadWithOverviewMode(true);
        webViewSettings.setUseWideViewPort(true);
        //支持缩放
        webViewSettings.setSupportZoom(true);

        webView.loadDataWithBaseURL(null,details,"text/html","utf-8",null);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }); /* */
    }

    @Override
    public void OnFailed(String msg) {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        infoPresenter.detachView();
    }
}
