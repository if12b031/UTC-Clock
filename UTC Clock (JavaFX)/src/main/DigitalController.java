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
	
	@Override
	public void update() {
		
		hoursOutput.setText(String.format("%02d", singeltonClock.getHours()));
		minutesOutput.setText(String.format("%02d", singeltonClock.getMinutes()));
		secondsOutput.setText(String.format("%02d", singeltonClock.getSeconds()));
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		singeltonClock = Clock.getInstance();
		singeltonClock.addWindow(this);
		timezoneOutput.setText(Integer.toString(singeltonClock.get_timezone()));
		
		
	}
	
	
	
}
