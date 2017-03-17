package FLow;

import http.Request;

import java.util.ArrayList;

import Instruction.Instruction;
import Instruction.Instructions;
import Match.Match;
import NetMonitor.Protocol_Type;

public class Initialize {
    private static String ODL_IP="10.108.125.125:8181"; 
    private ArrayList<String> nodes=new ArrayList<String>();
    public ArrayList<String> getNodes() {
		return nodes;
	}
	public Initialize setNodes(ArrayList<String> nodes) {
		this.nodes = nodes;
		return this;
	}
	public Initialize addNode(String node){
		this.nodes.add(node);
		return this;
	}
	/**
	 * delete the table from the node
	 * @param node
	 * @param tableid
	 * @return
	 */
	public  String[] delete_Table(String node,String tableid){
		String url="http://"+ODL_IP+"/restconf/config/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+tableid;
		Request request=new Request("admin", "admin");
		System.out.println(url);
		return request.Delete_request(url);
		
	}
	/**
	 * send initflow to the node
	 * @param node
	 */
    public void initFlow(String node){
     /*
      * flow all packet to table 3	
      */
    	Flow flow=new Flow();
    	flow.setId("0").setIdle_timeout("0").setHard_timeout("0").setPriority("200");
    	flow.setCookie(flow.getId()).setFlow_name("initflow"+flow.getId());
    	Match match=new Match();
    	match.Set_Mac_Match(null,null, "2048");
    	Instructions instructions=new Instructions();
    	Instruction gototable=new Instruction();
    	gototable.Set_Go_To_Table_Id("3").setOrder("0");
    	instructions.addInstruction(gototable);
    	flow.setInstructions(instructions).setMatch(match).setTable_id("0").Send(node);
    	/*
    	 * flow2 all packet to table 5
    	 */
    	Flow flow2=new Flow().setId("0").setHard_timeout("0").setIdle_timeout("0").setPriority("2");
    	flow2.setCookie(flow2.getId()).setFlow_name("initflow"+flow2.getId());
    	Instructions instructions2=new Instructions();
    	Instruction gototable5=new Instruction();
    	gototable5.Set_Go_To_Table_Id("5").setOrder("0");
    	instructions2.addInstruction(gototable5);
    	flow2.setInstructions(instructions2).setMatch(match).setTable_id("3").Send(node);
    	/*
    	 * flow1 measure icmp packet
    	 */
    	Flow flow1=new Flow().setId("1").setHard_timeout("0").setIdle_timeout("0");
    	flow1.setFlow_name("icmpflow"+flow1.getId()).setPriority("250").setCookie(flow1.getId());
    	match.Set_Ip_Match("17", null,null, null);
    	flow1.setInstructions(instructions).setMatch(match).setTable_id("0").Send(node);
    	/*
    	 * flow17 measure udp packet
    	 */
    	Flow flow17=new Flow().setId("17").setHard_timeout("0").setIdle_timeout("0");
    	flow17.setFlow_name("udpflow"+flow17.getId()).setPriority("250").setCookie(flow17.getId());
    	match.Set_Ip_Match("17", null,null, null);
    	flow17.setInstructions(instructions).setMatch(match).setTable_id("0").Send(node);
    	/*
    	 * flow6 measure tcp packet
    	 */
    	Flow flow6=new Flow().setId("6").setHard_timeout("0").setIdle_timeout("0");
    	flow6.setFlow_name("tcpflow"+flow6.getId()).setPriority("250").setCookie(flow6.getId());
    	match.Set_Ip_Match("6", null,null, null);
    	flow17.setInstructions(instructions).setMatch(match).setTable_id("0").Send(node);
    }
    /**
     * Init the switch
     */
	public void init() {
		// TODO Auto-generated method stub
		
		for(String n:nodes){
		//	delete_Table(n, "0");
		//	delete_Table(n, "3");
			initFlow(n);
		}

	}
	

}
