package application;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import Model.User;
import Model.User_DAO_Class;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	Connection dbConnection;
	PreparedStatement insertion;
	
	static User_DAO_Class dao;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/*BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Orowan");
			primaryStage.setScene(scene);
			primaryStage.show(); */
			
			Parent root = FXMLLoader.load (getClass().getResource(("login.fxml"))); 
			primaryStage.setTitle("ArcelorSys");
			primaryStage.setScene (new Scene (root, 800, 450));
			primaryStage.show ();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		dao = new User_DAO_Class();
		
		//dao.delete(6);
		
		dao.save(new User(1,"efra","efra","user"));
		
		//dao.update(2, new User(2,"Diego"));
		
		
		
		//User u = (User)dao.get(2);
		
		
		ArrayList<User> users = (ArrayList<User>) dao.getAll();
		
		System.out.print(users.get(0).getUsername());
		launch(args);
	}
}
