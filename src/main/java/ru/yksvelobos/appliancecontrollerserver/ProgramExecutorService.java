package ru.yksvelobos.appliancecontrollerserver;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Execute work program service
 *
 * @author Ilia Sobolevsky
 */
@Component
public class ProgramExecutorService {

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final ConcurrentHashMap<Long, Future<?>> futures = new ConcurrentHashMap<>();
    private Object object = new Object();

    /**
     * Execute work program
     * @param identifier unique key task
     * @param task TaskChain for execute
     */
    public void beginWork(Long identifier, Runnable task) {
        synchronized (object) {
            Future<?> future = executorService.submit(task);
            futures.put(identifier, future);

        }
    }

    public void stopWork(Long identifier) {
        Future<?> future = futures.get(identifier);
        future.cancel(true);
    }
}
