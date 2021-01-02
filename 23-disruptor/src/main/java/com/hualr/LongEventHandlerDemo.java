package com.hualr;

import com.google.common.base.Stopwatch;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * Function: <br>.
 * Creating Time: 2020/12/23 23:40 <br>
 * Version: 1.0.0 <br>
 *
 * @author zongqi
 */

public class LongEventHandlerDemo {
    private static final int HANDLER_TIME = 10;
    /**
     * <h2>缓存大小设置</h2>
     * 设置缓存大小 必须为2^
     */
    private static final int BUFFER_SIZE = 1024;
    private static int consumer1Index;
    private static int consumer2Index;

    /**
     * <h2>消费方法</h2>
     *
     * @param event-消费的事件 作为生产者和消费者之间的桥梁
     * @param sequence    当前的序列
     * @param endOfBatch  是否走在队列最前面boolean
     */
    public static void handleEvent(LongEvent event, long sequence, boolean endOfBatch) {
        consumer1Index++;
        System.out.println("conusmer:" + Thread.currentThread().getName() + "|" + event + "|" + sequence + "|" + endOfBatch);
    }

    public static void handleEvent2(LongEvent event, long sequence, boolean endOfBatch) {
        consumer2Index++;
        System.out.println("conusmer2:" + Thread.currentThread().getName() + "|" + event + "|" + sequence + "|" + endOfBatch);
    }

    public static void handleEvent3(LongEvent event, long sequence, boolean endOfBatch) {
        //ZNN 这里注意index2
        consumer2Index++;
        System.out.println("conusmer3:" + Thread.currentThread().getName() + "|" + event + "|" + sequence + "|" + endOfBatch);
    }

    /**
     * <h2>生产方法</h2>
     *
     * @param event    载体
     * @param sequence 其实这个顺序也并非那么重要
     * @param buffer   这个buffer是可以变化的
     */
    public static void translate(LongEvent event, long sequence, ByteBuffer buffer) {
        event.set(buffer.getLong(0));
        System.out.println("producer:" + Thread.currentThread().getName() + "|" + event + "|" + sequence + "|");
    }

    public static void main(String[] args) throws InterruptedException {
        //  构造一个Disruptor
        //Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, BUFFER_SIZE, DaemonThreadFactory.INSTANCE);
        //  disruptor可以进行构造定制化
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, BUFFER_SIZE, DaemonThreadFactory.INSTANCE, ProducerType.MULTI, new BlockingWaitStrategy());
        // 链接一个处理方法 广播消费
        //disruptor.handleEventsWith(LongEventHandlerDemo::handleEvent, LongEventHandlerDemo::handleEvent2);

        disruptor.handleEventsWith(LongEventHandlerDemo::handleEvent)
                .and(disruptor.handleEventsWith(LongEventHandlerDemo::handleEvent2))
                //THEN位于两个消费方法之后进行
                .then(LongEventHandlerDemo::handleEvent3);
        // Start the Disruptor, starts all threads running
        disruptor.start();
        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        long l = 0;
        Stopwatch stopWatch = Stopwatch.createStarted();
        while (l < HANDLER_TIME) {
            //生产信息
            ringBuffer.publishEvent(LongEventHandlerDemo::translate, bb.putLong(0, l++));
        }
        Thread.sleep(100);
        disruptor.shutdown();
        System.out.println(stopWatch.elapsed(TimeUnit.MICROSECONDS));
        System.out.println("consumer1:" + consumer1Index + "|" + "consumer2:" + consumer2Index);
    }
}