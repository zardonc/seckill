package org.seckill.order.service;

import org.seckill.entity.OrderRequest;
import org.seckill.entity.OrderRequestOps;
import org.seckill.entity.OrderRequestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// 通过队列合并用户下单请求
public class SeckillOrderService {
    // 模拟数据库一行记录
    private Integer stock = 8;
    // 任务等待队列
    private BlockingQueue<OrderRequestOps> orderQueue = new LinkedBlockingQueue<>(50);

    public static void main(String[] args) {
        SeckillOrderService seckillOrderService = new SeckillOrderService();
        seckillOrderService.orderByQueue();
    }

    // 1. 10个线程
    // 2. 库存6个
    // 3. 生成合并队列
    // 4. 每个用户获取自身的请求响应
    public void orderByQueue() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        mergeJob();
        List<Future<OrderRequestResult>> futures = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);// 制造并发场景
        for (int i = 0; i < 10; i++) {
            final Long orderId = i + 100L;
            final Long userId = Long.valueOf(i);
            Future<OrderRequestResult> requestResultFuture = executorService.submit(() -> {
                countDownLatch.countDown();
                countDownLatch.await(1, TimeUnit.SECONDS);
                return countOperate(new OrderRequest(orderId, userId, 1));
            });
            futures.add(requestResultFuture);
        }

        futures.forEach(orderRequestResultFuture -> {
            try {
                OrderRequestResult result = orderRequestResultFuture.get(300L, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + " 处理结果响应： " + result);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // 扣减库存操作
    public OrderRequestResult countOperate(OrderRequest orderRequest) throws InterruptedException {
        //TODO 阈值判断, 队列创建判断
        OrderRequestOps requestOps = new OrderRequestOps(orderRequest);
        synchronized (requestOps) {// requestOps局部变量在任务队列里也有引用，保证获取锁之后入队列
            boolean result = orderQueue.offer(requestOps, 100L, TimeUnit.MILLISECONDS);
            if (!result) {
                return new OrderRequestResult(false, "系统繁忙");
            }
            try {
                requestOps.wait(200L);
                if (requestOps.getOrderRequestResult() == null) {
                    return new OrderRequestResult(false, "等待超时");
                }
            } catch (InterruptedException e) {// 超时
                e.printStackTrace();
            }
        }
        return requestOps.getOrderRequestResult();
    }

    // 请求合并逻辑, 一个异步的轮询
    public void mergeJob() {
        // 异步线程合并请求
        new Thread(() -> {
            // 执行队列使用后清空
            List<OrderRequestOps> list = new ArrayList<>();
            while (true) {
                if (orderQueue.isEmpty()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    continue;
                }
                int batchSize = orderQueue.size();
                for (int i = 0; i < batchSize; i++) {
                    try {
                        list.add(orderQueue.poll(10L, TimeUnit.MILLISECONDS));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName() + " 合并扣减库存： " + list);
                // 需扣减库存总数
                int sum = list.stream().mapToInt(e -> e.getOrderRequest().getCount()).sum();
                // 依据库存是否足够分两种情况
                // FIXME 此处简化操作，需考虑多线程同时查库都有库存的情况
                if (sum <= stock) {// 库存足够
                    stock -= sum;
                    // notify user
                    list.forEach(orderRequestOps -> {
                        orderRequestOps.setOrderRequestResult(new OrderRequestResult(true, ""));
                        synchronized (orderRequestOps) {
                            orderRequestOps.notify();
                        }
                    });
                    continue;
                }
                for (OrderRequestOps requestOps : list) {// 下单总数大于当前库存
                    // 考虑排序后优先扣减订单数量较多的用户
                    int countCur = requestOps.getOrderRequest().getCount();
                    if (countCur <= stock) {
                        stock -= countCur;
                        requestOps.setOrderRequestResult(new OrderRequestResult(true, "ok"));
                    } else {
                        requestOps.setOrderRequestResult(new OrderRequestResult(false, "库存不足"));
                    }
                    synchronized (requestOps) { // 库存不足时通知，此处与添加入队列操作竞争锁
                        requestOps.notify();
                    }
                }
                // 清空数组
                list.clear();
            }
        }, "mergeThread").start();
    }
}