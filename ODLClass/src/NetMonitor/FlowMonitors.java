package NetMonitor;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;




public class FlowMonitors {
     private static Map<String, Map<Protocol_Type, Pair<Long>>> DifTyeDataMap=new ConcurrentHashMap<>();
     private ArrayList<Pair<String>> nodeTables=new ArrayList<>();
  
     /**
      * begin to monitor
      */
     public void begin(){
    	 	ScheduledThreadPoolExecutor executor=(ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
    	 	ThreadFactory flowFactory=new FLowThreadFactory(); 	 	
    	 	for(Pair<String> nodetable:nodeTables){
    		 executor.scheduleAtFixedRate(, 0, 3, TimeUnit.SECONDS);	 	
    	 	}
    	 
     }
     
}
