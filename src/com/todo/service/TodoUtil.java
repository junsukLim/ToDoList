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
		
		String category, title, desc,due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "===�߰��ϱ�===\n"
				+ "�߰��� ī�װ��� �Է��ϼ���.");
		category = sc.next();
	
		System.out.println("�߰��� ������ �Է��ϼ���.");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�̹� ����� �����Դϴ�.");
			return;
		}
		
		sc.nextLine();
		System.out.println("������ �Է��ϼ���.");
		desc = sc.nextLine().trim();
		
		System.out.println("�������ڸ� �Է��ϼ���. ex)2021/01/01");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(category,title, desc,due_date);
		list.addItem(t);
		System.out.println("�Ϸ�Ǿ����ϴ�.");
	}

	public void deleteItem(TodoList l) {
		int count=0;
		int num;
		String okay;
		
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ "===�����ϱ�===\n"
				+ "������ ����Ʈ�� ��ȣ�� �Է��ϼ���.");
		num = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			count++;
			if(num==count) {
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
				System.out.println("�� ����Ʈ�� �����Ͻðڽ��ϱ�? (y/n)");
				okay=sc.next();
				if (okay.equals("y")) {
					l.deleteItem(item);
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
					break;
				}
				else {
					System.out.println("������ ��ҵǾ����ϴ�.");
				}
			}
		}
	}


	public void updateItem(TodoList l) {
		int count=0;
		int num;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "===�����ϱ�===\n"
				+ "������ ����Ʈ�� ��ȣ�� �Է��ϼ���.");
		num=sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			count++;
			if(num==count) {
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
				System.out.println("���ο� ������ �Է��ϼ���.");
				String new_title = sc.next().trim();
				if (l.isDuplicate(new_title)) {
					System.out.println("�̹� ����� �����Դϴ�.");
					return;
				}
				System.out.println("���ο� ī�װ��� �Է��ϼ���.");
				String new_category = sc.next();
		
				sc.nextLine();
				System.out.println("���ο� ������ �Է��ϼ���.");
				String new_description = sc.nextLine().trim();
				
				System.out.println("���ο� �������ڸ� �Է��ϼ���. ex)2021/01/01");
				String new_duedate = sc.next();
				
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category,new_title, new_description,new_duedate);
				l.addItem(t);
				System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			}
		}

	}

	public void listAll(TodoList l) {
		int count=0;
		int num=0;
		for (TodoItem item : l.getList()) {
			count++;
		}
		System.out.println("\n[����� ����Ʈ] (�� "+count +"��)");
		for (TodoItem item : l.getList()) {
			num++;
			System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
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
}