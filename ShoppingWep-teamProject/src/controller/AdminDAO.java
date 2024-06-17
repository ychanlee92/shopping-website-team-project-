package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class AdminDAO {
	private static AdminDAO instance = null;
	private AdminDAO() {}
	
	public static AdminDAO getInstance() {
		if(instance == null) {
			synchronized (AdminDAO.class) {
				instance = new AdminDAO();
			}
		}
		return instance;
	}
	
	//관리자 아이디 체크 
	public boolean adminCheck(String id) {
		Connection con = null;
		CallableStatement cstmt = null;
		boolean success = false;
		int check = 0;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call ADMIN_CHECK_PROC(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.executeUpdate();
			check = cstmt.getInt(2);
			if(check > 0) {
				success = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
		return success;
	}
}