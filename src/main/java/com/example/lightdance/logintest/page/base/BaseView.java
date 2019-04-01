package com.example.lightdance.logintest.page.base;


import com.example.lightdance.logintest.app.MessageLevel;

/**
 * 视图的基类，共有方法写在这里面
 *
 * @author LightDance
 * @date 2018/2/2.
 */

public interface BaseView<T extends BasePresenterInterface> {

    /**
     * 参数表详见 {@link MessageLevel}
     *
     * @param msg   信息
     * @param level 默认为TOAST
     */
    void showMessage(String msg, MessageLevel level);

    /**
     * 绑定presenter，通过这个方法互相持有实例
     *
     * @param presenter 对应的Presenter
     */
    void bindPresenter(T presenter);
}
