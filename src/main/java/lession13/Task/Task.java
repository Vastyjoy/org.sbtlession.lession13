package lession13.Task;

import lession13.ExecutionManagerException.TaskException;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public class Task<T> {
    private final Callable<? extends T> callable;
    private volatile AtomicBoolean isTaskExecute;
    private volatile AtomicBoolean isFinal;
    private volatile AtomicBoolean throwException;
    private volatile TaskException exception;
    private volatile T result;
    private final Object object = new Object();


    public Task(Callable<? extends T> callable) {
        this.callable = callable;
        this.isTaskExecute = new AtomicBoolean(false);
        this.isFinal = new AtomicBoolean(false);
        this.throwException = new AtomicBoolean(false);
    }

    public void executeTask() throws Exception {
        isTaskExecute.set(true);
        System.err.println(Thread.currentThread().getName() + " :Захватил задачу");
        result = callable.call();
        isFinal.set(true);
        System.err.println(Thread.currentThread().getName() + " :Выполнил задачу");
        System.err.println(Thread.currentThread().getName() + " :Бужу все потоки");
        synchronized (object) {
            object.notifyAll();
        }
    }

    public T get() {
        try {
            while (true) {
                if (throwException.get()) throw exception;

                if (isFinal.get()) {
                    System.err.println(Thread.currentThread().getName() + " :Задача уже была выполнена, получил из кеша");
                    return result;
                }


                if (isTaskExecute.get()) {
                    try {
                        System.err.println(Thread.currentThread().getName() + ": Задача запущена другим потоком, ожидаю завершения вычисления");
                        synchronized (object) {
                            object.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new TaskException("Ожидание прервано");
                    }
                } else {
                    try {
                        executeTask();
                    } catch (Exception x) {
                        throwException.set(true);
                        exception = new TaskException("Прервано выполнение");
                        object.notifyAll();
                        throw exception;
                    }
                }
            }

        } catch (Exception x) {
            x.printStackTrace();
            throw new TaskException("Неизвестная ошибка");
        }
    }
}
