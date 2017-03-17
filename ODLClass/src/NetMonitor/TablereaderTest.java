package NetMonitor;

import FLow.Flow;

public class TablereaderTest {
	public static void main(String []args){
	TableReader tableReader=new TableReader();
	tableReader.setNode("openflow:1").setTableid("3");
	for(Flow flow:tableReader.read())
	   System.out.println(flow.getFlow_Statistic().getByte_count());
	}

}
