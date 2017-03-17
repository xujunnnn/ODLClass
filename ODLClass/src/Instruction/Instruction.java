package Instruction;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import Action.Action;


public class Instruction {
	private String order;
	@JSONField(name="apply-actions")
	private Apply_Actions apply_Actions;
	@JSONField(name="go-to-table")
	private Go_To_Table go_To_Table;
	@JSONField(name="meter")
	private Meter_Case meter_Case;
	public String getOrder() {
		return order;
	}
	public Instruction setOrder(String order) {
		this.order = order;
		return this;
	}

	public Go_To_Table getGo_To_Table() {
		return go_To_Table;
	}
	public Instruction setGo_To_Table(Go_To_Table go_To_Table) {
		this.go_To_Table = go_To_Table;
		return this;
	}

	public Instruction Set_Go_To_Table_Id(String table_id){
		Go_To_Table go_To_Table=new Go_To_Table();
		go_To_Table.setTable_id(table_id);
	    this.setGo_To_Table(go_To_Table);
		return this;
	}
	public Instruction Set_Meter(String meter){
		Meter_Case meter_Case=new Meter_Case();
		meter_Case.setMeter(meter);
		this.setMeter_Case(meter_Case);
		return this;
	}
	public Meter_Case getMeter_Case() {
		return meter_Case;
	}
	public Instruction setMeter_Case(Meter_Case meter_Case) {
		this.meter_Case = meter_Case;
		return this;
	}
	public Apply_Actions getApply_Actions() {
		return apply_Actions;
	}
	public Instruction setApply_Actions(Apply_Actions apply_Actions) {
		this.apply_Actions = apply_Actions;
		return this;
	}
    
}

class Go_To_Table{
	private String table_id;

	public String getTable_id() {
		return table_id;
	}

	public Go_To_Table setTable_id(String table_id) {
		this.table_id = table_id;
		return this;
	}
	
}
class Meter_Case{
	@JSONField(name="meter-id")
	private String meter_id;

	public String getMeter_id() {
		return meter_id;
	}

	public Meter_Case setMeter(String meter_id) {
		this.meter_id = meter_id;
		return this;
	}
}
