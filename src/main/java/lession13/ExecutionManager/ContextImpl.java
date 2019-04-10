package lession13.ExecutionManager;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ContextImpl implements Context {

    private AtomicInteger completedTaskCount = new AtomicInteger(0);
    private AtomicInteger failedTaskCount = new AtomicInteger(0);
    private AtomicInteger interruptedTaskCount = new AtomicInteger(0);
    private AtomicBoolean contextInterrupted = new AtomicBoolean(false);
    private AtomicBoolean isFinished = new AtomicBoolean(false);


    @Override
    public String toString() {
        return "ContextImpl{" +
                "completedTaskCount=" + completedTaskCount +
                ", failedTaskCount=" + failedTaskCount +
                ", interruptedTaskCount=" + interruptedTaskCount +
                ", contextInterrupted=" + contextInterrupted +
                ", isFinished=" + isFinished +
                '}';
    }

    public void setIsFinished(boolean bool) {
        isFinished.set(bool);
    }

    public void incrementCompletedTaskCount() {
        completedTaskCount.incrementAndGet();
    }

    public void incrementFailedTaskCount() {
        failedTaskCount.incrementAndGet();
    }

    public void incrementInterruptedTaskCount() {
        interruptedTaskCount.incrementAndGet();
    }


    public AtomicBoolean getContextInterrupt() {
        return contextInterrupted;
    }

    @Override
    public int getCompletedTaskCount() {
        return completedTaskCount.get();
    }

    @Override
    public int getFailedTaskCount() {
        return failedTaskCount.get();
    }

    @Override
    public int getInterruptedTaskCount() {
        return interruptedTaskCount.get();
    }

    @Override
    public void interrupt() {
        contextInterrupted.set(true);
    }

    @Override
    public boolean isFinished() {
        return isFinished.get();
    }
}
