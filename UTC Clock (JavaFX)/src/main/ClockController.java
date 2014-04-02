package main;

import interfaces.ICommand;

import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import commands.ComDec;
import commands.ComHelp;
import commands.ComInc;
import commands.ComSet;
import commands.ComShow;

 
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
	private Stack<ICommand> history = new Stack<ICommand>();
	private Stack<ICommand> undoHistory = new Stack<ICommand>();
	
	
	//f¸r jedes command muss diese funktion aufgerufen werden
	public void storeAndExecute(ICommand cmd) {//speichert die commands in eine history
        this.history.push(cmd);
        cmd.execute();      
     }
	
	@FXML
    private void openNewWindow(ActionEvent event)
    {
        System.out.println("Button \"SHOW\" pressed");
        
        int x,y, timezone;
        String display;
        
        try {
            x = Integer.parseInt(xCoord.getText());
            y = Integer.parseInt(yCoord.getText());            
            
            String[] splitTimezone = ((String) timezoneChoice.getValue()).split("\\(", 2);
            String TimezoneNumber = splitTimezone[0].replace(" ", "");
            String TimezoneText = splitTimezone[1].replace(")", "");
       
        if("UTC".equals(TimezoneText) == true){
        	timezone = 0;
        }else{
        	timezone = Integer.parseInt(TimezoneNumber);
        }
        display = (String) displayChoice.getValue();        
        
        if("Uhr(24h)".equals(display) == true || "Uhr(12h)".equals(display) == true){//just to be sure...
        	
        	
        	ICommand showClock = new ComShow(display, TimezoneText, timezone,x, y);
        	storeAndExecute(showClock);
        	System.out.println("Display: "+display+" Timezone: "+timezone+" x-Coordinate: "+x+" y-Coordinate: "+y);
        	return;
        	
        }else {
        	System.out.println("No value set at Display-ComboButton");
        }
        System.out.println("Display: "+display+" Timezone: "+timezone+" x-Coordinate: "+x+" y-Coordinate: "+y);
        
        }catch(NumberFormatException e){
        	System.out.println("Timezone, x or y Coordinate isn't a number!");
        	e.printStackTrace();
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
	        	ComSet cmd = new ComSet(h,m,s);
	        	storeAndExecute(cmd);
	        	return;
	        	
	        }else if(event.getSource().equals(incButton)){//INC-Button pressed
	        	System.out.println("INC-Button pressed");
	        	ComInc cmd = new ComInc(h,m,s);
	        	storeAndExecute(cmd);
	        	return;
	        	
	        }else if(event.getSource().equals(decButton)){//DEC-Button pressed
	        	System.out.println("DEC-Button pressed");
	        	ComDec cmd = new ComDec(h,m,s);
	        	storeAndExecute(cmd);
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
		ComHelp cmd = new ComHelp();
    	storeAndExecute(cmd);
    	return;
    }
	
	@FXML
	private void undo(ActionEvent event)
    {
		ICommand undoCommand = history.lastElement();
		undoCommand.undo();
		undoHistory.push(undoCommand);
		history.pop();
		System.out.println("UNDO-Button pressed");
    }
	
	@FXML
	private void redo(ActionEvent event)
    {
		ICommand redoCommand = undoHistory.lastElement();
		storeAndExecute(redoCommand);
		undoHistory.pop();
		System.out.println("REDO-Button pressed");
    }
	//MAKROS- check function after this Line !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@FXML
	private void makroAction1(ActionEvent event)
    {
		ICommand Command4 = new ComShow("Uhr(12h)", "Ghana, Iceland, Ireland", 0, 100, 100);//(display,timezone, x,  y)
		ICommand Command5 = new ComShow("Uhr(24h)", "Ghana, Iceland, Ireland", 0, 700, 100);
		storeAndExecute(Command4);
		storeAndExecute(Command5);
		System.out.println("Makro1-Button pressed");
    }
	
	@FXML
	private void makroAction2(ActionEvent event)
    {
		ICommand Command1 = new ComSet(10, 10, 10);
		ICommand Command2 = new ComShow("Uhr(12h)", "Ghana, Iceland, Ireland", 0, 100, 100);//(display,timezone, x,  y)
		ICommand Command3= new ComShow("Uhr(24h)", "Ghana, Iceland, Ireland", 0, 700, 100);
		storeAndExecute(Command1);
		storeAndExecute(Command2);
		storeAndExecute(Command3);
		
		System.out.println("Makro1-Button pressed");
    }
}