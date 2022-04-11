package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User_DAO_Class implements User_DAO{
	
	PreparedStatement insertion;
	PreparedStatement update;
	PreparedStatement delete;
	static DBConnexion db;
	
	
	public User_DAO_Class() {
		db=new DBConnexion();
	}

	
	
	public User get(long id) {
		
		User user = new User();
		
		try {
			
			Statement st = db.getDbConnection().createStatement();
			String request = "select * from Users where Id_user="+String.valueOf(id);
			ResultSet rs = st.executeQuery(request);
			
			if(rs.next())
			{
				user.setId_user(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRole(rs.getString(4));
			}else {
				return null;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		
		return user;
		
	}
	
	public int checkUserInformations(String username, String pwd) {
		try {
			
			Statement st = db.getDbConnection().createStatement();
			String request = "select * from Users where username='"+username+"' AND Password='"+pwd+"'";
			ResultSet rs = st.executeQuery(request);
			
			if(rs.next())
			{
				return rs.getInt(1);
				
			}else {
				return 0;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return 0;
		
	}
	
    
    public List<User> getAll(){
    	
    	ArrayList<User> users = new ArrayList<>();
		
		try {
			
			Statement st = db.getDbConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from Users");
			
			while (rs.next())
			{
				users.add(new User(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4)));
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
    	
    }
    
    
    
    public void save(User user) {
    	
    	try {
    		
			insertion=db.getDbConnection().prepareStatement("INSERT INTO Users(username,Password,role) VALUES (?, ?, ?)");
			insertion.setString(1,user.getUsername());
			insertion.setString(2,user.getPassword());
			insertion.setString(3,user.getRole());
			insertion.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    
    public void update(int id,User user) {
    	
    	try {
			update=db.getDbConnection().prepareStatement( "UPDATE Users SET username ='"+String.valueOf(user.getUsername())+"' WHERE Id_user="+String.valueOf(id));
			update.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void delete(int id) {
    	try {
			delete=db.getDbConnection().prepareStatement( "DELETE FROM Users WHERE Id_user="+String.valueOf(id));
			delete.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}
