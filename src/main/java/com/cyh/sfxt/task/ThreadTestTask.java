package com.cyh.sfxt.task;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@Slf4j
public class ThreadTestTask {

    public ExecutorService pool = null;

    @Scheduled(fixedDelayString = "10000")
    public void testOne(){
        ThreadFactory factory = new ThreadFactoryBuilder().setNamePrefix("scan_local_thread_").build();
        pool = new ThreadPoolExecutor(3, 40, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), factory);
        for (int i = 1; i <= 5; i++) {
            pool.submit(new test(i));
        }
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                break;
            }
        }
    }

    private class test implements Runnable {
        private int i;

        public test(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int j = 1; j <= 5; j++) {
                System.out.println(i + "子循环开始" + ">>>>>>" + j);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
