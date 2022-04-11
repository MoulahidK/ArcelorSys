package Controller;

import java.io.IOException;

import Model.User;
import Model.User_DAO_Class;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class User_Controller {
	
	static User_DAO_Class dao;

    @FXML
    private PasswordField pwd;

    @FXML
    private Button submit;

    @FXML
    private TextField username;
    
    
    @FXML
    private ComboBox<String> allUsers;
    
    @FXML
    private ComboBox<String> role;
    
    
    @FXML
    private Button addUserButton;

    @FXML
    private Button updateUserButton;
    
    
    @FXML
    private TextField emailUser;
    
    @FXML
    private TextField firstName;
    
    @FXML
    private TextField lastName;

    @FXML
    private PasswordField passWord;

   
    @FXML
    private TextField userName;
    
    
    @FXML
    public void initialize() {
    	try {
        role.getItems().addAll("admin","utilisateur");
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }

    @FXML
    void checkCredentials(ActionEvent event) {
    	
    	
    	
    	dao = new User_DAO_Class();
    	
    	int response = dao.checkUserInformations(String.valueOf(username.getText()), String.valueOf(pwd.getText()));
    	
    	if(response != 0) {
    		
    		   /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
    		    Parent root1 = null;
				try {
					root1 = (Parent) fxmlLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				} */
    		
    			User user = new User();
    			user = dao.get(response);
    			
    			System.out.print(user.getRole());
    			
    			
				if( user.getRole().equals("admin") ) {
					
					
					
					Parent part = null;
					try {
						part = FXMLLoader.load(getClass().getResource("admin.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
			        Stage new_stage = new Stage();
			        new_stage.setTitle("ArcelorSys");
			        Scene scene = new Scene(part,800, 480);
			        new_stage.setScene(scene);
			        new_stage.show();
			        
			        Stage stage = (Stage) submit.getScene().getWindow();
	    			// do what you have to do
	    			stage.close();
				}else {
					
					
					Parent part = null;
					try {
						part = FXMLLoader.load(getClass().getResource("user.fxml"));
					} catch (IOException e) {
						e.printStackTrace();
					}
			        Stage new_stage = new Stage();
			        new_stage.setTitle("ArcelorSys");
			        Scene scene = new Scene(part,800, 480);
			        new_stage.setScene(scene);
			        new_stage.show();
			        
			        Stage stage = (Stage) submit.getScene().getWindow();
	    			// do what you have to do
	    			stage.close();
				}
				
				
    		 
    		
    	}else {
    		
    		Alert alert = new Alert(AlertType.ERROR, "Les informations que vous avez saisi sont incorrectes !", ButtonType.OK);
    		alert.showAndWait();
    		
    		System.out.print("Informations incorrectes ! ");
    		
    	}
    	System.out.print(response);
    }
    
    @FXML
    void addUser(ActionEvent event) {
    	
    	dao = new User_DAO_Class();
    	
    	dao.save(new User(0,String.valueOf(emailUser.getText()),String.valueOf(passWord.getText()),"user")); 
    	
    	 Alert alert = new Alert(AlertType.INFORMATION,"Utilisateur ajouté !", ButtonType.OK);
 		 alert.showAndWait();
    	
    	System.out.print("User added !");
    	
    	emailUser.setText("");
    	passWord.setText(" ");
    	
    	
    }
    
    @FXML
    void updateUser(ActionEvent event) {
    	
    	dao = new User_DAO_Class();
    	
    	dao.update(0, new User());
    	
    	
    }
    
    void deleteUser() {
    	
    	dao = new User_DAO_Class();
    	
    	dao.delete(0);
    	
    }
    
    
    
    
    
    

}
