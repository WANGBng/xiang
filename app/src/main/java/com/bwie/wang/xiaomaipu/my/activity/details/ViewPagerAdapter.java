package com.bwie.wang.xiaomaipu.my.activity.details;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwie.wang.xiaomaipu.my.fragment.tab.DetailsFragment;
import com.bwie.wang.xiaomaipu.my.fragment.tab.EvaluateFragment;
import com.bwie.wang.xiaomaipu.my.fragment.tab.ProFragment;

/**
 * date:2019/1/3.
 *
 * @author 王丙均
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] menus={"商品","详情","评论"};


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProFragment();
            case 1:
                return new DetailsFragment();
            case 2:
                return new EvaluateFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menus[position];
    }

    @Override
    public int getCount() {
        return menus.length;
    }
}

