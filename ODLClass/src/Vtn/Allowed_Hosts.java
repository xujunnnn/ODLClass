package Vtn;

import java.util.ArrayList;

public class Allowed_Hosts {
	private ArrayList<String> allowed_hosts=new ArrayList<String>();
    public Allowed_Hosts(){}
    public Allowed_Hosts(ArrayList<String> allowed_hosts){
    	this.allowed_hosts=allowed_hosts;
    }
	public ArrayList<String> getAllowed_hosts() {
		return allowed_hosts;
	}

	public Allowed_Hosts setAllowed_hosts(ArrayList<String> allowed_hosts) {
		this.allowed_hosts = allowed_hosts;
		return this;
	}
	public Allowed_Hosts addHost(String host){
		this.allowed_hosts.add(host);
		return this;
		
	}

}
