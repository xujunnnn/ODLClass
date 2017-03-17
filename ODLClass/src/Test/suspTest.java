package Test;

public class suspTest {
	public static void main(String [] args) throws InterruptedException{
		final Synoobj synoobj=new Synoobj();
		Thread thread1=new Thread(){
			@Override
			public void run(){
				synoobj.print();
			}
		};
		thread1.setName("a");
		thread1.start();
		Thread.sleep(1000);
		Thread thread2=new Thread(){
			public void run(){
				System.out.println("thread 2 start but only begin");
			    synoobj.print();
			}
		};
		thread2.setName("b");
		thread2.start();
	}

}
class Synoobj{
	synchronized public void print(){
		System.out.println("begin");
		if(Thread.currentThread().getName().equals("a")){
				System.out.println("a suspend ");
				Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
}