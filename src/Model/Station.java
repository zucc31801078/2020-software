package Model;

import java.util.*;

public class Station {
	public final static int MaxDist = 65535;
	private String sname;//վ��
	private ArrayList<String> bTR = new ArrayList<>();//��·��
	private ArrayList<String> bs = new ArrayList<>();//��վ������Ϊ1��վ��

	
	//ִ���㷨�����
	private Station ps;//ǰһ��վ��
	private int dist;//���루����ʼվ��
	private int transNum;//������
	private int visited;//�����Ƿ����
	
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
	//վ����·�ߣ������ж����
	public void setBTR(String belongToRname) {
		this.bTR.add(belongToRname);
	}
	//����·������������㷨��
	public ArrayList<String> getBTR() {
		return this.bTR;
	}
	//����վ¼��
	public void setBs(String sname) {
		for(int i=0;i<this.bs.size();i++) {
			if(this.bs.get(i).equals(sname)) {
				return;
			}
		}
		this.bs.add(sname);
	}
	//����վ����������㷨��
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

	//ִ���㷨�����
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
