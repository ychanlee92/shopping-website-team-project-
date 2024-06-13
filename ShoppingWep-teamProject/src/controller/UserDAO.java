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
	// 사용자 로그인 함수
	public boolean login(String id, String pass) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
//			System.out.println("DB접속성공");

			// 아이디와 비밀번호가 일치하는 결과값이 있는지 확인
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

	// 회원가입 함수
	public void signUp(UserVO user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			// 회원가입시 저장한 유저객체를 데이터베이스에 입력
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
			// 회원가입과 동시에 회원가입 축하 쿠폰 입력
			System.out.println("신규회원 이벤트 웰컴 쿠폰이 발급되었습니다.\n쿠폰함에서 확인해보세요! ");
			cstmt = con.prepareCall("{CALL insertcoupon_w(?)}");
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

	// 로그인시 아이디 중복 확인 함수
	public boolean idCheck(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = DBUtil.makeConnection();
			// flag로 결과값이 있는지 확인
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

	// 사용자가 조회하는 정보
	public void printUser(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.makeConnection();
			String sql = "select u.userid, userpass, username, phone, address, accAmount, coupon_w, coupon_m, coupon_d from fancy_user u inner join coupon c on u.userid=c.userid where userid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String userId = rs.getString("u.userId");
				String pass = rs.getString("userPass");
				String name = rs.getString("userName");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				int accAmount = rs.getInt("accAmount");
				UserVO user = new UserVO(userId, pass, name, phone, address, accAmount);
				CouponVO coupon = new CouponVO()
				System.out.println(user.toStringUser());
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
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.executeUpdate();
			String message = cstmt.getString(2);
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
