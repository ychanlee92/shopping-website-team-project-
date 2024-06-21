package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import model.CartListVO;
import model.CartVO;
import model.CouponVO;
import model.ReviewVO;
import model.UserVO;
import oracle.jdbc.OracleTypes;

public class CartDAO {
	// cart table에 insert
	public void setCartRegister(CartVO cvo) {
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call cart_insert(?,?,?,?,?)}");
			cstmt.setString(1, cvo.getPdCode());
			cstmt.setString(2, cvo.getUserId());
			cstmt.setInt(3, cvo.getQuantity());
			cstmt.setInt(4, cvo.getSalesAmount());
			cstmt.setInt(5, cvo.getIsPayment());
			cstmt.executeUpdate();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}

	}

	// 특정 user의 카트 목록 개수 가져오기
	public int cartCount(String id) {
		Connection con = null;
		CallableStatement cstmt = null;
		int cartCount = 0;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call cart_count(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.executeQuery();
			cartCount = cstmt.getInt(2);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
		return cartCount;
	}

	// 특정 고객별 주문 상품 목록 출력하기
	public void choiceList(String id) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		CartListVO clvo = null;
		int totalPrice = 0;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call choicelist(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet) cstmt.getObject(2);
			System.out.println();
			System.out.printf("%-5s %-10s %-15s %-12s %-12s %10s %10s\n", "주문번호", "제품코드", "제품명", "브랜드", "카테고리", "주문수량",
					"가격");
			System.out.println(
					"---------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				clvo = new CartListVO();
				clvo.setOrder_num(rs.getInt("order_num"));
				clvo.setPdCode(rs.getString("pdcode"));
				clvo.setPdName(rs.getString("pdname"));
				clvo.setBrand(rs.getString("brand"));
				clvo.setCategory(rs.getString("category"));
				clvo.setQuantity(rs.getInt("quantity"));
				clvo.setSalesAmount(rs.getInt("salesamount"));
				System.out.println(clvo.toString());
				totalPrice += rs.getInt("salesamount");
			}
			System.out.println(
					"---------------------------------------------------------------------------------------------------------");
			System.out.printf("%75s %15s\n", "총 구매 금액 :", String.format("%,d", totalPrice));
			System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}

	// 특정 고객의 선택 상품 삭제
	public void deleteList(String id, int orderNum) {
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call cart_delete(?,?)}");
			cstmt.setString(1, id);
			cstmt.setInt(2, orderNum);
			cstmt.executeUpdate();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
	}

	// 장바구니 비우기
	public void clearCart(String id) {
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call cart_clear(?)}");
			cstmt.setString(1, id);
			cstmt.executeUpdate();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
	}

	// 장바구니 구매할 목록 보기
	public void purchaseList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartListVO clvo = null;
		CouponVO cvo = null;
		ArrayList<CouponVO> couponList = null;
		ArrayList<CartListVO> cartList = null;
		int totalPrice = 0;
		int isPayment = 0;
		try {
			couponList = new ArrayList<CouponVO>();
			cartList = new ArrayList<CartListVO>();
			con = DBUtil.makeConnection();
			String sql = "select c.order_num, c.pdCode, p.pdName,p.brand, c.quantity, c.salesAmount, cc.coupon_w, cc.coupon_m,cc.coupon_d from cart c inner join coupon cc on c.userId = cc.userId inner join product p on c.pdCode = p.pdCode where c.userid = ? and c.ispayment = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, isPayment);
			rs = pstmt.executeQuery();
			System.out.println();
			while (rs.next()) {
				int order_num = rs.getInt("order_num");
				String pdCode = rs.getString("pdCode");
				String pdName = rs.getString("pdName");
				String brand = rs.getString("brand");
				int quantity = rs.getInt("quantity");
				int salesAmount = rs.getInt("salesAmount");
				int coupon_w = rs.getInt("coupon_w");
				int coupon_m = rs.getInt("coupon_m");
				int coupon_d = rs.getInt("coupon_d");
				clvo = new CartListVO(order_num, pdCode, pdName, brand, quantity, salesAmount);
				cvo = new CouponVO(id, coupon_w, coupon_m, coupon_d);
				totalPrice += rs.getInt("salesamount");
				cartList.add(clvo);
				couponList.add(cvo);
			}
			printPurchaseList(cartList, couponList, totalPrice);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, con, rs);
		}
	}

	// 구매할 목록 출력 함수
	public void printPurchaseList(ArrayList<CartListVO> cartList, ArrayList<CouponVO> couponList, int totalPrice) {
		int coupon_w = 0;
		int coupon_m = 0;
		int coupon_d = 0;

		System.out.println("주문번호 \t|상품번호 \t|상품이름 \t|브랜드 \t\t|수량 \t|상품가격");
		System.out.println("----------------------------------------------------");
		for (int i = 0; i < couponList.size(); i++) {
			CouponVO coupon = couponList.get(i);
			CartListVO cart = cartList.get(i);
			System.out.print(cart.getOrder_num() + "\t|");
			System.out.print(cart.getPdCode() + "\t|");
			if (cart.getPdName().length() < 5) {
				System.out.print(cart.getPdName() + "\t|");
			} else {
				System.out.print(cart.getPdName().substring(0, 5) + "\t|");
			}
			if (cart.getBrand().length() > 5) {
				System.out.print(cart.getBrand() + "\t|");
			} else {
				System.out.print(cart.getBrand() + "\t\t|");
			}
			System.out.print(cart.getQuantity() + "\t|");
			System.out.print(cart.getSalesAmount());
			System.out.println("");
			coupon_w = coupon.getCoupon_w();
			coupon_m = coupon.getCoupon_m();
			coupon_d = coupon.getCoupon_d();
		}
		System.out.println("----------------------------------------------------");
		if (coupon_w == 1) {
			System.out.print("신규회원쿠폰 적용: 5000원 |");
		}
		if (coupon_m == 1) {
			System.out.print("누적10만원쿠폰 적용: 5000원 |");
		}
		if (coupon_d == 1) {
			System.out.print("누적20만원쿠폰 적용: 10000원 |");
		}
		System.out.println(" 최종결제금액: " + (totalPrice - coupon_w * 5000 - coupon_m * 5000 - coupon_d * 10000) + "원");
		System.out.println("");
	}

	// 장바구니 전체 구매 함수
	public void purchaseAll(String id) {
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call cart_purchaseAll(?,?)}");
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
			DBUtil.closeResource(cstmt, con);
		}
	}

	// 장바구니 선택 구매 함수
	public void purchaseOne(String id, int number) {
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call cart_purchaseOne(?,?,?)}");
			cstmt.setString(1, id);
			cstmt.setInt(2, number);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(3);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
	}

	public void printPayment(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1;
		ArrayList<CartVO> cartList = null;
		try {
			cartList = new ArrayList<>();
			con = DBUtil.makeConnection();
			String sql = "select * from cart where userid = ? and isPayment = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int order_num = rs.getInt("order_num");
				String pdCode = rs.getString("pdCode");
				int quantity = rs.getInt("quantity");
				int salesAmount = rs.getInt("salesAmount");
				int isPayment = rs.getInt("isPayment");
				Timestamp paymentDate = rs.getTimestamp("PaymentDate");
				CartVO cart = new CartVO(order_num, pdCode, id, quantity, salesAmount, isPayment, paymentDate);
				cartList.add(cart);
			}
			printPaymentList(cartList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, con, rs);
		}
	}

	public void printPaymentList(ArrayList<CartVO> cartList) {
		System.out.println("주문번호" + " \t|" + "상품번호" + "\t|" + "수량" + "\t|" + "상품가격" + "\t\t|" + "결제날짜");
		for (int i = 0; i < cartList.size(); i++) {
			CartVO cart = cartList.get(i);
			System.out.print(cart.getOrderNo() + "\t|");
			System.out.print(cart.getPdCode() + "\t|");
			System.out.print(cart.getQuantity() + "\t|");
			System.out.print(cart.getSalesAmount() + "\t\t|");
			System.out.print(cart.getPaymentDate());
			System.out.println("");
		}
	}

	public void printPaymentReview(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 1;
		ArrayList<CartVO> cartList = null;
		try {
			cartList = new ArrayList<>();
			con = DBUtil.makeConnection();
			String sql = "SELECT * FROM cart c WHERE c.userid = ? and c.ispayment =?  AND NOT EXISTS (SELECT r.comments FROM review r WHERE r.userid = c.userid and r.pdcode = c.pdcode)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int order_num = rs.getInt("order_num");
				String pdCode = rs.getString("pdCode");
				int quantity = rs.getInt("quantity");
				int salesAmount = rs.getInt("salesAmount");
				int isPayment = rs.getInt("isPayment");
				Timestamp paymentDate = rs.getTimestamp("PaymentDate");
				CartVO cart = new CartVO(order_num, pdCode, id, quantity, salesAmount, isPayment, paymentDate);
				cartList.add(cart);
			}
			printPaymentListReview(cartList);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(pstmt, con, rs);
		}
	}

	public void printPaymentListReview(ArrayList<CartVO> cartList) {
		System.out.println("주문번호" + " \t|" + "상품번호" + "\t|" + "수량" + "\t|" + "상품가격" + "\t\t|" + "결제날짜");
		for (int i = 0; i < cartList.size(); i++) {
			CartVO cart = cartList.get(i);
			System.out.print(cart.getOrderNo() + "\t|");
			System.out.print(cart.getPdCode() + "\t|");
			System.out.print(cart.getQuantity() + "\t|");
			System.out.print(cart.getSalesAmount() + "\t\t|");
			System.out.print(cart.getPaymentDate());
			System.out.println("");
		}
	}
}
