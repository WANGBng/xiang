package com.bwie.wang.xiaomaipu.my.activity.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.login.LoginPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.loginview.LoginView;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.LoginBean;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.RegisterBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注册页面
 * @author wangbingjun
 */
public class RegisterActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.te_jump)
    TextView teJump;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_code)
    EditText registerCode;
    @BindView(R.id.register_pwd)
    EditText registerPwd;
    @BindView(R.id.bt_register)
    Button btRegister;

    Unbinder unbinder;
    LoginPresenter loginPresenter;
    @BindView(R.id.reg_eye)
    ImageView regEye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        unbinder = ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);

    }

    @OnClick({R.id.te_jump, R.id.bt_register,R.id.reg_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.te_jump:
//                前往登陆
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_register:
                String name = registerPhone.getText().toString();
                String password = registerPwd.getText().toString();
                loginPresenter.getRegister(name, password);

                break;
            case R.id.reg_eye:
                //                摁下眼睛显示密码,抬起隐藏眼睛

               /* */regEye.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN){
                        registerPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else if (event.getAction()==MotionEvent.ACTION_UP){
                        registerPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    return false;
                }
            });

                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {

    }

    @Override
    public void onLoginFailed(String msg) {

    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {
        String message = registerBean.getMessage();
        String name = registerPhone.getText().toString();
        String password = registerPwd.getText().toString();
        Toast.makeText(this, "注册成功" + registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (message.equals("注册成功")) {

            EventBus.getDefault().post(new EventBusMassage(name, password));
            finish();
        }
    }

    @Override
    public void onRegisterFailed(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        loginPresenter.detachView();
    }


}
