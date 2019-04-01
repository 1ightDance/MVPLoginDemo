package com.example.lightdance.logintest.page.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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

public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {

    //等待时显示的东西

    private ProgressBar progressBar;
    //欢迎与提示错误用的textview

    private TextView welcomeTV;

    private EditText mEtUserName;
    private EditText mEtUserPwd;
    private Button mBtnLogin;
    /**记住密码的复选框*/
    private CheckBox mCbIsRemember;

    @Override
    public void onResume() {
        //就是在这里通过观察者模式通知的presenter开启presenter的生命周期。
        super.onResume();
    }

    /**会在presenter中被调用*/
    @Override
    public void render() {
        View v = getView();
        progressBar = v.findViewById(R.id.progressbar_login);
        welcomeTV = v.findViewById(R.id.welcome_login);
        mEtUserName = v.findViewById(R.id.user_name_login);
        mEtUserPwd = v.findViewById(R.id.user_pwd_login);
        mBtnLogin = v.findViewById(R.id.btn_login);
        mCbIsRemember = v.findViewById(R.id.checkbox_is_remember_login);
        //注，这里用了lambda表达式，相当于setOnClickListener(new OnClickListener(){
        //                                  @override
        //                                   void onClick(View v){...}
        //                              });

        mBtnLogin.setOnClickListener((view) -> {
            UserBean user = new UserBean();
            user.setUsername(mEtUserName.getText().toString());
            user.setUserpwd(mEtUserPwd.getText().toString());
            presenter.clickLogin(user, mCbIsRemember.isChecked());
        });
    }

    //绑定xml文件中的控件，按钮设监听
    @Override  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
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
        mCbIsRemember.setChecked(isRemember);
    }

    /**填充用户之前的登录信息*/
    @Override
    public void fillLoginInfo(UserBean user) {
        mEtUserName.setText(user.getUsername());
        mEtUserPwd.setText(user.getUserpwd());
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
        mBtnLogin.setEnabled(enable);
        mCbIsRemember.setEnabled(enable);
        mEtUserPwd.setEnabled(enable);
        mEtUserName.setEnabled(enable);
    }
}
