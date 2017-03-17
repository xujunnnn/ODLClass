package Instruction;

import java.util.ArrayList;

public class Instructions {
	
	private ArrayList<Instruction> instruction=new ArrayList<Instruction>();

	public ArrayList<Instruction> getInstruction() {
		return instruction;
	}

	public void setInstruction(ArrayList<Instruction> instruction) {
		this.instruction = instruction;
	}
	public Instructions(ArrayList<Instruction> instructions){
		this.instruction=instructions;
	}
	public Instructions addInstruction(Instruction instruction){
		this.instruction.add(instruction);
		return this;
	}
	public Instructions(){
		
	}
}
