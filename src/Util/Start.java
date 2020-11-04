package Util;

import java.util.*;
import Control.*;
import Model.*;

public class Start {
	public final static String FILEPATH = "D:\\data.txt";
	public static void main(String[] args) throws Exception{
		Scanner input = new Scanner(System.in);
		
		//读取文件
		ReadData file = new ReadData(FILEPATH);
		//提取所保存的站点和线路信息
		ArrayList<Station> station= file.getStation();
		ArrayList<Route> route= file.getRoute();
		
		//输出所有线路名称
		for(int i=0;i<route.size();i++) {
			System.out.print(route.get(i).getRname()+" ");
		}
		System.out.println();
		//输出菜单
		System.out.println("请选择所需任务: 1.查询线路 2.规划路线");
		//输入
		int choose = input.nextInt();
		if(choose==1) {
			System.out.print("请输入所需查询的线路名: ");
			String name = input.next();
			int index=-1;
			for(int i=0;i<route.size();i++) {
				if(route.get(i).getRname().equals(name)) {
					index=i;
					break;
				}
			}
			if(index==-1) {
				System.out.println("该线路名错误");
			}else {
				System.out.println(route.get(index).getRname()+":"+route.get(index).allRoute());
			}
		}else if(choose==2) {
			BFS bfs = new BFS();
			System.out.print("请输入始发站: ");
			String start = input.next();
			station=bfs.FindMin(start,station);
			System.out.print("请输入终点站: ");
			String end = input.next();
			bfs.shortPath(end, station);
		}
		
	}
}
