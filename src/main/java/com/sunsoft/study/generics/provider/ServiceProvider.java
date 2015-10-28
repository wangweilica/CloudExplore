package com.sunsoft.study.generics.provider;

public interface ServiceProvider<T extends Service> {

    public T getService();
}