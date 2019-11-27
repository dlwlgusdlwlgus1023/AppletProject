package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import ch21_jdbc.DB;

public class SellDAO {
	public Vector searchSell1(String sname) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbconn();
			String sql="SELECT sid, sname, spri, samo, "
					+ " (spri*samo)tot, spay  "
					+ " from sell where sname like ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+sname+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				String sid = rs.getString("sid");
				String sname1 = rs.getString("sname");
				int spri = rs.getInt("spri");
				int samo = rs.getInt("samo");
				int tot = rs.getInt("tot");
				String spay = rs.getString("spay");
				row.add(sid);
				row.add(sname1);
				row.add(spri);
				row.add(samo);
				row.add(tot);
				row.add(spay);
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
	public Vector listSell() {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn=DB.dbconn();
			String sql = "SELECT sid,sname, spri, samo,"
					+ " (spri*samo)tot, spay  "
					+ " FROM sell";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				String sid = rs.getString("sid");
				String sname = rs.getString("sname");
				int spri = rs.getInt("spri");
				int samo = rs.getInt("samo");
				int tot = rs.getInt("tot");
				String spay = rs.getString("spay");
				row.add(sid);
				row.add(sname);
				row.add(spri);
				row.add(samo);
				row.add(tot);
				row.add(spay);
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
	public int insertSell(SellDTO dto) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbconn();
			String sql = "insert into sell values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSid());
			pstmt.setString(2, dto.getSname());
			pstmt.setInt(3, dto.getSpri());
			pstmt.setInt(4, dto.getSamo());
			pstmt.setString(5, dto.getSpay());
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
	public int deleteSell() {
		int result=0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from sell");
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
}
