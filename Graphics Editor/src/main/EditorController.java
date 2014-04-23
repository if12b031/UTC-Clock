package main;

import interfaces.ICommand;
import interfaces.IMediator;
import interfaces.IShape;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Stack;

import commands.ComChangeLineWidth;
import commands.ComGroupShapes;
import commands.ComPaintCircle;
import commands.ComPaintElipse;
import commands.ComPaintRectangle;
import commands.ComPaintSquare;
import commands.ComPaintTriangle;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class EditorController implements Initializable,IMediator {
	
	@FXML private GridPane grid;
	@FXML private Slider slider;
	@FXML private ColorPicker colorPicker;
	@FXML private ImageView rectangle;
	@FXML private ImageView square;
	@FXML private ImageView triangle;
	@FXML private ImageView elipse;
	@FXML private ImageView circle;
	
	Image _rectangleShiny = new Image("assets/rechteck_marked.png",true);
	Image _rectangle = new Image("assets/rechteck.png",true);
	Image _squareShiny = new Image("assets/viereck_marked.png",true);
	Image _square = new Image("assets/viereck.png",true);
	Image _elipseShiny = new Image("assets/elipse_marked.png",true);
	Image _elipse = new Image("assets/elipse.png",true);
	Image _triangleShiny = new Image("assets/dreieck_marked.png",true);
	Image _triangle = new Image("assets/dreieck.png",true);
	Image _circleShiny = new Image("assets/kreis_marked.png",true);
	Image _circle = new Image("assets/kreis.png",true);
	
	public static EventHandler<MouseEvent> actualDragEventHandler,actualPressEventHandler,actualReleaseEventHandler;
	
	private GraphicsContext gc;
	private Canvas canvas;
	private Pane pane;
	
	private Stack<ICommand> history = new Stack<ICommand>();
	private Stack<ICommand> undoHistory = new Stack<ICommand>();
	private List<IShape> shapesToGroup = new ArrayList<IShape>();
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Canvas canvas = new Canvas(400, 300);
		Pane pane = new Pane();//füge dem gridpane diese pane hinzu und adde das canvas element in dieses pane
		final GraphicsContext gc = canvas.getGraphicsContext2D();
		initDraw(gc);
		setHandlers(canvas); 
        colorPicker.setValue(Color.BLACK);
        this.gc = gc;
        this.canvas = canvas;
        
       
        
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
	            	ComChangeLineWidth cmd = new ComChangeLineWidth(gc, new_val.doubleValue(), old_val.doubleValue());
	    			storeAndExecute(cmd);
	    			//gc = cmd.get_gc();     geht nicht weil-> final variable  
	    			gc.setLineWidth(new_val.doubleValue());
            }
        });
        
       pane.getChildren().add(canvas);
       System.out.println("canvas id im pane.getChildren = "+pane.getChildren().indexOf(canvas));
       this.pane = pane;
        grid.add(pane, 1, 1);
	}
	
	private void setHandlers(Canvas canvas) {
		
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                gc.beginPath();
                gc.moveTo(event.getX(), event.getY());
                gc.stroke();
            }
        });

        actualDragEventHandler = new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        };
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,actualDragEventHandler);
 
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
 
            }
        });
		
	}
	
	private void stopHandlers() {

		try{
			canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED, EditorController.actualDragEventHandler);
			canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, EditorController.actualPressEventHandler);
			canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, EditorController.actualReleaseEventHandler);
			
		}catch(NullPointerException e){
			System.out.println("No Press- Drag- or Release-Event-Handler set!");
		}
		
	}

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
	
	/*private void changeLineWidth(){
		ComChangeLineWidth cmd = new ComChangeLineWidth(gc, slider.getValue(), old_val.doubleValue());
		storeAndExecute(cmd);
		gc = cmd.get_gc();
	}*/
	
	//COMMANDS
	
	@FXML private void setLineColor(){
		Paint color = colorPicker.getValue();
		//System.out.println(gc.getStroke());
		ComSetLineColor cmd = new ComSetLineColor(gc,color,gc.getStroke());
		storeAndExecute(cmd);      
	}
	
	//SHAPE-ICONS
	
	@FXML private void setRectangle(){
		Paint color = colorPicker.getValue();
		if(rectangle.getImage().equals(_rectangleShiny) == true){
			rectangle.setImage(_rectangle);
			stopPaintShape();
		}else{
			paintRectangle();//tells mediator that a shape is going to be paint
			ComPaintRectangle cmd = new ComPaintRectangle(pane,color,gc.getStroke());
			storeAndExecute(cmd); 
			rectangle.setImage(_rectangleShiny);
		}
		
	}
	@FXML private void setSquare(){
		Paint color = colorPicker.getValue();
		if(square.getImage().equals(_squareShiny) == true){
			square.setImage(_square);
			stopPaintShape();
		}else{
			paintSquare();//tells mediator that a shape is going to be paint
			ComPaintSquare cmd = new ComPaintSquare(pane,color,gc.getStroke());
			storeAndExecute(cmd); 
			square.setImage(_squareShiny);
		}
		
	}
	@FXML private void setElipse(){
		Paint color = colorPicker.getValue();
		if(elipse.getImage().equals(_elipseShiny) == true){
			elipse.setImage(_elipse);
			stopPaintShape();
		}else{
			paintElipse();//tells mediator that a shape is going to be paint
			ComPaintElipse cmd = new ComPaintElipse(canvas,color,gc.getStroke());
			storeAndExecute(cmd); 
			elipse.setImage(_elipseShiny);
		}
		
	}
	@FXML private void setCircle(){
		Paint color = colorPicker.getValue();
		if(circle.getImage().equals(_circleShiny) == true){
			circle.setImage(_circle);
			stopPaintShape();
		}else{
			paintCircle();//tells mediator that a shape is going to be paint
			ComPaintCircle cmd = new ComPaintCircle(canvas,color,gc.getStroke());
			storeAndExecute(cmd); 
			circle.setImage(_circleShiny);
		}
		
	}
	@FXML private void setTriangle(){
		Paint color = colorPicker.getValue();
		if(triangle.getImage().equals(_triangleShiny) == true){
			triangle.setImage(_triangle);
			stopPaintShape();
		}else{
			paintTriangle();//tells mediator that a shape is going to be paint
			ComPaintTriangle cmd = new ComPaintTriangle(pane,color,gc.getStroke());
			storeAndExecute(cmd); 
			triangle.setImage(_triangleShiny);
		}
		
	}
	
	@FXML
	private void group(ActionEvent event){
		
		ICommand groupCommand = new ComGroupShapes(shapesToGroup);
		storeAndExecute(groupCommand);
		System.out.println("GROUP-Button pressed");
	}
	
	
	
	@FXML
	private void undo(ActionEvent event){
		
		try{ICommand undoCommand = history.lastElement();
		undoCommand.undo();
		undoHistory.push(undoCommand);
		history.pop();
		System.out.println("UNDO-Button pressed");
		}catch(NoSuchElementException e){
			System.out.println("There was no last Command executed!");
		}
    }
	
	public void storeAndExecute(ICommand cmd) {//speichert die commands in eine history
        this.history.push(cmd);
        cmd.execute();      
     }

	@Override
	public void paintSquare() {
		slider.disableProperty().setValue(true);
		rectangle.disableProperty().setValue(true);
		elipse.disableProperty().setValue(true);
		circle.disableProperty().setValue(true);
		triangle.disableProperty().setValue(true);
		stopHandlers();
		//all buttons disable that are not used if i type on some of the shape icons
		
	}

	@Override
	public void paintRectangle() {
		slider.disableProperty().setValue(true);
		elipse.disableProperty().setValue(true);
		circle.disableProperty().setValue(true);
		triangle.disableProperty().setValue(true);
		square.disableProperty().setValue(true);
		stopHandlers();
	}

	@Override
	public void paintTriangle() {
		slider.disableProperty().setValue(true);
		rectangle.disableProperty().setValue(true);
		elipse.disableProperty().setValue(true);
		circle.disableProperty().setValue(true);
		square.disableProperty().setValue(true);
		stopHandlers();
	}

	@Override
	public void paintElipse() {
		slider.disableProperty().setValue(true);
		rectangle.disableProperty().setValue(true);
		circle.disableProperty().setValue(true);
		triangle.disableProperty().setValue(true);
		square.disableProperty().setValue(true);
		stopHandlers();
	}

	@Override
	public void paintCircle() {
		slider.disableProperty().setValue(true);
		rectangle.disableProperty().setValue(true);
		elipse.disableProperty().setValue(true);
		triangle.disableProperty().setValue(true);
		square.disableProperty().setValue(true);
		stopHandlers();
		
	}

	@Override
	public void stopPaintShape() {
		slider.disableProperty().setValue(false);
		rectangle.disableProperty().setValue(false);
		square.disableProperty().setValue(false);
		elipse.disableProperty().setValue(false);
		circle.disableProperty().setValue(false);
		triangle.disableProperty().setValue(false);
		stopHandlers();
		setHandlers(canvas);
	}
	
}
