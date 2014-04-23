package commands;

import main.EditorController;
import objects.ObjectList;
import objects.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintRectangle implements ICommand {
	
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld;
	Rectangle rectangle;

	public ComPaintRectangle(Canvas canvas, Paint color) {
		_canvas = canvas;
		_color = color;
		_gc = canvas.getGraphicsContext2D();
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
		            	rectangle = (Rectangle) ObjectList.createShape("Rectangle");//klone ein rechteck!
		            	rectangle.setxCoord(event.getX());
		            	rectangle.setyCoord(event.getY());
		                //_gc.beginPath();
		                //_gc.moveTo(event.getX(), event.getY());
		            	_gc.rect(rectangle.getxCoord(), rectangle.getyCoord(),
		                		event.getX() - rectangle.getxCoord(),
		                		event.getY() - rectangle.getyCoord());
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);

				EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	_gc.rect(rectangle.getxCoord(), rectangle.getyCoord(),
		                		event.getX() - rectangle.getxCoord()
		                		,event.getY() - rectangle.getyCoord());
		            	_gc.stroke();
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,EditorController.actualDragEventHandler);
		        
		        EditorController.actualReleaseEventHandler =
		                new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//on Click markier es
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
