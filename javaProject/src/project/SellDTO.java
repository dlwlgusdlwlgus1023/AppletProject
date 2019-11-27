package project;

public class SellDTO {
	String sid,sname ,spay;
	int samo, spri;
	public SellDTO() {
	}
	public SellDTO(String sid, String sname, int spri, int samo, String spay) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.samo = samo;
		this.spri = spri;
		this.spay = spay;
	}
	@Override
	public String toString() {
		return "SellDTO [sid=" + sid + ", sname=" + sname + ", spay=" + spay + ", samo=" + samo + ", spri=" + spri
				+ "]";
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
	public String getSpay() {
		return spay;
	}
	public void setSpay(String spay) {
		this.spay = spay;
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