package com.example.lightdance.logintest.page.login;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lightdance.logintest.R;

/**
 * 登录功能的activity，作为fragment的 容器
 * @author LightDance
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //具体界面逻辑放给Fragment管理，activity仅负责绑定和管理工作。
        LoginFragment fragment = new LoginFragment();
        LoginContract.Presenter presenter = new LoginPresenter(fragment);
        //绑定
        fragment.bindPresenter(presenter);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout_container, fragment).commit();
    }
}
