package Util;

import java.util.*;
import Control.*;
import Model.*;

public class Start {
	public final static String FILEPATH = "D:\\data.txt";
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		
		//��ȡ�ļ�
		ReadData file = new ReadData(FILEPATH);
		//��ȡ�������վ�����·��Ϣ
		ArrayList<Station> station= file.getStation();
		ArrayList<Route> route= file.getRoute();
		
		//���������·����
		for(int i=0;i<route.size();i++) {
			System.out.print(route.get(i).getRname()+" ");
		}
		System.out.println();
		//����˵�
		System.out.println("��ѡ����������: 1.��ѯ��· 2.�滮·��");
		//����
		int choose = input.nextInt();
		if(choose==1) {
			System.out.print("�����������ѯ����·��: ");
			String name = input.next();
			int index=-1;
			for(int i=0;i<route.size();i++) {
				if(route.get(i).getRname().equals(name)) {
					index=i;
					break;
				}
			}
			if(index==-1) {
				System.out.println("����·������");
			}else {
				System.out.println(route.get(index).getRname()+":"+route.get(index).allRoute());
			}
		}else if(choose==2) {
			BFS bfs = new BFS();
			System.out.print("������ʼ��վ: ");
			String start = input.next();
			station=bfs.FindMin(start,station);
			System.out.print("�������յ�վ: ");
			String end = input.next();
			bfs.shortPath(end, station);
		}
		
	}
}
