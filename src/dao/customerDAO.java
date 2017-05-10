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
		Boolean existId = getUserId(userid);
		int rs=0;
		if(existId){
			String sql = "insert into customer_info (userid,username,PIN,accountnumber,cardnumber,status) value(?,?,?,?,?,?)"; 
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
	        ps.setString(1, userid);
	        ps.setString(2, username);
	        ps.setString(3, pIN2);
	        ps.setString(4, accountnumber);
	        ps.setString(5, cardnumber);
	        ps.setString(6, status);
	        	
	        rs =ps.executeUpdate();    //鎵цsql,鍚慍ustomer鎻掑叆5鏉′俊鎭�
		}else{
			rs = 0;
		}
		return rs;
	}

	public static Boolean getUserId(String userID){
		String sql="select * from customer_info where userid=?";
		Connection c;
		Boolean result=false;
		try {
			c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, userID);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				result = false;
			}else{
				result = true;
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
		String sql="select * from customer_info where userid=?";
		
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, userID); //鏍规嵁userid鏌ヨcustomer_info琛ㄧ殑鎵�鏈変俊鎭�
        	
            ResultSet rs =ps.executeQuery();    //鎵цsql
            
 
            //閬嶅巻缁撴灉闆�
            if (rs.next()) {
                bean = new customer();
                
                String userid=rs.getString("userid");
                String username=rs.getString("username");
                String accountnumber=rs.getString("accountnumber");
                String cardnumber=rs.getString("cardnumber");
                String status=rs.getString("status");
                //缁檅ean璧嬪��
                bean.setUserId(userid);
                bean.setUserName(username);
                bean.setAccountnumber(accountnumber);
                bean.setCardnumber(cardnumber);
                bean.setStatus(status);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;  //杩斿洖bean鍊硷紝鍙敤鏉ュ垽鏂煡璇㈢殑鎿嶄綔鏄惁鎴愬姛锛屽苟涓斿皢鏌ヨ缁撴灉灏佽鍦╞ean涓紝闇�瑕佹椂鎵撳嵃鍑烘潵
        
	}
		//鏍规嵁accountNumber寰楀埌鍙寘鍚玼id鐨刡ean
	public static customer get2(String accountNumber) {
		// TODO Auto-generated method stub
		customer bean=null;
		String sql="select userid from customer_info where accountnumber=?";
		
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, accountNumber); //鏍规嵁accountNumber鎵緐id
        	
            ResultSet rs =ps.executeQuery();    //鎵цsql,鎵惧埌uid鐨勭粨鏋滈泦
            
 
            //閬嶅巻缁撴灉闆�
            if (rs.next()) {
                bean = new customer();
                
                String userid=rs.getString("userid");
               // String username=rs.getString("username");
                //String accountnumber=rs.getString("accountnumber");
               // String cardnumber=rs.getString("cardnumber");
                //String status=rs.getString("status");
                //缁檅ean璧嬪��
                bean.setUserId(userid);
                //bean.setUserName(username);
                //bean.setAccountnumber(accountnumber);
                //bean.setCardnumber(cardnumber);
                //bean.setStatus(status);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;  //杩斿洖鍙惈鏈塧ccountnumber鍜寀id鐨刡ean
        
	}
	
	}