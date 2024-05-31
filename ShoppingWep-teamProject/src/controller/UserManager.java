package controller;

import java.util.Scanner;

public class UserManager {
	static Scanner sc = new Scanner(System.in);

	public static String login() {
		System.out.print("아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String pass = sc.nextLine();
		UserDAO ud = new UserDAO();
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
}
