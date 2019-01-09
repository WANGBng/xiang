package com.bwie.wang.xiaomaipu.my.activity.details;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bwie.wang.xiaomaipu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author wangbingjun
 */
public class InfoActivity extends AppCompatActivity {

    Unbinder unbinder;
    @BindView(R.id.all_back)
    ImageView allBack;
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


    @Override
    protected void onStart() {
        super.onStart();
      /*  if (allBack){
            getActionBar().setTitle(R.string.connect_to_internet);
        }*/
    }
}
