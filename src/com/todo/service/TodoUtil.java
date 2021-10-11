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
		
		System.out.print("[항목 추가]\n"+"제목 > ");
		title = sc.next();
		if(list.isDuplicate(title)){
			System.out.println("제목이 중복됩니다!");
			return;
		}
		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("내용 > ");;
		desc = sc.nextLine().trim();
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title,desc,category,due_date);
		if(list.addItem(t)>0)
			System.out.println("추가되었습니다.");
	}

	public void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 삭제]\n"+"삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.deleteItem(index)>0)
			System.out.println("삭제되었습니다.");
	}

	public void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n"+"수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("수정되었습니다.");
	}

	public void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n",l.getList().size());
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
	
	public static void findList(TodoList l, String keyword) {
		int count=0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
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

	public static void listCateAll(TodoList l) {
		int count=0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n",count);
	}

	public static void findCateList(TodoList l, String cate) {
		int count=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n",count);
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n",l.getCount());
		for(TodoItem item : l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
		}
		
	}
}