package com.bwie.wang.xiaomaipu.my.fragment.infotab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.application.MyScrollView;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.InfoPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.InfoView;
import com.bwie.wang.xiaomaipu.my.activity.details.InfoActivity;
import com.bwie.wang.xiaomaipu.my.bean.GoodsList.SyncShoppingBean;
import com.bwie.wang.xiaomaipu.my.bean.home.InfoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date:2019/1/3.
 *
 * @author 王丙均
 */
//商品页面
public class ProFragment extends Fragment implements InfoView {
    View view;
    @BindView(R.id.info_simple)
    FlyBanner infoSimple;
    @BindView(R.id.pro_price)
    TextView proPrice;
    @BindView(R.id.pro_num)
    TextView proNum;
    @BindView(R.id.pro_title)
    TextView proTitle;
    /* */
    @BindView(R.id.pro_tit)
    TextView proTit;
    /* @BindView(R.id.pro_details)
     SimpleDraweeView proDetails;*/
    @BindView(R.id.pro_ti)
    TextView proTi;
    @BindView(R.id.js_simple)
    SimpleDraweeView jsSimple;
    @BindView(R.id.pro_pj)
    TextView proPj;
    @BindView(R.id.pj_ti)
    TextView pjTi;
    @BindView(R.id.pl_rec)
    RecyclerView plRec;
    @BindView(R.id.cp_title)
    TextView cpTitle;
    @BindView(R.id.inf_simple)
    ImageView infSimple;
    @BindView(R.id.in_simple)
    ImageView inSimple;
    @BindView(R.id.web_view)
    WebView webView;

    private List<String> images = new ArrayList<>();

    Unbinder unbinder;

    InfoPresenter infoPresenter;
    WebSettings webViewSettings;
    private ScrollView scroll_view;
    MyScrollView myScrollView;
    private int h;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                try {
                    Calendar instance = Calendar.getInstance();
                    int hour = instance.get(Calendar.HOUR_OF_DAY);
                    int tohour = 2;
                    if (hour % 2 == 0) {
                        h = hour + tohour;
                    } else {
                        h = hour - 1 + tohour;
                    }
                    handler.sendEmptyMessageDelayed(0, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }; //上面是轮播图的handler
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            scroll_view.scrollTo(0, 300);// 改变滚动条的位置
        }
    };
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.pro_layout, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        unbinder = ButterKnife.bind(this, view);

//        获取详情的Presenter层
        infoPresenter = new InfoPresenter();
        infoPresenter.attachView(this);

//      接受传值
        Intent intent = getActivity().getIntent();
        int commodityId = intent.getIntExtra("commodityId", 6);
//        加载传值
        infoPresenter.loadData(commodityId);
//       handler刷新,用于轮播图
        handler.sendEmptyMessageDelayed(0, 1000);

        initView(view);


        return view;
    }
    private void initView(View view) {
        scroll_view = (ScrollView) view.findViewById(R.id.scroll_view);
    }

    @Override
    public void OnSuccess(InfoBean infoBean) {
        InfoBean.ResultBean result = infoBean.getResult();

        String[] s = result.getPicture().split("\\,");

        for (int i = 0; i < s.length; i++) {
            images.add(s[i]);
        }
        infoSimple.setImagesUrl(images);
//          获取成功传过来的值
        proTitle.setText(result.getCommodityName());
        proPrice.setText(String.valueOf(result.getPrice()));
        proNum.setText(String.valueOf("已售" + result.getSaleNum() + "件"));

        webViewSettings = webView.getSettings();
        webViewSettings.setLoadWithOverviewMode(true);
        webViewSettings.setUseWideViewPort(true);
        //支持缩放
        webViewSettings.setSupportZoom(true);
//        webViewSettings.获取getDetails里面的网址
        webView.loadDataWithBaseURL(null, result.getDetails(), "text/html", "utf-8", null);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    @Override
    public void OnFailed(String msg) {

    }


    @OnClick({R.id.inf_simple, R.id.in_simple})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inf_simple:
/*

                Intent intent = new Intent(getActivity(),InfoActivity.class);
                int commodityId = rxxpList.get(position).getCommodityId();
                intent.putExtra("commodityId",commodityId);
                startActivity(intent);
*/


                Toast.makeText(getActivity(),"加购成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.in_simple:
                break;
            default:
                break;
        }
    }


//  解决内存异常
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        infoPresenter.detachView();
    }

}
