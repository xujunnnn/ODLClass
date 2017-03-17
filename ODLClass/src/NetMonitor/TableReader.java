package NetMonitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import FLow.Flow;
import Table.Table;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import http.Request;



public class TableReader {
	private static final String ODL_IP="10.108.125.125:8181";
	private String node;
	private String tableid;
	public String getNode() {
		return node;
	}
	public TableReader setNode(String node) {
		this.node = node;
		return this;
	}
	public String getTableid() {
		return tableid;
	}
	public TableReader setTableid(String tableid) {
		this.tableid = tableid;
		return this;
	}

	public Map<String, Flow> read(){
    Map<String, Flow> flowmap=new HashMap<String, Flow>();
	Request request=new Request("admin", "admin");
	String url="http://"+ODL_IP+"/restconf/operational/opendaylight-inventory:nodes/node/"+this.node+"/flow-node-inventory:table/"+tableid;
	String s=request.Get_request(url)[1];
    JSONObject flowtable=JSONObject.parseObject(s);
//	System.out.println(s);
	JSONArray jsonArray=flowtable.getJSONArray("flow-node-inventory:table");
	JSONObject tablejson=jsonArray.getJSONObject(0);
	Table table=JSONObject.parseObject(tablejson.toJSONString(), Table.class);
    for(Flow flow:table.getFlow()){
    	flowmap.put(flow.getId(), flow);
    }
    return flowmap;

}
}