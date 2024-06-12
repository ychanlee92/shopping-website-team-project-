package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.CartListVO;
import model.CartVO;
import model.ProductVO;
import oracle.jdbc.OracleTypes;

public class CartDAO {
	//cart table에 insert
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


	//특정 user의 카트 목록 개수 가져오기
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

	//특정 고객별 주문 상품 목록 출력하기
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
			rs = (ResultSet)cstmt.getObject(2);
			System.out.println();
			 System.out.printf("%-5s %-10s %-15s %-12s %-12s %10s %10s\n", "주문번호", "제품코드", "제품명", "브랜드", "카테고리", "주문수량", "가격");
		        System.out.println("---------------------------------------------------------------------------------------------------------");
			
			while(rs.next()) {
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
			 System.out.println("---------------------------------------------------------------------------------------------------------");
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

	//특정 고객의 선택 상품 삭제
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

}
