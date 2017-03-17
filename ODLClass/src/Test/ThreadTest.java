package Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

 class Thread1 extends Thread{
	private volatile int num=5;
	@Override
    synchronized public void run(){
		num--;
		System.out.println(Thread.currentThread().getId()+"     "+num);
	}

}
public class ThreadTest{
	public static void main(String []args){
		
	Thread1 t1=new Thread1();
	Thread t=new Thread(t1);
	Thread t2=new Thread(t1);
	t.start();
	t2.start();
	//t3.start();
		  
	//	executor = Executors.newScheduledThreadPool(10);  
		//ScheduledExecutorService scheduler = (ScheduledExecutorService) executor;  
		//scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS); 
	}
}