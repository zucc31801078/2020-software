package Model;

import java.util.*;

public class Station {
	public final static int MaxDist = 65535;
	private String sname;//站名
	private ArrayList<String> bTR = new ArrayList<>();//线路名
	private ArrayList<String> bs = new ArrayList<>();//邻站（距离为1的站）

	
	//执行算法后更改
	private Station ps;//前一个站点
	private int dist;//距离（距起始站）
	private int transNum;//换乘数
	private int visited;//保存是否访问
	
	public Station() {
		this.dist=MaxDist;
		this.transNum=0;
		this.visited=0;
	}
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	//站所属路线（可能有多个）
	public void setBTR(String belongToRname) {
		this.bTR.add(belongToRname);
	}
	//所属路线输出（用于算法）
	public ArrayList<String> getBTR() {
		return this.bTR;
	}
	//相邻站录入
	public void setBs(String sname) {
		for(int i=0;i<this.bs.size();i++) {
			if(this.bs.get(i).equals(sname)) {
				return;
			}
		}
		this.bs.add(sname);
	}
	//相邻站输出（用于算法）
	public ArrayList<Station> getBs(ArrayList<Station> station){
		ArrayList<Station> result = new ArrayList<>();
		for(int i=0;i<this.bs.size();i++) {
			String tmp = this.bs.get(i);
			for(int j=0;j<station.size();j++) {
				if(station.get(j).getSname().equals(tmp)) {
					result.add(station.get(j));
					break;
				}
			}
		}
		return result;
	}
	public int getVisited() {
		return visited;
	}

	public void setVisited(int visited) {
		this.visited = visited;
	}

	//执行算法后更改
	public Station getPs() {
		return ps;
	}
	public void setPs(Station ps) {
		this.ps = ps;
	}
	public int getDist() {
		return dist;
	}
	public void setDist(int dist) {
		this.dist = dist;
	}
	public int getTransNum() {
		return transNum;
	}
	public void setTransNum(int transNum) {
		this.transNum = transNum;
	}
}
