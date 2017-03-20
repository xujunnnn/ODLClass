package FLow;

import java.util.ArrayList;

import NetMonitor.Protocol_Type;

public class InitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Initialize initialize=new Initialize();      
        ArrayList<Protocol_Type> protocol_Types=new ArrayList<>();
        protocol_Types.add(Protocol_Type.ICMP);
        protocol_Types.add(Protocol_Type.TCP);
        protocol_Types.add(Protocol_Type.UDP);
        initialize.setProtocol_Types(protocol_Types);
        for(Protocol_Type p:protocol_Types){
        	System.out.println(p);
        }
        initialize.init();
	}

}
