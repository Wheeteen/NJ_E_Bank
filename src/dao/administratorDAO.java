package dao;
//������get����,get�������ݿ���Ķ���
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.administrator;
import util.DBConnection;
//����CRUD����δд

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
		 
		String sql = "select * from administrator_info where account = ? and password=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        	ps.setString(1, account);
        	ps.setString(2, password);
            ResultSet rs =ps.executeQuery();    //ִ��sql
            
            //���������
            if (rs.next()) {
                bean = new administrator();
                int id = rs.getInt("id"); //ͨ��������ȡ������е�id
                
                //��ad.bean��ֵ
                bean.setAccount(account); 
                bean.setPassword(password);
                bean.setId(id);
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return bean;
        
	}

}
