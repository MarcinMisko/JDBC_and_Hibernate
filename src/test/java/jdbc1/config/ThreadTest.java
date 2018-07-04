package jdbc1.config;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    AtomicInteger i = new AtomicInteger();

    Runnable job = () -> {
        while(true){
            System.out.println("Running from: " + Thread.currentThread().getName());
            System.out.println("i = " + i);
        }
    };

    @Test
    public void threadTest() {
        new Thread(job).start();
        new Thread(job).start();
    }
}

