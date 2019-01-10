package com.bwie.wang.xiaomaipu.my.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bwie.wang.xiaomaipu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author wangbingjun
 */
public class My_DataActivity extends AppCompatActivity {

    @BindView(R.id.te)
    TextView te;
    @BindView(R.id.my_user_simple)
    SimpleDraweeView myUserSimple;
    @BindView(R.id.tee)
    TextView tee;
    @BindView(R.id.mye_user_name)
    TextView myeUserName;
    @BindView(R.id.m_pass)
    TextView mPass;
    @BindView(R.id.my_pass)
    TextView myPass;
    Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__data);

        unbinder = ButterKnife.bind(this);
    }

    @OnClick({R.id.my_user_simple, R.id.mye_user_name, R.id.my_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_user_simple:
//               点击头像调取相机相册

                break;
            case R.id.mye_user_name:
//                点击修改姓名
                break;
            case R.id.my_pass:
//                点击修改密码
                break;
            default:
                break;
        }
    }



}
