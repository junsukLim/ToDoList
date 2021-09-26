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
				+ "===추가하기===\n"
				+ "추가할 카테고리를 입력하세요.");
		category = sc.next();
	
		System.out.println("추가할 제목을 입력하세요.");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("이미 저장된 제목입니다.");
			return;
		}
		
		sc.nextLine();
		System.out.println("설명을 입력하세요.");
		desc = sc.nextLine().trim();
		
		System.out.println("마감일자를 입력하세요. ex)2021/01/01");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(category,title, desc,due_date);
		list.addItem(t);
		System.out.println("완료되었습니다.");
	}

	public void deleteItem(TodoList l) {
		int count=0;
		int num;
		String okay;
		
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ "===삭제하기===\n"
				+ "삭제할 리스트의 번호를 입력하세요.");
		num = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			count++;
			if(num==count) {
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
				System.out.println("위 리스트를 삭제하시겠습니까? (y/n)");
				okay=sc.next();
				if (okay.equals("y")) {
					l.deleteItem(item);
					System.out.println("삭제가 완료되었습니다.");
					break;
				}
				else {
					System.out.println("삭제가 취소되었습니다.");
				}
			}
		}
	}


	public void updateItem(TodoList l) {
		int count=0;
		int num;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "===수정하기===\n"
				+ "수정할 리스트의 번호를 입력하세요.");
		num=sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			count++;
			if(num==count) {
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
				System.out.println("새로운 제목을 입력하세요.");
				String new_title = sc.next().trim();
				if (l.isDuplicate(new_title)) {
					System.out.println("이미 저장된 제목입니다.");
					return;
				}
				System.out.println("새로운 카테고리를 입력하세요.");
				String new_category = sc.next();
		
				sc.nextLine();
				System.out.println("새로운 설명을 입력하세요.");
				String new_description = sc.nextLine().trim();
				
				System.out.println("새로운 마감일자를 입력하세요. ex)2021/01/01");
				String new_duedate = sc.next();
				
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category,new_title, new_description,new_duedate);
				l.addItem(t);
				System.out.println("수정이 완료되었습니다.");
			}
		}

	}

	public void listAll(TodoList l) {
		int count=0;
		int num=0;
		for (TodoItem item : l.getList()) {
			count++;
		}
		System.out.println("\n[저장된 리스트] (총 "+count +"개)");
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
		System.out.println("총 "+num+"개의 카테고리가 등록되어 있습니다.");
	}
	
	
	public void findItem(TodoList l) {
		int count=0;
		int num=0;
		String fword;
		Scanner sc = new Scanner(System.in);
		System.out.println("검색하고 싶은 단어를 입력하세요.");
		fword=sc.next();
		
		for (TodoItem item : l.getList()) {
			count++;
			if((item.getCategory()).contains(fword)||(item.getTitle()).contains(fword)||(item.getDesc()).contains(fword)||(item.getDue_date()).contains(fword)||(item.getCurrent_date()).contains(fword)) {
				num++;
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
			}
		}
		System.out.println("총 "+num+"개의 리스트를 찾았습니다.");
	}
	
	public void findCate(TodoList l) {
		int count=0;
		int num=0;
		String fcate;
		Scanner sc = new Scanner(System.in);
		System.out.println("검색하고 싶은 카테고리를 입력하세요.");
		fcate=sc.next();
		
		for (TodoItem item : l.getList()) {
			count++;
			if((item.getCategory()).contains(fcate)) {
				num++;
				System.out.println(num+". ["+item.getCategory() +"] " + item.getTitle() + " : " + item.getDesc() + " ("+item.getDue_date()+") <" + item.getCurrent_date() + ">");
			}
		}
		System.out.println("총 "+num+"개의 리스트를 찾았습니다.");
	}
	
	public void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("리스트가 저장되었습니다.");
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
			System.out.println(count+"개의 항목을 읽었습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("저장된 파일이 존재하지 않습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}