package com.example.lightdance.logintest.model.address;

/**
 * file description
 *
 * @author LightDance
 * @date 2018/3/6.
 */

 class AddressBean {
    private String username;
    private String address;

    public AddressBean(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
