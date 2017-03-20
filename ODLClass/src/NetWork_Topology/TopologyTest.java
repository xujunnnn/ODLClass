package NetWork_Topology;

public class TopologyTest {
	public static void main(String [] args){
	Topology topology=new Topology().update();
	
	//System.out.println(topology.getLinks().get(0).getLink_id());
//	for(String node:topology.get_access_node()){
	//	System.out.println(node);
	//}
//	for(Link link:topology.get_inner_link()){
	//	System.out.println(link.getLink_id());
//	}
for(Node node:topology.get_access_node()){
	System.out.println(node.getNode_id());
	
	}
	Node node=new Node();
	node.setNode_id("openflow:2");
for(Termination_point t:topology.get_ports_to_host(node)){
		
		//System.out.println(t.getTp_id());
	}
	
	}

}
