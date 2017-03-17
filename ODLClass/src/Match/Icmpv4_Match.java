package Match;

import com.alibaba.fastjson.annotation.JSONField;

public class Icmpv4_Match {
	@JSONField(name="icmpv4-type")
	private String icmpv4_type;
	@JSONField(name="icmpv4-code")
	private String icmpv4_code;
	public String getIcmpv4_type() {
	return icmpv4_type;
}
	public void setIcmpv4_type(String icmpv4_type) {
	this.icmpv4_type = icmpv4_type;
}
	public String getIcmpv4_code() {
	return icmpv4_code;
}
	public void setIcmpv4_code(String icmpv4_code) {
	this.icmpv4_code = icmpv4_code;
}
	public Icmpv4_Match(String icmpv4_type,String icmpv4_code){
		if (icmpv4_code!=null)
		this.icmpv4_code=icmpv4_code;
		if(icmpv4_type!=null)
		this.icmpv4_type=icmpv4_type;
	}

}
