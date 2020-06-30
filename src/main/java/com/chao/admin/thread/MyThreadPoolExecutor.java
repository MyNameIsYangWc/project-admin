package com.chao.admin.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 初始化线程池
 * @author 杨文超
 * @date 2020-06-30
 */
public class MyThreadPoolExecutor {

    private static ThreadPoolExecutor threadPoolExecutor;

    static {
        threadPoolExecutor=new ThreadPoolExecutor(
                3,//核心线程，不回收
                20,//线程上线
                10, TimeUnit.MINUTES,//线程存活时间
                new SynchronousQueue<>(),//任务队列
                new ThreadPoolExecutor.CallerRunsPolicy()//拒绝策略，不丢弃
        );
    }

    /**
     * 获取线程池
     * @return
     */
    public static ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }

}
