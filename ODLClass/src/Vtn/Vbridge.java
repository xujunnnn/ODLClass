package Vtn;

import http.Request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class Vbridge {
	private static String ODL_IP="127.0.0.1:8181";
	@JSONField(serialize=false)
	private Map_Type map_type;
	@JSONField(name="update-mode")
	private UpDate_Mode update_mode;
	private OperationType operation;
	private String description;
	@JSONField(name="age-interval")
	private String age_interval;
	@JSONField(name="tenant-name")
	private String tenant_name;
	@JSONField(name="bridge-name")
	private String bridge_name;
	@JSONField(serialize=false)
	private Mac_Map mac_Map;
	
	
	public UpDate_Mode getUpdate_mode() {
		return update_mode;
	}
	public Vbridge setUpdate_mode(UpDate_Mode update_mode) {
		this.update_mode = update_mode;
		return this;
	}
	public OperationType getOperation() {
		return operation;
	}
	public Vbridge setOperation(OperationType operation) {
		this.operation = operation;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Vbridge setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getAge_interval() {
		return age_interval;
	}
	public Vbridge setAge_interval(String age_interval) {
		this.age_interval = age_interval;
		return this;
	}
	public String getTenant_name() {
		return tenant_name;
	}
	public Vbridge setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
		return this;
	}
	public String getBridge_name() {
		return bridge_name;
	}
	public Vbridge setBridge_name(String bridge_name) {
		this.bridge_name = bridge_name;
		return this;
	}
	/**
	 * 
	 * @param allowed_Hosts
	 * @param denyed_Hosts
	 * @return
	 */
	public Vbridge Set_Mac_Map(Allowed_Hosts allowed_Hosts,Denyed_Hosts denyed_Hosts){
		this.map_type=Map_Type.mac_map;
		this.mac_Map=new Mac_Map();
		mac_Map.setBridge_name(this.bridge_name);
		mac_Map.setTenant_name(this.tenant_name);
		if(allowed_Hosts!=null)
		{
			this.mac_Map.setAllowed_hosts(allowed_Hosts.getAllowed_hosts());
		}
		if(denyed_Hosts!=null){
			this.mac_Map.setDenyed_Hosts(denyed_Hosts.getDenied_hosts());
		}
		return this;
		
	}
	/**
	 * 
	 */
	public void send(){
		Request request=new Request("admin", "admin");
		String url="http://"+ODL_IP+"/restconf/operations/vtn-vbridge:update-vbridge";
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("input", JSONObject.parseObject(JSON.toJSONString(this)));
		System.out.println(jsonObject);
		request.Post_request(url,jsonObject);
		if(this.map_type==Map_Type.mac_map){
			String url2="http://"+ODL_IP+"/restconf/operations/vtn-mac-map:set-mac-map";
			JSONObject jsonObject2=new JSONObject();
			jsonObject2.put("input", JSONObject.parseObject(JSON.toJSONString(this.mac_Map)));
			System.out.println(url2+jsonObject2);
			request.Post_request(url2,jsonObject2);
		}
		
	}

}

