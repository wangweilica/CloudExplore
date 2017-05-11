package com.sunsoft.study.delayqueue;

import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: Wangwei
 * Date: 2017/5/11
 * Desc:
 */
public class Job {
    private static int size;// 队列大小
    private Set<Task>[] queue; // 环状队列
    private static int index=0;// 指针

    public Job(Set<Task>[] queue) {
        this.queue = queue;
        size = queue.length;
    }

    public void schedule(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 每秒执行一次,环形队列移动一格
                System.out.println(index);
                Set<Task> slot= queue[index];
                if (!CollectionUtils.isEmpty(slot)) {
                    for(Task task:slot){
                        int cycleNum = task.getCycleNum();// 圈数
                        if (cycleNum == 0) {// 当前圈数
                            task.execute();
                            slot.remove(task);
                        } else {
                            task.setCycleNum(cycleNum-1);
                        }
                    }
                }
                index=(index+1)%size;
            }
        }, 0, 1000);
    }
}
