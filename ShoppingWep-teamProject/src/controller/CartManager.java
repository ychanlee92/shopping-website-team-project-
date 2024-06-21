package controller;

import java.util.Date;
import java.util.Scanner;

import model.CartVO;
import model.ProductVO;
import view.MenuView;

public class CartManager {
	public static Scanner input = new Scanner(System.in);

	// 카트에 담을 코드 조회 및 상품 가져오기
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
			// 상품코드 조회 및 cart저장
			if (pdao.searchPdCode(pdCode)) {
				pvo = pdao.choicePd(pdCode);
				addCart(pvo, id);
			}
		}

	}

	// 카트에 담을 값 만들기
	public void addCart(ProductVO pvo, String id) {
		CartVO cvo = new CartVO();
		CartDAO cdao = new CartDAO();

		int quantity = 0;
		int salesAmount = 0;
		int isPayment = 0;

		System.out.print("주문 수량을 입력하세요 : ");
		quantity = Integer.parseInt(input.nextLine());
		salesAmount = quantity * pvo.getPrice();
		System.out.print("☞ 선택한 상품을 장바구니에 담겠습니까? (Y/N) :");
		String isSave = input.nextLine();
		if (isSave.toUpperCase().equals("Y")) {
			cvo.setPdCode(pvo.getPdCode());
			cvo.setUserId(id);
			cvo.setQuantity(quantity);
			cvo.setSalesAmount(salesAmount);
			cvo.setIsPayment(isPayment);
			cdao.setCartRegister(cvo);
			System.out.println("☞ 선택하신 상품을 장바구니에 담았습니다.");
			System.out.println();
		} else {
			return;
		}
	}

	// 고객(userId)별 cart목록 보기
	public void myCartList(String id) {
		CartDAO cdao = new CartDAO();

		int cartCount = 0;
		cartCount = cdao.cartCount(id);

		if (cartCount == 0) {
			System.out.println("선택하신 상품이 없습니다.\n");
			return;
		}
		System.out.println();
		System.out.println(">>>> 고객님께서 선택하신 상품 목록입니다. <<<<");
		cdao.choiceList(id);
	}

	// 고객별 장바구니 선택 삭제
	public void myCartDelete(String id) {
		CartDAO cdao = new CartDAO();
		int orderNum = 0;

		System.out.println();
		System.out.println(">>>> 고객님께서 선택하신 상품 목록입니다. <<<<");
		cdao.choiceList(id);

		System.out.print("☞ 삭제할 주문번호를 입력하세요. : ");
		orderNum = Integer.parseInt(input.nextLine());
		cdao.deleteList(id, orderNum);
		System.out.println(orderNum + "번 상품이 삭제되었습니다.");
		System.out.println();
	}

	// 고객별 장바구니 모두 비우기
	public void myCartClear(String id) {
		CartDAO cdao = new CartDAO();
		System.out.println();
		System.out.println(">>>> 고객님께서 선택하신 상품 목록입니다. <<<<");
		cdao.choiceList(id);
		System.out.print(">> 정말로 장바구니를 다 비우시겠습니까? (Y/N) : ");
		String in = input.nextLine();
		if (in.toUpperCase().equals("Y")) {
			cdao.clearCart(id);
			System.out.println(">>>> 고객님의 장바구니가 비워졌습니다. <<<<");
			System.out.println();
		} else {
			return;
		}

	}
	//결제
	public void purchase(String id) {
		CartDAO cdao = new CartDAO();
		String regExp = "^[0-9]+$";
		int cartCount = 0;
		cartCount = cdao.cartCount(id);

		if (cartCount == 0) {
			System.out.println("선택하신 상품이 없습니다.\n");
			return;
		} else if (cartCount == 1) {
			System.out.println();
			System.out.println(">>>> 장바구니 목록 <<<<");
			cdao.purchaseList(id);
			System.out.println(">>>> 이 상품을 구매하시겠습니까? (Y/N) <<<<");
			String purchase = input.nextLine();
			if (purchase.toLowerCase().equals("y")) {
				cdao.purchaseAll(id);
			} else {
				return;
			}
		} else {
			System.out.println();
			System.out.println(">>>> 구매하실 상품을 선택하세요. <<<<");
			cdao.purchaseList(id);
			System.out.println("1. 장바구니 전체 구매 2. 선택 구매");
			String num = input.nextLine();
			if (num.matches(regExp)) {
				switch (Integer.parseInt(num)) {
				case 1:
					cdao.purchaseAll(id);
					break;
				case 2:
					System.out.print("☞ 구매할 주문번호를 입력하세요. : ");
					String number = input.nextLine();
					if (number.matches(regExp)) {
						cdao.purchaseOne(id, Integer.parseInt(number));
					} else {
						System.out.println("잘못 입력했습니다.");
					}
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
				}
			} else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	//결제내역 조회
	public void printPayment(String id) {
		CartDAO cdao = new CartDAO();
		cdao.printPayment(id);
	}
	//리뷰없는 결제내역 조회
	public void printPaymentReView(String id) {
		CartDAO cdao = new CartDAO();
		cdao.printPaymentReview(id);
	}

}
