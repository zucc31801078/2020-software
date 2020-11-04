package Control;

import java.util.*;
import Model.*;

public class BFS {
	public ArrayList<Station> FindMin(String firstStation,ArrayList<Station> station) {
		ArrayList<Station> result = new ArrayList<>();//�����
		
		//�ҵ�ʼ��վ
		int fsIndex=-1;
		for(int i=0;i<station.size();i++) {
			Station tmp = station.get(i);
			if(tmp.getSname().equals(firstStation)) {//������ͬ���ҵ�
				fsIndex=i;
				break;
			}
		}
		//��δ�ҵ��򱨴����
		if(fsIndex==-1) {
			System.out.println("δ�ҵ�����ʼվ��");
			System.exit(0);//δ�ҵ����˳�
			return station;
		}
		
		//ִ���㷨
		Queue<Station> queue = new LinkedList<>();//����ģ�����
		station.get(fsIndex).setVisited(1);//��Ƿ���
		queue.offer(station.get(fsIndex));//��ʼվ�������
		
		int dist=0;//���沽��
		while(!queue.isEmpty()) {
			Station tmpS = queue.remove();//�Ƴ�����ͷ��
			
			if(dist==0) {//�ж��ǲ��Ƕ�ͷ
				tmpS.setDist(dist);//���벽��
				dist++;
			}else {
				//�ж��Ƿ񻻳�
				dist=tmpS.getPs().getDist();
				tmpS.setDist(dist+1);
				dist++;
			}
			result.add(tmpS);//���������
			
			ArrayList<Station> tmpBs = tmpS.getBs(station);
			for(int i=0;i<tmpBs.size();i++) {
				if(tmpBs.get(i).getVisited()==0) {//�ж��Ƿ���ʹ�
					tmpBs.get(i).setPs(tmpS);//����ǰ��վ��Ϊ��ǰվ��
					tmpBs.get(i).setVisited(1);//��Ƿ���
					queue.offer(tmpBs.get(i));//��δ���ʹ���ֱ�Ӵ������
				}
			}
		}
		
		return result;//���ؽ����
	}
	
	public void shortPath(String endStation,ArrayList<Station> station) {
		//�ҵ��յ�վ
		int endIndex=-1;
		for(int i=0;i<station.size();i++) {
			Station tmp = station.get(i);
			if(tmp.getSname().equals(endStation)) {
				endIndex=i;
				break;
			}
		}
		//��δ�ҵ��򱨴����
		if(endIndex==-1) {
			System.out.println("δ�ҵ����յ�վ");
			System.exit(0);
			return ;
		}
		
		Stack<Station> stack = new Stack<>();//����ջ��ʵ���������
		Station tmp = station.get(endIndex);//ջ��Ϊ�յ�վ
		if(tmp.getDist()==0) {
			System.out.println("��վΪʼ��վ");
			return ;
		}
		int dist = tmp.getDist();//���ڱ���;��վ����
		int transNum = 0;//���ڱ��滻����
		//����ջ
		while(tmp.getPs()!=null) {
			stack.push(tmp);
			tmp=tmp.getPs();//����Ϊǰ��վ����ջ
		}
		
		//�жϻ���
		ArrayList<String> r1 =tmp.getBTR();
		ArrayList<String> r2 = stack.peek().getBTR();
		String now="";//���ڱ��浱ǰ��·
		int flag=0;
		//Ѱ�ҵ�ǰ��·
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
		System.out.println("��ǰΪ��"+now);
		System.out.print(tmp.getSname());
		//�𲽳�ջ
		while(!stack.isEmpty()) {
			//�ж��Ƿ񻻳�
			r1 = tmp.getBTR();
			r2 = stack.peek().getBTR();
			flag=0;
			for(int i=0;i<r1.size();i++) {
				for(int j=0;j<r2.size();j++) {
					//������վ�������е���·�뵱ǰ��·��ͬ����Ϊ������·
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
				System.out.println("ת����"+now);
				System.out.print(stack.pop().getSname());
				transNum++;
			}else {
				tmp=stack.peek();
				System.out.print("-->"+stack.pop().getSname());
			}
			
		}
		System.out.println();
		dist--;
		System.out.println(";��վ��"+dist);
		System.out.println("������"+transNum);
	}
}
