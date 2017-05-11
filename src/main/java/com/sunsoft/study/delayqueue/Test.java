package com.sunsoft.study.delayqueue;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Wangwei
 * Date: 2017/5/11
 * Desc:
 */
public class Test {
    private static int SIZE = 10;// 队列大小

    public static void main(String[] args) {
        Set<Task>[] queue = new Set[SIZE];
        Job job= new Job(queue);
        Task task= new Task(1);// 15秒之后执行，转过一圈之后第五个slot
        Set<Task> tasks= new HashSet<>();
        tasks.add(task);
        queue[5] = tasks;
        job.schedule();
    }
}
