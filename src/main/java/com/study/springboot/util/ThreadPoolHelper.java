package com.study.springboot.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @autor 10758
 * @system springboot-mybatis-mysql
 * @Time 2018/3/31
 */
public class ThreadPoolHelper {

    private static volatile ThreadPoolExecutor executor;

    public static void execute(Runnable runnable) {
        if (null == executor) {
            initPool();
        }
        executor.execute(runnable);

    }

    private static void initPool() {
        if (null == executor) {
            synchronized (ThreadPoolExecutor.class) {
                if (null == executor) {
                    int maxThreadNum = (Runtime.getRuntime().availableProcessors() << 1) + 1;
                    executor = new ThreadPoolExecutor(maxThreadNum, maxThreadNum, 10, TimeUnit.MINUTES,
                            new ArrayBlockingQueue<Runnable>(maxThreadNum), new CustomThreadFactory(),
                            new CustomRejectedExecutionHandler());
                }
            }
        }
    }

}

/**
 * 对多线程的线程名重命名
 */
class CustomThreadFactory implements ThreadFactory {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("ThreadPoolHelper-" + count.getAndIncrement());
        return thread;
    }
}


/**
 * 该类主要是阻塞队列满时，处理异常类，默认会抛出RejectedExecutionException异常，修改为阻塞等待<br/>
 */
class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        try {
            // 阻塞队列，防止队列满时抛异常
            executor.getQueue().put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}