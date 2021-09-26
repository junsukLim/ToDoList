package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		TodoUtil u = new TodoUtil();
		boolean isList = false;
		boolean quit = false;
		u.loadList(l,"todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "add":
				u.createItem(l);
				break;
			
			case "del":
				u.deleteItem(l);
				break;
				
			case "edit":
				u.updateItem(l);
				break;
				
			case "ls":
				u.listAll(l);
				break;
				
			case "ls_cate":
				u.listCate(l);
				break;
				
			case "find":
				u.findItem(l);
				break;
				
			case "find_cate":
				u.findCate(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				System.out.println("����� ������ �Ϸ�Ǿ����ϴ�.");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				System.out.println("���񿪼� ������ �Ϸ�Ǿ����ϴ�.");
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				System.out.println("��¥�� ������ �Ϸ�Ǿ����ϴ�.");
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				isList = true;
				System.out.println("��¥���� ������ �Ϸ�Ǿ����ϴ�.");
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("������ �޴��� �̸��� ��Ȯ�ϰ� �Է����ּ���. [�޴��ٽú��� - help]");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		u.saveList(l,"todolist.txt");
	}
}
