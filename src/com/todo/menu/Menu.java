package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("[��ü �޴�]");
        System.out.println("[�߰��ϱ�] -> add");
        System.out.println("[�����ϱ�] -> del");
        System.out.println("[�����ϱ�] -> edit");
        System.out.println("[�����ֱ�] -> ls");
        System.out.println("[����� ����] -> ls_name_asc");
        System.out.println("[���񿪼� ����] -> ls_name_desc");
        System.out.println("[��¥�� ����] -> ls_date");
        System.out.println("[����] -> Or press escape key to exit");
    }

	public static void prompt() {
		System.out.println("\n������ �޴� >");
	}
}