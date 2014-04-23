package main;

import interfaces.ICommand;

import java.util.Stack;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends javafx.application.Application{
	
    public static Stack<ICommand> history = new Stack<ICommand>();
	
	public static void main(String[] args) {
    	
		launch(args);
	}	
    @Override
    public void stop(){
    	//maybe save actual state in xml
    }

	@Override
	public void start(Stage primaryStage) throws Exception{
    	
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml/Editor.fxml"));
		    
	        Scene scene = new Scene(root, 700, 450);
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
