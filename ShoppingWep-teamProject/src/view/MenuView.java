package view;

public class MenuView {

	public static void khfancyViewer() {
		System.out.println("╔═════════════════════════════════════════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║  ██╗  ██╗    ██╗  ██╗                                           ║");
		System.out.println("║  ██║ ██╔╝    ██║  ██║                                           ║");
		System.out.println("║  █████╔╝     ███████║                                           ║");
		System.out.println("║  ██╔═██╗     ██╔══██║                                           ║");
		System.out.println("║  ██║  ██╗    ██║  ██║                                           ║");
		System.out.println("║  ╚═╝  ╚═╝    ╚═╝  ╚═╝                                           ║");
		System.out.println("║                                                                 ║");
		System.out.println("║  ███████╗    █████╗    ███╗   ██╗    ██████╗    ██╗   ██╗       ║");
		System.out.println("║  ██╔════╝   ██╔══██╗   ████╗  ██║   ██╔════╝     ██╗ ██╔╝       ║");
		System.out.println("║  ███████╗   ███████║   ██╔██╗ ██║   ██║           ████╔╝        ║");
		System.out.println("║  ██╔════╝   ██╔══██║   ██║╚██╗██║   ██║            ██╔╝         ║");
		System.out.println("║  ██║        ██║  ██║   ██║ ╚████║   ╚██████╗       ██║          ║");
		System.out.println("║  ╚═╝        ╚═╝  ╚═╝     ╚═══╝       ╚═════╝       ╚═╝          ║");
		System.out.println("║                                                                 ║");
		System.out.println("╠═════════════════════════════════════════════════════════════════╣");
		
	}

	public static void startMenuViewer() {
		System.out.println("╔═════════════════════════════════════════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║        1.로그인              2.회원가입            3.종료             ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void adminMenuViewer() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║        1.회원관리     2.상품관리      3.매출관리       4.로그아웃         ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void userManagementViewer() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║        1.회원조회     2.회원정보수정      3.회원삭제       4.뒤로가기       ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void productManagementViewer() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║     1.상품조회    2.상품추가    3.상품수정    4.상품삭제     5.뒤로가기      ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void calculateManagementViewer() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║      1.날짜별 매출조회        2.상품별 매출조회         3.뒤로가기          ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void customerMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║     1.마이페이지    2.상품보기    3.장바구니    4.결제하기     5.로그아웃     ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void customerInfoViewer(){
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║     1.내 정보    2.내 정보수정   3.탈퇴하기    4.결제내역     5.뒤로가기         ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void customer_ProductMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║       1.상품보기       2.상품검색       3.장바구니담기       4.뒤로가기        ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void customer_CartMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║  1.장바구니 보기      2.장바구니 항목 삭제      3.장바구니 비우기      4.뒤로가기    ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void product_SortMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║  1.가격낮은순     2.가격높은순     3.카테고리별     4.브랜드별     5.뒤로가기      ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	
	public static void admin_Product_MenuViewer() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║  1.가격낮은순(All)   2.가격높은순(All)   3.카테고리검색   4.브랜드검색   5.뒤로가기  ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void product_SearchMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║              ◈ 검색할 상품명의 키워드를 작성하세요(뒤로가기 : B)                 ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**키워드 입력: ");
	}
	
	public static void product_Choice() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║                ◈ 주문할 상품코드를 입력하세요(뒤로가기 : B)                   ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**상품코드 입력: ");
	}
	
	public static void product_Registration() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║                 ◈ 새로운 상품을 등록하세요.(뒤로가기 : B)                   ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
	}
	public static void product_Update() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║                     ◈ 수정할 상품의 코드를 입력하세요.                     ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
	}
	public static void product_Delete() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║                    ◈ 삭제할 상품의 코드를 입력하세요.                      ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
	}
	public static void calculate_detailMenuViewer() {
		System.out.println("╔═══════════════════════════ADMIN MENU════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║                1.세부항목 보기               2.뒤로가기               ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void enterAdminMenu() {
		System.out.println("╔═════════════════════════════════════════════════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║        1.관리자 메뉴          2.고객 메뉴            3.로그아웃         ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
}
