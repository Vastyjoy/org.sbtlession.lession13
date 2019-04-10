package lession13.ExecutionManager;

import java.util.concurrent.CountDownLatch;

public class TaskExecutionManager implements Runnable {
    private final ContextImpl context;
    private volatile Runnable runnable;
    private volatile boolean isCallBack;
    private final CountDownLatch barier;

    TaskExecutionManager(ContextImpl context, Runnable runnable, CountDownLatch barier, boolean isCallBack) {
        this.runnable = runnable;
        this.barier = barier;
        this.context = context;
        this.isCallBack = isCallBack;
    }

    @Override
    public void run() {

        try {
            if (context.getContextInterrupt().get() || Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            if (isCallBack) {
                barier.await();
                context.setIsFinished(true);
            }
            runnable.run();
            context.incrementCompletedTaskCount();
            barier.countDown();

        } catch (InterruptedException x) {
            context.incrementInterruptedTaskCount();
            barier.countDown();
        } catch (Exception x) {
            x.printStackTrace();
            context.incrementFailedTaskCount();
            barier.countDown();
        }

    }
}
