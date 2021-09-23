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
				+ "===추가하기===\n"
				+ "추가할 제목을 입력하세요.");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("이미 저장된 제목입니다.");
			return;
		}
		sc.nextLine();
		System.out.println("설명을 입력하세요.");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ "===삭제하기===\n"
				+ "삭제할 제목을 입력하세요.");
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
				+ "===수정하기===\n"
				+ "수정할 제목을 입력하세요.");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("추가되지 않은 제목입니다.");
			return;
		}

		System.out.println("새로운 제목을 입력하세요.");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 저장된 제목입니다.");
			return;
		}

		sc.nextLine();
		System.out.println("새로운 설명을 입력하세요.");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정이 완료되었습니다.");
			}
		}

	}

	public void listAll(TodoList l) {
		System.out.println("\n[저장된 리스트]");
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
			
			System.out.println("리스트 저장!!! ");
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
			System.out.println("리스트 로딩 완료 !!! ");
		} catch (FileNotFoundException e) {
			System.out.println("저장된 파일이 존재하지 않습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}