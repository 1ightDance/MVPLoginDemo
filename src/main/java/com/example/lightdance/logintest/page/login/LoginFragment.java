package com.example.lightdance.logintest.page.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lightdance.logintest.R;
import com.example.lightdance.logintest.app.AppError;
import com.example.lightdance.logintest.app.MessageLevel;
import com.example.lightdance.logintest.model.login.UserBean;
import com.example.lightdance.logintest.page.base.BaseFragment;

/**
 * 契约类中view的实现者，有一个私有变量mPresenter进行数据操作
 *
 * @author LightDance
 * @date 2018/3/5.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {
    private LoginContract.Presenter mPresenter;
    //等待时显示的东西
    private ProgressBar progressBar;
    //欢迎与提示错误用的textview
    private TextView welcomeTV;

    private EditText userNameET;
    private EditText userPwdET;
    private Button loginBtn;
    //记住密码的复选框
    private CheckBox isRememberCB;

    @Override
    public void onResume() {
        //生命周期之一，初始化presenter以在页面显示更新数据
        super.onResume();
        mPresenter.start();
    }


    //baseview的方法，在这实现了
    @Override
    public void bindPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    //绑定xml文件中的控件，按钮设监听
    @Nullable
    @Override  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        progressBar = root.findViewById(R.id.progressbar_login);
        welcomeTV = root.findViewById(R.id.welcome_login);
        userNameET = root.findViewById(R.id.user_name_login);
        userPwdET = root.findViewById(R.id.user_pwd_login);
        loginBtn = root.findViewById(R.id.btn_login);
        isRememberCB = root.findViewById(R.id.checkbox_is_remember_login);
        //注，这里用了lambda表达式，相当于setOnClickListener(new OnClickListener(){
        //                                  @override
        //                                   void onClick(View v){...}
        //                              });

        loginBtn.setOnClickListener((view) -> {
            UserBean user = new UserBean();
            user.setUsername(userNameET.getText().toString());
            user.setUserpwd(userPwdET.getText().toString());
            mPresenter.clickLogin(user, isRememberCB.isChecked());
        });
        return root;
    }

    //展示登录状态
    @Override
    public void showLogging() {
        progressBar.setVisibility(View.VISIBLE);
        enableInteractiveUI(false);
        showMessage("登录中...");
    }

    //展示登录状态
    @Override
    public void showLoginSuccess(String username) {
        progressBar.setVisibility(View.GONE);
        enableInteractiveUI(true);
        welcomeTV.setText("欢迎你" + username);
    }

    //展示登录状态
    @Override
    public void showLoginFailure(String description) {
        progressBar.setVisibility(View.GONE);
        enableInteractiveUI(true);
        welcomeTV.setText(description);
    }

    //显示是否记住了密码
    @Override
    public void showRememberState(boolean isRemember) {
        isRememberCB.setChecked(isRemember);
    }

    //填充用户之前的登录信息
    @Override
    public void showLoginInfo(UserBean user) {
        userNameET.setText(user.getUsername());
        userPwdET.setText(user.getUserpwd());
    }
    //显示失败信息提示
    @Override
    public void showFailureMessage(AppError error) {
        showMessage(error.toString(), MessageLevel.TOAST_IMPORTANT);
    }

    /**
     * 启用/禁用可交互的 UI 控件
     */
    private void enableInteractiveUI(boolean enable) {
        loginBtn.setEnabled(enable);
        isRememberCB.setEnabled(enable);
        userPwdET.setEnabled(enable);
        userNameET.setEnabled(enable);
    }
}
