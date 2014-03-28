package commands;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import interfaces.ICommand;

public class ComHelp implements ICommand {
	
	private FXMLLoader fxmlLoader;
	private Stage stage;

	@Override
	public void execute() {
		
		Parent root;
		URL location;
        try {
        	location = getClass().getResource("../fxml/help.fxml");
        	//root = (GridPane)FXMLLoader.load(getClass().getResource("../fxml/digitalClock.fxml"));
        	fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(location);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			root = (Parent) fxmlLoader.load(location.openStream());
        	        	
            stage = new Stage();
            stage.setTitle("Help");
            stage.setScene(new Scene(root, 1050, 650));
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
