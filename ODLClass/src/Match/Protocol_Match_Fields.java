package Match;

import com.alibaba.fastjson.annotation.JSONField;

public class Protocol_Match_Fields {
	@JSONField(name="mpls-label")
	private String mpls_label;
	@JSONField(name="mpls-tc")
	private String mpls_tc;
	@JSONField(name="mpls-bos")
	private String mpls_bos;
	public String getMpls_label() {
		return mpls_label;
	}
	public void setMpls_label(String mpls_label) {
		this.mpls_label = mpls_label;
	}
	public String getMpls_tc() {
		return mpls_tc;
	}
	public void setMpls_tc(String mpls_tc) {
		this.mpls_tc = mpls_tc;
	}
	public String getMpls_bos() {
		return mpls_bos;
	}
	public void setMpls_bos(String mpls_bos) {
		this.mpls_bos = mpls_bos;
	}

}
