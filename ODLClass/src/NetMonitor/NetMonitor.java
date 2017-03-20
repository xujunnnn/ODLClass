package NetMonitor;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import FLow.Flow;
import NetWork_Topology.Node;

public class NetMonitor implements Runnable {
	private Map<Pair<String, Protocol_Type>, Long> NetByteMap=new ConcurrentHashMap<>();
	private Map<Pair<String,Protocol_Type>, Long> NetSpeedMap=new ConcurrentHashMap<>();
	private ArrayList<Node> nodes=new ArrayList<>();
	private static long interval=3000;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(Node node:nodes){
			TableReader tableReader=new TableReader();
			tableReader.setNode(node.getNode_id());
			tableReader.setTableid("0");
			for(String id:tableReader.read().keySet()){
				Flow flow=tableReader.read().get(id);
				if(flow.getMatch().getIn_port()!=null){
					String in_port=flow.getMatch().getIn_port();
					Map<Pair<String, Protocol_Type>, Long> oldByteMap=new ConcurrentHashMap<>();
					if(flow.getMatch().getIp_Match()!=null){
						String proto_id=flow.getMatch().getIp_Match().getIp_protocol();
						Protocol_Type protocol_Type=Protocol_Type.Valueof(Integer.parseInt(proto_id));
						Pair<String, Protocol_Type> pair=new Pair<>();
						pair.setLeft(in_port);
						pair.setRight(protocol_Type);
						long bytecount=flow.getFlow_Statistic().getByte_count();
						oldByteMap.put(pair, bytecount);
						NetByteMap.put(pair,bytecount);
					}
				}
			}
			
		}
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<Pair<String, Protocol_Type>, Long> newByteMap=new ConcurrentHashMap<>();
		for(Node node:nodes){
			TableReader tableReader=new TableReader();
			tableReader.setNode(node.getNode_id());
			tableReader.setTableid("0");
			for(String id:tableReader.read().keySet()){
				Flow flow=tableReader.read().get(id);
				if(flow.getMatch().getIn_port()!=null){
					String in_port=flow.getMatch().getIn_port();
					Map<Pair<String, Protocol_Type>, Long> oldByteMap=new ConcurrentHashMap<>();
					if(flow.getMatch().getIp_Match()!=null){
						String proto_id=flow.getMatch().getIp_Match().getIp_protocol();
						Protocol_Type protocol_Type=Protocol_Type.Valueof(Integer.parseInt(proto_id));
						Pair<String, Protocol_Type> pair=new Pair<>();
						pair.setLeft(in_port);
						pair.setRight(protocol_Type);
						long bytecount=flow.getFlow_Statistic().getByte_count();
						newByteMap.put(pair, bytecount);
						NetByteMap.put(pair,bytecount);
						long speed=(newByteMap.get(pair)-oldByteMap.get(pair))/(interval/1000);
						NetSpeedMap.put(pair, speed);
					}
				}
			}
			
		}
		
		
	}
	
}
