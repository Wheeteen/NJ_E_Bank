package bean;

public class customer {
	private int id;
	private int card_number;
	private String name;
	private String password;
	private String authentication_code;
	
	public customer(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCard_number() {
		return card_number;
	}
	public void setCard_number(int card_number) {
		this.card_number = card_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthentication_code() {
		return authentication_code;
	}
	public void setAuthentication_code(String authentication_code) {
		this.authentication_code = authentication_code;
	}

}
