package Model;

import java.util.*;

public class Route {
	private String rname;
	private ArrayList<String> route = new ArrayList<>();
	
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	
	//����վ��
	public void addRoute(String sname) {
		this.route.add(sname);
	}
	
	//���·��
	public String allRoute() {
		String result="";
		
		for(int i=0;i<route.size();i++) {
			result+=route.get(i)+" ";
		}
		
		return result.trim();
	}
	public ArrayList<String> getRoute() {
		return this.route;
	}
}
