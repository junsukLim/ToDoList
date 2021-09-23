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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "===�߰��ϱ�===\n"
				+ "�߰��� ������ �Է��ϼ���.");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�̹� ����� �����Դϴ�.");
			return;
		}
		sc.nextLine();
		System.out.println("������ �Է��ϼ���.");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ "===�����ϱ�===\n"
				+ "������ ������ �Է��ϼ���.");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "===�����ϱ�===\n"
				+ "������ ������ �Է��ϼ���.");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�߰����� ���� �����Դϴ�.");
			return;
		}

		System.out.println("���ο� ������ �Է��ϼ���.");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� ����� �����Դϴ�.");
			return;
		}

		sc.nextLine();
		System.out.println("���ο� ������ �Է��ϼ���.");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			}
		}

	}

	public void listAll(TodoList l) {
		System.out.println("\n[����� ����Ʈ]");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "] => " + item.getDesc() + " (" + item.getCurrent_date() + ")");
		}
	}

	public void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("����Ʈ ����!!! ");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline,"##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String time = st.nextToken();
				TodoItem s = new TodoItem(title,desc,time);
				l.addItem(s);
				System.out.println(oneline);
			}
			br.close();
			System.out.println("����Ʈ �ε� �Ϸ� !!! ");
		} catch (FileNotFoundException e) {
			System.out.println("����� ������ �������� �ʽ��ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}