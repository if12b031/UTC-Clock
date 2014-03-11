package main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import interfaces.IClockWindow;

public class ClockWindow implements IClockWindow {
	
	@FXML private Text hoursOutput;
	@FXML private Text minutesOutput;//alle fxml sind nicht mit dieser java datei verknüpft somit kann ich keine werte ändern!
	@FXML private Text secondsOutput;
	@FXML private Text timezoneOutput;
	private String _display;
	private int _timezone, _x, _y;

	public ClockWindow(String display, int timezone, int x, int y) {
		
		_display = display;
		_timezone = timezone;
		_x = x;
		_y = y;
	}

	@Override
	public void show() {
		Parent root;
        try {
        	if("analog".equals(_display) == true){
        		root = FXMLLoader.load(getClass().getResource("../fxml/analogClock.fxml"));
        	}else{
        		root = FXMLLoader.load(getClass().getResource("../fxml/digitalClock.fxml"));
        	}
        	System.out.println(Integer.toString(_timezone));
        	//timezoneOutput.setText(intToString(_timezone));
        	
            Stage stage = new Stage();
            stage.setX(_x);
            stage.setY(_y);
            stage.setTitle("Clock");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}

	private String intToString(int i) {
		
		String string = Integer.toString(i);;

		if(i >= 0){
			return "+"+ string;
		}else{
			return "-"+ string;
		}
	}
}
