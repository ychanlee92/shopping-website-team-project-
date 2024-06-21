package controller;

import java.util.Scanner;

import model.UserVO;

public class UserManager {
	static Scanner sc = new Scanner(System.in);
	static UserDAO ud = new UserDAO();

	public String login() {
		System.out.print("아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String pass = sc.nextLine();
//		if(id.equals("admin"))
//			adminDAO.login(id,pass);
		boolean flag = ud.login(id, pass);
		if (flag) {
			System.out.println("로그인 성공!!");
			System.out.println("");
			return id;
		} else {
			System.out.println("로그인 정보가 일치하지 않습니다.");
			return null;
		}
	}

	public void signup() {
		String id = null;
		String regExp = "^[0-9]+$";
		for (;;) {
			System.out.print("ID를 입력하세요: ");
			id = sc.nextLine();
			boolean flag = ud.idCheck(id);
			if (!flag) {
				System.out.println("중복된 아이디입니다.");
			} else {
				break;
			}
		}
		System.out.print("비밀번호를 입력하세요: ");
		String pass = sc.nextLine();
		System.out.print("성함을 입력하세요: ");
		String name = sc.nextLine();
		for (;;) {
			System.out.print("전화번호를 입력하세요: ");
			String phone = sc.nextLine();
			if (phone.matches(regExp)) {
				if (phone.length() == 11) {
					boolean flag = ud.phoneCheck(phone);
					if (!flag) {
						System.out.println("중복된 전화번호입니다.");
					} else {
						System.out.print("주소를 입력하세요: ");
						String address = sc.nextLine();
						UserVO user = new UserVO(id, pass, name, phone, address, 0);
						ud.signUp(user);
						break;
					}
				} else {
					System.out.println("전화번호 11자리가 입력되지 않았습니다.");
				}
			} else {
				System.out.println("숫자만 입력하세요.");
			}
		}
	}

	public void printUser(String id) {
		ud.printUser(id);
	}

	public void updateUser(String id) {
		System.out.print("변경할 비밀번호를 입력하세요:  ");
		String pass = sc.nextLine();
		String phone = null;
		for (;;) {
			System.out.print("변경할 전화번호를 입력하세요:  ");
			phone = sc.nextLine();
			boolean flag = ud.phoneCheck(phone);
			if (!flag) {
				System.out.println("중복된 전화번호입니다.");
			} else {
				break;
			}
		}
		System.out.print("변경할 주소를 입력하세요:  ");
		String address = sc.nextLine();
		ud.updateUser(id, pass, phone, address);
	}

	public void deleteUser(String id) {
		ud.deleteUser(id);
	}

	public void printTotalUser() {
		ud.printTotalUser();
	}

	public void updateUserAdmin() {
		ud.printTotalUser();
		System.out.print("수정할 사용자의 ID를 입력하세요:  ");
		String id = sc.nextLine();
		boolean flag = ud.idCheck(id);
		if (!flag) {
			System.out.print("세로운 비밀번호를 입력하세요:  ");
			String pass = sc.nextLine();
			System.out.print("새로운 전화번호를 입력하세요:  ");
			String phone = sc.nextLine();
			boolean exit = ud.phoneCheck(phone);
			if (!exit) {
				System.out.print("새로운 주소를 입력하세요:  ");
				String address = sc.nextLine();
				ud.updateUser(id, pass, phone, address);
			} else {
				System.out.println("중복된 전화번호입니다.");
			}
		} else {
			System.out.println("ID 정보가 일치하지 않습니다.");
		}
	}

	public void deleteUserAdmin() {
		ud.printTotalUser();
		System.out.print("삭제할 사용자의 ID를 입력하세요:  ");
		String id = sc.nextLine();
		boolean flag = ud.idCheck(id);
		if (!flag) {
			ud.deleteUser(id);
		} else {
			System.out.println("ID 정보가 일치하지 않습니다.");
		}

	}
}
