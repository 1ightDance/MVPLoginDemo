package com.example.lightdance.logintest.page.login;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

import com.example.lightdance.logintest.app.AppError;
import com.example.lightdance.logintest.model.login.LoginInterface;
import com.example.lightdance.logintest.model.login.LoginLogic;
import com.example.lightdance.logintest.model.login.UserBean;
import com.example.lightdance.logintest.page.base.BasePresenterImpl;

/**
 * presenter，控制view显示什么，能从model存取信息，就是用它隔离的model和view
 *
 * @author LightDance
 * @date 2018/3/4.
 */

public class LoginPresenter  extends BasePresenterImpl implements LoginContract.Presenter {
    private LoginLogic loginLogic;
    private LoginContract.View mView;

    /**
     * 加载这个类的时候把持有（所控制）的view设置下，代码这么写是有一丢丢风险的,
     * 应该在destroy中写点什么，然后view被销毁时在其生命周期中让presenter也执行destroy，但是暂时可以忽略，
     * 因为RxJava中有定义好的方法防止内存泄漏和空指针异常
     */
    LoginPresenter(LoginContract.View view) {
        //加载LoginLogic用model层进行数据操作
        loginLogic = new LoginLogic();
        //view和presenter互持实例
        this.mView = view;
        this.mView.bindPresenter(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据
        mView.render();
    }

    @Override
    public void onStart() {
        //记住密码的自动填写操作
        boolean isRememberPassword = loginLogic.isRememberPassword();
        mView.showRememberState(isRememberPassword);
        if (isRememberPassword) {
            loginLogic.getStoredUserLoginInfo(new LoginInterface.GetLoginInfoListener() {
                @Override
                public void onSuccess(UserBean user) {
                    mView.fillLoginInfo(user);
                }

                @Override
                public void onFailure(AppError error) {
                    mView.showFailureMessage(error);
                }
            });
        }
    }

    /**
     * 销毁防空指针:有时候数据获取到时，用户已经把界面关了，这时候再去回调界面的东西会报空指针异常。
     * 因此有时候需要在onDestroy中解除绑定啥的。
     */
    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {

    }

    //点登陆，在view中被调用
    @Override
    public void clickLogin(UserBean user, boolean isRemember) {
        if (user.getUsername().isEmpty() || user.getUserpwd().isEmpty()) {
            mView.showLoginFailure("账号或密码不得为空");
            return;
        }
        mView.showLogging();
        //还记得那个LoginInterface里的接口吗，就是这么用的
        loginLogic.login(user, new LoginInterface.LoginListener() {
            @Override
            public void onSuccess(UserBean user) {
                loginLogic.saveLoginInfo(user , isRemember);
                mView.showLoginSuccess(user.getUsername());
            }

            @Override
            public void onFailure(AppError error) {
                mView.showLoginFailure(error.toString());
            }
        });
    }
}
