package Action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class Action {
	private String order;
	@JSONField(name="output-action")
	private OutPut_Action outPut_Action;
	public OutPut_Action getOutPut_Action() {
		return outPut_Action;
	}

	public void setOutPut_Action(OutPut_Action outPut_Action) {
		this.outPut_Action = outPut_Action;
	}

	public Set_Queue_Action getSet_Queue_Action() {
		return set_Queue_Action;
	}

	public void setSet_Queue_Action(Set_Queue_Action set_Queue_Action) {
		this.set_Queue_Action = set_Queue_Action;
	}
    @JSONField(name="set-queue-action")
	private Set_Queue_Action set_Queue_Action;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	public Action Set_Out_Put_Connector(String node_connector){
		OutPut_Action outPut_Action=new OutPut_Action();
		outPut_Action.setMax_length("65535");
		outPut_Action.setOutput_node_connector(node_connector);
		this.outPut_Action=outPut_Action;
		return this;
	}
	public Action Set_Queue_Id(String queue_id){
		Set_Queue_Action set_Queue_Action=new Set_Queue_Action();
		set_Queue_Action.setQueue(queue_id);
		this.set_Queue_Action=set_Queue_Action;
		return this;
	}
	public static void main(String []args){
		Action action=new Action();
		action.setOrder("0");
		action.Set_Out_Put_Connector("openflow:1");
		Action action2=new Action();
		action2.setOrder("1");
		action2.Set_Queue_Id("3");
		String json=JSON.toJSONString(action);
		String json2=JSON.toJSONString(action2);
		System.out.println(json);
		System.out.println(json2);
	}
}
class OutPut_Action{
	@JSONField(name="output-node-connector")
	private String output_node_connector;
	@JSONField(name="max-length")
	private String max_length;
	public String getOutput_node_connector() {
		return output_node_connector;
	}
	public void setOutput_node_connector(String output_node_connector) {
		this.output_node_connector = output_node_connector;
	}
	public String getMax_length() {
		return max_length;
	}
	public void setMax_length(String max_length) {
		this.max_length = max_length;
	}
}
class Set_Queue_Action{
	@JSONField(name="queue-id")
	private String queue;
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getQueue_id() {
		return queue_id;
	}
	public void setQueue_id(String queue_id) {
		this.queue_id = queue_id;
	}
	private String queue_id;
}
