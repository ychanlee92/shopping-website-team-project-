import java.util.Scanner;

import controller.CalculateDAO;
import controller.CalculateManager;
import controller.CartManager;
import controller.ProductManager;
import controller.UserManager;
import view.ADMIN_MAIN_CHOICE;
import view.CALCULATE_DETAIL_CHOICE;
import view.CALCULATE_MAN_CHOICE;
import view.CART_CHOICE;
import view.CUSTOMER_MAIN_CHOICE;
import view.MYPAGE_CHOICE;
import view.MenuView;
import view.PRODUCT_CHOICE;
import view.PRODUCT_MAN_CHOICE;
import view.PRODUCT_SORT_CHOICE;
import view.START_CHOICE;
import view.USER_MAN_CHOICE;

public class FancyMain {
//	public static CustomerVO customer = new CustomerVO();
	public static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		MenuView.khfancyViewer();
		startMenu();
	}

	// 실행시 처음 보여질 화면
	public static void startMenu() {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean exitFlag = false;
		UserManager um = new UserManager();
		String id = null;
		try {
			while (!exitFlag) {
				// 실행시 처음 보여질 화면
				MenuView.startMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 3) {
						switch (selectNum) {
						case START_CHOICE.SIGNIN: // 로그인 (관리자 메뉴 or 고객메뉴)
							id = um.login();
							if (id == null) {
								return;
							}
							if (id.equals("ckehf223")) {
								adminMenu();
							}else {
								customerMenu(id);
							}
							break;
						case START_CHOICE.SIGNUP: // 회원가입
							um.signup();
							break;
						case START_CHOICE.EXIT: // 프로그램 종료
							exitFlag = true;
							System.out.println("안녕히 가세요!");
							break;
						}
					} else {
						System.out.println("1~3까지 입력가능합니다 다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch

	}// end of firstMenu

	// 관리자 메뉴
	public static void adminMenu() {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;

		try {
			while (!flag) {
				// 관리자 로그인 시 보여지는 화면 UI
				MenuView.adminMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 4) {
						switch (selectNum) {
						case ADMIN_MAIN_CHOICE.USER_MAN: // 회원 관리(조회,수정,삭제)
							userManagement();
							break;
						case ADMIN_MAIN_CHOICE.PRODUCT_MAN: // 상품관리(조회,추가,수정,삭제)
							break;
						case ADMIN_MAIN_CHOICE.CALCULATE_MAN: // 매출관리(날짜별 조회,상품별 조회)
							calculateManagement();
							break;
						case ADMIN_MAIN_CHOICE.EXIT: // 로그아웃
							flag = true;
							break;
						}
					} else {
						System.out.println("1-4까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch

	}// end of adminMenu

	// 관리자 회원관리
	public static void userManagement() {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		UserManager um = new UserManager();
		try {
			while (!flag) {
				// 관리자 회원관리 UI
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 4) {
						switch (selectNum) {
						case USER_MAN_CHOICE.LIST: // 모든 회원 조회
							um.printTotalUser();
							break;
						case USER_MAN_CHOICE.UPDATE: // 특정 회원 정보 수정
							um.updateUserAdmin();
							break;
						case USER_MAN_CHOICE.DELETE: // 특정 회원 강제 삭제(탈퇴)
							um.deleteUserAdmin();
							break;
						case USER_MAN_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-4까지 입력가능합니다.다시입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}

			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch
	}// userManagementTable

	// 관리자 상품 관리
	public static void productManagement() {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		try {
			while (!flag) {
				// 관리자 상품관리 UI
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 5) {
						switch (selectNum) {
						case PRODUCT_MAN_CHOICE.LIST: // 모든 상품 조회
							break;
						case PRODUCT_MAN_CHOICE.INSERT: // 상픔 추가
							break;
						case PRODUCT_MAN_CHOICE.UPDATE: // 상품 수정
							break;
						case PRODUCT_MAN_CHOICE.DELETE: // 특정 상품 삭제
							break;
						case PRODUCT_MAN_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}// end of switch
					} else {
						System.out.println("1~5까지 입력가능합니다 다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch
	}// end of productManagementTable

	// 관리자 매출(정산)관리
	public static void calculateManagement() {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		boolean check = false;
		try {
			while (!flag) {
				// 관리자 매출관리 UI
				MenuView.calculateManagementViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 3) {
						CalculateDAO dao = CalculateDAO.getInstance();
						switch (selectNum) {
						case CALCULATE_MAN_CHOICE.DATE_LIST: // 날짜별 매출 조회
							check = dao.getCalculate_dateList();
							calculate_dateMenu(check);
							break;
						case CALCULATE_MAN_CHOICE.PRODUCT_LIST: // 상품별 매출 조회
							check = dao.getCalculate_productList();
							calculate_productMenu(check);
							break;
						case CALCULATE_MAN_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-3까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch

	}// calculateManagementTable

	// 관리자 매출(정산) 날짜별
	public static void calculate_dateMenu(boolean check) {
		if(!check) {
			return;
		}
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		
		try {
			while (!flag) {
				// 관리자 매출 날짜메뉴 UI
				MenuView.calculate_detailMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 2) {
						switch (selectNum) {
						case CALCULATE_DETAIL_CHOICE.SELECT_LIST: // 선택한 날짜 매출 조회
							CalculateManager.getCalculate_selectDate();
							break;
						case CALCULATE_DETAIL_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-2까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch

	}// calculateManagementTable

	// 관리자 매출(정산) 상품별
	public static void calculate_productMenu(boolean check) {
		if(!check) {
			return;
		}
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;

		try {
			while (!flag) {
				// 관리자 매출 상품메뉴 UI
				MenuView.calculate_detailMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 2) {
						switch (selectNum) {
						case CALCULATE_DETAIL_CHOICE.SELECT_LIST: // 선택한 상품 매출 조회
							CalculateManager.getCalculate_selectProduct();
							break;
						case CALCULATE_DETAIL_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-2까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch

	}// calculateManagementTable

	// 고객메뉴
	public static void customerMenu(String id) {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		UserManager um = new UserManager();

		try {
			while (!flag) {
				// 고객 로그인 시 보여지는 화면 UI
				MenuView.customerMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 5) {
						switch (selectNum) {
						case CUSTOMER_MAIN_CHOICE.MYPAGE: // 마이페이지(회원정보 보기,수정,탈퇴,결제내역)
							customerInfo(id);
							break;
						case CUSTOMER_MAIN_CHOICE.PRODUCT: // 상품보기(선택정렬,검색,장바구니에 담기)
							customer_ProductMenu(id);
							break;
						case CUSTOMER_MAIN_CHOICE.CART: // 장바구니(조회,취소)
							customer_CartMenu(id);
							break;
						case CUSTOMER_MAIN_CHOICE.PAYMENT: // 결제하기(쿠폰사용여부?,누적금액 20만 배수마다 쿠폰 증정)
							break;
						case CUSTOMER_MAIN_CHOICE.EXIT: // 로그아웃
							flag = true;
							break;
						}
					} else {
						System.out.println("1-5까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch

	}// end of customerMenu

	// 회원 마이페이지
	public static void customerInfo(String id) {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		UserManager um = new UserManager();

		try {
			while (!flag) {
				// 마이페이지 선택시 보여지는 화면 UI
				MenuView.customerInfoViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 5) {
						switch (selectNum) {
						case MYPAGE_CHOICE.SHOWINFO: // 내정보 조회
							um.printUser(id);
							break;
						case MYPAGE_CHOICE.UPDATE: // 내 정보 수정(PW,PHONE,ADDRESS)
							um.updateUser(id);
							break;
						case MYPAGE_CHOICE.DELETEACCOUNT: // 회원 탈퇴
							um.deleteUser(id);
							return;
						case MYPAGE_CHOICE.PAYMENT_LIST: // 결제내역 조회
							// 장바구니 영역
							break;
						case MYPAGE_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-5까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch
	}// end of customerInfoTable

	// 회원 상품보기 메뉴
	public static void customer_ProductMenu(String id) {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		ProductManager pm = new ProductManager();
		CartManager cm = new CartManager();

		try {
			while (!flag) {
				// 상품보기 선택시 보여지는 화면 UI
				MenuView.customer_ProductMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 4) {
						switch (selectNum) {
						case PRODUCT_CHOICE.LIST: // 상품보기(정렬)
							product_SortMenu();
							break;
						case PRODUCT_CHOICE.SEARCH: // 특정 상품검색(비슷한 내용보여주기)
							pm.productSearch();
							break;
						case PRODUCT_CHOICE.ADDITEM: // 쇼핑하기 담기
							cm.putInCart(id);
							break;
						case PRODUCT_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-4까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch
	}// end of customerProductManagementTable

	// 회원 장바구니 메뉴
	public static void customer_CartMenu(String id) {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		CartManager cm = new CartManager();

		try {
			while (!flag) {
				// 장바구니 선택시 보여지는 화면 UI
				MenuView.customer_CartMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {
					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 5) {
						switch (selectNum) {
						case CART_CHOICE.LIST: // 내 장바구니 조회
							cm.myCartList(id);
							// 여기하고 있음
							break;
						case CART_CHOICE.DELETE_ITEM: // 장바구니 항목 지정삭제
							cm.myCartDelete(id);
							break;
						case CART_CHOICE.DELETE_CART: // 장바구니 항목 전체삭제
							cm.myCartClear(id);
							break;
						case CART_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-4까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch

	}// end of customerCartManagementTable

	// 상품 선택 정렬 메뉴
	public static void product_SortMenu() {
		String regExp = "^[0-9]+$";
		String checkNum = null;
		int selectNum = 0;
		boolean flag = false;
		ProductManager pm = new ProductManager();
		try {
			while (!flag) {
				// 상품 정렬 선택시 보여지는 화면 UI
				MenuView.product_SortMenuViewer();
				checkNum = input.nextLine();
				// 패턴검색
				if (checkNum.matches(regExp)) {

					selectNum = Integer.parseInt(checkNum);
					if (selectNum > 0 && selectNum <= 5) {
						switch (selectNum) {
						case PRODUCT_SORT_CHOICE.MINPRICE: // 가격높은순
						case PRODUCT_SORT_CHOICE.MAXPRICE: // 가격낮은순
						case PRODUCT_SORT_CHOICE.CATEGORY: // 카테고리
						case PRODUCT_SORT_CHOICE.BRAND: // 브랜드
							pm.sortPdList(selectNum);
							break;
						case PRODUCT_SORT_CHOICE.BACK: // 뒤로가기
							flag = true;
							break;
						}
					} else {
						System.out.println("1-3까지 입력가능합니다.다시 입력해주세요!");
					}
				} else {
					System.out.println("잘못입력하셨습니다.");
				}
			} // end of while
		} catch (Exception e) {
			e.printStackTrace();
		} // end of try-catch
	}// end of product_SortMenu

}// end of ShoppingWepMain class