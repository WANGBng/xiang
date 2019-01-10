package com.bwie.wang.xiaomaipu.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.circle.CirclePresenter;
import com.bwie.wang.xiaomaipu.mvp.view.circle.CircleView;
import com.bwie.wang.xiaomaipu.my.activity.MainActivity;
import com.bwie.wang.xiaomaipu.my.adapter.circle.CircleAdapter;
import com.bwie.wang.xiaomaipu.my.bean.circle.CircleBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class CircleFragment extends Fragment implements CircleView {
    public static final String BUNDLE_TITLE = "title";
    Unbinder unbinder;
    View view;
    CirclePresenter circlePresenter;
    CircleAdapter circle_Adapter;
    @BindView(R.id.circe_rec)
    RecyclerView circeRec;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.circle_fragment_layout, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        unbinder = ButterKnife.bind(this, view);

        circlePresenter = new CirclePresenter();
        circlePresenter.attachView(this);
        circlePresenter.getCir();

        return view;

    }

    public static CircleFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        CircleFragment fragment = new CircleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCircleSuccess(CircleBean circleBean) {
        List<CircleBean.ResultBean> result = circleBean.getResult();
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        circeRec.setLayoutManager(manager);
        circle_Adapter = new CircleAdapter(getActivity(), result);
        circeRec.setAdapter(circle_Adapter);
        circle_Adapter.notifyDataSetChanged();
//        circeRec.set
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                加载没有完成,明天继续
            }
        });
    }

    @Override
    public void OnCircleFailed(Throwable throwable) {
        Log.d("圈子数据获取失败", "onFailed: " + throwable.getMessage());

    }

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
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((System.currentTimeMillis() - exitTime) > 2000) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    } else {
                        getActivity().finish();
                        System.exit(0);
                    }
                    return true;
                }
                return false;
            }
        });
    }//    进行获取焦点

    /**
     * 防止内存泄漏,造成手机的内存消耗过大
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        circlePresenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
