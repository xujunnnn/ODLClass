package FLow;

import http.Request;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import Action.Action;
import Instruction.Apply_Actions;
import Instruction.Instruction;
import Instruction.Instructions;
import Match.Match;
import NetMonitor.Flow_Statistic;


/**
 * @author xu
 *
 */
/**
 * @author xu
 *
 */
public class Flow {
	private static String ODL_IP="10.108.125.125:8181";
	
	public String getFlow_name() {
		return flow_name;
	}
	public Flow setFlow_name(String flow_name) {
		this.flow_name = flow_name;
		return this;
	}
	public String getPriority() {
		return priority;
	}
	public Flow setPriority(String priority) {
		this.priority = priority;
		return this;
	}
	public String getIdle_timeout() {
		return idle_timeout;
	}
	public Flow setIdle_timeout(String idle_timeout) {
		this.idle_timeout = idle_timeout;
		return this;
	}
	public String getHard_timeout() {
		return hard_timeout;
	}
	public Flow setHard_timeout(String hard_timeout) {
		this.hard_timeout = hard_timeout;
		return this;
	}
	public String getTable_id() {
		return table_id;
	}
	public Flow setTable_id(String table_id) {
		this.table_id = table_id;
		return this;
	}
	public String getId() {
		return id;
	}
	public Flow setId(String id) {
		this.id = id;
		return this;
	}
	public Instructions getInstructions() {
		return instructions;
	}
	public Flow setInstructions(Instructions instructions) {
		this.instructions = instructions;
		return this;
	}
	public Match getMatch() {
		return match;
	}
	public Flow setMatch(Match match) {
		this.match = match;
		return this;
	}
	public String getCookie() {
		return cookie;
	}
	public Flow setCookie(String cookie) {
		this.cookie = cookie;
		return this;
	}
	/**
	 * send a flow to the node
	 * @param node the specific openflow switch
	 * @return s s[0]=code s[1]=information
	 */
	public String[] Send(String node){
		Request request=new Request("admin", "admin");
		String url="http://"+ODL_IP+"/restconf/config/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:table/"+this.getTable_id()+"/flow/"+this.getId();
		System.out.println(url);
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(JSON.parseObject(JSON.toJSONString(this)));
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("flow", jsonArray);
		System.out.println(jsonObject);
		return request.Put_request(url,jsonObject);
	}
	@JSONField(name="flow-name")
	private String flow_name;
	private String  priority;
	@JSONField(name="idle-timeout")
	private String  idle_timeout;
	@JSONField(name="hard-timeout")
	private String  hard_timeout;
	@JSONField(name="opendaylight-flow-statistics:flow-statistics")
	private Flow_Statistic flow_Statistic;
	private String table_id;
	private String cookie;
	private String id;
	private Instructions instructions;
    private Match match;
    public static void main(String []args){
    	Flow flow=new Flow();
    	//Match match=new Match().Set_Mac_Match("00:00:00:00:00:02", "00:00:00:00:00:01", "2048");
    	Match match=new Match();
    		match.Set_Mac_Match(null, null, "2048");
    //	match.setTcp_source_port("12000");
    //	match.setTcp_destination_port("12000");
         //match.setUdp_source_port("12000");
       //  match.setUdp_destination_port("1200");
    	//match.setIpv4_source("10.0.0.1/8");
    	//match.setIpv4_destination("10.0.0.4/8");
        match.Set_Ip_Match("0", null,null, null);
    	//match.Set_Mac_Match(null, null, "2048");
    	Instructions instructions=new Instructions();
    	Instruction instruction=new Instruction();
    	instruction.Set_Meter("3").setOrder("0");
    	Instruction instruction2=new Instruction();
    	//Apply_Actions actions=new Apply_Actions();
    //Action action=new Action();
    	//action.Set_Out_Put_Connector("openflow:1:1").setOrder("0");
    	///actions.addAction(action);
    //	instruction2.setApply_Actions(actions);
    //	instruction2.setOrder("1");
    	instruction2.Set_Go_To_Table_Id("5").setOrder("1");
    	instructions.addInstruction(instruction);
    	instructions.addInstruction(instruction2);
    	flow.setCookie("123");
    	flow.setFlow_name("fllow");
    	flow.setHard_timeout("0");
    	flow.setIdle_timeout("0");
    	flow.setId("1");
    	flow.setInstructions(instructions);
    	flow.setMatch(match);
    	flow.setPriority("200").setTable_id("3");
    	String json=JSON.toJSONString(flow);
    	flow.Send("openflow:1");
    	
    	System.out.println(json);
    	
    }
	public Flow_Statistic getFlow_Statistic() {
		return flow_Statistic;
	}
	public void setFlow_Statistic(Flow_Statistic flow_Statistic) {
		this.flow_Statistic = flow_Statistic;
	}
}
