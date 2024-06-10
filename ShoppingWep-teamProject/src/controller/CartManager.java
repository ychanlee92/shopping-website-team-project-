package controller;

import java.util.Date;
import java.util.Scanner;

import model.CartVO;
import model.ProductVO;
import view.MenuView;

public class CartManager {
	public static Scanner input = new Scanner(System.in);

	//카트에 담을 코드 조회 및 상품 가져오기
	public void putInCart(String id) {
		ProductDAO pdao = new ProductDAO();
		ProductVO pvo = new ProductVO();
		
		boolean flagExit = false;

		while (!flagExit) {
			MenuView.product_Choice();
			String pdCode = input.nextLine().toUpperCase();
			if (pdCode.equals("B")) {
				flagExit = true;
			}
			//상품코드 조회 및 cart저장
			if(pdao.searchPdCode(pdCode)) {
				pvo = pdao.choicePd(pdCode);
				addCart(pvo, id);
			}
		}

	}
	//카트에 담을 값 만들기
	public void addCart(ProductVO pvo, String id) {
		CartVO cvo = new CartVO();
		CartDAO cdao = new CartDAO();
		
		int quantity = 0;
		int salesAmount = 0;
		int isPayment = 0;
		
		System.out.print("주문 수량을 입력하세요 : ");
		quantity = Integer.parseInt(input.nextLine());
		salesAmount = quantity * pvo.getPrice();
		System.out.print("☞ 선택한 상품을 장부구니에 담겠습니까? (Y/N) :");
		String isSave = input.nextLine();
		if(isSave.toUpperCase().equals("Y")) {
			cvo.setPdCode(pvo.getPdCode());
			cvo.setUserId(id);
			cvo.setQuantity(quantity);
			cvo.setSalesAmount(salesAmount);
			cvo.setIsPayment(isPayment);
			cdao.setCartRegister(cvo);
			System.out.println(cvo.toString());
		}else {
			return;
		}
	}
	
	//userId별 cart목록 보기
	public void myCartList(String id) {
		CartDAO cdao = new CartDAO();
		
		int cartCount = 0;
		int totalPrice = 0;
		cartCount = cdao.cartCount(id);
		
		if(cartCount == 0) {
			System.out.println("선택하신 상품이 없습니다.\n");
			return;
		}
		System.out.println();
		System.out.println(">>>> 고객님께서 선택하신 상품 목록입니다. <<<<");
		totalPrice = cdao.choiceList(id);
		//여기부터해야해
		
	}
	

}
