package commands;

import main.EditorController;
import objects.MyRectangle;
import objects.ObjectList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintRectangle implements ICommand {
	
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld,_white = new Color(1, 1, 1, 1);
	MyRectangle rectangle;
	Pane _pane;

	public ComPaintRectangle(Pane pane, Paint color, Paint colorOld) {
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
				EditorController.actualPressEventHandler =  new EventHandler<MouseEvent>(){
		 
		            @Override
		            public void handle(MouseEvent event) {
		            	//Rectangle rectangle = new Rectangle();
		            	rectangle = (MyRectangle) ObjectList.createShape("Rectangle");//klone ein rechteck!
		            	rectangle.setX(event.getX());
		            	rectangle.setY(event.getY());
		            	rectangle.setyCoord(event.getY());
		            	rectangle.setxCoord(event.getX());
		                //_gc.beginPath();
		                //_gc.moveTo(event.getX(), event.getY());

		            	_gc.fillRect(rectangle.getX(), rectangle.getY(),
		                		event.getX() - rectangle.getX()
		                		,event.getY() - rectangle.getY());

		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);

				EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	if(rectangle.getyCoord() < event.getY()){
		            		rectangle.setyCoord(event.getY());
		            	}
		            	if(rectangle.getxCoord() < event.getX()){
		            		rectangle.setxCoord(event.getX());
		            	}
		            	
		            	
						_gc.setFill(_white);
		            	_gc.fillRect(rectangle.getX(), rectangle.getY(),
		            			rectangle.getxCoord() - rectangle.getX()
		                		,rectangle.getyCoord() - rectangle.getY());
		            	_gc.setFill(_color);
		            	_gc.fillRect(rectangle.getX(), rectangle.getY(),
		                		event.getX() - rectangle.getX()
		                		,event.getY() - rectangle.getY());
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,EditorController.actualDragEventHandler);
		        
		        EditorController.actualReleaseEventHandler =
		                new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//on Click markier es
		            	rectangle.setWidth(event.getX() - rectangle.getX());
		            	rectangle.setHeight(event.getY() - rectangle.getY());
		            	rectangle.setFill(_color);
	            		rectangle.draw(_pane);//draw defined rectangle and add it as child to pane
		            	rectangle.setHandler();
	            		_gc.clearRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getWidth());
		            	ObjectList.add(rectangle);
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,EditorController.actualReleaseEventHandler);
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
