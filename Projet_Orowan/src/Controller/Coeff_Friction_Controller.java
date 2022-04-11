package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;


public class Coeff_Friction_Controller {
	
	public FileChooser fil_chooser = new FileChooser();
	
	@FXML
    private ProgressBar bar;
	
	

    @FXML
    private Text coeff_value;
	
	 @FXML
	 private Button displayDataButtton;

    @FXML
    private ListView<String> listCoeffFriction = new ListView<String>();
    
    @FXML
    private Text labellWelcome;
    
    @FXML
    private Button loadFileButton;
    
    @FXML
    private Button logout;

    @FXML
    private Button openAdminPage;
	
	public ArrayList<String> datas ;
	public int k=1;
	public String loadedFile;
	
	NumberAxis xAxis = new NumberAxis();
	

	NumberAxis yAxis = new NumberAxis();
	
	@FXML
    private LineChart<String, String> chart= new LineChart(xAxis, yAxis);
	
  
    
	
	public Coeff_Friction_Controller() {
		super();
	}
	
	  @FXML
	    void goToAdminPage(ActionEvent event) {
		  
		  Parent part = null;
			try {
				part = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        Stage new_stage = new Stage();
	        new_stage.setTitle("ArcelorSys");
	        Scene scene = new Scene(part,800, 480);
	        new_stage.setScene(scene);
	        new_stage.show();
	        
	        Stage stage = (Stage) openAdminPage.getScene().getWindow();
			// do what you have to do
			stage.close();

	    }


	    @FXML
	    void logOut(ActionEvent event) {
	    	
	        
	       /* Parent part = null;
			try {
				part = FXMLLoader.load(getClass().getResource("login2.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        Stage new_stage = new Stage();
	        new_stage.setTitle("ArcelorSys");
	        Scene scene = new Scene(part,800, 480);
	        new_stage.setScene(scene);
	        new_stage.show(); */
	        
	        Stage stage = (Stage) logout.getScene().getWindow();
	        // do what you have to do
	        stage.close(); 
	    }
	
	
	@FXML
    void displayData(ActionEvent event) {
		
		k=1;
		
		System.out.println("Hello"); 	
		
		this.datas= new ArrayList<String>();
		
		this.lireFichierOutput();
		
		//System.out.println("Hello");
		 
		Timer timer = new Timer();
	    timer.scheduleAtFixedRate(new TimerTask() {
	        @Override
	        public void run() {
	        	javafx.application.Platform.runLater(new Runnable() {
	                @Override
	                public void run() {
	                	if(k==(datas.size()-1)) {
	                		timer.cancel();
	                	}
	                String element = datas.get(k);
	   		        listCoeffFriction.getItems().add(0, String.valueOf(element));
	                
	                    System.out.print("Hello");
	                    /*listCoeffFriction.getItems().clear();
	                    for(int i=0;i<k;i++){
	       		         String element = datas.get(i);
	       		         listCoeffFriction.getItems().add(String.valueOf(element));
	       		         } */
	                    coeff_value.setText(datas.get(k));
	                    bar.setProgress(Double.valueOf(datas.get(k))*4) ;
	                    k++;
	                    
	                    
	                
	                }
	                
	            });
	        }
	    }, 0, 1000);       
		
		//this.afficherValeur(this.datas);
	    
	   /* XYChart.Series<String, String> dataSeries1 = new XYChart.Series<String, String>();
	    dataSeries1.setName("2014");

	    dataSeries1.getData().add(new XYChart.Data<String, String>( "1", "567"));
	    dataSeries1.getData().add(new XYChart.Data<String, String>( "1", "567"));
	    dataSeries1.getData().add(new XYChart.Data<String, String>( "1", "567"));
	    dataSeries1.getData().add(new XYChart.Data<String, String>( "1", "567"));
	    dataSeries1.getData().add(new XYChart.Data<String, String>( "1", "567"));
	    dataSeries1.getData().add(new XYChart.Data<String, String>( "1", "567"));
	    chart.getData().add(dataSeries1); */

		
	}
	
	 @FXML
	 void loadFile(ActionEvent event) {
		 
		 Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		 
		 File file = fil_chooser.showOpenDialog(stage);
		 
         if (file != null) {
        	 
        	 Alert alert = new Alert(AlertType.INFORMATION, file.getAbsolutePath()+ "  selected", ButtonType.OK);
     		 alert.showAndWait();
     		 loadedFile = String.valueOf(file.getAbsolutePath());
         }

	    }
	 
	 @FXML
	void computeOrowanModel() {
		 
		 System.out.print("Hello");
		 Process process=null;
			String user_dir=null;
			try {
				//user_dir = System.getProperty("user.dir");
				String s = null;
				String path = "C:\\Users\\josue\\OneDrive\\Bureau\\Orowan\\Orowan_x64.exe";
				process = new ProcessBuilder(path).start();
				OutputStream out = process.getOutputStream();
				s="i\nc\nC:\\Users\\josue\\OneDrive\\Bureau\\Orowan\\inv_cst.txt\nC:\\Users\\josue\\OneDrive\\Bureau\\oox.txt";
				out.write(s.getBytes());
				out.flush();
				out.close();
				process.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.print("Hello");
			
			BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line = null;
	        while (true) {
	            try {
					line = r.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (line == null) { break; }
	            System.out.println(line);
	        }
			
		}
	
	public void afficherValeur( ArrayList<String> datas) {
		/*for (int i = 0; i < this.datas.size();i++) { 		      
        System.out.println(this.datas.get(i)); 		
  }  */ 
		int k=1;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
			  //listCoeffFriction.getItems().clear();
			  
			  	String element = datas.get(k);
		         listCoeffFriction.getItems().add(listCoeffFriction.getItems().size()-1, String.valueOf(element));


		       /* for(int i=0;i<datas.size();i++){
		         String element = datas.get(i+1);
		         listCoeffFriction.getItems().add(listCoeffFriction.getItems().size()-1, String.valueOf(element));
		        } */
			  System.out.println(datas.get(k));
		  }
		}, 0, 1000);//wait 0 ms before doing the action and do it evry 1000ms (1second)

		timer.cancel();
	}
	
	
	
	public void lireFichierOutput() {
		
		String user_dir = System.getProperty("user.dir");
		
		//File file = new File(System.getProperty("user.dir")+"\\src\\oo.txt");
		File file = new File("C:\\Users\\josue\\OneDrive\\Bureau\\Orowan\\out.txt");
 
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
		String[] data = null;
        BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        try {
			while ((st = br.readLine()) != null) {

			    // Print the string
				data = st.split("\t");
			    System.out.println(data[3]); 
				this.datas.add(data[3]);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
