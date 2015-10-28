package com.sunsoft.study.generics.provider;

import java.util.HashMap;
import java.util.Map;

public class ServiceProviderRegistry<T extends Service> {
	
	// 注册表
    private Map<Class<?>, ServiceProvider<?>> registry = new HashMap<>();
    @SuppressWarnings("hiding")
	public <T extends Service> void register(Class<T> clazz, ServiceProvider<T> provider) {
    	 registry.put(clazz, provider);
    }
     
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T extends Service> ServiceProvider<T> lookup(Class<T> cls) {
		return (ServiceProvider<T>) registry.get(cls);
	}
	// 有问题的注册（对照组）
	private Map<Class<T>, ServiceProvider<T>> map = new HashMap<>();
	public  void register1(Class<T> clazz, ServiceProvider<T> provider) {
		map.put(clazz, provider);
    }
}