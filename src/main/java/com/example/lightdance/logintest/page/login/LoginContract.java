package com.example.lightdance.logintest.page.login;

import com.example.lightdance.logintest.app.AppError;
import com.example.lightdance.logintest.model.login.UserBean;
import com.example.lightdance.logintest.page.base.BasePresenterInterface;
import com.example.lightdance.logintest.page.base.BaseView;

/**
 * 登录功能的契约类
 * @author LightDance
 * @date 2018/3/4.
 */

class LoginContract {
    interface Presenter extends BasePresenterInterface {
        void clickLogin(UserBean user , boolean isRemember);

        void clickSignup();

        void clickLogout();
    }

    interface View extends BaseView<Presenter> {
        void showLogging();

        void showLoginSuccess(String username);

        void showLoginFailure(String description);

        void showRememberState(boolean isRemember);

        void showLoginInfo(UserBean user);

        void showFailureMessage(AppError error);
    }
}
