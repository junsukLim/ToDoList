package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public void createItem(TodoList list) {
		
		String title, desc, category, due_date,member;
		int is_completed;
		int importance;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� �߰�]\n"+"���� > ");
		title = sc.next();
		if(list.isDuplicate(title)){
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		System.out.print("ī�װ� > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("���� > ");;
		desc = sc.nextLine().trim();
		System.out.print("�������� > ");
		due_date = sc.nextLine().trim();
		is_completed = 0;
		System.out.print("�߿䵵(1~5) > ");
		importance = sc.nextInt();
		System.out.print("�Բ��ϴ� ��� > ");
		member =sc.next();
		TodoItem t = new TodoItem(title,desc,category,due_date,is_completed,importance,member);
		if(list.addItem(t)>0)
			System.out.println("�߰��Ǿ����ϴ�.");
	}

	public void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[�׸� ����]\n"+"������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		if(l.deleteItem(index)>0)
			System.out.println("�����Ǿ����ϴ�.");
	}

	public void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date,new_member;
		int new_is_completed;
		int new_importance;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� ����]\n"+"������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		
		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		if(l.isDuplicate(new_title)){
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("�� ���� > ");
		new_desc = sc.nextLine().trim();
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();
		System.out.print("�ϷῩ��(�Ϸ�:1/�̿Ϸ�:0) > ");
		new_is_completed = sc.nextInt();
		System.out.print("�߿䵵(1~5) > ");
		new_importance = sc.nextInt();
		System.out.print("�Բ��ϴ� ��� > ");
		new_member = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, new_is_completed,new_importance,new_member);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("�����Ǿ����ϴ�.");
	}

	public void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n",l.getList().size());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public void listCate(TodoList l) {
		String[] allCate=new String[100];
		int count=0;
		int num=0;
		int i=0;
		
		for (TodoItem item : l.getList()) {
			allCate[num]=item.getCategory();
			count=0;
			for(i=0;i<num;i++) {
				if(allCate[i].equals(allCate[num])) {
					count++;
				}
			}
			if(count==0) num++;
		}
		for(i=0;i<num;i++) {
			System.out.print(allCate[i]);
			if(i!=num-1) {
				System.out.print(" / ");
			}
			else {
				System.out.print("\n");
			}
		}
		System.out.println("�� "+num+"���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.");
	}
	
	
	public void findItem(TodoList l) {
		int count=0;
		int num=0;
		String fword;
		Scanner sc = new Scanner(System.in);
		System.out.println("�˻��ϰ� ���� �ܾ �Է��ϼ���.");
		fword=sc.next();
		
		for (TodoItem item : l.getList()) {
			count++;
			if((item.getCategory()).contains(fword)||(item.getTitle()).contains(fword)||(item.getDesc()).contains(fword)||(item.getDue_date()).contains(fword)||(item.getCurrent_date()).contains(fword)) {
				num++;
				if(item.getIs_completed()==1) {
					System.out.println(item.getId()+". �߿䵵:"+item.getImportance()+" [O] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " + " - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
				else {
					System.out.println(item.getId()+". �߿䵵:"+item.getImportance()+" [X] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " +" - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
			}
		}
		System.out.println("�� "+num+"���� ����Ʈ�� ã�ҽ��ϴ�.");
	}
	
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n",count);
	}
	
	public void findCate(TodoList l) {
		int count=0;
		int num=0;
		String fcate;
		Scanner sc = new Scanner(System.in);
		System.out.println("�˻��ϰ� ���� ī�װ��� �Է��ϼ���.");
		fcate=sc.next();
		
		for (TodoItem item : l.getList()) {
			count++;
			if((item.getCategory()).contains(fcate)) {
				num++;
				if(item.getIs_completed()==1) {
					System.out.println(item.getId()+". �߿䵵:"+item.getImportance()+" [O] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " +  " - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
				else {
					System.out.println(item.getId()+". �߿䵵:"+item.getImportance()+" [X] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " +  " - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
			}
		}
		System.out.println("�� "+num+"���� ����Ʈ�� ã�ҽ��ϴ�.");
	}
	
	public void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("����Ʈ�� ����Ǿ����ϴ�.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			int count=0;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline,"##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String time = st.nextToken();
				int is_completed = (int) st.nextElement();
				int importance = (int) st.nextElement();
				String member= st.nextToken();
				TodoItem s = new TodoItem(category,title,desc,due_date,time,is_completed,importance,member);
				l.addItem(s);
				System.out.println(oneline);
				count++;
			}
			br.close();
			System.out.println(count+"���� �׸��� �о����ϴ�.");
		} catch (FileNotFoundException e) {
			System.out.println("����� ������ �������� �ʽ��ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void listCateAll(TodoList l) {
		int count=0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n",count);
	}

	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n",count);
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n",l.getCount());
		for(TodoItem item : l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
		}
		
	}

	public void completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[�׸� �Ϸ�]\n"+"�Ϸ��� �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		if(l.completeItem(index)>0)
			System.out.println("�Ϸ�ó�� �Ǿ����ϴ�.");
	}

	public static void listAll(TodoList l,int i) {
		int count=0;
		int num=0;
		for (TodoItem item : l.getList()) {
			count++;
			if(item.getIs_completed()==i) {
				num++;
				System.out.println(item.getId()+". �߿䵵:"+item.getImportance()+" [O] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " +" [with "+item.getMember()+"] " +  item.getDesc() + " - "+item.getDue_date()+" - " + item.getCurrent_date());
			}
		}					
		System.out.println("�� "+num+"���� ����Ʈ�� ã�ҽ��ϴ�.");
	}

	public void changeImportance(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[�׸� ����]\n"+"������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		if(l.changeImportance(index)>0)
			System.out.println("����Ǿ����ϴ�.");
	}

	public static void findimpList(TodoList l, int imp) {
		int count=0;
		for(TodoItem item : l.getListImportance(imp)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n",count);
	}

	public void changeMember(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[�׸� ����]\n"+"������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		if(l.changeMember(index)>0)
			System.out.println("����Ǿ����ϴ�.");
	}

	public static void findMemList(TodoList l, String mem) {
		int count=0;
		for(TodoItem item : l.getListMember(mem)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n",count);
	}

	public void multi_deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		int number;
		int count=0;
		int[] index = new int[100];
		System.out.print("[�׸� ����]\n"+"������ �׸��� ������ �Է��Ͻÿ� > ");
		number = sc.nextInt();
		for(int i=0;i<number;i++) {
			System.out.print("������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
			index[i]=sc.nextInt();
			if(l.deleteItem(index[i])>0) count++;
		}
		System.out.println("�����Ǿ����ϴ�.");
	}

	public void multi_completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		int number;
		int count=0;
		int[] index = new int[100];
		System.out.print("[�׸� �Ϸ�]\n"+"�Ϸ��� �׸��� ������ �Է��Ͻÿ� > ");
		number = sc.nextInt();
		for(int i=0;i<number;i++) {
			System.out.print("�Ϸ��� �׸��� ��ȣ�� �Է��Ͻÿ� > ");
			index[i]=sc.nextInt();
			if(l.completeItem(index[i])>0) count++;
		}
		System.out.println("�Ϸ�ó�� �Ǿ����ϴ�.");
	}

	public void multi_createItem(TodoList l) {
		String title, desc, category, due_date,member;
		int is_completed;
		int importance;
		int number;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("[�׸� �߰�]\n"+"�߰��� �׸��� ������ �Է��Ͻÿ� > ");
		number = sc.nextInt();
		
		System.out.print("[�׸� �߰�]\n"+"���� > ");
		title = sc.next();
		if(l.isDuplicate(title)){
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		
		System.out.print("ī�װ� > ");
		category = sc.next();
		sc.nextLine();
		
		System.out.print("���� > ");;
		desc = sc.nextLine().trim();
		
		System.out.print("�߿䵵(1~5) > ");
		importance = sc.nextInt();
		System.out.print("�Բ��ϴ� ��� > ");
		member =sc.next();
		for(int i=0;i<number;i++) {
			System.out.print("�������� > ");
			due_date = sc.next();
			is_completed = 0;
			TodoItem t = new TodoItem(title,desc,category,due_date,is_completed,importance,member);
			if(l.addItem(t)>0);
		}
		System.out.println("�߰��Ǿ����ϴ�.");
	}

	public static void showImportantList(TodoList l, int important) {
		System.out.printf("[��ü ���, �� %d��]\n",l.getCount());
		int count=0;
		for(TodoItem item : l.getOrderedList("due_date",1)) {
			if((item.getImportance()>3)&&(item.getIs_completed()==0)) {
				count++;
				System.out.println(item.toString());
			}
			if(count==5) break;
		}
	}
}