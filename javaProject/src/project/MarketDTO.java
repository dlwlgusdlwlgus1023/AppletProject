package project;

public class MarketDTO {
	int prod_no, price, amoun;
	String name, company;
	public MarketDTO() {
	}
	public MarketDTO(int prod_no, String name, String company,int price, int amoun) {
		super();
		this.prod_no = prod_no;
		this.price = price;
		this.amoun = amoun;
		this.name = name;
		this.company = company;
	}
	@Override
	public String toString() {
		return "MarketDTO [prod_no=" + prod_no + ", price=" + price + ", amoun=" + amoun + ", name=" + name
				+ ", company=" + company + "]";
	}
	public int getProd_no() {
		return prod_no;
	}
	public void setProd_no(int prod_no) {
		this.prod_no = prod_no;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmoun() {
		return amoun;
	}
	public void setAmoun(int amoun) {
		this.amoun = amoun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
