package controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import model.UserVO;

public class UserDAO {

	public boolean login(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			System.out.println("DB접속성공");
			String sql = "select * from fancy_user where userid = ? and userpass = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return flag;
	}

	public void signUp(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL insertuser(?,?,?,?,?,?,?)}");
			cstmt.setString(1, user.getUserId());
			cstmt.setString(2, user.getUserPass());
			cstmt.setString(3, user.getUserName());
			cstmt.setString(4, user.getPhone());
			cstmt.setString(5, user.getAddress());
			cstmt.setInt(6, user.getAccAmount());
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(7);
			System.out.println(message);
			System.out.println("신규회원 이벤트 웰컴 쿠폰이 발급되었습니다.\n쿠폰함에서 확인해보세요! ");
			cstmt = con.prepareCall("{CALL updatecoupon1(?)}");
			cstmt.setString(1, user.getUserId());
			cstmt.executeUpdate();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public boolean idCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from fancy_user where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				flag = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return flag;
	}

	public void printUser(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from fancy_user where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("userId");
				String pass = rs.getString("userPass");
				String name = rs.getString("userName");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				int accAmount = rs.getInt("accAmount");
				UserVO user = new UserVO(userId, pass, name, phone, address, accAmount);
				System.out.println(user.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void updateUser(String id, String pass, String phone, String address) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL updateUser(?,?,?,?,?)}");
			cstmt.setString(1, id);
			cstmt.setString(2, pass);
			cstmt.setString(3, phone);
			cstmt.setString(4, address);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(5);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void deleteUser(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{CALL deleteUser(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(4);
			System.out.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	public void printTotalUser() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO user = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "select * from fancy_user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (!rs.next()) {
				String userId = rs.getString("userId");
				String pass = rs.getString("userPass");
				String name = rs.getString("userName");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				int accAmount = rs.getInt("accAmount");
				user = new UserVO(userId, pass, name, phone, address, accAmount);
			}
			System.out.println(user.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

}
