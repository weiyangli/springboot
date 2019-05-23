package com.example.springBoot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    private static ExecutorService service;

    public  static void test() {
        test2();
    }

    public static void main(String[] args) {
        service = Executors.newFixedThreadPool(20);
        for (int i = 1; i <= 2; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            });
        }
    }

    public synchronized static void test2 () {
        for (int i = 0; i<= 10000; i++) {
            System.out.println(String.format("我是第%o个数字", i));
        }
    }

}
