package lession13.TestManager;

public class CallBack implements Runnable{
    int i;

    public CallBack(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"Вызван callback  :"+i+" группы задач");
    }
}