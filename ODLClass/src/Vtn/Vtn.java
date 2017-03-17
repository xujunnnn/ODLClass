package Vtn;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import http.Request;

public class Vtn {
	private static String ODL_IP="127.0.0.1:8181";
	@JSONField(name="tenant-name")
	private String tenant_name;
	public UpDate_Mode getUpdate_mode() {
		return update_mode;
	}
	public Vtn setUpdate_mode(UpDate_Mode update_mode) {
		this.update_mode = update_mode;
		return this;
	}
	public OperationType getOperation() {
		return operation;
	}
	public Vtn setOperation(OperationType operation) {
		this.operation = operation;
		return this;
	}
	@JSONField(name="update-mode")
	private UpDate_Mode update_mode;
	private OperationType operation;
	private String description;
	@JSONField(name="idle-timeout")
	private String idle_timeout;
	@JSONField(name="hard-timeout")
	private String hard_timeout;
	public String getTenant_name() {
		return tenant_name;
	}
	public Vtn setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Vtn setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getIdle_timeout() {
		return idle_timeout;
	}
	public Vtn setIdle_timeout(String idle_timeout) {
		this.idle_timeout = idle_timeout;
		return this;
	}
	public String getHard_timeout() {
		return hard_timeout;
	}
	public Vtn setHard_timeout(String hard_timeout) {
		this.hard_timeout = hard_timeout;
		return this;
	}
	/**
	 * send the vtn 
	 * @return
	 */
	public String [] Send(){
		Request request=new Request("admin","admin");
		String url="http://"+ODL_IP+"/restconf/operations/vtn:update-vtn";
		String json=JSON.toJSONString(this);
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("input", JSONObject.parseObject(JSON.toJSONString(this)));
		System.out.println(url+jsonObject);
		return request.Post_request(url,jsonObject);
	}
	public static void main(String []args){
		Vtn vtn=new Vtn();
		vtn.setTenant_name("Tena").setDescription("tena").setIdle_timeout("0").setIdle_timeout("0").setOperation(OperationType.SET).setUpdate_mode(UpDate_Mode.CREATE);
		vtn.Send();
		Vbridge vbridge=new Vbridge();
		vbridge.setTenant_name(vtn.getTenant_name()).setBridge_name("vbr").setUpdate_mode(UpDate_Mode.CREATE).setOperation(OperationType.SET).setAge_interval("200").setDescription("123");
		Allowed_Hosts allowed_Hosts=new Allowed_Hosts();
		allowed_Hosts.addHost("10:00:00:00:00:00@0").addHost("11:00:00:00:00:00@0");
		vbridge.Set_Mac_Map(allowed_Hosts, null).send();
	}

}
