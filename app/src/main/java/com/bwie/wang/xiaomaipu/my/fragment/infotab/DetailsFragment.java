package com.bwie.wang.xiaomaipu.my.fragment.infotab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.ProFragmentPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.ProFragmentView;
import com.bwie.wang.xiaomaipu.my.bean.home.ProFragmentBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2019/1/3.
 *商品的详情页面
 * @author 王丙均
 */
public class DetailsFragment extends Fragment implements ProFragmentView {
    View view;
    @BindView(R.id.web_view_details)
    WebView webView;

    ProFragmentPresenter proFragmentPresenter;

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
        proFragmentPresenter = new ProFragmentPresenter();
        proFragmentPresenter.attachView(this);

//      接受传值
        Intent intent = getActivity().getIntent();
        int commodityId = intent.getIntExtra("commodityId", 6);
//        加载传值
        proFragmentPresenter.loadData(commodityId);

        return view;
    }

    @Override
    public void OnSuccess(ProFragmentBean infoBean) {
        ProFragmentBean.ResultBean result = infoBean.getResult();

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
        });
    }

    @Override
    public void OnFailed(String msg) {
        Log.d("DetailsFragmentOnFailed", "DetailsFragmentOnFailed: "+msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        proFragmentPresenter.detachView();
    }
}
