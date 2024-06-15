package controller;

import java.util.Scanner;

import model.ProductVO;
import view.MenuView;

public class ProductManager {
	public static Scanner input = new Scanner(System.in);

	// 전체 상품목록 보여주기(초기화면)
	public void productList() {
		ProductDAO pdao = new ProductDAO();

		System.out.println("\t\t=== KH Fancy 상품 목록 ===");
		pdao.pdAllList();
	}

	// 회원 상품 정렬하여 보기
	public void sortPdList(int selectNum) {
		ProductDAO pdao = new ProductDAO();

		System.out.println("\t\t=== KH Fancy 상품 정렬 결과 ===");
		pdao.pdSortList(selectNum);
	}

	// 상품명(일부) 입력 후 키워드로 검색하기
	public void productSearch() {
		ProductDAO pdao = new ProductDAO();
		boolean flagExit = false;
		String searchChar = null;

		while (!flagExit) {
			MenuView.product_SearchMenuViewer();
			searchChar = input.nextLine();
			if (searchChar.toUpperCase().equals("B")) {
				flagExit = true;
			}
			pdao.pdSearch(searchChar);

		}
	}

	// 관리자 상품 검색
	public void adminSortPdList(int selectNum) {
		ProductDAO pdao = new ProductDAO();
		String cateNBrand = null;
		if (selectNum == 1 || selectNum == 2) {
			System.out.println("\t\t=== KH Fancy Admin 상품 검색 결과 ===");
			pdao.pdSortList(selectNum);
		} else if (selectNum == 3) {
			System.out.print("☞ 검색할 카테고리를 입력하세요 : ");
			cateNBrand = input.nextLine();
			pdao.adminpdSearcy(selectNum, cateNBrand);
		} else {
			System.out.print("☞ 검색할 브랜드를 입력하세요 : ");
			cateNBrand = input.nextLine();
			pdao.adminpdSearcy(selectNum, cateNBrand);
		}
	}

	// 관리자 상품 등록
	public void productRegistration() {
		ProductDAO pado = new ProductDAO();
		boolean flagExit = false;
		ProductVO pvo = new ProductVO();

		String pdCode = null;
		String pdName = null;
		int price = 0;

		MenuView.product_Registration();
		while (!flagExit) {
			System.out.println("**상품코드 예시 [상의-TO, 하의-BO, 신발-SH, 악세사리-AC]");

			System.out.print("**상품코드 입력(이전메뉴-B): ");
			pdCode = input.nextLine().toUpperCase();
			if (pdCode.toUpperCase().equals("B")) {
				flagExit = true;
			} else if (!(pdCode.equals("TO") || pdCode.equals("BO") || pdCode.equals("SH") || pdCode.equals("AC"))) {
				System.out.println("**상품코드 예시 [상의-TO, 하의-BO, 신발-SH, 악세사리-AC]");
				System.out.println("**상품코드 입력이 잘못되었습니다. 이전 메뉴로 돌아갑니다.");
				System.out.println();
				flagExit = true;
			} else {
				pvo.setPdCode(pdCode);
				System.out.print("**상품명 입력: ");
				pdName = input.nextLine();
				pvo.setPdName(pdName);
				System.out.print("  브랜드 입력: ");
				pvo.setBrand(input.nextLine().toUpperCase());
				System.out.print("  카테고리 입력: ");
				pvo.setCategory(input.nextLine().toUpperCase());
				System.out.print("  가격 입력: ");
				price = Integer.parseInt(input.nextLine());
				pvo.setPrice(price);
				pado.pdRegistration(pvo);
				System.out.println("\n==| 상품명 : " + pdName + " 등록 완료 |==\n");
			}
		}
	}

	// 관리자 상품 업데이트(상품코드 변경 불가. 상품코드로 검색하여 수정)
	public void productUpdate() {
		ProductDAO pdao = new ProductDAO();
		boolean flagExit = false;
		ProductVO pvo = new ProductVO();

		String pdCode = null;
		int price = 0;

		MenuView.product_Update();
		while (!flagExit) {

			System.out.print("**수정할 상품코드 입력(이전메뉴-B): ");
			pdCode = input.nextLine().toUpperCase();
			System.out.println();
			if (pdCode.toUpperCase().equals("B")) {
				flagExit = true;
			} else {

				System.out.println();
				System.out.printf("%-5s %-15s %-15s %-15s %4s\n", "상품코드", "상품명", "브랜드", "카테고리", "가격");
				System.out.println("---------------------------------------------------------------------");
				ProductVO printPd = pdao.choicePd(pdCode);
				System.out.println(printPd.toString());
				System.out.println();
				pvo.setPdCode(pdCode);
				System.out.print("**상품명 입력: ");
				pvo.setPdName(input.nextLine());
				System.out.print("  브랜드 입력: ");
				pvo.setBrand(input.nextLine().toUpperCase());
				System.out.print("  카테고리 입력: ");
				pvo.setCategory(input.nextLine().toUpperCase());
				System.out.print("  가격 입력: ");
				price = Integer.parseInt(input.nextLine());
				pvo.setPrice(price);
				pdao.pdUpdate(pvo);
				System.out.println("\n==| 상품코드 : " + pdCode + " 수정 완료 |==\n");
			}
		}

	}

	// 관리자 상품 삭제(상품코드로 검색하여 삭제)
	public void productDelete() {
		ProductDAO pdao = new ProductDAO();
		boolean flagExit = false;
		String check = null;
		String pdCode = null;

		MenuView.product_Delete();
		while (!flagExit) {
			System.out.print("**삭제할 상품코드 입력 (이전 메뉴-B): ");
			pdCode = input.nextLine().toUpperCase();
			if (pdCode.toUpperCase().equals("B")) {
				flagExit = true;
			} else {
				System.out.println("\n삭제할 상품이 맞는지 한번 더 확인해 주세요.\n");
				System.out.printf("%-5s %-15s %-15s %-15s %4s\n", "상품코드", "상품명", "브랜드", "카테고리", "가격");
				System.out.println("---------------------------------------------------------------------");
				ProductVO printPd = pdao.choicePd(pdCode);
				System.out.println(printPd.toString());
				System.out.println();
				System.out.print("\n정말로 삭제하시겠습니까? (Y/N): ");
				check = input.nextLine().toUpperCase();
				if(check.equals("Y")) {
					pdao.pdDelete(pdCode);
					System.out.println("\n==| 상품코드 : " + pdCode + " 삭제 완료 |==\n");
				}else {
					flagExit = true;
				}
			}
		}//end of while
	}

}
