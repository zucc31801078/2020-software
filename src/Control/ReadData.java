package Control;

import java.util.*;
import java.io.*;
import Model.*;

public class ReadData {
	private ArrayList<Station> station= new ArrayList<>();
	private ArrayList<Route> route= new ArrayList<>();
	
	public ArrayList<Station> getStation() {
		return station;
	}

	public ArrayList<Route> getRoute() {
		return route;
	}
	
	public ReadData(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			//线路部分
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while((tempString = reader.readLine()) != null) {//读取每行
				Route r = new Route();
				String[] t = tempString.split(" ");//每行分为多个字符串(包括线路名和站点名)
				r.setRname(t[0]);//首字符串为线路名
				for(int i=1;i<t.length;i++) {
					r.addRoute(t[i]);//剩下字符均为线路内站点名称
				}
				route.add(r);//将结果添加至结果集
			}
			//站点部分
			for(int i=0;i<route.size();i++) {
				String rname = route.get(i).getRname();
				ArrayList<String> l = route.get(i).getRoute();//临时保存当前线路内部站点
				//判断环线(若线路首尾相同则为环线)
				if(l.get(0).equals(l.get(l.size()-1))) {
					int flag=0;
					//优先处理首尾站点
					for(int k=0;k<station.size();k++) {
						//若该站点曾经录入过，则添加相关信息
						//有环线的站点首尾相同且需添加两个相邻站
						if(station.get(k).getSname().equals(l.get(0))) {
							station.get(k).setBTR(rname);//更新所属线路
							//更新邻站
							station.get(k).setBs(l.get(1));
							station.get(k).setBs(l.get(l.size()-2));
							flag=1;
							break;
						}
					}
					//若该站点从未录入，则新建
					if(flag==0) {
						Station s = new Station();
						s.setSname(l.get(0));//添加站点名称
						s.setBTR(rname);//添加所属线路
						//添加邻站
						s.setBs(l.get(1));
						s.setBs(l.get(l.size()-2));
						station.add(s);
					}
				}else {
					int flag=0;
					//优先处理首部站点
					for(int k=0;k<station.size();k++) {
						//若该站点曾经录入过，则添加相关信息
						//未有环线首尾站点仅有一个相邻站
						if(station.get(k).getSname().equals(l.get(0))) {
							station.get(k).setBTR(rname);
							station.get(k).setBs(l.get(1));
							flag=1;
							break;
						}
					}
					//若该站点从未录入，则新建
					if(flag==0) {
						Station s = new Station();
						s.setSname(l.get(0));
						s.setBTR(rname);
						s.setBs(l.get(1));
						station.add(s);
					}
					flag=0;
					//处理尾部站点
					for(int k=0;k<station.size();k++) {
						//若该站点曾经录入过，则添加相关信息
						if(station.get(k).getSname().equals(l.get(l.size()-1))) {
							station.get(k).setBTR(rname);
							station.get(k).setBs(l.get(l.size()-2));
							flag=1;
							break;
						}
					}
					//若该站点从未录入，则新建
					if(flag==0) {
						Station s = new Station();
						s.setSname(l.get(l.size()-1));
						s.setBTR(rname);
						s.setBs(l.get(l.size()-2));
						station.add(s);
					}
					
				}
				//遍历处理剩余站点
				for(int j=1;j<l.size()-1;j++) {
					int flag=0;
					for(int k=0;k<station.size();k++) {
						if(station.get(k).getSname().equals(l.get(j))) {
							station.get(k).setBTR(rname);
							station.get(k).setBs(l.get(j-1));
							station.get(k).setBs(l.get(j+1));
							flag=1;
							break;
						}
					}
					if(flag==0) {
						Station s = new Station();
						s.setSname(l.get(j));
						s.setBTR(rname);
						//前后站点均为邻站
						s.setBs(l.get(j-1));
						s.setBs(l.get(j+1));
						station.add(s);
					}
					
					
				}
			}
		}catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }
	}
}
