package NetMonitor;

public class FlowreaderTest {
	public static void main(String [] args){
	FlowReader flowReader=new FlowReader().setNode("openflow:1").setTableid("0").setFlowid("0");
	
	FlowMonThread flowMonThread=new FlowMonThread().setFlowReader(flowReader);
	flowMonThread.start();
	
	}
}
