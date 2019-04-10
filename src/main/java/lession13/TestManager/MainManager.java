package lession13.TestManager;

import lession13.ExecutionManager.Context;
import lession13.ExecutionManager.ExecutionManager;
import lession13.ExecutionManager.ExecutionManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class MainManager {


    public static void main(String[] args) {
        ExecutionManager executionManager=new ExecutionManagerImpl();

        CallBack callBack=new CallBack(1);
        Printer printer1=new Printer(1);
        Printer printer2=new Printer(2);
        Printer printer3=new Printer(3);
        Printer printer4=new Printer(4);
        Context context=executionManager.execute(callBack,printer1,printer2,printer3,printer4);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(context);

        executionManager.execute(callBack,printer1,printer2,printer3,printer4);
        context=executionManager.execute(callBack,printer1,printer2,printer3,printer4);
        context.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(context);

    }
}
