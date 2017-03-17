package Match;

import com.alibaba.fastjson.annotation.JSONField;

public class Tcp_Flag_Match {
	@JSONField(name="tcp-flag")
	private String tcp_flag;
	public String getTcp_flag() {
		return tcp_flag;
	}
	public void setTcp_flag(String tcp_flag) {
		this.tcp_flag = tcp_flag;
	}
}
