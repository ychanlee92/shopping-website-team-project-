package controller;

import java.util.Scanner;

public class ReviewManager {
	static Scanner sc = new Scanner(System.in);
	static ReviewDAO rd = new ReviewDAO();

	public void printReview(String id) {
		int reviewCount = 0;
		reviewCount = rd.reviewCount(id);
		if (reviewCount == 0) {
			System.out.println("작성한 리뷰가 없습니다.");
			return;
		}
		rd.printReview(id);
	}

	public void insertReview(String id) {
		String regExp = "^[0-9]+$";
		String regExp2 = "^[0-5](\\.[0-9]{1,2})?$";

		System.out.println("");
		System.out.println("☞ 리뷰를 작성할 주문번호를 입력하세요. : ");
		String num = sc.nextLine();
		if (num.matches(regExp)) {
			System.out.println("☞ 평점을 입력하세요. : ");
			String rate = sc.nextLine();
			if (rate.matches(regExp2)) {
				System.out.println("☞ 리뷰를 작성하세요. : ");
				String comments = sc.nextLine();
				rd.insertReview(num, rate, comments, id);
			} else {
				System.out.println("평점은 5점만접입니다.");
			}
		} else {
			System.out.println("잘못 입력했습니다.");
		}
	}

	public void removeReview(String id) {
		String regExp = "^[0-9]+$";
		System.out.println("");

		int reviewCount = 0;
		reviewCount = rd.reviewCount(id);
		if (reviewCount == 0) {
			System.out.println("제거할 리뷰가 없습니다.");
			return;
		}
		if (reviewCount == 1) {
			rd.removeReviewOne(id);
		} else {
			rd.printReview(id);
			System.out.println("☞ 삭제할 리뷰번호를 입력하세요. : ");
			String num = sc.nextLine();
			if (num.matches(regExp)) {
				rd.removeReview(num, id);
			}
		}
	}

	public void printReviewAdmin() {
		rd.printReviewAdmin();
	}

	public void removeReviewAdmin() {
		String regExp = "^[0-9]+$";
		System.out.println("");

		int reviewCount = 0;
		reviewCount = rd.reviewCountAdmin();
		if (reviewCount == 0) {
			System.out.println("제거할 리뷰가 없습니다.");
			return;
		}
		if (reviewCount == 1) {
			rd.removeReviewOneAdmin();
		} else {
			rd.printReviewAdmin();
			System.out.println("☞ 삭제할 리뷰번호를 입력하세요. : ");
			String num = sc.nextLine();
			if (num.matches(regExp)) {
				rd.removeReviewAdmin(num);
			}
		}
	}
}
