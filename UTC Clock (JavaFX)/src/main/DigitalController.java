package main;

import java.net.URL;
import java.util.ResourceBundle;

import interfaces.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.Clock;

public class DigitalController implements Observer, Initializable {
	
	@FXML private Text hoursOutput;
	@FXML private Text minutesOutput;
	@FXML private Text secondsOutput;
	@FXML private Text timezoneOutput;
	private static Clock singeltonClock;
	int _timezone;
	
	@Override
	public void update() {
		
		hoursOutput.setText(String.format("%02d", (singeltonClock.getHours()+_timezone)%24));
		minutesOutput.setText(String.format("%02d", (singeltonClock.getMinutes()+_timezone)%24));
		secondsOutput.setText(String.format("%02d", (singeltonClock.getSeconds()+_timezone)%24));
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		singeltonClock = Clock.getInstance();
		_timezone = singeltonClock.get_timezone();
		singeltonClock.addWindow(this);
		timezoneOutput.setText(Integer.toString(_timezone));
		
		
	}
	
	
	
}
