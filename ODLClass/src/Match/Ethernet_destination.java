package Match;

import com.alibaba.fastjson.annotation.JSONField;

public class Ethernet_destination {
	private String address;
	@JSONField(name="mask")
	private String Mask;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMask() {
		return Mask;
	}
	public void setMask(String mask) {
		Mask = mask;
	}
}
