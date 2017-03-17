package Vtn;

import java.util.ArrayList;

public class Denyed_Hosts {
	private ArrayList<String> denied_hosts=new ArrayList<String>();
    public Denyed_Hosts(){}
    public Denyed_Hosts(ArrayList<String> denied_hosts){
    	this.denied_hosts=denied_hosts;
    }
    public Denyed_Hosts addHost(String host){
    	this.denied_hosts.add(host);
    	return this;
    }
	public ArrayList<String> getDenied_hosts() {
		return denied_hosts;
	}
    
	public Denyed_Hosts setDenied_hosts(ArrayList< String> denied_hosts) {
		this.denied_hosts = denied_hosts;
		return this;
	}

}
