package bean;

public class customer {
	private String UserId;
	private String Accountnumber;
	private String UserName;
	private String PIN;
	private String Cardnumber;
	private int Status;
	private int Balance;
	//新增4个
	private String email;
	private String Upwd;
	private String urlKey;
	private String codeRster;
	private String codePwd;
	public String getCodeRster() {
		return codeRster;
	}
	public void setCodeRster(String codeRster) {
		this.codeRster = codeRster;
	}
	public String getCodePwd() {
		return codePwd;
	}
	public void setCodePwd(String codePwd) {
		this.codePwd = codePwd;
	}
	public String getUrlKey() {
		return urlKey;
	}
	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}
	private String Uaccount;
	public String getUaccount() {
		return Uaccount;
	}
	public void setUaccount(String uaccount) {
		Uaccount = uaccount;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUpwd() {
		return Upwd;
	}
	public void setUpwd(String upwd) {
		Upwd = upwd;
	}
	
	
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
	public int getStatus(){
		return Status;
	}
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		Status=status;
	}
	public int getBalance(){
		
		return Balance;
	}
	public void setBalance(int balance) {
		// TODO Auto-generated method stub
		Balance=balance;
	}
	
}