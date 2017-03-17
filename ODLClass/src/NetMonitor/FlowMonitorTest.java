package NetMonitor;

import java.util.ArrayList;

public class FlowMonitorTest {
	public static void main(String []args){
		FlowMonitor flowMonitor=new FlowMonitor();
		ArrayList<String> flow=new ArrayList<String>();
	
		flow.add("0");
		flowMonitor.setTableList(flow);
		flowMonitor.begin();
	for(int i=0;i<1000;i++){
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flowMonitor.getData(Protocol_Type.UDP)!=null)
			System.out.println("UDP    "+"sum= "+flowMonitor.getData(Protocol_Type.UDP).getLeft()+"  speed= "+flowMonitor.getData(Protocol_Type.UDP).getRight());
			if(flowMonitor.getData(Protocol_Type.ICMP)!=null)
			System.out.println("ICMP   "+"sum= "+flowMonitor.getData(Protocol_Type.ICMP).getLeft()+"  speed= "+flowMonitor.getData(Protocol_Type.ICMP).getRight());
			if(flowMonitor.getData(Protocol_Type.UNKNOW)!=null)
			System.out.println("UNKONW   "+"sum= "+flowMonitor.getData(Protocol_Type.UNKNOW).getLeft()+"  speed= "+flowMonitor.getData(Protocol_Type.UNKNOW).getRight());
	}
		
	}

}
