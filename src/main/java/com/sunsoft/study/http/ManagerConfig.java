package com.sunsoft.study.http;

import java.util.ResourceBundle;

public class ManagerConfig
{
  private static ResourceBundle configBundle;
  public static final int MAX_CONNECTION = 2000;
  public static final int MAX_PERROUTE = 1000;
  public static final int MAX_CONNECTION_TIMEOUT = 10000;
  public static final int MAX_SOCKET_TIMEOUT = 6000;
  private int connection;
  private int preroute;
  private int connectionTimout;
  private int socketTimeout;
  private static String configPath = "config";
  public static ManagerConfig build()
  {
    try
    {
    	// Thread.currentThread().getContextClassLoader().getResource("").getPath()
      configBundle = ResourceBundle.getBundle(configPath);
    } catch (Exception e) {
      return null;
    }

    ManagerConfig config = new ManagerConfig();

    config.connection = getIntProperty("connection", 2000);
    config.preroute = getIntProperty("preroute", 1000);
    config.connectionTimout = getIntProperty("connection.timeout", 10000);
    config.socketTimeout = getIntProperty("socket.timeout", 6000);

    return config;
  }

  private static int getIntProperty(String key, int maxValue)
  {
	  String value;
    try
    {
      value = configBundle.getString(key);
    }
    catch (Exception e)
    {
     
      return maxValue;
    }
    int configValue = maxValue;
    try {
      configValue = Integer.parseInt(value);
      if ((configValue < maxValue) && (configValue > 0))
        return configValue;
    }
    catch (NumberFormatException e) {
      return maxValue;
    }

    return configValue;
  }

  public int getConnection()
  {
    return this.connection;
  }

  public void setConnection(int connection)
  {
    this.connection = connection;
  }

  public int getPreroute()
  {
    return this.preroute;
  }

  public void setPreroute(int preroute)
  {
    this.preroute = preroute;
  }

  public int getConnectionTimout()
  {
    return this.connectionTimout;
  }

  public void setConnectionTimout(int connectionTimout)
  {
    this.connectionTimout = connectionTimout;
  }

  public int getSocketTimeout()
  {
    return this.socketTimeout;
  }

  public void setSocketTimeout(int socketTimeout)
  {
    this.socketTimeout = socketTimeout;
  }
}