package com.sunsoft.study.thread.disruptor;

public class LongEvent
{
    private long value;

    public void set(long value)
{
    this.value = value;
}

    public long get(){return this.value;}
}