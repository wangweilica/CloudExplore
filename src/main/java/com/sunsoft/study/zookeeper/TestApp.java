package com.sunsoft.study.zookeeper;

import org.junit.Test;

import java.util.List;

/**
 * Author: Wangwei
 * Date: 2016/5/24
 * Desc:
 */
public class TestApp {

    protected Zk zk;
    @Test
    public void getDate() {
        zk = new Zk(Zk.CONNECTION_STRING, Zk.SESSION_TIMEOUT);
        System.out.println(zk.readData(Zk.USERSERVICE_CONFIG_PATH));
        zk.releaseConnection();
    }

    @Test
    public void list() {
        zk = new Zk(Zk.CONNECTION_STRING, Zk.SESSION_TIMEOUT);
        listAll("/", 0);
        zk.releaseConnection();
        //  list.forEach(i -> this.listAll("/"+i));
    }

    @Test
    public void simulate() throws InterruptedException {
        Application app = new Application();
        // load from DB
        app.loadFromDB();
        app.updateConfigToZk();
       /* app.run();

        //Ä£ÄâÅäÖÃÐÞ¸Ä
        UserServiceConfig config = new UserServiceConfig(1800, "192.168.1.234:2182");
        app.updateConfigToZk();
        app.run();*/
    }

    private void listAll(String path, int deep) {
        if (zk.existsNode(path)) {
            deep++;
            StringBuffer sb = new StringBuffer("");

            for (int i = 0; i < deep; i++) sb.append("    ");
            List<String> array = zk.getChildren(path);
            for (String o : array) {
                String tmp = "/" + o;
                System.out.print("\n"+sb + o);
//                if (cfgManager.exist(tmp))  System.out.print("------------------"+cfgManager.readFromZk(tmp));
                this.listAll(tmp, deep);
            }
        } else
            return;
    }

}
