package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.administrator;
import bean.customer;
import util.DBConnection;

public class customerDAO {
	public int getTotal(){
		return 0;
		
	}
	
	public void add(customer bean){
		
	}
	
	public void update(customer bean){
		
	}
	
    public void delete(customer bean){
		
	}
    public customer get(int id){
    	customer bean=null;
    	return bean;
    }

	public static int insert(String userid, String username, String pIN2, String accountnumber, String cardnumber) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into customer_info (userid,username,PIN,accountnumber,cardnumber) value(?,?,?,?,?)"; 
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, userid);
        ps.setString(2, username);
        ps.setString(3, pIN2);
        ps.setString(4, accountnumber);
        ps.setString(5, cardnumber);
        	
        int rs =ps.executeUpdate();    //执行sql,向Customer插入5条信息
		return rs;
            
	
}
}

