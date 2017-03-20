package FLow;

import http.Request;

import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.classfmt.MethodInfoWithAnnotations;

import Instruction.Instruction;
import Instruction.Instructions;
import Match.Match;
import NetMonitor.Protocol_Type;
import NetWork_Topology.Node;
import NetWork_Topology.Termination_point;
import NetWork_Topology.Topology;

public class Initialize {
    private static String ODL_IP="10.108.125.125:8181"; 
    private ArrayList<Node> nodes=new ArrayList<Node>();
    private ArrayList<Protocol_Type> protocol_Types=new ArrayList<>();
    public ArrayList<Protocol_Type> getProtocol_Types() {
		return protocol_Types;
	}


	public void setProtocol_Types(ArrayList<Protocol_Type> protocol_Types) {
		this.protocol_Types = protocol_Types;
	}



	private static int id=0;
    
    public String getid(){
    	String flowid=String.valueOf(id);
    	id++;
    	return flowid;
    }
    
    
    /**
     * 
     * @return
     */
    public Initialize addProtocol(Protocol_Type protocol_Type){
    	this.protocol_Types.add(protocol_Type);
    	return this;
    }
    
    public ArrayList<Node> getNodes() {
		return nodes;
	}
	public Initialize setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
		return this;
	}
	public Initialize addNode(Node node){
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
    public void initFlow(Node node){
    	Flow flow=new Flow();
    	Match match=new Match().Set_Mac_Match(null, null, "2048");
    	Instructions instructions=new Instructions();
    	Instruction instruction=new Instruction().Set_Go_To_Table_Id("3").setOrder("0");
    	instructions.addInstruction(instruction);
    	/*
    	 * send flow gototable 3
    	 */
    	flow.setId(getid())
    		.setFlow_name("initflow"+flow.getId())
    		.setCookie(flow.getId())
    		.setHard_timeout("0")
    		.setIdle_timeout("0")
    		.setPriority("210")
    		.setTable_id("0")
    		.setMatch(match)
    		.setInstructions(instructions)
    		.Send(node.getNode_id());
    	Instructions instructions2=new Instructions();
    	Instruction instruction2=new Instruction().Set_Go_To_Table_Id("5").setOrder("0");
    	/*
    	 * send flow gototable 5
    	 */
    	flow.setId(getid())
    		.setFlow_name("initflow"+flow.getId())
    		.setCookie(flow.getId())
    		.setInstructions(instructions2)
    		.setTable_id("3")
    		.Send(node.getNode_id());
    	
    	Topology topology=new Topology().update();
    	/*
    	  for(Protocol_Type p:protocol_Types){
    		System.out.println(p);
    	for(Termination_point t:topology.get_ports_to_host(node)){
    		
    			System.out.println(p);
    		Flow flow2=new Flow();
    		System.out.println("protocol-type      "+String.valueOf(p.value()));
    		Match match2=new Match().Set_Ip_Match(String.valueOf(p.value()), null, null,null).setIn_phy_port(t.getTp_id());
    		
    		flow2.setId(getid())
    			 .setFlow_name("initflow"+flow.getId())
    			 .setCookie(flow.getId())
    			 .setIdle_timeout("0")
    			 .setHard_timeout("0")
    			 .setPriority("210")
    			 .setTable_id("0")
    			 .setInstructions(instructions)
    			 .setMatch(match2)
    			 .Send(node.getNode_id());
    			 
    		}		 
    	}
    	
    		*/
    	ArrayList<Termination_point> termination_points=topology.get_ports_to_host(node);
    	for(Termination_point t:termination_points){
    		for(Protocol_Type p:protocol_Types){
    			Flow flow2=new Flow();
        		Match match2=new Match().Set_Ip_Match(String.valueOf(p.value()), null, null,null)
        								.setIn_port(t.getTp_id())
        								.Set_Mac_Match(null, null, "2048");
        		flow2.setId(getid())
        			 .setFlow_name("initflow"+flow2.getId())
        			 .setCookie(flow2.getId())
        			 .setIdle_timeout("0")
        			 .setHard_timeout("0")
        			 .setPriority("210")
        			 .setTable_id("0")
        			 .setInstructions(instructions)
        			 .setMatch(match2)
        			 .Send(node.getNode_id());
    		}
    	}
    		
    }
   	
   
    
    /**
     * Init the switch
     */
	public void init() {
		// TODO Auto-generated method stub
		Topology topology=new Topology().update();
		for(Node n:topology.get_access_node()){
			System.out.println(n.getNode_id());
			initFlow(n);
		}
	}
	

}
