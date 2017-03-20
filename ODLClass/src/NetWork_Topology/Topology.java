package NetWork_Topology;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import http.Request;

public class Topology {
	private static final String ODL_IP="10.108.125.125:8181";
	@JSONField(name="topology-id")
	private String topology_id;
	@JSONField(name="node")
	private ArrayList<Node> nodes=new ArrayList<>();
	@JSONField(name="link")
	private ArrayList<Link> links=new ArrayList<>();
	
	
	public String getTopology_id() {
	
		return topology_id;
	}
	public void setTopology_id(String topology_id) {
		this.topology_id = topology_id;
	}
	public ArrayList<Node> getNodes() {
		
		return nodes;
	}
	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	public ArrayList<Link> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
	/**
	 * get the topology information
	 * @return
	 */
	public Topology update(){
		Request request=new Request("admin", "admin");
		String url="http://"+ODL_IP+"/restconf/operational/network-topology:network-topology";
		String json=request.Get_request(url)[1];
		JSONObject jsonObject=JSONObject.parseObject(json);
		JSONObject topojson=jsonObject.getJSONObject("network-topology").getJSONArray("topology").getJSONObject(0);
		Topology topology=JSONObject.parseObject(topojson.toJSONString(), Topology.class);
		return topology;
	}
	/**
	 * get the access switch name
	 * @return
	 */
	public ArrayList<Node> get_access_node(){
		ArrayList<Node> Accessnodes=new ArrayList<>();
		Topology topology=this.update();
		ArrayList<Node> nodes=topology.getNodes();
		for(Node node:nodes){
			if(node.getNode_id().startsWith("host")){
				String node_connector=node.getHost_tracker_service_attachment_points().get(0).getTp_id();
				String []nodeinfo=node_connector.split(":");
				String nodeid=nodeinfo[0]+":"+nodeinfo[1];
				Node node2=new Node();
				node2.setNode_id(nodeid);
				if(!Accessnodes.contains(node2)){
					Accessnodes.add(node2);
					
				}
			}
			
		}
		return Accessnodes;
		
	}
	/**
	 * get inner links
	 * @return
	 */
	public ArrayList<Link> get_inner_link(){
		ArrayList<Link> inner_links=new ArrayList<>();
		Topology topology=this.update();
		for(Link link:topology.getLinks()){
				if((link.getSource().getSource_node().startsWith("openflow")) && (link.getDestination().getDest_node().startsWith("openflow"))){
				inner_links.add(link);
			}
		}
		return inner_links;
	}
	/**
	 * get node which is a opnflow switch
	 * @return
	 */
	public ArrayList<Node> get_switch(){
		ArrayList<Node> switches=new ArrayList<>();
		Topology topology=this.update();
		for(Node node:topology.getNodes()){
			if(node.getNode_id().startsWith("openflow")){
				switches.add(node);
			}
		}
		return switches;
	}
	/**
	 * 
	 */
	public ArrayList<Termination_point> get_ports_to_host(){
		ArrayList<Termination_point>  ports_to_host=new ArrayList<>();
		ArrayList<Termination_point> ports_to_switch=this.get_ports_to_switch();
		for(Node node:this.getNodes()){
			for(Termination_point termination_point:node.getTermination_points()){
				if(!ports_to_switch.contains(termination_point) && !termination_point.getTp_id().endsWith("LOCAL")){
					System.out.println(termination_point.getTp_id());
					ports_to_host.add(termination_point);
				}
			}
		}
		return ports_to_host;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<Termination_point> get_ports_to_switch(){
		ArrayList<Termination_point> ports_to_switch=new ArrayList<>();
		for(Link link:this.get_inner_link()){
			Termination_point termination_point=new Termination_point();
			termination_point.setTp_id(link.getSource().getSource_tp());
		    ports_to_switch.add(termination_point);
		    Termination_point termination_point2=new Termination_point();
		    termination_point2.setTp_id(link.getDestination().getDest_node());
		}
		return ports_to_switch;
	}
	public ArrayList<Termination_point> get_ports_to_host(Node thenode){
		ArrayList<Termination_point>  ports_to_host=new ArrayList<>();
		ArrayList<Termination_point> ports_to_switch=this.get_ports_to_switch();
		for(Node node:this.getNodes()){
			if(node.getNode_id().equals(thenode.getNode_id())){
			for(Termination_point termination_point:node.getTermination_points()){
				if(!ports_to_switch.contains(termination_point) && !termination_point.getTp_id().endsWith("LOCAL")){
					System.out.println(termination_point.getTp_id());
					ports_to_host.add(termination_point);
				}
			}
		  }
		}
		return ports_to_host;
		
	}
}
