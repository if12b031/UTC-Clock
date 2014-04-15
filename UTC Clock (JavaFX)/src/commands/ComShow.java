package commands;

import java.io.IOException;
import java.net.URL;

import main.Clock;
import main.DigitalController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import interfaces.ICommand;

public class ComShow implements ICommand {
	
	private String _display, _timezoneText;
	private int _x,_y,_timezone;
	private FXMLLoader fxmlLoader;
	private Stage stage;
	
	public ComShow(String display,String timezoneText, int timezone, int x, int y){
		_display = display;
		_timezone = timezone;
		_timezoneText = timezoneText;
		_x = x;
		_y = y;
	}

	@Override
	public void execute() {
		
		Parent root;
		URL location;
        try {
        	location = getClass().getResource("../fxml/digitalClock.fxml");
        	//root = (GridPane)FXMLLoader.load(getClass().getResource("../fxml/digitalClock.fxml"));
        	fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(location);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			root = (Parent) fxmlLoader.load(location.openStream());
        	        	
            stage = new Stage();
            System.out.println("x = " + _x +" "+ "y = "+ _y);
            stage.setX(_x);
            stage.setY(_y);
            stage.setTitle("Clock");
            if("Uhr(12h)".equals(_display)){
            	stage.setScene(new Scene(root, 550, 450));
            }else{
            	stage.setScene(new Scene(root, 450, 450));
            }
            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){

				@Override
				public void handle(WindowEvent arg0) {
					
				Clock.getInstance().removeWindow((DigitalController) fxmlLoader.getController());
					
					
				}
            	
            });
            DigitalController dc = fxmlLoader.getController();
            dc.set_timezone(_timezone);
            dc.set_timezoneText(_timezoneText);
            dc.set_display(_display);
            dc.check_display();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void undo() {
		
		stage.hide();
		
	}	
	
}
