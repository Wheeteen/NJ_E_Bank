package bean;

import java.util.Date;

public class record {
	
	private int id;
	private String accountnumber;
	private int amount;   //操作数量
	private int balance;
	private String authentication_code; //权限码：默认为null,>50000需要插入
	private String type;   //操作类型：0：存款，1：取款
	private Date tdate;  //操作日期
    private String uid;  //外键
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getAuthentication_code() {
		return authentication_code;
	}
	public void setAuthentication_code(String authentication_code) {
		this.authentication_code = authentication_code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getTdate() {
		return tdate;
	}
	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}
