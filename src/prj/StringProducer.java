package prj;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Produce threads for the String Resource Server.
 * <p/>
 * Created by McGuireMW on 4/20/14.
 */
public class StringProducer extends Thread {
    private Queue<Thread> stringThreads = new ConcurrentLinkedQueue<Thread>();
    private final int MAX = 5;
    private Thread stringThread;
    private Random random = new Random();
    private int pause = 1;
    private int threadNum = 0;
    private String newString;

    public StringProducer() {
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                stringThread = new Thread();
                threadNum++;
                newString  = "<StringProducer> New String: " + threadNum;
                System.out.println(newString);
                stringThread.setName(newString);
                stringThread.start();
                stringThreads.add(stringThread);
                notifyAll();
                pause = random.nextInt(5000);
                sleep(pause);
            } catch (InterruptedException intExc) {
                System.err.println("InterruptedException caught when producing String Thread: " + intExc);
            } catch (Exception e) {
                System.err.println("Error when creating threads: " + e);
            }
            if (stringThreads.size() == 5) {
                break;
            }
        }
    }

    public int getQueueSize() {
        return stringThreads.size();
    }

    public Thread getThread() {
        return stringThreads.poll();
    }
}
