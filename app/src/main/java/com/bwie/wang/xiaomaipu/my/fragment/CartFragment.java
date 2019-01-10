package com.bwie.wang.xiaomaipu.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.my.activity.MainActivity;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class CartFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.cart_fragment_layout, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }


        return view;
    }
    public static CartFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    //    进行获取焦点，点击返回键返回上一级
    @Override
    public void onResume() {
        super.onResume();

        getFours();

    }
    //    进行获取焦点
    long exitTime = 0;
    private void getFours() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                    if ((System.currentTimeMillis() - exitTime) > 2000){
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }else {
                        getActivity().finish();
                        System.exit(0);
                    }
                    return true;
                }
                return false;
            }
        });
    }//    进行获取焦点

}
