package controller;

import java.util.Scanner;

import view.MenuView;

public class ProductManager {
	public static Scanner input = new Scanner(System.in); 
	//전체 상품목록 보여주기(초기화면)
	public void productList() {
		ProductDAO pdao = new ProductDAO();
		
		System.out.println("\t\t=== KH Fancy 상품 목록 ===");
		pdao.pdAllList();
	}

	public void sortPdList(int selectNum) {
		ProductDAO pdao = new ProductDAO();
		
		System.out.println("\t\t=== KH Fancy 상품 정렬 결과 ===");
		pdao.pdSortList(selectNum);
	}

	public void productSearch() {
		ProductDAO pdao = new ProductDAO();
		boolean flagExit = false;
		String searchChar = null;
		
		while(!flagExit) {
			MenuView.product_SearchMenuViewer();
			searchChar = input.nextLine();
			if(searchChar.toUpperCase().equals("B")) {
				flagExit = true;
			}
			pdao.pdSearch(searchChar);
			
		}
	}

	

}
