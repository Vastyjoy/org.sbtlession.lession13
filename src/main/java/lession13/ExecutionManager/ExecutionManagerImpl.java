package lession13.ExecutionManager;

import lession13.FixedThreadPool.FixedThreadPool;

import java.util.concurrent.CountDownLatch;


public class ExecutionManagerImpl implements ExecutionManager {
    FixedThreadPool fixedThreadPool=FixedThreadPool.getInstance(2);
    {
        fixedThreadPool.start();
    }

    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ContextImpl context=new ContextImpl();
        CountDownLatch barier=new CountDownLatch(tasks.length);
        for(Runnable runnable:tasks){
            TaskExecutionManager task=new TaskExecutionManager(context,runnable,barier,false);
            fixedThreadPool.execute(task);
        }
        TaskExecutionManager callBackTask=new TaskExecutionManager(context,callback,barier,true);
        fixedThreadPool.execute(callBackTask);
        return context;
    }
}
