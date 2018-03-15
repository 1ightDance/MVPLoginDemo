package com.example.lightdance.logintest.page.login;

import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lightdance.logintest.R;

/**
 * 登录功能的activity，作为fragment的 容器
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment fragment =new  LoginFragment();
        LoginContract.Presenter presenter =new  LoginPresenter<>(fragment);

        fragment.bindPresenter(presenter);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout_container , fragment).commit();
    }
}
