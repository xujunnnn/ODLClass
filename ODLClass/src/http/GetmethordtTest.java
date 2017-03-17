package http;

import net.sf.json.JSONObject;

public class GetmethordtTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String url="http://10.205.30.162:8181/restconf/operational/opendaylight-inventory:nodes";
		//Request request1=new Request("admin", "admin");
		//System.out.println(request1.Get_request(url));
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("operation", "set");
		jsonObj.put("id",1);
		jsonObj.put("default-cost", 1000);
		System.out.println(jsonObj);
	}

}
