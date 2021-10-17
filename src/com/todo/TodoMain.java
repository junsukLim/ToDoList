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
		/*
		 l.importData("todolist.txt");
		*/
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
				
			case "multi_add":
				u.multi_createItem(l);
				break;
			
			case "del":
				u.deleteItem(l);
				break;
				
			case "multi_del":
				u.multi_deleteItem(l);
				break;
				
			case "edit":
				u.updateItem(l);
				break;
				
			case "ls":
				u.listAll(l);
				break;
				
			case "comp":
				u.completeItem(l);
				break;
				
			case "multi_comp":
				u.multi_completeItem(l);
				break;
			
			case "ls_comp":
				TodoUtil.listAll(l,1);
				break;
				
			case "chg_imp":
				u.changeImportance(l);
				break;
				
			case "chg_mem":
				u.changeMember(l);
				break;
				
			case "ls_imp":
				System.out.println("�߿䵵�� ������ �Ϸ�Ǿ����ϴ�.");
				TodoUtil.listAll(l,"importance",0);
				break;
				
			case "ls_imp_desc":
				System.out.println("�߿䵵���� ������ �Ϸ�Ǿ����ϴ�.");
				TodoUtil.listAll(l,"importance",1);
				break;
				
			case "show_important":
				int important = 4;
				TodoUtil.showImportantList(l,important);
				break;
				
			case "find_imp":
				int imp = sc.nextInt();
				TodoUtil.findimpList(l,imp);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l,keyword);
				break;
				
			case "find_mem":
				String mem = sc.nextLine().trim();
				TodoUtil.findMemList(l,mem);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l,cate);
				break;
				
			case "ls_name":
				System.out.println("����� ������ �Ϸ�Ǿ����ϴ�.");
				TodoUtil.listAll(l,"title",1);
				break;

			case "ls_name_desc":
				System.out.println("���񿪼� ������ �Ϸ�Ǿ����ϴ�.");
				TodoUtil.listAll(l,"title",0);
				break;
				
			case "ls_date":
				System.out.println("��¥�� ������ �Ϸ�Ǿ����ϴ�.");
				TodoUtil.listAll(l,"due_date",1);
				break;
				
			case "ls_date_desc":
				System.out.println("��¥���� ������ �Ϸ�Ǿ����ϴ�.");
				TodoUtil.listAll(l,"due_date",0);
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
