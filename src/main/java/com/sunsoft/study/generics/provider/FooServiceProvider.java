package com.sunsoft.study.generics.provider;

public class FooServiceProvider implements ServiceProvider<FooService> {

     @Override
     public FooService getService() {
          return new FooService();
     }

}