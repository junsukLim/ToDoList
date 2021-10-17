package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("[전체 메뉴]");
        System.out.println("[추가하기] -> add");
        System.out.println("[다중추가하기] -> multi_add");
        System.out.println("[삭제하기] -> del");
        System.out.println("[다중삭제하기] -> multi_del");
        System.out.println("[수정하기] -> edit");
        System.out.println("[완료하기] -> comp");
        System.out.println("[다중완료하기] -> multi_comp");
        System.out.println("[중요도 수정하기] -> chg_imp");
        System.out.println("[함께하는 사람 수정하기] -> chg_mem");
        System.out.println("[중요한 일 보여주기] -> show_important");
        System.out.println("[전체 검색하기] -> find");
        System.out.println("[카테고리 검색하기] -> find_cate");
        System.out.println("[중요도 검색하기] -> find_imp");
        System.out.println("[함께하는 사람 검색하기] -> find_mem");
        System.out.println("[리스트 보기] -> ls");
        System.out.println("[카테고리 목록 보기] -> ls_cate");
        System.out.println("[완료된 항목 보기] -> ls_comp");
        System.out.println("[중요도순 정렬] -> ls_imp");
        System.out.println("[중요도순 정렬] -> ls_imp_desc");
        System.out.println("[제목순 정렬] -> ls_name");
        System.out.println("[제목역순 정렬] -> ls_name_desc");
        System.out.println("[날짜순 정렬] -> ls_date");
        System.out.println("[날짜역순 정렬] -> ls_date_desc");
        System.out.println("[종료] -> Or press escape key to exit");
    }

	public static void prompt() {
		System.out.println("\n실행할 메뉴 >");
	}
}