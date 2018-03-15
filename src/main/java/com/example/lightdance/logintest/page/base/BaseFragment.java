package com.example.lightdance.logintest.page.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.lightdance.logintest.app.MessageLevel;

/**
 * 采用fragment充当界面，activity扮演控制fragment的角色
 * @author LightDance
 * @date 2018/2/2.
 */

public class BaseFragment extends Fragment {

    /**
     * 参数表详见 {@see com.example.lightdance.takemyadvice.app.MessageLevel}
     * @param message 信息
     * @param level 默认为TOAST
     */
    public void showMessage(String message , MessageLevel level){
        level = MessageLevel.TOAST;
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
    public void showMessage(String message){
        showMessage(message , MessageLevel.TOAST);
    }
}
