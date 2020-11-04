package Control;

import java.util.*;
import Model.*;

public class BFS {
	public ArrayList<Station> FindMin(String firstStation,ArrayList<Station> station) {
		ArrayList<Station> result = new ArrayList<>();//结果集
		
		//找到始发站
		int fsIndex=-1;
		for(int i=0;i<station.size();i++) {
			Station tmp = station.get(i);
			if(tmp.getSname().equals(firstStation)) {//名称相同即找到
				fsIndex=i;
				break;
			}
		}
		//若未找到则报错结束
		if(fsIndex==-1) {
			System.out.println("未找到该起始站点");
			System.exit(0);//未找到则退出
			return station;
		}
		
		//执行算法
		Queue<Station> queue = new LinkedList<>();//链表模拟队列
		station.get(fsIndex).setVisited(1);//标记访问
		queue.offer(station.get(fsIndex));//初始站点入队列
		
		int dist=0;//保存步数
		while(!queue.isEmpty()) {
			Station tmpS = queue.remove();//移出队列头部
			
			if(dist==0) {//判断是不是队头
				tmpS.setDist(dist);//存入步数
				dist++;
			}else {
				//判断是否换乘
				dist=tmpS.getPs().getDist();
				tmpS.setDist(dist+1);
				dist++;
			}
			result.add(tmpS);//结果集增加
			
			ArrayList<Station> tmpBs = tmpS.getBs(station);
			for(int i=0;i<tmpBs.size();i++) {
				if(tmpBs.get(i).getVisited()==0) {//判断是否访问过
					tmpBs.get(i).setPs(tmpS);//保存前置站点为当前站点
					tmpBs.get(i).setVisited(1);//标记访问
					queue.offer(tmpBs.get(i));//若未访问过则直接存入队列
				}
			}
		}
		
		return result;//返回结果集
	}
	
	public void shortPath(String endStation,ArrayList<Station> station) {
		//找到终点站
		int endIndex=-1;
		for(int i=0;i<station.size();i++) {
			Station tmp = station.get(i);
			if(tmp.getSname().equals(endStation)) {
				endIndex=i;
				break;
			}
		}
		//若未找到则报错结束
		if(endIndex==-1) {
			System.out.println("未找到该终点站");
			System.exit(0);
			return ;
		}
		
		Stack<Station> stack = new Stack<>();//建立栈以实现逆序输出
		Station tmp = station.get(endIndex);//栈底为终点站
		if(tmp.getDist()==0) {
			System.out.println("该站为始发站");
			return ;
		}
		int dist = tmp.getDist();//用于保存途经站点数
		int transNum = 0;//用于保存换乘数
		//逐步入栈
		while(tmp.getPs()!=null) {
			stack.push(tmp);
			tmp=tmp.getPs();//更新为前置站点入栈
		}
		
		//判断换乘
		ArrayList<String> r1 =tmp.getBTR();
		ArrayList<String> r2 = stack.peek().getBTR();
		String now="";//用于保存当前线路
		int flag=0;
		//寻找当前线路
		for(int i=0;i<r1.size();i++) {
			for(int j=0;j<r2.size();j++) {
				if(r1.get(i).equals(r2.get(j))) {
					now=r1.get(i);
					flag=1;
					break;
				}
			}
			if(flag==1) {
				break;
			}
		}
		System.out.println("当前为："+now);
		System.out.print(tmp.getSname());
		//逐步出栈
		while(!stack.isEmpty()) {
			//判断是否换乘
			r1 = tmp.getBTR();
			r2 = stack.peek().getBTR();
			flag=0;
			for(int i=0;i<r1.size();i++) {
				for(int j=0;j<r2.size();j++) {
					//若两个站点所共有的线路与当前线路不同，则为换乘线路
					if(r1.get(i).equals(r2.get(j))&&(!now.equals(r1.get(i)))) {
						now=r1.get(i);
						flag=1;
						break;
					}
				}
				if(flag==1) {
					break;
				}
			}
			if(flag==1) {
				tmp=stack.peek();
				System.out.println();
				System.out.println("转至："+now);
				System.out.print(stack.pop().getSname());
				transNum++;
			}else {
				tmp=stack.peek();
				System.out.print("-->"+stack.pop().getSname());
			}
			
		}
		System.out.println();
		dist--;
		System.out.println("途径站数"+dist);
		System.out.println("换乘数"+transNum);
	}
}
