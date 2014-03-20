package main;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.Clock;

public class Main extends javafx.application.Application{
	
	private static Clock singeltonClock;
	
    
    public static void main(String[] args) {
    	singeltonClock = Clock.getInstance();
		singeltonClock.startTimer();
		
		launch(args);
	}	
    @Override
    public void stop(){
    	Clock.getInstance().myTimer.pause();
    }

	@Override
	public void start(Stage primaryStage) throws Exception{
    	
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml/fxml_example.fxml"));
		    
	        Scene scene = new Scene(root, 614, 428);
	        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
	        	@Override
	        	public void handle(WindowEvent event){
	        		Platform.exit();
	        	}
	        });
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
