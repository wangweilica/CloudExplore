package com.sunsoft.study.zookeeper;

import java.io.Serializable;

/**
 * Author: Wangwei
 * Date: 2016/5/24
 * Desc:
 */
public class UserServiceConfig implements Serializable{

    private static final long serialVersionUID = 9005017901545705096L;

    private  Integer tokenExpired;

    private String registryAddress;

    public UserServiceConfig(Integer tokenExpired, String registryAddress) {
        this.tokenExpired = tokenExpired;
        this.registryAddress = registryAddress;
    }

    public Integer getTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(Integer tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    @Override
    public String toString() {
        return "expired:"+this.getTokenExpired()+",registerAddresss:"+this.getRegistryAddress();
    }
}
