package com.sunsoft.study.proxy;

public class CountServiceImpl implements CountService {
  
    private int count = 0;  
  
    public int count() throws InterruptedException {
        Thread.sleep(1000);
        ++count;
        System.out.println(count);
        return  count;
    }  
}  