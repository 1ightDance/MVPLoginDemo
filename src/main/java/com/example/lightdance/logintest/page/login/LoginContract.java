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
    /**
     * presenter,各种点击事件
     */
    interface Presenter extends BasePresenterInterface {
        void clickLogin(UserBean user , boolean isRemember);


    }

    /**
     * view 各种UI状态。其实一些常用、通用的方法应当提取到BaseView中
     */
    interface View extends BaseView<Presenter> {
        /**
         * 这个方法是为了分担onCreate的任务，因此在以最快速度建立好界面后，通过这个方法绑定xml中的控件，
         * 设置点击监听啥的。可以理解为能在onResume时才做的初始化工作。
         */
        void render();

        void showLogging();

        void showLoginSuccess(String username);

        void showLoginFailure(String description);

        void showRememberState(boolean isRemember);

        void fillLoginInfo(UserBean user);

        void showFailureMessage(AppError error);
    }
}
