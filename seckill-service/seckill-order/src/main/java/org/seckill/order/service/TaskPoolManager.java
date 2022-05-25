package org.seckill.order.service;

import org.seckill.entity.BusinessThread;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.*;

// BeanFactoryAware，可以获取到 BeanFactory，可以用来手动注册 Bean
@Component
public class TaskPoolManager implements BeanFactoryAware {
    private final static int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    private final static int MAX_POOL_SIZE = 64;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;
    // 调度线程池
    final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    // 用于储存在队列中的订单,防止重复提交,在真实场景中，可用redis代替
    Map<String, Object> cacheMap = new ConcurrentHashMap<>();
    // 订单的缓冲队列,当线程池满了，则将订单存入到此缓冲队列
    Queue<Object> msgQueue = new LinkedBlockingQueue<>();
    // 线程池容量满拒绝方案
    final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            BusinessThread businessThread = (BusinessThread) r;
            String orderStr = businessThread.getAcceptStr();
            // 业务订单入缓冲队列
            msgQueue.offer(orderStr);
            System.out.println("系统忙, 把此订单交给(调度线程池)逐一处理，订单号：" + orderStr);
        }
    };

    final ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<>(WORK_QUEUE_SIZE), this.handler);
    // 调度任务将订单缓冲队列的记录重新加入线程池(1秒间隔)
    final ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            //判断缓冲队列是否存在记录
            if (!msgQueue.isEmpty()) {
                //当线程池的队列容量少于WORK_QUEUE_SIZE，则开始把缓冲队列的订单 加入到 线程池
                if (executor.getQueue().size() < WORK_QUEUE_SIZE) {
                    String orderId = (String) msgQueue.poll();
                    BusinessThread businessThread = new BusinessThread(orderId);
                    executor.execute(businessThread);
                    System.out.println("(调度线程池)缓冲队列出现订单业务，重新添加到线程池，订单号：" + orderId);
                }
            }
        }
    }, 0, 1, TimeUnit.SECONDS);
    // 用于从IOC容器获取对象
    // 如果实现Runnable的类是通过spring的application.xml文件进行注入,可通过 factory.getBean()获取
    private BeanFactory beanFactory;

    /**
     * 将任务加入订单线程池
     */
    public void addOrders(String orderId) {
        System.out.println("此订单准备添加到线程池，订单号：" + orderId);
        if (!cacheMap.containsKey(orderId)) {
            cacheMap.put(orderId, new Object());
            BusinessThread businessThread = new BusinessThread(orderId);
            executor.execute(businessThread);
        }
    }

    /**
     * 获取消息缓冲队列
     */
    public Queue<Object> getMsgQueue() {
        return msgQueue;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    // 终止线程池
    public void shutdown() {
        //true表示如果定时任务在执行，立即中止，false则等待任务结束后再停止
        System.out.println("终止订单线程池+调度线程池：" + scheduledFuture.cancel(false));
        scheduledExecutorService.shutdown();
        executor.shutdown();
    }
}
