package commands;

import main.EditorController;
import objects.ObjectList;
import objects.MySquare;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintSquare implements ICommand {
	
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld,_white = new Color(1, 1, 1, 1);
	MySquare square;
	Pane _pane;
	double max,min,w,h;

	public ComPaintSquare(Pane pane, Paint color, Paint colorOld) {
		_canvas = (Canvas) pane.getChildren().get(0);
		_color = color;
		_colorOld = colorOld;
		_gc = _canvas.getGraphicsContext2D();
		_pane = pane;
	}

	@Override
	public void execute() {
		_gc.setStroke(_color);
		setUpEventHandler();
	}

	

	@Override
	public void undo() {
		_gc.setStroke(_colorOld);
		removeEventHandler();
	}
	
	
	//EVENTHANDLER-Management
	
	
	private void removeEventHandler() {
		
		_canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                _gc.beginPath();
                _gc.moveTo(event.getX(), event.getY());
                _gc.stroke();
            }
        });
         
        _canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
                _gc.lineTo(event.getX(), event.getY());
                _gc.stroke();
            }
        });
 
        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
 
            }
        });
	}

	private void setUpEventHandler(){
		//_canvas.removeEventHandler(eventType, eventHandler);
		EditorController.actualPressEventHandler =
		                new EventHandler<MouseEvent>(){
		 
		            @Override
		            public void handle(MouseEvent event) {
		            	//Rectangle square = new Rectangle();
		            	square = (MySquare) ObjectList.createShape("Square");//klone ein quadrat!
		            	square.setX(event.getX());
		            	square.setY(event.getY());
		            	square.setyCoord(event.getY());//for remember the biggest x or y coordinate to delete
		            	square.setxCoord(event.getX());
		                //_gc.beginPath();
		                //_gc.moveTo(event.getX(), event.getY());
		            	//x und y koordinate linke obere ecke , width,height
		            	w = event.getX() - square.getX();
		            	h = event.getY() - square.getY();
		            	max = Math.max(Math.abs(w),Math.abs(h));
		            	if(max == Math.abs(w)){
		            		if(h < 0){
		            			h = -Math.abs(w);
		            		}else{
		            			h = Math.abs(w);
		            		}
		            	}else{
		            		if(w < 0){
		            			w = -Math.abs(h);
		            		}else{
		            			w = Math.abs(h);
		            		}
		            		
		            	}
		            	_gc.fillRect(square.getX(), square.getY(),
		                		w
		                		,h);
		            }
		        };
				
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);
			
				EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//System.out.println(Math.abs(event.getX() - square.getX()));
		            	if(square.getyCoord() < event.getY()){
		            		square.setyCoord(event.getY());
		            		
		            	}
		            	if(square.getxCoord() < event.getX()){
		            		square.setxCoord(event.getX());
		            	}
		            	w = event.getX() - square.getX();
		            	h = event.getY() - square.getY();
		            	max = Math.max(Math.abs(w),Math.abs(h));
		            	if(max == Math.abs(w)){
		            		if(h < 0){
		            			h = -Math.abs(w);
		            		}else{
		            			h = Math.abs(w);
		            		}
		            	}else{
		            		if(w < 0){
		            			w = -Math.abs(h);
		            		}else{
		            			w = Math.abs(h);
		            		}
		            		
		            	}
		            	_gc.setFill(_white);
		            	_gc.fillRect(square.getX(), square.getY(),
		            			square.getxCoord() - square.getX()
		                		,square.getyCoord() - square.getY());
		            	_gc.setFill(_color);
		            	_gc.fillRect(square.getX(), square.getY(),
		                		w
		                		,h);
		            	_gc.stroke();
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,EditorController.actualDragEventHandler);
		        
		        EditorController.actualReleaseEventHandler = 
		                new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//on Click markier es
		            	square.setWidth(w);
		            	square.setHeight(h);
		            	square.setFill(_color);
	            		square.draw(_pane);//draw defined square and add it as child to pane
		            	square.setHandler();
	            		_gc.clearRect(square.getX(), square.getY(), square.getWidth(), square.getWidth());
		            	ObjectList.add(square);
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,EditorController.actualReleaseEventHandler);
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
