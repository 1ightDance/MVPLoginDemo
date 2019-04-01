package com.example.lightdance.logintest.page.base;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;

/**
 * 基类presenter,extends了lifecycleObserver的一些方法，以便为Presenter添加生命周期
 * 可以根据需要添加生命周期相关逻辑，避免生命周期相关方法变得臃肿
 *
 * 详细说明请参考 观察者模式，LifecycleObserver
 *
 * onStart和onCreate没有出现
 *
 * @author LightDance
 * @date 2018/3/2.
 */

public interface BasePresenterInterface extends DefaultLifecycleObserver {


    @Override
    default void onResume(@NonNull LifecycleOwner owner) {

    }
    @Override
    default void onPause(@NonNull LifecycleOwner owner) {

    }

    @Override
    default void onStop(@NonNull LifecycleOwner owner) {

    }

    @Override
    default void onDestroy(@NonNull LifecycleOwner owner) {

    }

}
