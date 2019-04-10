package lession13.Task;

import java.util.concurrent.Callable;

public class TaskTest {

    public static void main(String[] args) {
        Callable<Integer> callable=new Callable<Integer>() {
            int temp=1;
            @Override
            public Integer call() throws Exception {
                for(int i=2;i<10;i++){
                    temp*=i;
                }
                return temp;
            }
        };

      Task<Integer> task = new Task<Integer>(callable);

        for(int i=0; i<10;i++){
            new Thread(task::get).start();
        }
    }
}
