package com.bwie.wang.xiaomaipu.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.goodslist.GoodsListPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.goodslist.GoodsListView;
import com.bwie.wang.xiaomaipu.my.activity.MainActivity;
import com.bwie.wang.xiaomaipu.my.adapter.goods.GoodsListAdapter;
import com.bwie.wang.xiaomaipu.my.bean.GoodsList.GoodsListBean;
import com.bwie.wang.xiaomaipu.my.bean.GoodsList.SyncShoppingBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class GoodsListFragment extends Fragment implements GoodsListView{
    public static final String BUNDLE_TITLE = "title";
    View view;
    @BindView(R.id.shoppingfragment_cartrecycler)
    RecyclerView shoppingfragmentCartrecycler;
    @BindView(R.id.shoppingfragment_checkbox)
    CheckBox shoppingfragmentCheckbox;
    @BindView(R.id.shoppingfragment_price)
    TextView shoppingfragmentPrice;
    @BindView(R.id.shoppingfragment_button)
    Button shoppingfragmentButton;
    Unbinder unbinder;
    GoodsListPresenter goodsListPresenter;
    GoodsListAdapter goodsListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.goods_fragment_layout, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        goodsListPresenter = new GoodsListPresenter();
        goodsListPresenter.attachView(this);
        goodsListPresenter.getGoodsListModel();


//        goodsListPresenter.getGoodsListModel(userId,1,"sessionId","1547119469374299");
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.shoppingfragment_checkbox, R.id.shoppingfragment_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shoppingfragment_checkbox:
                break;
            case R.id.shoppingfragment_button:
                break;
            default:
                break;
        }
    }
    public static GoodsListFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        GoodsListFragment fragment = new GoodsListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void OnGoodsListSuccess(GoodsListBean goodsListBean) {
        List<GoodsListBean.ResultBean> result = goodsListBean.getResult();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        shoppingfragmentCartrecycler.setLayoutManager(layoutManager);
        goodsListAdapter = new GoodsListAdapter(getActivity(),result);
        shoppingfragmentCartrecycler.setAdapter(goodsListAdapter);

    }

    @Override
    public void OnGoodsListFailed(String msg) {

    }

    @Override
    public void OnSyncShoppingBeanSuccess(SyncShoppingBean syncShoppingBean) {

    }

    @Override
    public void OnSyncShoppingBeanFailed(String msg) {

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
                       /* Toast.makeText(getActivity(),"再按一次就退出了哟",Toast.LENGTH_SHORT).show();
                        exitTime = System.currentTimeMillis();*/
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        goodsListPresenter.detachView();
    }
}
