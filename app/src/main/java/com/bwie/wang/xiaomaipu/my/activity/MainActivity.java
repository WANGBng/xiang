package com.bwie.wang.xiaomaipu.my.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

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

/**fragment切换页面
 * @author wangbingjun
 * @date 2018/12/29
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Unbinder unbinder;

    private final int ITEM_ONE = 0;
    private final int ITEM_TWO = 2;
    private final int ITEM_THRE = 3;
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator alphaTabsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
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
        String[] titles={"首页","圈子","购物车","订单","我的"};
        public MainAdapter(FragmentManager fm) {
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
