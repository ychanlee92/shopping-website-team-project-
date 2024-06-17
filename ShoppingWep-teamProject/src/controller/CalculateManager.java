package controller;

import java.util.Scanner;

public class CalculateManager {
	public static Scanner input = new Scanner(System.in);
	public static CalculateDAO dao = CalculateDAO.getInstance();

	// 지정 날짜 선택후 리스트 가져오기
	public static void getCalculate_selectDate() {
		String regExp = "^[0-9]+$";
		System.out.println("날짜 입력 ex)24년05월01일 (20240501)");
		System.out.print("날짜를 입력: ");
		String date = input.nextLine();
		//패턴 체크
		if (date.matches(regExp) && date.length() == 8) {
			//입력한 날짜의 판매매출이 있는지 확인
			if(dao.getCalculatedateCheck(date)) {
				dao.getCalculate_detailDateList(date);
			}else {
				System.out.println("선택한 날짜의 판매내역이 존재하지 않습니다.");
			}
		} else {
			System.out.println("잘못입력하셨습니다. 날짜입력형식을 확인해주세요!");
		}
	}

	// 지정 날짜 선택후 리스트 가져오기
	public static void getCalculate_selectProduct() {
		System.out.println("상품 코드를 입력해주세요");
		System.out.print("상품코드 입력: ");
		String pdcode = input.nextLine().toUpperCase();
		//입력한 상품 판매매출 있는지 확인
		if(dao.getCalculateProductCheck(pdcode)) {
			dao.getCalculate_detailProductList(pdcode);
		}else {
			System.out.println("선택한 상품의 판매내역이 존재하지 않습니다.");
		}
	}
}