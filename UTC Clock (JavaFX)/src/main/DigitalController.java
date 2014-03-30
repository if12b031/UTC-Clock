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
	@FXML private Text appendixOutput;
	@FXML private Text title;
	private static Clock singeltonClock;
	private int _timezone;
	private String _timezoneText;
	String _display;
	String _am_pm;
	
	@Override
	public void update() {
		
		if("Uhr(24h)".equals(this._display)){
			hoursOutput.setText(String.format("%02d", (singeltonClock.getHours()+_timezone)%24));
			minutesOutput.setText(String.format("%02d", (singeltonClock.getMinutes())%60));
			secondsOutput.setText(String.format("%02d", (singeltonClock.getSeconds())%60));	
		}else{
			hoursOutput.setText(String.format("%02d", (singeltonClock.getHours()+_timezone)%12));
			minutesOutput.setText(String.format("%02d", (singeltonClock.getMinutes())%60));
			secondsOutput.setText(String.format("%02d", (singeltonClock.getSeconds())%60));	
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		singeltonClock = Clock.getInstance();
		singeltonClock.addWindow(this);		
		
	}
	
	public void set_timezone(int _timezone) {
		this._timezone = _timezone;
	}
	
	public void set_display(String _display) {
		this._display = _display;
	}

	public void check_display() {
		
		if("Uhr(12h)".equals(this._display)){
			title.setText("12h-Clock");
			if(((singeltonClock.getHours()+this._timezone)%24) < 13){
				_am_pm = "am";
			}else{
				_am_pm = "pm";
			}			
			appendixOutput.setText(" " + _am_pm);
		}else{
			title.setText("24h-Clock");
		}
		timezoneOutput.setText((Integer.toString(_timezone) 
				+ " (" 
				+ _timezoneText) 
				+ ")");		
	}

	public String get_timezoneText() {
		return _timezoneText;
	}

	public void set_timezoneText(String _timezoneText) {
		this._timezoneText = _timezoneText;
	}
	
	
}
