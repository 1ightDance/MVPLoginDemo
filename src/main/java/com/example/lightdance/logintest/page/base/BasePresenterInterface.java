package com.example.lightdance.logintest.page.base;

/**
 * presenter的基类
 *
 * @author LightDance
 * @date 2018/3/2.
 */

public interface BasePresenterInterface {
    /**
     * 和 View (Fragment) 的生命周期结合，用于初始化
     */
    void start();

    /**
     * 销毁
     */
    void destroy();

}
