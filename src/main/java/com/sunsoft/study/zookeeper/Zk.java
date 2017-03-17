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
     * ����ZK����
     *
     * @param connectString  ZK��������ַ�б�
     * @param sessionTimeout Session��ʱʱ��
     */
    public void createConnection(String connectString, int sessionTimeout) {
        this.releaseConnection();
        try {
            client = new ZooKeeper(connectString, sessionTimeout, this);
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            System.out.println("���Ӵ���ʧ�ܣ����� InterruptedException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("���Ӵ���ʧ�ܣ����� IOException");
            e.printStackTrace();
        }
    }

    /**
     * �ر�ZK����
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
     * �����ڵ�
     *
     * @param path �ڵ�path
     * @param data ��ʼ��������
     * @return boolean
     */
    public boolean createPath(String path, ZkData data) {
        try {
            this.client.create(path, data.getData(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            System.out.println("�ڵ㴴��ʧ�ܣ�����KeeperException");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("�ڵ㴴��ʧ�ܣ����� InterruptedException");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * ��ȡָ���ڵ���������
     *
     * @param path �ڵ�path
     * @return String
     */
    public ZkData readData(String path) {
        try {
            byte[] bytes = this.client.getData(path, false, null);
            return new ZkData(bytes, new Stat());
        } catch (KeeperException e) {
            System.out.println("��ȡ����ʧ�ܣ�����KeeperException��path: " + path);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("��ȡ����ʧ�ܣ����� InterruptedException��path: " + path);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ����ָ���ڵ���������
     *
     * @param path �ڵ�path
     * @param data ��������
     * @return boolean
     */
    public boolean writeData(String path, ZkData data) {
        try {
            this.client.setData(path, data.getData(), -1);
        } catch (KeeperException e) {
            System.out.println("��������ʧ�ܣ�����KeeperException��path: " + path);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("��������ʧ�ܣ����� InterruptedException��path: " + path);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ɾ��ָ���ڵ�
     * @param path �ڵ�path
     */
    public void deleteNode(String path) {
        try {
            this.client.delete(path, -1);
        } catch (KeeperException e) {
            System.out.println("ɾ���ڵ�ʧ�ܣ�����KeeperException��path: " + path);
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("ɾ���ڵ�ʧ�ܣ����� InterruptedException��path: " + path);
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
        if ( sample.createPath( ZK_PATH, "���ǽڵ��ʼ����" ) ) { 
            System.out.println(); 
            System.out.println( "��������: " + sample.readData( ZK_PATH ) + "\n" ); 
            sample.writeData( ZK_PATH, "���º������" ); 
            System.out.println( "��������: " + sample.readData( ZK_PATH ) + "\n" ); 
            sample.deleteNode( ZK_PATH ); 
        } 
 
        sample.releaseConnection(); 
    } */

    /**
     * �յ�����Server��Watcher֪ͨ��Ĵ���
     */
    @Override
    public void process(WatchedEvent event) {
        if (KeeperState.SyncConnected == event.getState()) {
          //  System.out.println( "�յ��¼�֪ͨ��ͬ�����ӵ�zookeeper \n"  );
            connectedSemaphore.countDown();
        }
        if (Event.EventType.NodeDataChanged == event.getType()) {
            System.out.println( "�յ��¼�֪ͨ�����ݷ����仯" + event.getState() +"\n"  );
        }
    }

} 