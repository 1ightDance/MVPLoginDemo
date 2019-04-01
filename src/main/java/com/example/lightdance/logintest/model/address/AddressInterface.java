package com.example.lightdance.logintest.model.address;

import com.example.lightdance.logintest.app.AppError;

/**
 * 地址管理接口，定义要实现的方法。
 * 其实用静态常类定义操作方式的设计并不好，正确方法是再定义专门的增加，删除，修改方法，
 * 而不是提供一个通用接口{@link #modifyAddress}.这会将不同的职责耦合在一起。
 *
 * @author LightDance
 * @date 2018/3/6.
 */

public interface AddressInterface {
    public final static int OPERATION_ADD = 0;
    public final static int OPERATION_MODIFY = 1;
    public final static int OPERATION_DELETE = 2;

    /**
     * 回调接口，用于控制获取到model层的数据之后的操作。
     */
    interface AddressOperateListener {
        /**
         * 操作成功，传回地址信息
         * @param address 地址操作成功时引发的逻辑
         */
        void onSuccess(AddressBean address);

        /**
         * 失败时引发的逻辑
         * @param error 错误类型
         */
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
