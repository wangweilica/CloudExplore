package com.sunsoft.study.generics;

import org.junit.Test;

import com.sunsoft.study.generics.provider.FooService;
import com.sunsoft.study.generics.provider.FooServiceProvider;
import com.sunsoft.study.generics.provider.Service;
import com.sunsoft.study.generics.provider.ServiceProvider;
import com.sunsoft.study.generics.provider.ServiceProviderRegistry;

/**
 * @File: GenericsTest.java
 * @Date: 2015年10月27日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015 
 *
 * @注意：本内容仅限于本人使用，禁止外泄以及用于其他的商业目的
 */
public class GenericsTest {
	
     @Test
     public void ServiceProviderTest() {
    	 ServiceProviderRegistry<? extends Service> registry = new ServiceProviderRegistry<Service>();
         registry.register(FooService.class, new FooServiceProvider());
         ServiceProvider<FooService> provider = registry.lookup(FooService.class);
         // 生产中
         provider.getService().product();;
     }
}
