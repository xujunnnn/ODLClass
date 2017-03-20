package NetWork_Topology;

import com.alibaba.fastjson.annotation.JSONField;

public class Termination_point {
	@JSONField(name="tp-id")
	private String tp_id;

	public String getTp_id() {
		return tp_id;
	}

	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}

}
