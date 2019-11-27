package project;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import ch14.TryEx;
import ch21_jdbc.DB;
import ch22_oracle_jdbc.ScoreDTO;

public class MarketDAO {
	
	public int insertMarket(MarketDTO dto) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbconn();
			String sql = "insert into market values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getProd_no());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getCompany());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setInt(5, dto.getAmoun());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public Vector listMarket() {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn=DB.dbconn();
			String sql = "SELECT prod_no, name, company, price,"
					+ " amoun, (price*amoun)tot  "
					+ " FROM market";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				int prod_no = rs.getInt("prod_no");
				String name = rs.getString("name");
				String company = rs.getString("company");
				int price = rs.getInt("price");
				int amoun = rs.getInt("amoun");
				int tot = rs.getInt("tot");
				row.add(prod_no);
				row.add(name);
				row.add(company);
				row.add(price);
				row.add(amoun);
				row.add(tot);
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
	public Vector searchmarket(String name) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbconn();
			String sql="SELECT prod_no, name, company, price,"
					+ " amoun, (price*amoun)tot  "
					+ " from market where name like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				int prod_no = rs.getInt("prod_no");
				String name1 = rs.getString("name");
				String company = rs.getString("company");
				int price = rs.getInt("price");
				int amoun = rs.getInt("amoun");
				int tot = rs.getInt("tot");
				row.add(prod_no);
				row.add(name1);
				row.add(company);
				row.add(price);
				row.add(amoun);
				row.add(tot);
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
	public int deleteMarket(String name) {
		int result=0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from market where name=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public int updateMarket(MarketDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("update market ");
			sb.append(" set name=?, company=?,price=?,amoun=? ");
			sb.append(" where prod_no=?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getCompany());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getAmoun());
			pstmt.setInt(5, dto.getProd_no());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public MarketDTO viewMarket(int prod_no) {
		MarketDTO dto =null;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs =null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("select prod_no,name,company,price,amoun ");
			sb.append(" from market ");
			sb.append(" where prod_no=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, prod_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String company = rs.getString("company");
				int price = rs.getInt("price");
				int amoun = rs.getInt("amoun");
				dto = new MarketDTO(prod_no, name, company, price, amoun); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	public int updateMarket2(MarketDTO dto)  {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE market");
			sb.append(" SET amoun=amoun-? ");
			sb.append(" WHERE NAME = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, dto.getAmoun());
			pstmt.setString(2, dto.getName());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public int updateMarket3(MarketDTO dto)  {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE market");
			sb.append(" SET amoun=amoun+? ");
			sb.append(" WHERE NAME = ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, dto.getAmoun());
			pstmt.setString(2, dto.getName());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
