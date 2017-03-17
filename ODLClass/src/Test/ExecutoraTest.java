package Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import NetMonitor.FlowReader;
import NetMonitor.Flow_Statistic;

public class ExecutoraTest {
	private  static Map<Integer,Long> map=new ConcurrentHashMap<Integer, Long>();
	public static void main(String []args){
		Executor executor=Executors.newScheduledThreadPool(5);
		ScheduledExecutorService scheduledExecutorService=(ScheduledExecutorService) executor;
		Runnable task=new Runnable() {
			private int runtimes=0;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FlowReader flowReader=new FlowReader();
				flowReader.setNode("openflow:1").setTableid("0").setFlowid("0");
				Flow_Statistic flow_Statistic=flowReader.read();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Flow_Statistic flow_Statistic2=flowReader.read();
				long speed=flow_Statistic2.getByte_count()-flow_Statistic.getByte_count();
				//System.out.println(flow_Statistic.getPacket_count());
				//map.put(1,flow_Statistic.getPacket_count());
				//long old=map.get(1);
				
				System.out.println(Thread.currentThread().getId()+"  "+speed);
			}

		
		};
		scheduledExecutorService.scheduleAtFixedRate(task, 1, 3,TimeUnit.SECONDS);
		
	}

}
