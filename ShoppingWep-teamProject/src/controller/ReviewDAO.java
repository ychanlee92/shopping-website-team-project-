package controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import model.CouponVO;
import model.ReviewVO;
import model.UserVO;

public class ReviewDAO {
	// 리뷰 조회
	public void printReview(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> reviewList = null;
		try {
			reviewList = new ArrayList<>();
			con = DBUtil.makeConnection();
			String sql = "select * from review where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int reviewNum = rs.getInt("review_num");
				String userId = rs.getString("userId");
				String pdCode = rs.getString("pdCode");
				String comments = rs.getString("comments");
				double evaluation = rs.getDouble("evaluation");
				ReviewVO review = new ReviewVO(reviewNum, userId, pdCode, comments, evaluation);
				reviewList.add(review);
			}
			printReviewUser(reviewList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, con, rs);
		}
	}

	// 리뷰 출력함수
	public void printReviewUser(ArrayList<ReviewVO> reviewList) {
		System.out.println("리뷰 번호" + " \t|" + "상품번호" + "\t|" + "리뷰내용" + "\t\t\t\t\t|" + "평점");
		for (int i = 0; i < reviewList.size(); i++) {
			ReviewVO review = reviewList.get(i);
			System.out.print(review.getReviewNum() + "\t|");
			System.out.print(review.getPdCode() + "\t|");
			if (review.getReviewCom().length() > 25) {
				System.out.print(review.getReviewCom().substring(0, 22) + "..." + "\t\t|");
			} else {
				System.out.printf("%-25s %1s", review.getReviewCom(), "\t\t|");
			}
			System.out.print(review.getRate());
			System.out.println("");
		}
	}

	// 리뷰 작성
	public void insertReview(String num, String rate, String comments, String id) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL insertReview(?,?,?,?,?)}");
			cstmt.setString(1, id);
			cstmt.setDouble(2, Double.parseDouble(rate));
			cstmt.setString(3, comments);
			cstmt.setInt(4, Integer.parseInt(num));
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(5);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}

	// 리뷰 제거
	public void removeReview(String num, String id) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL removeReview(?,?,?)}");
			cstmt.setString(1, id);
			cstmt.setInt(2, Integer.parseInt(num));
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(3);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}

	// 리뷰 카운트함수
	public int reviewCount(String id) {
		Connection con = null;
		CallableStatement cstmt = null;
		int reviewCount = 0;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call review_count(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.executeQuery();
			reviewCount = cstmt.getInt(2);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
		return reviewCount;
	}

	// 리뷰 하나만 지우기
	public void removeReviewOne(String id) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL removeReviewOne(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(2);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}
	
	public void printReviewAdmin() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReviewVO> reviewList = null;
		try {
			reviewList = new ArrayList<>();
			con = DBUtil.makeConnection();
			String sql = "select * from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int reviewNum = rs.getInt("review_num");
				String userId = rs.getString("userId");
				String pdCode = rs.getString("pdCode");
				String comments = rs.getString("comments");
				double evaluation = rs.getDouble("evaluation");
				ReviewVO review = new ReviewVO(reviewNum, userId, pdCode, comments, evaluation);
				reviewList.add(review);
			}
			printReviewUserAdmin(reviewList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, con, rs);
		}
	}

	public void printReviewUserAdmin(ArrayList<ReviewVO> reviewList) {
		System.out.println("리뷰 번호" + " \t|" + "상품번호" + "\t|" + "아이디" + "\t|" + "리뷰내용" + "\t\t\t\t\t|" + "평점");
		for (int i = 0; i < reviewList.size(); i++) {
			ReviewVO review = reviewList.get(i);
			System.out.print(review.getReviewNum() + "\t|");
			System.out.print(review.getPdCode() + "\t|");
			System.out.print(review.getUserId() + "\t|");
			if (review.getReviewCom().length() > 25) {
				System.out.print(review.getReviewCom().substring(0, 22) + "..." + "\t\t|");
			} else {
				System.out.printf("%-25s %1s", review.getReviewCom(), "\t\t|");
			}
			System.out.print(review.getRate());
			System.out.println("");
		}
	}

	public int reviewCountAdmin() {
		Connection con = null;
		CallableStatement cstmt = null;
		int reviewCount = 0;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call review_countAdmin(?)}");
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.executeQuery();
			reviewCount = cstmt.getInt(1);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
		return reviewCount;
	}

	public void removeReviewOneAdmin() {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL removeReviewOneAdmin(?)}");
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(1);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}

	public void removeReviewAdmin(String num) {
		Connection con = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL removeReviewAdmin(?,?)}");
			cstmt.setInt(1, Integer.parseInt(num));
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(2);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}
}
