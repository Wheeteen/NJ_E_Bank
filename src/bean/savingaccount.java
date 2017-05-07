package bean;

public class savingaccount {
	private int id;
	private int cid;
	private int account_number;
	private String accont_PIN;
	private float balance;
	private char status;   //ÕË»§×´Ì¬
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getAccount_number() {
		return account_number;
	}
	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}
	public String getAccont_PIN() {
		return accont_PIN;
	}
	public void setAccont_PIN(String accont_PIN) {
		this.accont_PIN = accont_PIN;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
}
