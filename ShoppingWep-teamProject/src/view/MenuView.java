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
		System.out.println("║     1.내 정보    2.내 정보수정   3.탈퇴하기    4.결제내역     5.뒤로가기     ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void customer_ProductMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║       1.상품보기       2.상품검색       3.장바구니담기       4.뒤로가기     ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void customer_CartMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║   1.장바구니 보기    2.장바구니 항목삭제    3.장바구니 전체삭제    4.뒤로가기    ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	
	public static void product_SortMenuViewer() {
		System.out.println("╔════════════════════════Welcome to KH FANCY══════════════════════╗");
		System.out.println("║                                                                 ║");
		System.out.println("║        1.가격높은순           2.가격 낮은순           3.뒤로가기         ║");
		System.out.println("║                                                                 ║");
		System.out.println("╚═════════════════════════════════════════════════════════════════╝");
		System.out.print("**번호 선택: ");
	}
	public static void mainMenu() {
        System.out.println("메뉴를 선택하세요 : ");
        System.out.print("1. 로그인 2. 회원가입 3. 종료   ");
    }

    public static void userMenu() {
        System.out.println("메뉴를 선택하세요 : ");
        System.out.print("1. 마이페이지 2. 상품보기 3. 장바구니 4. 결제하기 5. 로그아웃   ");
    }
}
