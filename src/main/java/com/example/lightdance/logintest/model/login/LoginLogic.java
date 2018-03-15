package com.example.lightdance.logintest.model.login;

import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.lightdance.logintest.app.App;
import com.example.lightdance.logintest.app.AppError;

/**
 * 实现类
 *
 * @author LightDance
 * @date 2018/3/5.
 */

public class LoginLogic implements LoginInterface {

    @Override
    public void getStoredUserLoginInfo(GetLoginInfoListener listener) {
        //sharedpreferences是个轻量的本地存储工具，getstring根据键值对取数据，第二个参数是默认值
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        //构造用户数据
        UserBean user = new UserBean();
        String username = pref.getString("username", "");
        user.setUsername(username);
        //看是否密码也被存上，然后进行获取
        if (isRememberPassword()) {
            String userpassword = pref.getString("userPwd", "");
            user.setUserpwd(userpassword);
        }
        if (user.getUsername().isEmpty()) {
            //如果用户名空的，说明之前存数据失败，就不用填了
            listener.onFailure(AppError.USER_INFO_NOT_FOUND);
            return;
        } else {
            //否则要通过这个方法把数据填上，listener参数一般在presenter中new一个，
            // 然后重写接口中的方法实现想要的功能
            listener.onSuccess(user);
        }

    }

    @Override
    public boolean isRememberPassword() {
        //获取“记没记住密码”参数
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        if (pref == null) {
            return false;
        }
        return pref.getBoolean("isRememberPassword", false);
    }

    @Override
    public void login(UserBean user, LoginListener listener) {
        //登录，模拟了一个正确账号密码
        String mockUsername = "test";
        String mockPassword = "test";
        //可以先不管下面这些，大概功能是用android的多线程工具延时两秒后执行{}中的线程进行帐密匹配
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (user.getUsername().equals(mockUsername) && user.getUserpwd().equals(mockPassword)) {
                //匹配成功，用这个方法对用户进行欢迎
                listener.onSuccess(user);
            } else {
                //登录失败，给你说原因
                listener.onFailure(AppError.PASSWORD_NOT_MATCH_USERNAME);
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
                pref.edit().putString("userPwd", "")
                        .apply();
            }
        }, 2000);
    }

    @Override
    public void saveLoginInfo(UserBean user, boolean isRemember) {
        //存登录状态的方法，和最上面取登录状态的方法差不多
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
        editor.putString("username", user.getUsername());
        editor.putBoolean("isRememberPassword", isRemember);
        if (isRemember) {
            editor.putString("userPwd", user.getUserpwd());
        } else {
            editor.putString("userPwd", "");
        }
        editor.apply();
    }
}
