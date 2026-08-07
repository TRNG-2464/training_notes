package com.revature.threads.problems.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * PRODUCER-CONSUMER PROBLEM: one or more "producer" threads generate data
 * and add it to a shared buffer, while one or more "consumer" threads
 * remove and process that data. The challenge is coordinating them safely:
 *   - The producer must not add to the buffer when it's full.
 *   - The consumer must not remove from the buffer when it's empty.
 *   - Both threads are reading/writing shared data, so access must be
 *     synchronized to avoid race conditions.
 *
 * SOLUTION USED HERE: Java's BlockingQueue handles all of that coordination
 * for us automatically:
 *   - queue.put(item)  -> adds an item, but WAITS if the queue is full.
 *   - queue.take()     -> removes an item, but WAITS if the queue is empty.
 * This means we don't have to manually write wait()/notify() logic or
 * worry about locking -- BlockingQueue is thread-safe by design.
 */
public class ProducerConsumerSimulator {
    public static void main(String[] args) {
        // A shared buffer with a fixed capacity of 5. Once it holds 5
        // items, any producer calling put() will pause until the
        // consumer makes room by calling take().
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);

        Thread producerThread = new Thread(new Producer(sharedQueue), "Producer");
        Thread consumerThread = new Thread(new Consumer(sharedQueue), "Consumer");

        producerThread.start();
        consumerThread.start();
    }
}

/*
 * Produces integers 1-15 and places them onto the shared queue, one at a
 * time, with a short delay to simulate real work being done.
 */
class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 15; i++) {
                System.out.println("Producer: producing item " + i);

                // put() adds the item to the queue. If the queue is full
                // (already holds 5 items), this call BLOCKS until the
                // consumer removes something -- this is what prevents the
                // producer from overwhelming the buffer.
                queue.put(i);
                Thread.sleep(200); // simulate time spent "producing" an item
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

/*
 * Consumes integers from the shared queue and "processes" them, with a
 * short delay to simulate real work being done. Runs slower than the
 * producer on purpose, so you can see the queue fill up.
 */
class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 15; i++) {
                // take() removes an item from the queue. If the queue is
                // empty, this call BLOCKS until the producer adds
                // something -- this is what prevents the consumer from
                // trying to process data that doesn't exist yet.
                int item = queue.take();

                System.out.println("\tConsumer: consumed item " + item);
                Thread.sleep(500); // simulate time spent "processing" an item
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}