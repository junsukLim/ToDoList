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
		is_completed = 0;
		System.out.print("중요도(1~5) > ");
		importance = sc.nextInt();
		System.out.print("함께하는 사람 > ");
		member =sc.next();
		TodoItem t = new TodoItem(title,desc,category,due_date,is_completed,importance,member);
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
		
		String new_title, new_desc, new_category, new_due_date,new_member;
		int new_is_completed;
		int new_importance;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 수정]\n"+"수정할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		if(l.isDuplicate(new_title)){
			System.out.println("제목이 중복됩니다!");
			return;
		}
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		System.out.print("완료여부(완료:1/미완료:0) > ");
		new_is_completed = sc.nextInt();
		System.out.print("중요도(1~5) > ");
		new_importance = sc.nextInt();
		System.out.print("함께하는 사람 > ");
		new_member = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date, new_is_completed,new_importance,new_member);
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
				if(item.getIs_completed()==1) {
					System.out.println(item.getId()+". 중요도:"+item.getImportance()+" [O] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " + " - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
				else {
					System.out.println(item.getId()+". 중요도:"+item.getImportance()+" [X] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " +" - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
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
				if(item.getIs_completed()==1) {
					System.out.println(item.getId()+". 중요도:"+item.getImportance()+" [O] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " +  " - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
				else {
					System.out.println(item.getId()+". 중요도:"+item.getImportance()+" [X] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " + item.getDesc() +" [with "+item.getMember()+"] " +  " - "+item.getDue_date()+" - " + item.getCurrent_date());
				}
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
				int is_completed = (int) st.nextElement();
				int importance = (int) st.nextElement();
				String member= st.nextToken();
				TodoItem s = new TodoItem(category,title,desc,due_date,time,is_completed,importance,member);
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

	public void completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 완료]\n"+"완료할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.completeItem(index)>0)
			System.out.println("완료처리 되었습니다.");
	}

	public static void listAll(TodoList l,int i) {
		int count=0;
		int num=0;
		for (TodoItem item : l.getList()) {
			count++;
			if(item.getIs_completed()==i) {
				num++;
				System.out.println(item.getId()+". 중요도:"+item.getImportance()+" [O] "+" ["+item.getCategory() +"] " + item.getTitle() + " - " +" [with "+item.getMember()+"] " +  item.getDesc() + " - "+item.getDue_date()+" - " + item.getCurrent_date());
			}
		}					
		System.out.println("총 "+num+"개의 리스트를 찾았습니다.");
	}

	public void changeImportance(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 변경]\n"+"변경할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.changeImportance(index)>0)
			System.out.println("변경되었습니다.");
	}

	public static void findimpList(TodoList l, int imp) {
		int count=0;
		for(TodoItem item : l.getListImportance(imp)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n",count);
	}

	public void changeMember(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 변경]\n"+"변경할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.changeMember(index)>0)
			System.out.println("변경되었습니다.");
	}

	public static void findMemList(TodoList l, String mem) {
		int count=0;
		for(TodoItem item : l.getListMember(mem)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n",count);
	}

	public void multi_deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		int number;
		int count=0;
		int[] index = new int[100];
		System.out.print("[항목 삭제]\n"+"삭제할 항목의 갯수를 입력하시오 > ");
		number = sc.nextInt();
		for(int i=0;i<number;i++) {
			System.out.print("삭제할 항목의 번호를 입력하시오 > ");
			index[i]=sc.nextInt();
			if(l.deleteItem(index[i])>0) count++;
		}
		System.out.println("삭제되었습니다.");
	}

	public void multi_completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		int number;
		int count=0;
		int[] index = new int[100];
		System.out.print("[항목 완료]\n"+"완료할 항목의 갯수를 입력하시오 > ");
		number = sc.nextInt();
		for(int i=0;i<number;i++) {
			System.out.print("완료할 항목의 번호를 입력하시오 > ");
			index[i]=sc.nextInt();
			if(l.completeItem(index[i])>0) count++;
		}
		System.out.println("완료처리 되었습니다.");
	}

	public void multi_createItem(TodoList l) {
		String title, desc, category, due_date,member;
		int is_completed;
		int importance;
		int number;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 추가]\n"+"추가할 항목의 갯수를 입력하시오 > ");
		number = sc.nextInt();
		
		System.out.print("[항목 추가]\n"+"제목 > ");
		title = sc.next();
		if(l.isDuplicate(title)){
			System.out.println("제목이 중복됩니다!");
			return;
		}
		
		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		
		System.out.print("내용 > ");;
		desc = sc.nextLine().trim();
		
		System.out.print("중요도(1~5) > ");
		importance = sc.nextInt();
		System.out.print("함께하는 사람 > ");
		member =sc.next();
		for(int i=0;i<number;i++) {
			System.out.print("마감일자 > ");
			due_date = sc.next();
			is_completed = 0;
			TodoItem t = new TodoItem(title,desc,category,due_date,is_completed,importance,member);
			if(l.addItem(t)>0);
		}
		System.out.println("추가되었습니다.");
	}

	public static void showImportantList(TodoList l, int important) {
		System.out.printf("[전체 목록, 총 %d개]\n",l.getCount());
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