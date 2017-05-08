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

	public static int insert(String userid, String username, String pIN2, String accountnumber, String cardnumber,String status) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into customer_info (userid,username,PIN,accountnumber,cardnumber,status) value(?,?,?,?,?,?)"; 
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, userid);
        ps.setString(2, username);
        ps.setString(3, pIN2);
        ps.setString(4, accountnumber);
        ps.setString(5, cardnumber);
        ps.setString(6, status);
        	
        int rs =ps.executeUpdate();    //执行sql,向Customer插入5条信息
		return rs;
            
	
}

	public static customer get(String userID) {
		// TODO Auto-generated method stub
		customer bean=null;
		String sql="select * from customer_info where userid=?";
		
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, userID); //根据userid查询customer_info表的所有信息
        	
            ResultSet rs =ps.executeQuery();    //执行sql
            
 
            //遍历结果集
            if (rs.next()) {
                bean = new customer();
                
                String userid=rs.getString("userid");
                String username=rs.getString("username");
                String accountnumber=rs.getString("accountnumber");
                String cardnumber=rs.getString("cardnumber");
                String status=rs.getString("status");
                //给bean赋值
                bean.setUserId(userid);
                bean.setUserName(username);
                bean.setAccountnumber(accountnumber);
                bean.setCardnumber(cardnumber);
                bean.setStatus(status);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;  //返回bean值，可用来判断查询的操作是否成功，并且将查询结果封装在bean中，需要时打印出来
        
	}
		
	}
