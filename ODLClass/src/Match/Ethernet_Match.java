package Match;

import com.alibaba.fastjson.annotation.JSONField;

public class Ethernet_Match {
	@JSONField(name="ethernet-source")
	private Ethernet_source ethernet_source;
	@JSONField(name="ethernet-destination")
	private Ethernet_destination ethernet_destination;
	@JSONField(name="ethernet-type")
	private Ethernet_type ethernet_type;
	public Ethernet_source getEthernet_source() {
		return ethernet_source;
	}
	public void setEthernet_source(Ethernet_source ethernet_source) {
		this.ethernet_source = ethernet_source;
	}
	public Ethernet_destination getEthernet_destination() {
		return ethernet_destination;
	}
	public void setEthernet_destination(Ethernet_destination ethernet_destination) {
		this.ethernet_destination = ethernet_destination;
	}
	public Ethernet_type getEthernet_type() {
		return ethernet_type;
	}
	public void setEthernet_type(Ethernet_type ethernet_type) {
		this.ethernet_type = ethernet_type;
	}
	public Ethernet_Match(){}
	/**
	 * 
	 * @param src
	 * @param dest
	 * @param ethetType
	 */
	public Ethernet_Match (String src,String dest,String ethetType){
		
		
		if(src!=null){
			Ethernet_source ethernet_source=new Ethernet_source();
			ethernet_source.setAddress(src);
			this.setEthernet_source(ethernet_source);
		}
		if(dest!=null){
			Ethernet_destination ethernet_destination=new Ethernet_destination();
			ethernet_destination.setAddress(dest);
			this.setEthernet_destination(ethernet_destination);
		}
		//set the default ethernettype
		Ethernet_type ethernet_type=new Ethernet_type();
		ethernet_type.setType(ethetType);
		
		
		this.setEthernet_type(ethernet_type);
		
	}                                                                            

}

