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
		
		String title, desc, category, due_date;
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
		
		TodoItem t = new TodoItem(title,desc,category,due_date);
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
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� ����]\n"+"������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int index = sc.nextInt();
		
		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("�� ���� > ");
		new_desc = sc.nextLine().trim();
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
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
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
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
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
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
				TodoItem s = new TodoItem(category,title,desc,due_date,time);
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
}