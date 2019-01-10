package com.bwie.wang.xiaomaipu.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.my.activity.MainActivity;
import com.bwie.wang.xiaomaipu.my.activity.mine.My_AddressActivity;
import com.bwie.wang.xiaomaipu.my.activity.mine.My_CircleActivity;
import com.bwie.wang.xiaomaipu.my.activity.mine.My_DataActivity;
import com.bwie.wang.xiaomaipu.my.activity.mine.My_FootprintsActivity;
import com.bwie.wang.xiaomaipu.my.activity.mine.My_WalletActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 * 我的页面进行跳转
 */
public class MineFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";
    View view;
    @BindView(R.id.personal_data)
    TextView personalData;
    @BindView(R.id.layput_person_da)
    LinearLayout layputPersonDa;
    @BindView(R.id.personal_circle)
    TextView personalCircle;
    @BindView(R.id.layput_person_circle)
    LinearLayout layputPersonCircle;
    @BindView(R.id.personal_footprints)
    TextView personalFootprints;
    @BindView(R.id.layput_person_footprints)
    LinearLayout layputPersonFootprints;
    @BindView(R.id.personal_wallet)
    TextView personalWallet;
    @BindView(R.id.layout_personal_wallet)
    LinearLayout layoutPersonalWallet;
    @BindView(R.id.personal_address)
    TextView personalAddress;
    @BindView(R.id.layout_personal_address)
    LinearLayout layoutPersonalAddress;
    @BindView(R.id.wode_simple)
    SimpleDraweeView wodeSimple;
    @BindView(R.id.wode_name)
    TextView wodeName;




    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.mine_fragment_layout, container, false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static MineFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        MineFragment fragment = new MineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.personal_data, R.id.layput_person_da, R.id.personal_circle, R.id.layput_person_circle, R.id.personal_footprints, R.id.layput_person_footprints, R.id.personal_wallet, R.id.layout_personal_wallet, R.id.personal_address, R.id.layout_personal_address, R.id.wode_simple, R.id.wode_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_data:
//              跳转到个人资料

                setMyDataActivity();
                break;
            case R.id.layput_person_da:
//                跳转到个人资料
                setMyDataActivity();
                break;
            case R.id.personal_circle:
//              跳转到我的圈子
                setMyCircleActivity();
                break;
            case R.id.layput_person_circle:
                //              跳转到我的圈子
                setMyCircleActivity();
                break;
            case R.id.personal_footprints:
                //跳转到我的足迹
                setMyFootprintsActivity();
                break;
            case R.id.layput_person_footprints:
                //跳转到我的足迹
                setMyFootprintsActivity();
                break;
            case R.id.personal_wallet:
//                跳转到我的钱包
                setMyWalletActivity();
                break;
            case R.id.layout_personal_wallet:
//                跳转到我的钱包
                setMyWalletActivity();
                break;
            case R.id.personal_address:
//                跳转到地址
                setMyAddressActivity();
                break;
            case R.id.layout_personal_address:
//                跳转到地址
                setMyAddressActivity();
                break;
            case R.id.wode_simple:

                break;
            case R.id.wode_name:
                wodeName.getText();
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
//                        .show()
//                        .setTitle("修改");
                        //.getWindow().setContentView(R.layout.alert_wode_layout)
                //.setView(new EditText(getActivity()))
               // .create();
                break;
            default:
                break;
        }
    }
//    抽取跳转
    private void setMyAddressActivity() {
        //跳转到地址
        Intent intentMyaddressActivity = new Intent(getActivity(), My_AddressActivity.class);
        startActivity(intentMyaddressActivity);
    }

    private void setMyWalletActivity() {
        //跳转到我的钱包
        Intent intentMywalletActivity = new Intent(getActivity(), My_WalletActivity.class);
        startActivity(intentMywalletActivity);
    }

    private void setMyFootprintsActivity() {
        //跳转到我的足迹
        Intent intentMyfootprintsActivity = new Intent(getActivity(), My_FootprintsActivity.class);
        startActivity(intentMyfootprintsActivity);
    }

    private void setMyCircleActivity() {
        //跳转到我的圈子
        Intent intentMycircleActivity = new Intent(getActivity(), My_CircleActivity.class);
        startActivity(intentMycircleActivity);
    }

    private void setMyDataActivity() {
        Intent intentMyDaActivity = new Intent(getActivity(), My_DataActivity.class);
        startActivity(intentMyDaActivity);
    }
//    上面是抽取跳转

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

    //防止内存的溢出
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
