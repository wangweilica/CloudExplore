package com.sunsoft.study.zookeeper;

import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;
import java.util.Arrays;

public class ZkData {

   private byte[] data;
   private Stat stat;

   public byte[] getBytes() {
      return data;
   }

   public ZkData() {
      super();
   }

   public ZkData(byte[] data, Stat stat) {
      this.data = data;
      this.stat = stat;
   }

   @Override
   public String toString() {
      return "ZkData [data=" + Arrays.toString(getData()) + ",stat=" + getStat() + "]";
   }

   public String getDataString() {
      return new String(getData(), Charset.forName("UTF-8"));
   }
   
   public byte[] getData() {
      return data;
   }

   public void setData(byte[] data) {
      this.data = data;
   }

   public Stat getStat() {
      return stat;
   }

   public void setStat(Stat stat) {
      this.stat = stat;
   }
}
