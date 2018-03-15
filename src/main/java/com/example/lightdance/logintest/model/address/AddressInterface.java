package com.example.lightdance.logintest.model.address;

import com.example.lightdance.logintest.app.AppError;

/**
 * 地址管理类
 *
 * @author LightDance
 * @date 2018/3/6.
 */

public interface AddressInterface {
    public final static int OPERATION_ADD = 0;
    public final static int OPERATION_MODIFY = 1;
    public final static int OPERATION_DELETE = 2;

    interface AddressOperateListener {
        /**
         * 操作成功，传回地址信息
         */
        void onSuccess(AddressBean address);

        void onFailure(AppError error);
    }

    /**
     * 获取地址信息
     *
     * @param listener 回调监听
     */
    void getAddressInfo(AddressOperateListener listener);

    /**
     * 修改地址数据
     * @param address 修改后的地址数据
     * @param operationType 操作类型
     * @param listener 回调监听
     */
    void modifyAddress(AddressBean address, int operationType, AddressOperateListener listener);
}
