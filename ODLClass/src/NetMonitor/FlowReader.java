package NetMonitor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import http.Request;

public class FlowReader {
	private static String ODL_IP="10.108.125.125:8181";
	private String tableid;
	private String node;
	public String getTableid() {
		return tableid;
	}
	public FlowReader setTableid(String tableid) {
		this.tableid = tableid;
		return this;
	}
	public String getNode() {
		return node;
	}
	public FlowReader setNode(String node) {
		this.node = node;
		return this;
	}
	public String getFlowid() {
		return flowid;
	}
	public FlowReader setFlowid(String flowid) {
		this.flowid = flowid;
		return this;
	}
	private String flowid;
	public  Flow_Statistic read(){
		Request request=new Request("admin", "admin");
		String url="http://"+ODL_IP+"/restconf/operational/opendaylight-inventory:nodes/node/"+this.node+"/flow-node-inventory:table/"+this.tableid+"/flow/"+this.flowid+"/opendaylight-flow-statistics:flow-statistics";
	  String s=request.Get_request(url)[1];
	//  System.out.println(s);
	  JSONObject jsonObject=JSONObject.parseObject(s);
	  
	  Flow_Statistic flow_Statistic=JSON.parseObject(jsonObject.getJSONObject("opendaylight-flow-statistics:flow-statistics").toJSONString(), Flow_Statistic.class);
	  return flow_Statistic;
	}

}
