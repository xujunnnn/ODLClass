package Instruction;

import java.util.ArrayList;

import com.alibaba.fastjson.annotation.JSONField;

import Action.Action;

public class Apply_Actions{
	@JSONField(name="action")
	private ArrayList<Action> actions=new ArrayList<Action>();

	public ArrayList<Action> getActions() {
		return actions;
	}
    public Apply_Actions(){}
	public void setAction(ArrayList<Action> action) {
		this.actions = action;
	}
	public Apply_Actions addAction(Action action){
		this.actions.add(action);
		return this;
	}



	
}