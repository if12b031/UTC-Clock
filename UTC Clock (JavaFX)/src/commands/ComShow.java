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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import interfaces.ICommand;

public class ComShow implements ICommand {
	
	private String _display;
	private int _x,_y;
	private static Clock singeltonClock;
	private FXMLLoader fxmlLoader;
	
	public ComShow(String display, int x, int y){
		_display = display;
		_x = x;
		_y = y;
	}

	@Override
	public void execute() {
		
		Parent root;
		URL location;
        try {
        	if("analog".equals(_display) == true){
        		location = getClass().getResource("../fxml/analogClock.fxml");
        		//root = (GridPane)FXMLLoader.load(getClass().getResource("../fxml/analogClock.fxml"));
        	}else{
        		location = getClass().getResource("../fxml/digitalClock.fxml");
        		//root = (GridPane)FXMLLoader.load(getClass().getResource("../fxml/digitalClock.fxml"));
        	}
        	fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(location);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			root = (Parent) fxmlLoader.load(location.openStream());
        	        	
            Stage stage = new Stage();
            stage.setX(_x);
            stage.setY(_y);
            stage.setTitle("Clock");
            stage.setScene(new Scene(root, 450, 450));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>(){

				@Override
				public void handle(WindowEvent arg0) {
					
				Clock.getInstance().removeWindow((DigitalController) fxmlLoader.getController());;
					
					
				}
            	
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}	
	
}
