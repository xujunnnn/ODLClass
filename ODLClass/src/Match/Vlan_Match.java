package Match;

import com.alibaba.fastjson.annotation.JSONField;

public class Vlan_Match {
	@JSONField(name="vlan-pcp")
	private String vlan_pcp;
	@JSONField(name="vlan-id")
	private Vlan_id vlan_id;

	public String getVlan_pcp() {
		return vlan_pcp;
	}

	public void setVlan_pcp(String vlan_pcp) {
		this.vlan_pcp = vlan_pcp;
	}

	public Vlan_id getVlan_id() {
		return vlan_id;
	}

	public void setVlan_id(Vlan_id vlan_id) {
		this.vlan_id = vlan_id;
	}
	public Vlan_Match(String vlanpcp,String vlanid){
		if(vlanid!=null)
		{
			Vlan_id vlan_id=new Vlan_id(vlanid);
			this.vlan_id=vlan_id;
		}
		if(vlanpcp!=null){
			this.vlan_pcp=vlanpcp;
		}
	}

}
class Vlan_id{
	private String vlan_id_present="true";
	private String vlan_id;
	public Vlan_id(String vlanid){
		this.vlan_id=vlanid;
	}
	public String getVlan_id_present() {
		return vlan_id_present;
	}
	public void setVlan_id_present(String vlan_id_present) {
		this.vlan_id_present = vlan_id_present;
	}
	public String getVlan_id() {
		return vlan_id;
	}
	public void setVlan_id(String vlan_id) {
		this.vlan_id = vlan_id;
	}
}