package lession13.TestManager;

import java.util.concurrent.Executors;

public class Printer implements Runnable{
    int j;

    public Printer(int j) {
        this.j = j;
    }
    @Override
    public void run() {
        for(int i=0; i<3;i++){
            System.out.println(Thread.currentThread().getName()+"-Принтер "+j+" печатает "+i);
        }
    }
}