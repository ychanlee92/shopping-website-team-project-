package controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import model.CalculateVO;
import oracle.jdbc.OracleTypes;

public class CalculateDAO {
	private static CalculateDAO instance = null;
	private SimpleDateFormat sdf;

	private CalculateDAO() {
		sdf = new SimpleDateFormat("YY년MM월dd일");
	}

	public static CalculateDAO getInstance() {
		if (instance == null) {
			synchronized (CalculateDAO.class) {
				instance = new CalculateDAO();
			}
		}
		return instance;
	}

	// 날짜별 매출리스트 가져오기
	public boolean getCalculate_dateList() {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		boolean success = false;
		try {
			success = getCalculateCheck();
			if (success) {
				String sql = "{CALL CAL_DATE_PROC(?)}";
				con = DBUtil.makeConnection();
				cstmt = con.prepareCall(sql);
				cstmt.registerOutParameter(1, OracleTypes.CURSOR);
				cstmt.executeQuery();
				rs = (ResultSet) cstmt.getObject(1);
				System.out.println("────────────── 날짜별 매출  ────────────────");
				System.out.println("-----------------------------------------");
				System.out.println("    판매 날짜  │    수 량    │     매  출    │");
				System.out.println("─────────────────────────────────────────");
				while (rs.next()) {
					CalculateVO cvo = new CalculateVO();
					cvo.setPaymentDate(sdf.format(rs.getDate("paymentdate")));
					cvo.setQuantity(rs.getInt("quantity"));
					cvo.setSalesAmount(rs.getInt("salesamount"));
					cvo.printDateList();
					System.out.println();
				}
			} else {
				System.out.println("판매된 상품이 없습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
		return success;
	}

	// 상품별 매출리스트 가져오기
	public boolean getCalculate_productList() {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		boolean success = false;
		try {
			success = getCalculateCheck();
			if (success) {
				con = DBUtil.makeConnection();
				cstmt = con.prepareCall("{CALL CAL_PD_PROC(?)}");
				cstmt.registerOutParameter(1, OracleTypes.CURSOR);
				cstmt.executeQuery();
				rs = (ResultSet) cstmt.getObject(1);
				System.out.println("────────────── 상품별 매출  ────────────────");
				System.out.println("-----------------------------------------");
				System.out.println("    상품 코드  │    수 량    │     매  출    │");
				System.out.println("─────────────────────────────────────────");
				while (rs.next()) {
					CalculateVO cvo = new CalculateVO();
					cvo.setPdcode(rs.getString("pdcode"));
					cvo.setQuantity(rs.getInt("quantity"));
					cvo.setSalesAmount(rs.getInt("salesamount"));
					cvo.printProducteList();
					System.out.println();
				}
			} else {
				System.out.println("판매된 상품이 없습니다.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
		return success;
	}

	// 지정 날짜 매출리스트 가져오기
	public void getCalculate_detailDateList(String date) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		boolean stop = false;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call CAL_DETAIL_DATE_PROC(?,?)}");
			cstmt.setString(1, date);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet) cstmt.getObject(2);
			while (rs.next()) {
				if (!stop) {
					System.out.println("───────── " + sdf.format(rs.getDate("paymentdate")) + " 매출 리스트 ─────────");
					System.out.println("----------------------------------------");
					System.out.println("    상품 코드  │    수 량    │    가  격    │");
					System.out.println("────────────────────────────────────────");
					stop = true;
				}
				CalculateVO cvo = new CalculateVO();
				cvo.setPdcode(rs.getString("pdcode"));
				cvo.setQuantity(rs.getInt("quantity"));
				cvo.setSalesAmount(rs.getInt("salesamount"));
				cvo.printProducteList();
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}

	// 지정 날짜 매출리스트 가져오기
	public void getCalculate_detailProductList(String pdcode) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		boolean stop = false;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call CAL_DETAIL_PD_PROC(?,?)}");
			cstmt.setString(1, pdcode);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet) cstmt.getObject(2);
			while (rs.next()) {
				if(!stop) {
					System.out.println("─────────── " + rs.getString("pdcode") + " 매출 리스트 ────────────");
					System.out.println("----------------------------------------");
					System.out.println("    판매 날짜  │    수 량    │    가  격    │");
					System.out.println("────────────────────────────────────────");
					stop = true;
				}
				CalculateVO cvo = new CalculateVO();
				cvo.setPaymentDate(sdf.format(rs.getDate("paymentdate")));
				cvo.setQuantity(rs.getInt("quantity"));
				cvo.setSalesAmount(rs.getInt("salesamount"));
				cvo.printDateList();
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}
	}

	// 판매 내역이 있는지 확인하는 함수
	public boolean getCalculateCheck() {
		Connection con = null;
		CallableStatement cstmt = null;
		int check = 0;
		boolean success = false;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call CAL_COUNT(?)}");
			cstmt.registerOutParameter(1, Types.NUMERIC);
			cstmt.executeQuery();
			check = cstmt.getInt(1);
			if (check > 0) {
				success = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
		return success;
	}

	// 지정날짜의 매출이 있는지 확인하는 함수
	public boolean getCalculatedateCheck(String date) {
		Connection con = null;
		CallableStatement cstmt = null;
		int check = 0;
		boolean success = false;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call CAL_DATE_CHECK(?,?)}");
			cstmt.setString(1, date);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.executeQuery();
			check = cstmt.getInt(2);
			if (check > 0) {
				success = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
		return success;
	}

	// 지정상품 매출이 있는지 확인하는 함수
	public boolean getCalculateProductCheck(String pdcode) {
		Connection con = null;
		CallableStatement cstmt = null;
		int check = 0;
		boolean success = false;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call CAL_PD_CHECK(?,?)}");
			cstmt.setString(1, pdcode);
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.executeQuery();
			check = cstmt.getInt(2);
			if (check > 0) {
				success = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		} finally {
			DBUtil.closeResource(cstmt, con);
		}
		return success;
	}
}
