package main;

import commands.ComShow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

 
public class ClockController<T> {
	
	//diverse actionevents der button kommen hier rein oder in eine .js datei
	
	
	//mit @FXML stellt er die Verbindung zum fxml her 
	//und die variable MUSS genau so heiﬂen wie die fx:id im fxml-dokument
	@FXML 	private ChoiceBox<T> displayChoice;
	@FXML	private ChoiceBox<T> timezoneChoice;
	@FXML	private TextField hours;
	@FXML	private TextField minutes;
	@FXML	private TextField seconds;
	@FXML	private TextField xCoord;
	@FXML	private TextField yCoord;
	@FXML	private Button undoButton;
	@FXML	private Button redoButton;
	@FXML	private Button helpButton;
	@FXML	private Button setButton;
	@FXML	private Button incButton;
	@FXML	private Button decButton;
	
	
	
	//Excpectionhandling fehlt das w¸rd ich als n‰chstes machen !!!
	@FXML
    private void openNewWindow(ActionEvent event)
    {
        System.out.println("Button \"SHOW\" pressed");
        
        int x,y;
        String timezone,value;
        
        x = Integer.parseInt(xCoord.getText());
        y = Integer.parseInt(yCoord.getText());
        
        timezone = (String) timezoneChoice.getValue();
        value = (String) displayChoice.getValue();
        
        if("digital".equals(value) == true){//if selelcted digital in choicebox -> open digitalView
        	
        	//Comshow mit argument "digital" aufrufen
        	System.out.println("Display: "+value+" Timezone: "+timezone+" x-Coordinate: "+x+" y-Coordinate: "+y);
        	return;
        	
        }else if("analog".equals(value) == true){//if selelcted analog in choicebox -> open analogView
        	
        	//Comshow mit argument "analog" aufrufen
        	System.out.println("Display: "+value+" Timezone: "+timezone+" x-Coordinate: "+x+" y-Coordinate: "+y);
        	return;
        	
        }
        System.out.println("No value set at Display-ChoiceButton");
        System.out.println("Display: "+value+" Timezone: "+timezone+" x-Coordinate: "+x+" y-Coordinate: "+y);
        return;
    }
}