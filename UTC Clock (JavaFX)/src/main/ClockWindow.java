package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import interfaces.IClockWindow;

public class ClockWindow extends javafx.application.Application implements IClockWindow {

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml/fxml_example.fxml"));
		    
	        Scene scene = new Scene(root, 614, 428);
	        
	        stage.setScene(scene);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
