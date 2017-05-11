package com.sunsoft.study.delayqueue;

/**
 * Author: Wangwei
 * Date: 2017/5/11
 * Desc:
 */
public class Task {

    private int cycleNum;

    public Task(int cycleNum) {
        this.cycleNum = cycleNum;
    }

    public void execute() {
        System.out.println("task completed!");
    }

    public int getCycleNum() {
        return cycleNum;
    }

    public void setCycleNum(int cycleNum) {
        this.cycleNum = cycleNum;
    }
}
