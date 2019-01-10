package com.bwie.wang.xiaomaipu.my.activity.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.my.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 详情页面
 * @author wangbingjun
 */
public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.all_back)
    ImageView allBack;
    Unbinder unbinder;
    private TabLayout tab;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        unbinder = ButterKnife.bind(this);
        initView();
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(pager);
    }

    void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        pager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
//  点击返回按钮,销毁当前视图,返回上一级目录
    @OnClick(R.id.all_back)
    public void onViewClicked() {
        finish();
    }
}
