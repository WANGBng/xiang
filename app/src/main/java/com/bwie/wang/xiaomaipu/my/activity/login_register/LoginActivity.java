package com.bwie.wang.xiaomaipu.my.activity.login_register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.wang.xiaomaipu.R;
import com.bwie.wang.xiaomaipu.mvp.presenter.login.LoginPresenter;
import com.bwie.wang.xiaomaipu.mvp.view.loginview.LoginView;
import com.bwie.wang.xiaomaipu.my.activity.MainActivity;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.LoginBean;
import com.bwie.wang.xiaomaipu.my.bean.loginorregister.RegisterBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**登陆页面
 * @author wangbingjun
 * @date 2018/12/29
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.phone)
    ImageView phone;
    @BindView(R.id.image_eye)
    ImageView image_eye;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_check)
    CheckBox loginCheck;
    @BindView(R.id.register_jump)
    TextView registerJump;
    @BindView(R.id.login_bt)
    Button loginBt;

    Unbinder unbinder;
    LoginPresenter loginPresenter;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);

        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);

    }



    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void message(EventBusMassage eventBusMassage){
        loginPhone.setText(eventBusMassage.phone);
        loginPwd.setText(eventBusMassage.pwd);
    }
    //各种点击事件
    @OnClick({R.id.login_check, R.id.register_jump, R.id.login_bt, R.id.image_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_check:
//                点击记住密码
                if (loginCheck.isChecked()) {
                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                } else {
                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();
                }
                break;
            case R.id.register_jump:
//                点击前往注册页面
                Toast.makeText(this,"前往注册",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.login_bt:
                //点击登录按钮进行验证是否不为空
                String phone = loginPhone.getText().toString();
                String pwd = loginPwd.getText().toString();

                loginPresenter.getLogin(phone,pwd);

               if (phone.length()!=0&&pwd.length()!=0){
                }else {
                    Toast.makeText(this,"手机号货密码不能是空的",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.image_eye:
//                摁下眼睛显示密码,抬起隐藏眼睛

               image_eye.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN){
                            loginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }else if (event.getAction()==MotionEvent.ACTION_UP){
                            loginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                        return false;
                    }
                });

                break;
            default:
                Toast.makeText(this,"主人这不是点击的按钮喲",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        String status = loginBean.getStatus();
        String message = loginBean.getMessage();
        //判断成功码
        if (status.equals("0000")){
            String phoneE = loginBean.getResult().getPhone();

            Toast.makeText(this,"message"+message,Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("phone",phoneE);
            intent.putExtras(bundle);
            setResult(1,intent);

            String pwd = loginPwd.getText().toString();
            String phone = loginPhone.getText().toString();
//             && phone.length() <12 && pwd.length()<17
            if (phone.length()!=0&&pwd.length()!=0){
                Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent1);
            }else {
                Toast.makeText(this,"手机号或密码不能是空的",Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onLoginFailed(String msg) {

    }

    @Override
    public void onRegisterSuccess(RegisterBean registerBean) {

    }

    @Override
    public void onRegisterFailed(String msg) {

    }

//    解决内存泄漏或溢出
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        loginPresenter.detachView();
    }
}
