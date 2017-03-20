package NetMonitor;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import FLow.Flow;


public class FlowMonitor  {
	/*
	 * save the flows to monitor
	 */
	private ArrayList<String> tableList=new ArrayList<String>();
	/*
	 * save the speed of each kind of flow into the map
	 */
	private Map<Protocol_Type, Pair<Long>> fLowTypeMap=new ConcurrentHashMap<Protocol_Type, Pair<Long>>();
	private Map<String, Pair<Long>> srcMap=new ConcurrentHashMap<String, Pair<Long>>();
	private Map<String, Pair<Long>> destMap=new ConcurrentHashMap<String, Pair<Long>>();
	/**
	 * return the speed b/s of the specified flow
	 * @param flowid
	 * @return
	 */
	public Pair<Long> getData(Protocol_Type protocol_Type){
		if(fLowTypeMap.get(protocol_Type)!=null){
		return fLowTypeMap.get(protocol_Type);
		}
		else 
			{
			return null;
					}
	}
	/**
	 * return the dataMap
	 * @return
	 */


	public void begin(){
		ScheduledThreadPoolExecutor executor=(ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
		ThreadFactory flowFactory=new FLowThreadFactory();
		for (String tableid:tableList) {
		 executor.scheduleAtFixedRate(flowFactory.newThread(new FlowMonTask().setNode("openflow:1").setTableid(tableid).setFlowTypeMap(fLowTypeMap)), 0, 3, TimeUnit.SECONDS);	 	
		}
		
	}
	public Map<String, Pair<Long>> getSrcMap() {
		return srcMap;
	}
	public void setSrcMap(Map<String, Pair<Long>> srcMap) {
		this.srcMap = srcMap;
	}
	public ArrayList<String> getTableList() {
		return tableList;
	}
	public FlowMonitor setTableList(ArrayList<String> tableList) {
		this.tableList = tableList;
		return this;
	}
	

}


class FlowMonTask implements Runnable{
	private String node;
	private String tableid;
	private Map<Protocol_Type, Pair<Long>> flowTypeMap;
	private Map<String, Pair<Long>> srcMap;
	private Map<String, Pair<Long>> destMap;
	public String getNode() {
		return node;
	}
	public FlowMonTask setNode(String node) {
		this.node = node;
		return this;
	}
	public String getTableid() {
		return tableid;
	}
	public FlowMonTask setTableid(String tableid) {
		this.tableid = tableid;
		return this;
	}
	public String getFlowid() {
		return flowid;
	}
	public FlowMonTask setFlowid(String flowid) {
		this.flowid = flowid;
		return this;
	}
	public long getInterval() {
		return interval;
	}
	public FlowMonTask setInterval(long interval) {
		this.interval = interval;
		return this;
	}
	
	
	private String flowid;
	private long interval=3000;
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		TableReader tableReader=new TableReader();
		tableReader.setNode(node).setTableid(tableid);
		Map<String, Flow> oldflowMap=tableReader.read();
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Flow> nowflowMap=tableReader.read();
		
		for(String s:nowflowMap.keySet()){
			if(nowflowMap.get(s).getMatch().getIp_Match()!=null){
			String ip_protocol=nowflowMap.get(s).getMatch().getIp_Match().getIp_protocol();
			long byte_count=oldflowMap.get(s).getFlow_Statistic().getByte_count();
			
				long speed=(nowflowMap.get(s).getFlow_Statistic().getByte_count()-oldflowMap.get(s).getFlow_Statistic().getByte_count())/(interval/1000);
				Pair<Long> datapair=new Pair<Long>(byte_count, speed);
				flowTypeMap.put(Protocol_Type.Valueof(Integer.parseInt(ip_protocol)),datapair);
				
			
		}
			if(nowflowMap.get(s).getId().equals("0")){
					long byte_count=oldflowMap.get(s).getFlow_Statistic().getByte_count();
					long speed=(nowflowMap.get(s).getFlow_Statistic().getByte_count()-oldflowMap.get(s).getFlow_Statistic().getByte_count())/(interval/1000);
					Pair<Long> datapair=new Pair<Long>(byte_count, speed);
					flowTypeMap.put(Protocol_Type.Valueof(0),datapair);
			}
		}
		
	}
	
	public Map<Protocol_Type, Pair<Long>> getFlowTypeMap() {
		return flowTypeMap;
	}
	public FlowMonTask setFlowTypeMap(Map<Protocol_Type, Pair<Long>> flowTypeMap) {
		this.flowTypeMap = flowTypeMap;
		return this;
	}
	public Map<String, Pair<Long>> getSrcMap() {
		return srcMap;
	}
	public FlowMonTask setSrcMap(Map<String, Pair<Long>> srcMap) {
		this.srcMap = srcMap;
		return this;
	}
	public Map<String, Pair<Long>> getDestMap() {
		return destMap;
	}
	public FlowMonTask setDestMap(Map<String, Pair<Long>> destMap) {
		this.destMap = destMap;
		return this;
	}
	
	
}
