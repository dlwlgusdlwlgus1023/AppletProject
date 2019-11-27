package project;

public class UserDTO {
	String uname, id, paw, hp;
	int birth;
	
	
	public UserDTO() {
	}


	public UserDTO(String uname, String id, String paw, String hp, int birth) {
		super();
		this.uname = uname;
		this.id = id;
		this.paw = paw;
		this.hp = hp;
		this.birth = birth;
	}


	@Override
	public String toString() {
		return "UserDTO [uname=" + uname + ", id=" + id + ", paw=" + paw + ", hp=" + hp + ", birth=" + birth + "]";
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPaw() {
		return paw;
	}


	public void setPaw(String paw) {
		this.paw = paw;
	}


	public String getHp() {
		return hp;
	}


	public void setHp(String hp) {
		this.hp = hp;
	}


	public int getBirth() {
		return birth;
	}


	public void setBirth(int birth) {
		this.birth = birth;
	}
	
	
	
	
	
}
