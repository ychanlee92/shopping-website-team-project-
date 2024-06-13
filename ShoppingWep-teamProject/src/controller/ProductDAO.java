package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ProductVO;

public class ProductDAO {

	//전체상품 목록 
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

	
	//정렬기준별 상품목록 보여주기
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
			switch(selectNum) {
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

	//키워드로 상품 검색하여 보여주기(유사상품 포함)
	public void pdSearch(String searchChar) {
		String sql = "select * from product where pdname like ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO pvo = null;

		try {
			con = DBUtil.makeConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchChar + "%");
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

	//상품코드 유무조회
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
			if(rs.next()) {
				pdMatch = true; //상품매치됨
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

	//특정 상품 가져오기
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
			if(rs.next()) {
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

}
