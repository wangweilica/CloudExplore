package com.sunsoft.study.zookeeper;



/**
 * Author: Wangwei
 * Date: 2016/5/24
 * Desc:
 */
public class Application {
    private ZkData zkData;

    private ZkData getUserServiceConfig() {
        //首次获取时，连接zk取得配置，并监听配置变化
        Zk zk = new Zk();
        zkData = zk.readData(Zk.USERSERVICE_CONFIG_PATH);
        System.out.println("config => " + zkData.getDataString());
        return zkData;
    }

    /**
     * 模拟程序运行
     *
     * @throws InterruptedException
     */
    public void run() throws InterruptedException {
        getUserServiceConfig();
        connectionDubboService();
    }

    private void connectionDubboService() throws InterruptedException {
        System.out.println("【准备建立连接...】");
        System.out.println(zkData.getDataString());
        Thread.sleep(500);
        System.out.println("连接成功...");
    }

    public void loadFromDB() {
        UserServiceConfig config = new UserServiceConfig(1801, "xxx.xx.xx.xx");
        zkData = new ZkData(config.toString().getBytes(), null);
    }

    public void updateConfigToZk() {
        Zk zk = new Zk();
        if (!zk.existsNode(Zk.USERSERVICE_CONFIG_PATH)) {
            zk.createPath(Zk.USERSERVICE_CONFIG_PATH, zkData);
        } else {
            zk.writeData(Zk.USERSERVICE_CONFIG_PATH, zkData);
        }
    }
}
