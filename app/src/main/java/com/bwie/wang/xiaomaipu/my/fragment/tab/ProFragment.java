package com.bwie.wang.xiaomaipu.my.fragment.tab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.home.InfoPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.InfoView;
import com.bwie.wang.xiaomaipu.my.bean.home.InfoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.recker.flybanner.FlyBanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.pro_tit)
    TextView proTit;
    @BindView(R.id.pro_details)
    SimpleDraweeView proDetails;
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
    String details;
    private List<String> images = new ArrayList<>();

    Unbinder unbinder;

    InfoPresenter infoPresenter;
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
        infoPresenter = new InfoPresenter();
        infoPresenter.attachView(this);
        infoPresenter.loadData(6);

        handler.sendEmptyMessageDelayed(0, 1000);

        return view;
    }

    @Override
    public void OnSuccess(InfoBean infoBean) {
        InfoBean.ResultBean result = infoBean.getResult();

        details = result.getDetails();
        String[] s = result.getPicture().split("\\,");
        Log.d("TAGTAGTAGTAG", "OnSuccess: " + details);

        for (int i = 0; i < s.length; i++) {
            images.add(s[i]);
        }
        infoSimple.setImagesUrl(images);

        proTitle.setText(result.getCommodityName());
        proPrice.setText(String.valueOf(result.getPrice()));
        proNum.setText(String.valueOf("已售" + result.getSaleNum()+1 + "件"));









        proDetails.setImageURI("http://a.vpimg2.com/upload/merchandise/pdcvis/609796/2018/1019/171/de592765-8724-404a-95ef-db6942ef2d11.jpg");
        cpTitle.setText("J-dc-tit-new dc-tit-new");



    }

    public void Jsoup(View view){
                try {
                    Document doc = Jsoup.connect(details).get();
                    System.out.println(doc.title() + "toubu");
                    Elements select = doc.select("div.J-dc-tit-new dc-tit-new");
                   // Log.i("ss",select("p").attr("dc-title"));
                  //  Toast.makeText("aaa",select("p").attr("dc-title"),Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

   /* private Element select(String p) {

        return null;
    }*/


    @Override
    public void OnFailed(String msg) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        infoPresenter.detachView();
    }
}
