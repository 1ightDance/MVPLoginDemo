package com.example.lightdance.logintest.model.login;

import com.example.lightdance.logintest.app.AppError;

/**
 * 登录的model层接口类
 *
 * @author LightDance
 * @date 2018/3/5.
 */

public interface LoginInterface {
    /**
     * 监听器，用于作为参数放在下面的方法中，通过<strong>回调<strong/>方式，
     * 在被用到的地方设置具体的代码逻辑，处理具体问题，在LoginLogic中可以看到怎么用的
     */
    interface LoginListener {
        /**
         * 成功时，把user传给onSuccess方法，这个方法用来欢迎新登陆的用户
         * @param user 用户实体类
         */
        void onSuccess(UserBean user);

        /**
         * 失败时，传入错误原因，Apperror里的tostring方法可以避免每次都打字
         * @param error 错误原因
         */
        void onFailure(AppError error);
    }

    /**
     * 获取登录状态的监听，比如已经填过的用户名密码，是否记住密码啥的，方便填入view中
     */
    interface GetLoginInfoListener {
        /**
         * 成功，提供用户数据
         * @param user 用户
         */
        void onSuccess(UserBean user);

        /**
         * 失败，提示不可以填
         * @param error 原因
         */
        void onFailure(AppError error);
    }

    /**
     * 获取存储的用户信息，如果有的话
     * @param listener 监听器
     */
    void getStoredUserLoginInfo(GetLoginInfoListener listener);

    /**
     * 单个返回值的非耗时接口可以通过函数直接返回值
     * @return 用户是否记住密码
     */
    boolean isRememberPassword();

    /**
     * 登陆
     * @param user 用户
     * @param listener 监听
     */
    void login(UserBean user, LoginListener listener);

    /**
     * 存用户填过的数据到手机存储，
     * @param user 用户信息
     * @param isRemember 是不是要把密码也存上
     */
    void saveLoginInfo(UserBean user, boolean isRemember);

}
