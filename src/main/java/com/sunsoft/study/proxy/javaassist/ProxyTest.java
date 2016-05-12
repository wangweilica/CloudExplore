package com.sunsoft.study.proxy.javaassist;

import com.sunsoft.study.proxy.CountService;
import com.sunsoft.study.proxy.CountServiceImpl;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * Author: Wangwei
 * Date: 2016/5/12
 * Desc:
 */
public class ProxyTest {

    public static void main(String[] args) throws Exception {
        CountService delegate = new CountServiceImpl();
        CountService javassistProxy = createJavassistDynamicProxy(delegate);
        javassistProxy.count();
    }

    private static CountService createJavassistDynamicProxy(final CountService delegate) throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[] { CountService.class });
        Class<?> proxyClass = proxyFactory.createClass();
        CountService javassistProxy = (CountService) proxyClass.newInstance();
        ((ProxyObject) javassistProxy).setHandler(new JavaAssitInterceptor(delegate));
        return javassistProxy;
    }

    private static class JavaAssitInterceptor implements MethodHandler {

        final Object delegate;

        JavaAssitInterceptor(Object delegate) {
            this.delegate = delegate;
        }

        public Object invoke(Object self, Method m, Method proceed,
                             Object[] args) throws Throwable {
            System.out.println("----------before---------");
            Object o =  m.invoke(delegate, args);
            System.out.println("----------end---------");
            return o;
        }
    }

}
