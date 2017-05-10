package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import bean.customer;
import bean.record;
import util.DBConnection;
import util.Date;

public class recordDAO {
	public int getTotal(){
		return 0;
		
	}
	
	public void add(record bean){
		
	}
	
	public void update(record bean){
		
	}
	
    public void delete(record bean){
		
	}
    public record get(int id){
    	record bean=null;
    	return bean;
    }

	public static int insert(String accountNumber, int depositAmount, int balance, String type, Timestamp d2t,
			String uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into record_info (accountnumber,amount,balance,type,tdate,uid) value(?,?,?,?,?,?)"; 
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, accountNumber);
        ps.setInt(2, depositAmount);
        ps.setInt(3, balance);
        ps.setString(4, type);
        ps.setTimestamp(5, d2t);
        ps.setString(6, uid);
        	
        int rs =ps.executeUpdate();    //执行sql,向Customer插入5条信息
		return rs;
	}

	public static int insert(String accountNumber, int depositAmount, int balance, String currency,
			String type, Timestamp d2t) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into record_info (Anumber,amount,balance,currency,Otype,Odate) value(?,?,?,?,?,?)"; 
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, accountNumber);
        ps.setInt(2, depositAmount);
        ps.setInt(3, balance);
        ps.setString(4,currency);
        ps.setString(5, type);
        ps.setTimestamp(6, d2t);
              	
        int rs =ps.executeUpdate();    //执行sql,向Customer插入5条信息
		return rs;
	}

	public static record getBalance(String accountNumber) {
		// TODO Auto-generated method stub
		record bean=null;
		String sql="select balance from record_info where accountnumber=?";
		
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, accountNumber); 
        	
            ResultSet rs =ps.executeQuery();    //执行sql
            
 
            //遍历结果集
            if (rs.next()) {
                bean = new record();
                
                String balance=rs.getString("balance");
                
                //给bean赋值
                bean.setBalance(Integer.parseInt(balance));
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean; 
	}

	public static record getBalance1(String AN) {
		// TODO Auto-generated method stub
		record bean=null;
		String sql="select * from where record_info accountnumber=?";
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, AN);
            ResultSet rs =ps.executeQuery();    
            
 
            //遍历结果集
            if (rs.next()) {
                bean = new record();
                bean.setId(rs.getInt("balance"));  //把结果查询到的balance放到bean上
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
		return bean;  //返回带有balance的bean
	}

	public static int insert(int balance,String accountnumber) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into record_info (accountnumber,balance) value(?,?) "; 
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
       
		ps.setString(1,accountnumber);
		ps.setInt(2, balance);
        
        	
        int rs =ps.executeUpdate();    //执行sql,向Customer插入5条信息
		return rs;  //返回插入balance
		
	}
}
