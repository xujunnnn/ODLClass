package Test;

import NetWork_Topology.Link;
import NetWork_Topology.Topology;

public class InnerLinktest {
public static void main(String []args){
	Topology topology=new Topology();
	for(Link link:topology.get_inner_link()){
		System.out.println(link.getSource().getSource_node());
		
	}
}
}
