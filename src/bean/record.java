package bean;

import java.util.Date;

public class record {
	
	private int id;
	private int cid;
	private float count;  //交易金额
	private String type;  //交易类型
	private Date tdate;  //交易日期
	
	
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
	public float getCount() {
		return count;
	}
	public void setCount(float count) {
		this.count = count;
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
	
}
