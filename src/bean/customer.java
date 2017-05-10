package bean;

public class customer {
	private String UserId;
	private String Accountnumber;
	private String UserName;
	private String PIN;
	private String Cardnumber;
	private String Status;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getAccountnumber() {
		return Accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		Accountnumber = accountnumber;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPIN() {
		return PIN;
	}
	public void setPIN(String pIN) {
		PIN = pIN;
	}
	public String getCardnumber() {
		return Cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		Cardnumber = cardnumber;
	}
	public String getStatus(){
		return Status;
	}
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		Status=status;
	}
	
}