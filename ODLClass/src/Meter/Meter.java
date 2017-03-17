package Meter;

import http.Request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class Meter {
	private static String ODL_IP="127.0.0.1:8181";
	@JSONField(name="meter-id")
	private String meter_id;
	@JSONField(name="meter-name")
	private String meter_name;
	@JSONField(name="container-name")
	private String container_name;
	private String flags="meter-kbps";
	@JSONField(name="meter-band-headers")
	private Meter_Band_Headers meter_band_headers;
	/**
	 * set drop-rate for the meter
	 * @param droprate
	 * @return
	 */
	public Meter Set_drop_rate(String droprate){
		Meter_Band_Header meter_Band_Header=new Meter_Band_Header().setBand_burst_size("100").setBand_id(meter_id).setBand_rate("100").setDrop_burst_size("100").setDrop_rate(droprate);
		meter_Band_Header.Set_Type("ofpmbt-drop");
		Meter_Band_Headers meter_Band_Headers=new Meter_Band_Headers();
		meter_Band_Headers.addMeter_Band_Header(meter_Band_Header);
		this.meter_band_headers=meter_Band_Headers;
		return this;
	}
	/**
	 * send the meter to the specified node
	 * @param node
	 * @return s[0]=code s[1]=description
	 */
	public String [] Send(String node){
		Request request=new Request("admin", "admin");
		String url="http://"+ODL_IP+"/restconf/config/opendaylight-inventory:nodes/node/"+node+"/flow-node-inventory:meter/"+meter_id;
	    JSONArray jsonArray=new JSONArray();
	    jsonArray.add(JSONObject.parse(JSON.toJSONString(this)));
	    JSONObject jsonObject=new JSONObject();
	   
	    jsonObject.put("flow-node-inventory:meter", jsonArray);
	    System.out.println(url);
	    System.out.println(jsonObject);
		String s[]=request.Put_request(url, jsonObject);
	    return s;
	}
	public static void main(String []args){
		Meter meter=new Meter().setContainer_name("meter").setMeter_id("3");
		meter.setMeter_name("meter"+meter.getMeter_id()).Set_drop_rate("100");
		JSONArray jsonArray=new JSONArray();
		String json1=JSON.toJSONString(meter.getMeter_band_headers());
		
		String json=JSON.toJSONString(meter);
		meter.Send("openflow:1");
		
		System.out.println(json);
		System.out.println(json1);
	}
	public String getMeter_id() {
		return meter_id;
	}
	public Meter setMeter_id(String meter_id) {
		this.meter_id = meter_id;
		return this;
	}
	public String getMeter_name() {
		return meter_name;
	}
	public Meter setMeter_name(String meter_name) {
		this.meter_name = meter_name;
		return this;
	}
	public String getContainer_name() {
		return container_name;
	}
	public Meter setContainer_name(String container_name) {
		this.container_name = container_name;
		return this;
	}
	public String getFlags() {
		return flags;
	}
	public Meter setFlags(String flags) {
		this.flags = flags;
		return this;
	}
	public Meter_Band_Headers getMeter_band_headers() {
		return meter_band_headers;
	}
	public Meter setMeter_band_headers(Meter_Band_Headers meter_band_headers) {
		this.meter_band_headers = meter_band_headers;
		return this;
	}
}
