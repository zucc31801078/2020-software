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
			//��·����
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while((tempString = reader.readLine()) != null) {//��ȡÿ��
				Route r = new Route();
				String[] t = tempString.split(" ");//ÿ�з�Ϊ����ַ���(������·����վ����)
				r.setRname(t[0]);//���ַ���Ϊ��·��
				for(int i=1;i<t.length;i++) {
					r.addRoute(t[i]);//ʣ���ַ���Ϊ��·��վ������
				}
				route.add(r);//���������������
			}
			//վ�㲿��
			for(int i=0;i<route.size();i++) {
				String rname = route.get(i).getRname();
				ArrayList<String> l = route.get(i).getRoute();//��ʱ���浱ǰ��·�ڲ�վ��
				//�жϻ���(����·��β��ͬ��Ϊ����)
				if(l.get(0).equals(l.get(l.size()-1))) {
					int flag=0;
					//���ȴ�����βվ��
					for(int k=0;k<station.size();k++) {
						//����վ������¼���������������Ϣ
						//�л��ߵ�վ����β��ͬ���������������վ
						if(station.get(k).getSname().equals(l.get(0))) {
							station.get(k).setBTR(rname);//����������·
							//������վ
							station.get(k).setBs(l.get(1));
							station.get(k).setBs(l.get(l.size()-2));
							flag=1;
							break;
						}
					}
					//����վ���δ¼�룬���½�
					if(flag==0) {
						Station s = new Station();
						s.setSname(l.get(0));//���վ������
						s.setBTR(rname);//���������·
						//�����վ
						s.setBs(l.get(1));
						s.setBs(l.get(l.size()-2));
						station.add(s);
					}
				}else {
					int flag=0;
					//���ȴ����ײ�վ��
					for(int k=0;k<station.size();k++) {
						//����վ������¼���������������Ϣ
						//δ�л�����βվ�����һ������վ
						if(station.get(k).getSname().equals(l.get(0))) {
							station.get(k).setBTR(rname);
							station.get(k).setBs(l.get(1));
							flag=1;
							break;
						}
					}
					//����վ���δ¼�룬���½�
					if(flag==0) {
						Station s = new Station();
						s.setSname(l.get(0));
						s.setBTR(rname);
						s.setBs(l.get(1));
						station.add(s);
					}
					flag=0;
					//����β��վ��
					for(int k=0;k<station.size();k++) {
						//����վ������¼���������������Ϣ
						if(station.get(k).getSname().equals(l.get(l.size()-1))) {
							station.get(k).setBTR(rname);
							station.get(k).setBs(l.get(l.size()-2));
							flag=1;
							break;
						}
					}
					//����վ���δ¼�룬���½�
					if(flag==0) {
						Station s = new Station();
						s.setSname(l.get(l.size()-1));
						s.setBTR(rname);
						s.setBs(l.get(l.size()-2));
						station.add(s);
					}
					
				}
				//��������ʣ��վ��
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
						//ǰ��վ���Ϊ��վ
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
