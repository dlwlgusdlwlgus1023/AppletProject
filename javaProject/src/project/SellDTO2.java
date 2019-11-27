package project;

public class SellDTO2 {
	String sid,sname ;
	int num, samo, spri;
	public SellDTO2(int num, String sid, String sname, int samo, int spri) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.num = num;
		this.samo = samo;
		this.spri = spri;
	}
	@Override
	public String toString() {
		return "SellDTO2 [sid=" + sid + ", sname=" + sname + ", num=" + num + ", samo=" + samo + ", spri=" + spri + "]";
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSamo() {
		return samo;
	}
	public void setSamo(int samo) {
		this.samo = samo;
	}
	public int getSpri() {
		return spri;
	}
	public void setSpri(int spri) {
		this.spri = spri;
	}
	
	}
