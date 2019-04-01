package com.example.lightdance.logintest.model.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.lightdance.logintest.app.App;
import com.example.lightdance.logintest.app.AppError;
import com.example.lightdance.logintest.app.MockInfo;

/**
 * 实现类
 *
 * @author LightDance
 * @date 2018/3/5.
 */

public class LoginLogic implements LoginInterface {

    private final static String TAG_USERNAME = "username";
    private final static String TAG_PASSWORD = "userPwd";
    private final static String TAG_IS_REMEMBER = "isRememberPassword";

    @Override
    public void getStoredUserLoginInfo(GetLoginInfoListener listener) {

        UserBean user = getUserInfoFromSharedPreference();
        if (!user.getUsername().isEmpty()) {
            //如果用户名空的，说明之前存数据没存，就不用填了
            //否则要通过这个方法把数据填上，listener参数一般在presenter中new一个，
            // 然后重写接口中的方法实现想要的功能
            listener.onSuccess(user);
        }
    }
    private UserBean getUserInfoFromSharedPreference() {
        //sharedPreferences是个轻量的本地存储工具，getString根据键值对取数据，第二个参数是默认值
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        //构造用户数据

        UserBean user = new UserBean();
        String username = pref.getString(TAG_USERNAME, "");
        user.setUsername(username);
        //看是否密码也被存上，然后进行获取
        if (isRememberPassword()) {
            String userPassword = pref.getString(TAG_PASSWORD, "");
            user.setUserpwd(userPassword);
        }
        return user;
    }

    @Override
    public boolean isRememberPassword() {
        //获取“记没记住密码”参数
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        if (pref == null) {
            return false;
        }
        return pref.getBoolean(TAG_IS_REMEMBER, false);
    }

    @Override
    public void login(UserBean user, LoginListener listener) {
        // 可以先不管下面这些，大概功能是用android的多线程工具延时两秒后执行{}中的线程进行帐密匹配
        // 模拟登录操作
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (user.getUsername().equals(MockInfo.MOCK_USERNAME)
                    && user.getUserpwd().equals(MockInfo.MOCK_PASSWORD)) {
                //匹配成功，用这个方法对用户进行欢迎
                listener.onSuccess(user);
            } else {
                //登录失败，给你说原因
                listener.onFailure(AppError.PASSWORD_NOT_MATCH_USERNAME);
            }


        }, 2000);
    }

    @Override
    public void saveLoginInfo(UserBean user, boolean isRemember) {
        //存登录状态的方法，和最上面取登录状态的方法差不多
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
        editor.putString(TAG_USERNAME, user.getUsername());
        editor.putBoolean(TAG_IS_REMEMBER, isRemember);
        if (isRemember) {
            editor.putString(TAG_PASSWORD, user.getUserpwd());
        } else {
            editor.putString(TAG_PASSWORD, "");
        }
        editor.apply();
    }
}
