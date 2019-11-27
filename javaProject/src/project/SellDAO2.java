package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import ch21_jdbc.DB;



public class SellDAO2 {
	public Vector listSell2(SellDTO2 dto) {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn=DB.dbconn();
			String sql = "SELECT num, sid,sname, spri, samo,"
					+ " (spri*samo)tot  "
					+ " FROM sell2"
					+ " where sid=?";
			MarketLogin ma = new MarketLogin();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ma.iden);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				int num = rs.getInt("num");
				String sid = rs.getString("sid");
				String sname = rs.getString("sname");
				int spri = rs.getInt("spri");
				int samo = rs.getInt("samo");
				int tot = rs.getInt("tot");
				row.add(num);
				row.add(sid);
				row.add(sname);
				row.add(spri);
				row.add(samo);
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
	public int insertSell2(SellDTO2 dto) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbconn();
			String sql = "insert into sell2 values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getSid());
			pstmt.setString(3, dto.getSname());
			pstmt.setInt(4, dto.getSpri());
			pstmt.setInt(5, dto.getSamo());
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
	public int deleteSell2() {
		int result=0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from sell2");
			pstmt=conn.prepareStatement(sb.toString());
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
	public int deleteSell22(int num) {//!!!!!
		int result=0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from sell2 where num = ?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
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
	public int deletesell2(int num) {
		int result=0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from sell2 where num=?");
			pstmt.setInt(1, num);
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
}
