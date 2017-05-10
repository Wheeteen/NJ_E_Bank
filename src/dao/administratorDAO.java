package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.administrator;
import util.DBConnection;
//具体CRUD操作未写

public class administratorDAO {
	
	public int getTotal(){
		return 0;
		
	}
	
	public void add(administrator bean){
		
	}
	
	public void update(administrator bean){
		
	}
	
    public void delete(administrator bean){
		
	}
    public administrator get(int id){
    	administrator bean=null;
    	return bean;
    }
 
    
    
    
    public static administrator get(String account, String password) {
    	administrator bean = null;
		 
    	
		String sql = "select * from administrator_info where Aname= ? and pwd=?";     //将account=nick,password=725713的所有信息抽取
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, account);
        	ps.setString(2, password);
            ResultSet rs =ps.executeQuery();    //执行sql
            
 
            //遍历结果集
            if (rs.next()) {
                bean = new administrator();
                
                //将执行后的结果放入bean对象里
                bean.setAccount(rs.getString("Aname")); 
                bean.setPassword(rs.getString("pwd"));
                bean.setId(rs.getInt("id"));
                bean.setCode(rs.getString("Acode"));
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;   //返回bean对象
        
	}

}