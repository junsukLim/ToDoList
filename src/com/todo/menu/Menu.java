package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("[��ü �޴�]");
        System.out.println("[�߰��ϱ�] -> add");
        System.out.println("[�����߰��ϱ�] -> multi_add");
        System.out.println("[�����ϱ�] -> del");
        System.out.println("[���߻����ϱ�] -> multi_del");
        System.out.println("[�����ϱ�] -> edit");
        System.out.println("[�Ϸ��ϱ�] -> comp");
        System.out.println("[���߿Ϸ��ϱ�] -> multi_comp");
        System.out.println("[�߿䵵 �����ϱ�] -> chg_imp");
        System.out.println("[�Բ��ϴ� ��� �����ϱ�] -> chg_mem");
        System.out.println("[�߿��� �� �����ֱ�] -> show_important");
        System.out.println("[��ü �˻��ϱ�] -> find");
        System.out.println("[ī�װ� �˻��ϱ�] -> find_cate");
        System.out.println("[�߿䵵 �˻��ϱ�] -> find_imp");
        System.out.println("[�Բ��ϴ� ��� �˻��ϱ�] -> find_mem");
        System.out.println("[����Ʈ ����] -> ls");
        System.out.println("[ī�װ� ��� ����] -> ls_cate");
        System.out.println("[�Ϸ�� �׸� ����] -> ls_comp");
        System.out.println("[�߿䵵�� ����] -> ls_imp");
        System.out.println("[�߿䵵�� ����] -> ls_imp_desc");
        System.out.println("[����� ����] -> ls_name");
        System.out.println("[���񿪼� ����] -> ls_name_desc");
        System.out.println("[��¥�� ����] -> ls_date");
        System.out.println("[��¥���� ����] -> ls_date_desc");
        System.out.println("[����] -> Or press escape key to exit");
    }

	public static void prompt() {
		System.out.println("\n������ �޴� >");
	}
}