package com.example.lightdance.logintest.page.login;

import com.example.lightdance.logintest.app.AppError;
import com.example.lightdance.logintest.model.login.LoginInterface;
import com.example.lightdance.logintest.model.login.LoginLogic;
import com.example.lightdance.logintest.model.login.UserBean;

/**
 * presenter，控制view显示什么，能从model存取信息，就是用它隔离的model和view
 *
 * @author LightDance
 * @date 2018/3/4.
 */

public class LoginPresenter<V extends LoginContract.View> implements LoginContract.Presenter {
    private LoginLogic loginLogic;
    private LoginContract.View view;

    //加载这个类的时候把持有（所控制）的view设置下，代码这么写是有一丢丢风险的,
    // 应该在destroy中写点什么，然后view被销毁时在其生命周期中让presenter也执行destroy，但是暂时可以忽略
    public LoginPresenter(V view) {
        //加载LoginLogic，互持实例
        loginLogic = new LoginLogic();
        this.view = view;
        this.view.bindPresenter(this);
    }

    //初始化数据
    @Override
    public void start() {
        boolean isRememberPassword = loginLogic.isRememberPassword();
        view.showRememberState(isRememberPassword);
        if (isRememberPassword) {
            loginLogic.getStoredUserLoginInfo(new LoginInterface.GetLoginInfoListener() {
                @Override
                public void onSuccess(UserBean user) {
                    view.showLoginInfo(user);
                }

                @Override
                public void onFailure(AppError error) {
                    view.showFailureMessage(error);
                }
            });
        }
    }

    //销毁防空指针
    @Override
    public void destroy() {

    }

    //点登陆，在view中被调用
    @Override
    public void clickLogin(UserBean user, boolean isRemember) {
        if (user.getUsername().isEmpty() || user.getUserpwd().isEmpty()) {
            view.showLoginFailure("账号或密码不得为空");
            return;
        }
        view.showLogging();
        //还记得那个LoginInterface里的接口吗，就是这么用的
        loginLogic.login(user, new LoginInterface.LoginListener() {
            @Override
            public void onSuccess(UserBean user) {
                view.showLoginSuccess(user.getUsername());
            }

            @Override
            public void onFailure(AppError error) {
                view.showLoginFailure(error.toString());
            }
        });
    }

    //想有个注册和一个登出功能，但暂时还没写
    @Override
    public void clickSignup() {

    }

    @Override
    public void clickLogout() {

    }
}
