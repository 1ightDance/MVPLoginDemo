package com.example.lightdance.logintest.page.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.lightdance.logintest.app.MessageLevel;

/**
 * 采用fragment充当界面，activity扮演控制fragment的角色
 * @author LightDance
 * @date 2018/2/2.
 */

public class BaseFragment<P extends BasePresenterInterface> extends Fragment implements BaseView<P>{
    /**
     * 泛型控制presenter具体类型
     */
    protected P presenter;

    /**
     * 参数表详见 {@see com.example.lightdance.takemyadvice.app.MessageLevel}
     * @param message 信息
     * @param level 消息重要等级
     */
    @Override
    public void showMessage(String message , MessageLevel level){
        switch (level){
            case TOAST:
                Toast.makeText(getActivity() , message , Toast.LENGTH_SHORT).show();
                break;
            case TOAST_IMPORTANT:
                Toast.makeText(getActivity() , message , Toast.LENGTH_LONG).show();
                break;
            case SNACKBAR:
                Toast.makeText(getActivity() , message + "from snackbar" , Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

    @Override
    public void bindPresenter(P presenter) {
        //让presenter观察自己(给presenter添加声明周期)
        getLifecycle().addObserver(presenter);
        this.presenter = presenter;
    }

    public void showMessage(String message){
        showMessage(message , MessageLevel.TOAST);
    }
}
