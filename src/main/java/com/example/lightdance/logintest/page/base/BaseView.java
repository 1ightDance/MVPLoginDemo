package com.example.lightdance.logintest.page.base;


import com.example.lightdance.logintest.app.MessageLevel;

/**
 * 视图的基类，共有方法写在这里面
 * @author  LightDance
 * @date 2018/2/2.
 */

public interface BaseView<T extends BasePresenterInterface> {
    /*/**
     * 据说这个方法是为了分担onCreate的任务，因此在以最快速度建立好界面后，通过这个方法绑定xml中的控件，
     * 设置点击监听啥的
     */
    //void render();待进一步学习后使用

    /**
     * 参数表详见 {@see com.example.lightdance.takemyadvice.app.MessageLevel}
     * @param msg 信息
     * @param level 默认为TOAST
     */
    void showMessage(String msg, MessageLevel level);

    /**
     * 绑定presenter，通过这个方法互相持有实例
     * @param presenter 对应的Presenter
     */
    void bindPresenter(T presenter);
}
