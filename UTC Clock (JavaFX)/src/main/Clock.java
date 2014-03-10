package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import interfaces.IClock;

public class Clock extends javafx.application.Application implements IClock {
	
	private int _hours, _minutes, _seconds;
    
    public static void main(String[] args) {
		launch(args);
	}	
    
    @Override
	public void start(Stage primaryStage) throws Exception{
    	
    	MyTimer myTimer = new MyTimer();
		
		this.setHours(12);
		this.setMinutes(59);
		this.setSeconds(55);
		
		myTimer.setClock(this);
		myTimer.start();
    	
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml/fxml_example.fxml"));
		    
	        Scene scene = new Scene(root, 614, 428);
	        
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}	
	
	//SETTERS bzw. GETTERS
	
	public int getHours() {
		return _hours;
	}

	public void setHours(int _hours) {
		this._hours = _hours;
	}
	
	public int getMinutes() {
		return _minutes;
	}

	public void setMinutes(int _minutes) {
		this._minutes = _minutes;
	}
	
	public int getSeconds() {
		return _seconds;
	}

	public void setSeconds(int _seconds) {
		this._seconds = _seconds;
	}
}
