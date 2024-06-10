package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

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


	public int choiceList(String id) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		CartVO cvo = null;
		
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call choicelist(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);
			
			if(rs.next()) {
				cvo = new CartVO();
				cvo.setOrderNo(rs.getInt("c.order_num"));
			//여기부터 내일 하자	
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
