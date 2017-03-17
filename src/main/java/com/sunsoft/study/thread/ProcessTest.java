package com.sunsoft.study.thread;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author: Wangwei
 * Date: 2016/5/31
 * Desc:
 */
public class ProcessTest {

    @Test
    public void test() throws IOException, InterruptedException {
        Process process =  Runtime.getRuntime().exec("ipconfig");
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line=bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
       // process.waitFor();
    }
}
