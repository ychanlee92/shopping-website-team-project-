package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductVO;
import oracle.jdbc.internal.OracleTypes;

public class ProductDAO {

	// 전체상품 목록
	public void pdAllList() {
		String sql = "select * from product";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pvo = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println();
			System.out.printf("%-5s %-15s %-15s %-15s %4s\n", "상품코드", "상품명", "브랜드", "카테고리", "가격");
			System.out.println("---------------------------------------------------------------------");

			while (rs.next()) {
				pvo = new ProductVO();
				pvo.setPdCode(rs.getString("PDCODE"));
				pvo.setPdName(rs.getString("PDNAME"));
				pvo.setBrand(rs.getString("BRAND"));
				pvo.setCategory(rs.getString("CATEGORY"));
				pvo.setPrice(rs.getInt("PRICE"));

				System.out.println(pvo.toString());
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	// 회원 정렬기준별 상품목록 보여주기
	public void pdSortList(int selectNum) {
		String sql1 = "select * from product order by price";
		String sql2 = "select * from product order by price desc";
		String sql3 = "select * from product order by category";
		String sql4 = "select * from product order by brand";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pvo = null;

		try {
			con = DBUtil.makeConnection();
			switch (selectNum) {
			case 1:
				pstmt = con.prepareStatement(sql1);
				break;
			case 2:
				pstmt = con.prepareStatement(sql2);
				break;
			case 3:
				pstmt = con.prepareStatement(sql3);
				break;
			case 4:
				pstmt = con.prepareStatement(sql4);
				break;

			}
			rs = pstmt.executeQuery();

			System.out.println();
			System.out.printf("%-5s %-15s %-15s %-15s %4s\n", "상품코드", "상품명", "브랜드", "카테고리", "가격");
			System.out.println("---------------------------------------------------------------------");

			while (rs.next()) {
				pvo = new ProductVO();
				pvo.setPdCode(rs.getString("PDCODE"));
				pvo.setPdName(rs.getString("PDNAME"));
				pvo.setBrand(rs.getString("BRAND"));
				pvo.setCategory(rs.getString("CATEGORY"));
				pvo.setPrice(rs.getInt("PRICE"));

				System.out.println(pvo.toString());
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}

	// 키워드로 상품 검색하여 보여주기(유사상품 포함)
	public void pdSearch(String searchChar) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ProductVO pvo = null;

		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call pdReview(?,?)}");
			cstmt.setString(1, searchChar);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);


			System.out.println();
			System.out.printf("%-5s %-15s %-15s %-15s %-4s %-100s\n", "상품코드", "상품명", "브랜드", "카테고리", "가격", "\t리뷰(상품평)");
			System.out.println("-----------------------------------------------------------------------------------------------");

			while (rs.next()) {
				pvo = new ProductVO();
				pvo.setPdCode(rs.getString("PDCODE"));
				pvo.setPdName(rs.getString("PDNAME"));
				pvo.setBrand(rs.getString("BRAND"));
				pvo.setCategory(rs.getString("CATEGORY"));
				pvo.setPrice(rs.getInt("PRICE"));
				
				System.out.println(pvo.toString()+"\t"+rs.getString("comments"));
				
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, cstmt, con);
		}

	}

	// 상품코드 유무조회
	public boolean searchPdCode(String pdCode) {
		String sql = "select * from product where pdcode = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean pdMatch = false;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pdMatch = true; // 상품매치됨
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return pdMatch;
	}

	// 특정 상품 가져오기
	public ProductVO choicePd(String pdCode) {
		String sql = "select * from product where pdcode = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pvo = null;
		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pvo = new ProductVO();
				pvo.setPdCode(rs.getString("pdcode"));
				pvo.setPdName(rs.getString("pdname"));
				pvo.setBrand(rs.getString("brand"));
				pvo.setCategory(rs.getString("category"));
				pvo.setPrice(rs.getInt("price"));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return pvo;
	}

	//관리자 상품보기(카테고리/브랜드 별)
	public void adminpdSearcy(int selectNum, String cateNBrand) {
		String sql1 = "select * from product where category = ?";
		String sql2 = "select * from product where brand = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pvo = null;

		try {
			con = DBUtil.makeConnection();
			if(selectNum == 3) {
				pstmt = con.prepareStatement(sql1);
			}else {
				pstmt = con.prepareStatement(sql2);
			}
			pstmt.setString(1, cateNBrand);
			rs = pstmt.executeQuery();

			System.out.println();
			System.out.printf("%-5s %-15s %-15s %-15s %4s\n", "상품코드", "상품명", "브랜드", "카테고리", "가격");
			System.out.println("---------------------------------------------------------------------");

			while (rs.next()) {
				pvo = new ProductVO();
				pvo.setPdCode(rs.getString("PDCODE"));
				pvo.setPdName(rs.getString("PDNAME"));
				pvo.setBrand(rs.getString("BRAND"));
				pvo.setCategory(rs.getString("CATEGORY"));
				pvo.setPrice(rs.getInt("PRICE"));

				System.out.println(pvo.toString());
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
	}


	//관리자 상품등록
	public void pdRegistration(ProductVO pvo) {
		Connection con = null;
		CallableStatement cstmt = null;
		
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call productInsert(?,?,?,?,?)}");
			cstmt.setString(1, pvo.getPdCode());
			cstmt.setString(2, pvo.getPdName());
			cstmt.setString(3, pvo.getBrand());
			cstmt.setString(4, pvo.getCategory());
			cstmt.setInt(5, pvo.getPrice());
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
	//관리자 상품 수정
	public void pdUpdate(ProductVO pvo) {
		Connection con = null;
		CallableStatement cstmt = null;
		
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call productUpdate(?,?,?,?,?)}");
			cstmt.setString(1, pvo.getPdCode());
			cstmt.setString(2, pvo.getPdName());
			cstmt.setString(3, pvo.getBrand());
			cstmt.setString(4, pvo.getCategory());
			cstmt.setInt(5, pvo.getPrice());
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

	
	//관리자 상품 삭제
	public void pdDelete(String pdCode) {
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.makeConnection();
			cstmt = con.prepareCall("{call productDelete(?)}");
			cstmt.setString(1, pdCode);
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

	
	//카테고리 또는 브랜드가 있는지 유무 확인
	public boolean adminPdSearcyIs(int selectNum, String cateNBrand) {
		String sql1 = "select * from product where category = ?";
		String sql2 = "select * from product where brand = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean Match = false;

		try {
			con = DBUtil.makeConnection();
			if(selectNum == 3) {
				pstmt = con.prepareStatement(sql1);
			} else {
				pstmt = con.prepareStatement(sql2);
			}
			pstmt.setString(1, cateNBrand);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Match = true; // 상품매치됨
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(rs, pstmt, con);
		}
		return Match;
	}

	
}
