package com.example.lightdance.logintest.page.base;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

/**
 * 实现basePresenter中的一些基本方法，为Presenter设置声明周期
 * 主要和生命周期、变量初始化相关
 *
 * @author LightDance
 * @date 2018/4/1.
 */

public class BasePresenterImpl implements BasePresenterInterface{

    private boolean isFirstStarted = true;

    /**
     * 注意，这个不是通过Lifecycle管理的onCreate()，是我们自己定义的！没！有！重！写！
     */
    public void onCreate() {}

    /**
     * 注意，这个不是通过Lifecycle管理的onStart()，是我们自己定义的！没！有！重！写！
     */
    public void onStart() {
    }

    /**
     * 由于view和presenter生命周期不一致，因此有一部分工作要在onResume中完成
     * 即：view运行到resume之后，presenter才开启生命周期
     *
     * @param owner the component, whose state was changed
     */
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        if (isFirstStarted) {
            onCreate();
            isFirstStarted = false;
        }
        onStart();
    }
}
