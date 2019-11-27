package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import ch21_jdbc.DB;

public class UserDAO {
	public int insertUser(UserDTO dto) {
		int result=0;
		Connection conn = null;
		PreparedStatement pstmt=null;
		try {
			conn=DB.dbconn();
			String sql = "insert into user values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUname());
			pstmt.setInt(2, dto.getBirth());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getPaw());
			pstmt.setString(5, dto.getHp());
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
	public Vector listUser() {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			conn=DB.dbconn();
			String sql = "select * from user order by uname";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				String name = rs.getString("uname");
				int birth = rs.getInt("birth");
				String id = rs.getString("id");
				String paw = rs.getString("paw");
				String hp = rs.getString("hp");
				row.add(name);
				row.add(birth);
				row.add(id);
				row.add(paw);
				row.add(hp);
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
	public Vector searchuser(String uname) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DB.dbconn();
			String sql="select * from"
					+ " user where uname like ? order by uname";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+uname+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				String uname1 = rs.getString("uname");
				int birth = rs.getInt("birth");
				String id = rs.getString("id");
				String paw = rs.getString("paw");
				String hp = rs.getString("hp");
				row.add(uname1);
				row.add(birth);
				row.add(id);
				row.add(paw);
				row.add(hp);
				
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
	public int deleteuser(String uname) {
		int result=0;
		Connection conn =null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.dbconn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from user where uname=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, uname);
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
