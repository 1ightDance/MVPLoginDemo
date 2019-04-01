package com.example.lightdance.logintest.model.address;

import com.example.lightdance.logintest.app.App;
import com.example.lightdance.logintest.app.AppError;
import com.example.lightdance.logintest.app.MockInfo;

/**
 * 地址信息的具体逻辑，对{@link AddressInterface}的实现
 *
 * @author LightDance
 * @date 2018/3/7.
 */

public class AddressLogic implements AddressInterface {
    @Override
    public void getAddressInfo(AddressOperateListener listener) {
        //假设这里是访问数据库等操作，然后操作成功返回了一条数据。
        AddressBean result = new AddressBean(MockInfo.MOCK_USERNAME , MockInfo.MOCK_ADDRESS);
        listener.onSuccess(result);
    }

    @Override
    public void modifyAddress(AddressBean address, int operationType, AddressOperateListener listener) {
        //这里假设因为网络原因操作失败了，调用操作失败的逻辑
        listener.onFailure(AppError.NETWORK_LINK_ERROR);
    }
}
