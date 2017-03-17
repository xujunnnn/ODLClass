package FLow;

import Instruction.Instruction;
import Instruction.Instructions;
import Match.Match;

public class TestFlow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Flow flow3=new Flow();
    	flow3.setId("2").setIdle_timeout("0").setHard_timeout("0").setPriority("250");
    	flow3.setCookie(flow3.getId()).setFlow_name("initflow"+flow3.getId());
    	Match match2=new Match();
    	match2.Set_Mac_Match(null, null, "2048");
    	match2.Set_Ip_Match("1", null,null, null);
    	Instructions instructions=new Instructions();
    	Instruction gototable=new Instruction();
    	gototable.Set_Go_To_Table_Id("3").setOrder("0");
    	instructions.addInstruction(gototable);
    	flow3.setInstructions(instructions).setMatch(match2).setTable_id("0").Send("openflow:1");
    	flow3.setId("17");
    	match2.Set_Ip_Match("17", null, null, null);
    	flow3.setMatch(match2).Send("openflow:1");
	}

}
