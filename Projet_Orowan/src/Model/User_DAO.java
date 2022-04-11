package Model;

import java.util.List;
import Model.User;

public interface User_DAO {
	
	User get(long id);
    
    List<User> getAll();
    
    void save(User user);
    
    void update(int id,User user);
    
    void delete(int id);

}
