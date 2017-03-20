package NetMonitor;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import FLow.Flow;

public class AllFlowMonTask implements Runnable {
    private String node;
    private String table;
    private long interval;
    private ArrayList<Protocol_Type> protocol_Types;
	private Map<String, Map<Protocol_Type, Pair<Long>>> DifTyeDataMap;
	public AllFlowMonTask() {
		// TODO Auto-generated constructor stub
	}
	
    public AllFlowMonTask(String node,String table, Map<String, Map<Protocol_Type, Pair<Long>>> DifTyeDataMap,long interval) {
		this.node=node;
		this.table=table;
		this.DifTyeDataMap=DifTyeDataMap;
		this.interval=interval;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		TableReader tableReader=new TableReader();
		tableReader.setNode(node).setTableid(table);
		Map<String, Flow> oldflowMap=tableReader.read();
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Flow> nowflowMap=tableReader.read();
		Map<Protocol_Type, Pair<Long>> singleNodeMap=new ConcurrentHashMap<>();
		for(String s:nowflowMap.keySet()){
			if(nowflowMap.get(s).getMatch().getIp_Match()!=null){
			String ip_protocol=nowflowMap.get(s).getMatch().getIp_Match().getIp_protocol();
			long byte_count=oldflowMap.get(s).getFlow_Statistic().getByte_count();
			long speed=(nowflowMap.get(s).getFlow_Statistic().getByte_count()-oldflowMap.get(s).getFlow_Statistic().getByte_count())/(interval/1000);
			Pair<Long> datapair=new Pair<Long>(byte_count, speed);		
			
			singleNodeMap.put(Protocol_Type.Valueof(Integer.parseInt(ip_protocol)),datapair);
				
			
		}
			if(nowflowMap.get(s).getId().equals("0")){
					long byte_count=oldflowMap.get(s).getFlow_Statistic().getByte_count();
					long speed=(nowflowMap.get(s).getFlow_Statistic().getByte_count()-oldflowMap.get(s).getFlow_Statistic().getByte_count())/(interval/1000);
					Pair<Long> datapair=new Pair<Long>(byte_count, speed);
					singleNodeMap.put(Protocol_Type.Valueof(0),datapair);
			}
		}
		DifTyeDataMap.put(node, singleNodeMap);
	}
	/**
	 * return the map
	 * @return
	 */
	 public Map<String, Map<Protocol_Type, Pair<Long>>> getGlobalMap(){
		 return this.DifTyeDataMap;
	 } 
	 
	 public Map<Protocol_Type, Pair<Long>> getSumMap(){
		 Map<Protocol_Type, Pair<Long>> sumMap=new ConcurrentHashMap<>();
		 for(Protocol_Type p:protocol_Types){
			 Pair<Long> pair=new Pair<>();
			 for(String node:DifTyeDataMap.keySet()){
				pair.setLeft(DifTyeDataMap.get(node).get(p).getLeft()+pair.getLeft());
				pair.setRight(DifTyeDataMap.get(node).get(p).getRight()+pair.getRight());
			 }
			 sumMap.put(p, pair);
		 }
		 return sumMap;
		 
	 }

}
