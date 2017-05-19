package dao;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.administrator;
import bean.customer;
import util.DBConnection;
import util.getUUID;

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

    public static int insert(String userid, String username, String pIN2, String accountnumber, String cardnumber,int status,int balance) throws SQLException {
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
	        ps.setInt(6, status);
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
	 
	public static int deleteUserInfo(String userID){
		Boolean existId = getId(userID,"Cid");
		int rs;
		
		if(!existId){
			String sql="delete from customer_info where Cid =?";
			Connection c;
			try {
				c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, userID);
				rs =ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				rs = 0;
				e.printStackTrace();
			}
			
		}else{
			rs = 0;
		}
		return rs;
	}
	
	//changePIN
	public static int ChangePIN(String cardNum,String id,String oldPIN,String newPIN){
		int result = 0;
		customer bean = new customer();
		bean = getAN(id);
		if(bean == null){
			result = 0;
			System.out.println("Incorrect Identify ID Number");
		}else{
			String cardNumber = bean.getCardnumber();
			if(cardNumber.equals(cardNum)){
				String oriPin = bean.getPIN();
				if(oriPin.equals(oldPIN)){
					if(oldPIN.equals(newPIN)){
						System.out.println("The new PIN should not be identical to the old one.");
						result = 3;
					}else{
						String sql1="update customer_info set PIN=? where Cid="+id;
						Connection c;
						try {
							c = DBConnection.getConnection();
							PreparedStatement ps1 = c.prepareStatement(sql1);		
							ps1.setString(1, newPIN);
							int rs1 =ps1.executeUpdate();
							if(rs1>0){
								result = 5;
								System.out.println("修改成功");
							}else{
								result = 4;
								System.out.println("修改没成功");
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}else{
					result = 2;
					System.out.println("Incorrect old PIN");
				}
			}else{
				result = 1;
				System.out.println("Incorrect card number");
			}
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
                int status=rs.getInt("status");
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
                
                String accountnumber=rs.getString("Anumber");
                int status=rs.getInt("status");
                int balance=rs.getInt("balance");
               
                //给bean赋值
                bean.setAccountnumber(accountnumber);
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
                bean.setStatus(rs.getInt("status"));
                bean.setBalance(rs.getInt("balance"));
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;  //返回一个带有所有customer_info信息的bean
		
	}


	public static int UpdateBalance(String accountNumber, int balance) throws SQLException {
		
		// TODO Auto-generated method stub
		String sql="update customer_info set balance=? where Anumber=?";
		Connection c = DBConnection.getConnection();
		PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, balance);    
        ps.setString(2, accountNumber);
        int rs =ps.executeUpdate();   
		return rs;
	}
	
	//search for Uaccount
		public static boolean getUaccount(String Uaccount,String pwd) throws SQLException {
			// TODO Auto-generated method stub
			String sql="select Uaccount,Upwd from customer_info where Uaccount=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,Uaccount);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				String upwd = rs.getString("Upwd");
				if(upwd.equals(pwd)){
					return true;
				}else{
					return false;
				}
				
			}else{
				return false;
			}
		}

		//search for Upwd
		public static boolean getUpwd(String Upwd) throws SQLException {
			// TODO Auto-generated method stub
			boolean result=false;
			String sql="select Upwd from customer_info where Upwd=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,Upwd);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				result=true;
			}else{
				result=false;
			}
			return result;
		}

		//search for Cid
		public static boolean getCid(String CustomerID) throws SQLException {
			// TODO Auto-generated method stub
			boolean result=false;
			String sql="select Cid from customer_info where Cid=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,CustomerID);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				result=true;
			}else{
				result=false;
			}
			return result;
		}

		public static int getThreeElement(String Uaccount, String customerID, String email) throws SQLException {
			// TODO Auto-generated method stub
			int result = 0;
			String sql="select Uaccount,Cid,Email from customer_info where Uaccount = ?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,Uaccount);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
			  String id = rs.getString("Cid");
			  String dEmail = rs.getString("Email");
			  if(id.equals(customerID)){
				  if(dEmail.equals(email)){
					 result = 3;
					 System.out.println("Succeed."); 
				  }else{
					  result = 2;
					  System.out.println("Invalid email address."); 
				  }
			  }else{
				  result = 1;
				  System.out.println("Invalid identity id number."); 
			  }
			}else{
				result = 0;
				System.out.println("Invalid user account");
			}
			return result;
		}

		public static int RegisterInfo(String username, String userID, String email, String password, int newStatus) throws SQLException {
			// TODO Auto-generated method stub
			int rs=0;
			if(getCid(userID)){
				if(!getUserAccount(username)){
					String sql="update customer_info set Uaccount=?,Email=?,Upwd=?,status=?  where Cid=?";
					Connection c = DBConnection.getConnection();
					PreparedStatement ps = c.prepareStatement(sql);
			        ps.setString(1, username);    
			        ps.setString(2, email);
			        ps.setString(3, password);
			        ps.setInt(4, newStatus);
			        ps.setString(5, userID);
			        int result =ps.executeUpdate();
			        
			        if(result>0){
			        	rs = 2;
			        	System.out.println("successful.");
			        }else{
			        	rs = 3;
			        	System.out.println("insert dataset wrong.");
			        }
				}else{
					rs = 1;
					System.out.println("The user account has existed.");
				}
			}else{
				rs = 0;
				System.out.println("Invalid identity id number.");
			}
			   
			return rs;
			
		}

		public static int updatePwd(String newPwd, String customerID) throws SQLException {
			// TODO Auto-generated method stub
			int rs=0;
			String sql="update customer_info set Upwd=?  where Cid=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
	        ps.setString(1, newPwd);    
	        ps.setString(2, customerID);      
	        rs =ps.executeUpdate();   
			return rs;
		}
		public static int resetPwd(String code,String newPwd) throws SQLException{
			int result = 0;
			String sql1="select Upwd from customer_info where codePwd = ?";
			String sql2 = "update customer_info set Upwd=?,codePwd = NULL  where codePwd=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql1);
			ps.setString(1,code);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				String oldPwd = rs.getString("Upwd");
				if(oldPwd.equals(newPwd)){
					result=1;
					System.out.println("The new password should not be identical to the old one.");//原則是不存在的
				}else{
					PreparedStatement ps1 = c.prepareStatement(sql2);
			        ps1.setString(1, newPwd);    
			        ps1.setString(2, code);      
			        int rs1 =ps1.executeUpdate();   
			        if(rs1>0){
			        	result = 2;
			        	System.out.println("Succeed");
			        }else{
			        	result = 3;
			        	System.out.println("update dataset wrong.");
			        }
				}
			}else{
				result=0;
				System.out.println("Invalid user account.");//原則是不存在的
			}
			return result;
		}
		//利用uuid来随机生成数字相当于是给user account加密
		public static String codeEn(String account,String key){
			String code = null;
			String code1 = getUUID.getUUID();
			
			int rs=0;
			String sql="update customer_info set "+key+" =? where Uaccount=?";
			Connection c;
			try {
				c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
		        ps.setString(1,code1); 
		        ps.setString(2,account);
		        rs =ps.executeUpdate();
		        if(rs>0){
		        	code = code1;
		        }else{
		        	code = null;
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				code = null;
				e.printStackTrace();
			}
			
			return code;
			
		}
		public static boolean updateRegisterInfo(String uaccount, String userID, String email, String password,
				String newStatus) throws SQLException {
			// TODO Auto-generated method stub
			int rs=0;
			String sql="update customer_info set Uacount=?,Email=?,Upwd=?,status=? where Cid=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
	        ps.setString(1,uaccount);    
	        ps.setString(2, email);
	        ps.setString(3,password);    
	        ps.setString(4, newStatus);
	        ps.setString(5, userID);    
	       
	        
	        rs =ps.executeUpdate();   
			if(rs>0){
				return true;
			}else{
				return false;
			}
		}
		//专门验证code存不存在 true:存在  false ：不存在
		public static Boolean verifyCode(String code,String codeKey){
			Boolean rs = false;
			String sql="select "+codeKey+" from customer_info where "+codeKey+" =?";
			Connection c;
			try {
				c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, code);
				ResultSet result=ps.executeQuery();
				if(result.next()){
					rs = true;
				}else{
					rs = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return rs;
		}
		//update status 激活用户的账号，将status改为0
		public static int updateStatus(String code) throws SQLException {
			// TODO Auto-generated method stub
			int rs=0;
			String sql2="update customer_info set codeRster = NULL,status = 0 where codeRster =?";
			Connection c = DBConnection.getConnection();
			
			if(verifyCode(code, "codeRster")){
				PreparedStatement ps1 = c.prepareStatement(sql2);
				ps1.setString(1, code);
				int result2 = ps1.executeUpdate();
				if(result2>0){
					rs = 2;
					System.out.println("Succeed");
				}else{
					rs = 1;
					System.out.println("update fail.");
				}
			}else{
				rs = 0;
				System.out.println("code 不存在，已经激活了");
			}
			
			return rs;
		}

		public static boolean getUserAccount(String Uaccount) throws SQLException {
			// TODO Auto-generated method stub	
			String sql="select Uaccount from customer_info where Uaccount=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,Uaccount);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
			
			
			
		}

		public static boolean getPwd(String newPwd) throws SQLException {
			// TODO Auto-generated method stub
			String sql="select Upwd from customer_info where Upwd=?";
			Connection c = DBConnection.getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,newPwd);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		}

		public static customer getULogInfo(String uaccount, String upwd) {
			// TODO Auto-generated method stub
			customer bean = null;
			 
	    	
			String sql = "select * from administrator_info where Uaccount= ? and Upwd=?";     //将account=nick,password=725713的所有信息抽取
	        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
	        	ps.setString(1, uaccount);
	        	ps.setString(2, upwd);
	            ResultSet rs =ps.executeQuery();    //执行sql
	            
	 
	            //遍历结果集
	            if (rs.next()) {
	                bean = new customer();
	                
	                //将执行后的结果放入bean对象里
	               bean.setUaccount(uaccount);
	               bean.setUpwd(upwd);
	            }
	 
	        } catch (SQLException e) {
	 
	            e.printStackTrace();
	        }
	        return bean;   //返回bean对象
	        
		}
	
}