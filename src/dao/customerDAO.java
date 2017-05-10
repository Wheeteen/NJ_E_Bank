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

    public static int insert(String userid, String username, String pIN2, String accountnumber, String cardnumber,String status,int balance) throws SQLException {
		// TODO Auto-generated method stub
		Boolean existId = getId(userid,"Cid");
		int rs=0;
		
		if(existId){
			String sql = "insert into customer_info (Cid,Cname,PIN,Anumber,Cnumber,status,balance) value(?,?,?,?,?,?,?)"; 
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
	        ps.setString(1, userid);
	        ps.setString(2, username);
	        ps.setString(3, pIN2);
	        ps.setString(4, accountnumber);
	        ps.setString(5, cardnumber);
	        ps.setString(6, status);
	        ps.setInt(7, balance);
	        	
	        rs =ps.executeUpdate();    //鎵цsql,鍚慍ustomer鎻掑叆5鏉′俊鎭�
		}else{
			rs = 0;
		}
		return rs;    
	
	}
 	

	public static Boolean getId(String userID,String cid){
		String sql="select * from customer_info where "+ cid+"=?";
		Connection c;
		Boolean result=false;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, userID);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				if(cid=="Cid"){
					result = false;
				}else{
					result = true;
				}	
			}else{
				if(cid=="Cid"){
					result = true;
				}else{
					result = false;
				}	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	 
	
	public static customer get(String userID) {
		// TODO Auto-generated method stub
		customer bean=null;
		String sql="select * from customer_info where Cid=?";
		
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, userID); //根据userid查询customer_info表的所有信息
        	
            ResultSet rs =ps.executeQuery();    //执行sql
            
 
            //遍历结果集
            if (rs.next()) {
                bean = new customer();
                
                String userid=rs.getString("Cid");
                String username=rs.getString("Cname");
                String PIN=rs.getString("PIN");
                String accountnumber=rs.getString("Anumber");
                String cardnumber=rs.getString("Cnumber");
                String status=rs.getString("status");
                int balance=rs.getInt("balance");
                //给bean赋值
                bean.setUserId(userid);
                bean.setUserName(username);
                bean.setPIN(PIN);
                bean.setAccountnumber(accountnumber);
                bean.setCardnumber(cardnumber);
                bean.setStatus(status);
                bean.setBalance(balance);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;  //返回bean值，可用来判断查询的操作是否成功，并且将查询结果封装在bean中，需要时打印出来
        
	}
		//根据accountNumber得到只包含uid的bean
	public static customer getAll(String accountNumber) {
		// TODO Auto-generated method stub
		customer bean=null;
		String sql="select * from customer_info where Anumber=?";
		
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, accountNumber); //根据accountNumber找uid
        	
            ResultSet rs =ps.executeQuery();    //执行sql,找到uid的结果集
            
 
            //遍历结果集
            if (rs.next()) {
                bean = new customer();
                
                String userid=rs.getString("Cid");
               String username=rs.getString("Cname");
               String PIN=rs.getString("PIN");
                String accountnumber=rs.getString("Anumber");
               String cardnumber=rs.getString("Cnumber");
                String status=rs.getString("status");
                int balance=rs.getInt("balance");
               
                //给bean赋值
                bean.setUserId(userid);
                bean.setUserName(username);
                bean.setPIN(PIN);
                bean.setAccountnumber(accountnumber);
                bean.setCardnumber(cardnumber);
                bean.setStatus(status);
                bean.setBalance(balance);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;  
        
	}

	public static customer getAN(String userid) {
		// TODO Auto-generated method stub
		customer bean=null;
		String sql="select * from customer_info where Cid=?";
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, userid);
            ResultSet rs =ps.executeQuery();    
            
 
            //遍历结果集
            if (rs.next()) {
                bean = new customer();
                bean.setUserId(rs.getString("Cid"));
                bean.setUserName(rs.getString("Cname"));
                bean.setPIN(rs.getString("PIN"));
                bean.setCardnumber(rs.getString("Cnumber"));
                bean.setAccountnumber(rs.getString("Anumber"));
                bean.setStatus(rs.getString("status"));
                bean.setBalance(rs.getInt("balance"));
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;  //返回一个带有所有customer_info信息的bean
		
	}

	public static int insertBalance(String accountNumber,int balance) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		String sql = "insert into customer_info (Cid,Cname,PIN,Anumber,Cnumber,status,balance) value(?,?,?,?,?,?,?)"; 
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, "newCid6");    //insert时候需要提供变量的Cid,不能重复，否则出现Exception...待处理
        ps.setString(2, "newCname");
        ps.setString(3, "newPIN");
        ps.setString(4, "newAnumber");
        ps.setString(5, "newCnumber");
        ps.setString(6, "newstatus");
        ps.setInt(7, balance);
        int rs =ps.executeUpdate();    //执行sql,向Customer_info插入5条信息
		return rs;
            
		            
			

	}

	public static int UpdateBalance(String accountNumber, int balance) throws SQLException {
		
		// TODO Auto-generated method stub
		String sql="update customer_info set balance=1000 where Anumber=?";
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, accountNumber);    //insert时候需要提供变量的Cid,不能重复，否则出现Exception...待处理
        //ps.setInt(2, balance);
        int rs =ps.executeUpdate();    //执行sql,向Customer_info插入5条信息
		return rs;
	}
	
	}