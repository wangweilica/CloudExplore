package com.sunsoft.study.thread.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class LongEventMain
{
    public static void handleEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("consumer:" + event);
        System.out.println(event.get());
    }

    public static void translate(LongEvent event, long sequence, ByteBuffer buffer)
    {
        event.set(buffer.getLong(0));
        System.out.println("producer:" + event);
    }

    public static void main(String[] args) throws Exception
    {
        // Executor that will be used to construct new threads for consumers
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        // Construct the Disruptor
        // Executor executor = Executors.newCachedThreadPool();
        // Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, Executors.defaultThreadFactory());

        // Connect the handler
        // disruptor.handleEventsWith(LongEventMain::handleEvent);
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);
            ringBuffer.publishEvent(LongEventMain::translate, bb);
            Thread.sleep(2000);
        }
    }
}