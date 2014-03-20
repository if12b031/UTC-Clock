package main;

import interfaces.ICommand;

import java.util.ArrayList;
import java.util.List;

import commands.ComShow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.Clock;

 
public class ClockController<T> {
	
	//diverse actionevents der button kommen hier rein oder in eine .js datei
	
	
	//mit @FXML stellt er die Verbindung zum fxml her 
	//und die variable MUSS genau so heiﬂen wie die fx:id im fxml-dokument
	@FXML 	private ComboBox<T> displayChoice;
	@FXML	private ComboBox<T> timezoneChoice;
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
	private List<ICommand> history = new ArrayList<ICommand>();//2 stacks f¸r un und redo
	private static Clock singeltonClock;
	
	
	//f¸r jedes command muss diese funktion aufgerufen werden
	public void storeAndExecute(ICommand cmd) {//speichert die commands in eine history
        this.history.add(cmd);
        cmd.execute();      
     }
	
	@FXML
    private void openNewWindow(ActionEvent event)
    {
        System.out.println("Button \"SHOW\" pressed");
        
        int x,y, timezone;
        String display;
        
        try{
            x = Integer.parseInt(xCoord.getText());
            y = Integer.parseInt(yCoord.getText());
       
        if("UTC".equals((String) timezoneChoice.getValue()) == true){
        	timezone = 0;
        }else{
        	timezone = Integer.parseInt((String) timezoneChoice.getValue());
        }
        display = (String) displayChoice.getValue();
        singeltonClock = Clock.getInstance();
        singeltonClock.set_timezone(timezone);
        
        if("digital".equals(display) == true || "analog".equals(display) == true){//just to be sure...
        	
        	ICommand showClock = new ComShow(display, x, y);
        	storeAndExecute(showClock);
        	System.out.println("Display: "+display+" Timezone: "+timezone+" x-Coordinate: "+x+" y-Coordinate: "+y);
        	return;
        	
        }else {
        System.out.println("No value set at Display-ComboButton");
        }
        System.out.println("Display: "+display+" Timezone: "+timezone+" x-Coordinate: "+x+" y-Coordinate: "+y);
        
        }catch(NumberFormatException e){
        	System.out.println("Timezone, x or y Coordinate isn't a number!");
        }
        return;
    }
	
	@FXML
	private void changeTime(ActionEvent event)
    {
		
		int s, m, h;
		
		try{
			s = Integer.parseInt(seconds.getText());
	        m = Integer.parseInt(minutes.getText());
	        h = Integer.parseInt(hours.getText());
	        
			checkTime60(s);
			checkTime60(m);
			checkTime24(h);
	        
	        if(event.getSource().equals(setButton)){//SET-Button pressed
	        	System.out.println("SET-Button pressed");
	        	return;
	        	
	        }else if(event.getSource().equals(incButton)){//INC-Button pressed
	        	System.out.println("INC-Button pressed");
	        	return;
	        	
	        }else if(event.getSource().equals(decButton)){//DEC-Button pressed
	        	System.out.println("DEC-Button pressed");
	        	return;
	        	
	        }   
		}catch(NumberFormatException e){
			System.out.println("seconds, minutes, and hours have to be a valid number!(No Number)");
			return;
		}catch (NoValidTimeNumber e) {
			System.out.println("seconds, minutes, and hours have to be a valid number!(wrong Number)");
			return;
		}
    }
	
	private void checkTime24(int h) throws NoValidTimeNumber {
		
		if(h >= 0 && h < 24){
			return;
		}else{
			NoValidTimeNumber exc = new NoValidTimeNumber();
			throw exc;
		}
		
	}

	private void checkTime60(int t) throws NoValidTimeNumber {
		
		if(t >= 0 && t < 60){
			return;
		}else{
			NoValidTimeNumber exc = new NoValidTimeNumber();
			throw exc;
		}
		
	}

	@FXML
	private void help(ActionEvent event)
    {
		System.out.println("HELP-Button pressed window will open");
    }
	
	@FXML
	private void undo(ActionEvent event)
    {
		System.out.println("UNDO-Button pressed");
    }
	
	@FXML
	private void redo(ActionEvent event)
    {
		System.out.println("REDO-Button pressed");
    }
}