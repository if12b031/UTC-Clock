package main;

import interfaces.ICommand;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Stack;

import commands.ComChangeLineWidth;
import commands.ComSetLineColor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class EditorController implements Initializable {
	
	@FXML private GridPane grid;
	@FXML private Slider slider;
	@FXML private ColorPicker colorPicker;
	
	private GraphicsContext gc;
	
	private Stack<ICommand> history = new Stack<ICommand>();
	private Stack<ICommand> undoHistory = new Stack<ICommand>();
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Canvas canvas = new Canvas(400, 300);
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		initDraw(gc);
		
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                gc.beginPath();
                gc.moveTo(event.getX(), event.getY());
                gc.stroke();
            }
        });
         
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });
 
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
 
            }
        });
        
        colorPicker.setValue(Color.BLACK);
        this.gc = gc;
        
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
	            	ComChangeLineWidth cmd = new ComChangeLineWidth(gc, new_val.doubleValue(), old_val.doubleValue());
	    			storeAndExecute(cmd);
	    			//gc = cmd.get_gc();     geht nicht weil-> final variable  
	    			gc.setLineWidth(new_val.doubleValue());
            }
        });
        
       
        grid.add(canvas, 1, 1);
	}
	
	/*private void changeLineWidth(){
		ComChangeLineWidth cmd = new ComChangeLineWidth(gc, slider.getValue(), old_val.doubleValue());
		storeAndExecute(cmd);
		gc = cmd.get_gc();
	}*/
	
	
	private void initDraw(GraphicsContext gc){
        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();
         
        gc.setFill(Color.LIGHTGRAY);
        gc.setStroke(Color.BLACK);// für umrandung!
        gc.setLineWidth(5);
 
        gc.fill();
        gc.strokeRect(
                0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvasWidth,    //width of the rectangle
                canvasHeight);  //height of the rectangle
         
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
         
    }
	
	@FXML private void setLineColor(){
		Paint color = colorPicker.getValue();
		//System.out.println(gc.getStroke());
		ComSetLineColor cmd = new ComSetLineColor(gc,color,gc.getStroke());
		storeAndExecute(cmd);      
	}
	
	public void storeAndExecute(ICommand cmd) {//speichert die commands in eine history
        this.history.push(cmd);
        cmd.execute();      
     }
	
	@FXML
	private void undo(ActionEvent event)
    {
		try{ICommand undoCommand = history.lastElement();
		undoCommand.undo();
		undoHistory.push(undoCommand);
		history.pop();
		System.out.println("UNDO-Button pressed");
		}catch(NoSuchElementException e){
			System.out.println("There was no last Command executed!");
		}
    }
	
	
	
	
	
	
}
