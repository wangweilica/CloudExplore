package com.sunsoft.study.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * ZooKeeper Java Api
 * ZK Api Version: 3.4.8
 *
 * @author wwei
 */
public class Zk implements Watcher {

    public static final int SESSION_TIMEOUT = 10000;
    public static final String CONNECTION_STRING = "192.168.1.49:2181";//192.168.1.234:2181,192.168.1.234:2182
    public static final String USERSERVICE_CONFIG_PATH = "/config";

    private ZooKeeper client = null;

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public Zk(String connectString, int sessionTimeout) {
        super();
        createConnection(connectString, sessionTimeout);
    }

    public Zk() {
        super();
        createConnection(this.CONNECTION_STRING, this.SESSION_TIMEOUT);
    }

    /**
     * 创建ZK连接
     *
     * @param connectString  ZK服务器地址列表
     * @param sessionTimeout Session超时时间
     */
    public void createConnection(String connectString, int sessionTimeout) {
        this.releaseConnection();
        try {
            client = new ZooKeeper(connectString, sessionTimeout, this);
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            System.out.println("连接创建失败，发生 InterruptedException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("连接创建失败，发生 IOException");
            e.printStackTrace();
        }
    }

    /**
     * 关闭ZK连接
     */
    public void releaseConnection() {
        if (this.client != null) {
            try {
                this.client.close();
            } catch (InterruptedException e) {
                // ignore 
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建节点
     *
     * @param path 节点path
     * @param data 初始数据内容
     * @return boolean
     */
    public boolean createPath(String path, ZkData data) {
        try {
            this.client.create(path, data.getData(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            System.out.println("节点创建失败，发生KeeperException");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("节点创建失败，发生 InterruptedException");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 读取指定节点数据内容
     *
     * @param path 节点path
     * @return String
     */
    public ZkData readData(String path) {
        try {
            byte[] bytes = this.client.getData(path, false, null);
            return new ZkData(bytes, new Stat());
        } catch (KeeperException e) {
            System.out.println("读取数据失败，发生KeeperException，path: " + path);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("读取数据失败，发生 InterruptedException，path: " + path);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新指定节点数据内容
     *
     * @param path 节点path
     * @param data 数据内容
     * @return boolean
     */
    public boolean writeData(String path, ZkData data) {
        try {
            this.client.setData(path, data.getData(), -1);
        } catch (KeeperException e) {
            System.out.println("更新数据失败，发生KeeperException，path: " + path);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("更新数据失败，发生 InterruptedException，path: " + path);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除指定节点
     * @param path 节点path
     */
    public void deleteNode(String path) {
        try {
            this.client.delete(path, -1);
        } catch (KeeperException e) {
            System.out.println("删除节点失败，发生KeeperException，path: " + path);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("删除节点失败，发生 InterruptedException，path: " + path);
            e.printStackTrace();
        }
    }

    public boolean existsNode(String path) {
        try {
            return this.client.exists(path, false)!=null;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getChildren(String path) {
        try {
            return this.client.getChildren(path, false);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

  /*  public static void main( String[] args ) {
 
        Zk sample = new Zk();
        sample.createConnection( CONNECTION_STRING, SESSION_TIMEOUT ); 
        if ( sample.createPath( ZK_PATH, "我是节点初始内容" ) ) { 
            System.out.println(); 
            System.out.println( "数据内容: " + sample.readData( ZK_PATH ) + "\n" ); 
            sample.writeData( ZK_PATH, "更新后的数据" ); 
            System.out.println( "数据内容: " + sample.readData( ZK_PATH ) + "\n" ); 
            sample.deleteNode( ZK_PATH ); 
        } 
 
        sample.releaseConnection(); 
    } */

    /**
     * 收到来自Server的Watcher通知后的处理。
     */
    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
          //  System.out.println( "收到事件通知：同步连接到zookeeper \n"  );
            connectedSemaphore.countDown();
        }
        if (Event.EventType.NodeDataChanged == event.getType()) {
            System.out.println( "收到事件通知：数据发生变化" + event.getState() +"\n"  );
        }
    }

} 