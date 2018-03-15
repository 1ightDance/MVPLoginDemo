package com.example.lightdance.logintest.page.address;

import com.example.lightdance.logintest.page.base.BasePresenterInterface;
import com.example.lightdance.logintest.page.base.BaseView;

/**
 * 用户中心的契约类，没弄，还是看登录吧
 * @author LightDance
 * @date 2018/3/6.
 */

public class AddressContract {
    interface Presenter extends BasePresenterInterface {
        /**
         * 点了改地址
         */
        void clickModifyAddress();

        /**
         * 查看详情
         */
        void clickDetail();

    }

    interface View extends BaseView<Presenter>{
        /**
         * 显示已有地址
         */
        void showAddress();

        /**
         * 显示加载失败
         */
        void showLoadingFailed();

    }
}
