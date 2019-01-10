package com.bwie.wang.xiaomaipu.my.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.my.fragment.CartFragment;
import com.bwie.wang.xiaomaipu.my.fragment.CircleFragment;
import com.bwie.wang.xiaomaipu.my.fragment.GoodsListFragment;
import com.bwie.wang.xiaomaipu.my.fragment.HomeFragment;
import com.bwie.wang.xiaomaipu.my.fragment.MineFragment;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment切换页面
 *
 * @author wangbingjun
 * @date 2018/12/29
 */

public class MainActivity extends AppCompatActivity {
    Unbinder unbinder;

    private final int ITEM_ONE = 0;
    private final int ITEM_TWO = 2;
    private final int ITEM_THRE = 3;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaTabsIndicator;


    public static final String TAGMainActivity = MainActivity.class.getSimpleName();
    private static final int CODE_UPDATE_UI = 1;
    private String spec = "https://suggest.taobao.com/sug?code=utf-8&q=%E6%89%8B%E6%9C%BA";
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);



        boolean netIsConnection = isNetConnection();
        if (netIsConnection) {
            // 获取网络数据
            getDataFromServer();
        } else {
            // 弹出对话框
            new AlertDialog
                    .Builder(this)
                    .setTitle("提示！")
                    .setMessage("网络未连接，是否打开网络")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
    }


    private boolean isNetConnection() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this,"我没网络",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return networkInfo.isAvailable();
        }
    }

    void getDataFromServer() {
        //自定义适配器
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.addOnPageChangeListener(mainAdapter);
        alphaTabsIndicator.setViewPager(mViewPager);
        alphaTabsIndicator.getTabView(0);
        alphaTabsIndicator.getTabView(1);
        alphaTabsIndicator.getTabView(2);
        alphaTabsIndicator.getTabView(3);
        alphaTabsIndicator.getTabView(4);
    }
    //自定义适配器

    class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {
        List<Fragment> fragments = new ArrayList<>();
        String[] titles = {"首页", "圈子", "购物车", "订单", "我的"};

          MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(HomeFragment.newInstance(titles[0]));
            fragments.add(CircleFragment.newInstance(titles[1]));
            fragments.add(GoodsListFragment.newInstance(titles[2]));
            fragments.add(CartFragment.newInstance(titles[3]));
            fragments.add(MineFragment.newInstance(titles[4]));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //            当滑动时我们几个F进行的交换
            if (ITEM_ONE == position) {
                alphaTabsIndicator.getTabView(0).showNumber(alphaTabsIndicator.getTabView(0).getBadgeNumber() - 1);
            } else if (ITEM_TWO == position) {
                alphaTabsIndicator.getCurrentItemView().removeShow();

            } else if (ITEM_THRE == position) {
                alphaTabsIndicator.removeAllBadge();
            }
        }

        @Override
        public void onPageSelected(int position) {
//            这里是在页面滑动的时候进行的一个动画的效果,根据自己的喜好去设计

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //改变时发生的状态,还在等我去进行一个整体的效果

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
