package commands;

import main.EditorController;
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
	Rectangle rectangle = new Rectangle();

	public ComPaintRectangle(Canvas canvas, Paint color, Paint colorOld) {
		_canvas = canvas;
		_color = color;
		_colorOld = colorOld;
		_gc = canvas.getGraphicsContext2D();
	}

	@Override
	public void execute() {
		_gc.setStroke(_color);
		//_canvas.removeEventHandler(eventType, eventHandler);
		_canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
            	System.out.println("Mouse pressed");
            	//Rectangle rectangle = new Rectangle();
            	rectangle.setxCoord(event.getX());
            	rectangle.setyCoord(event.getY());
                //_gc.beginPath();
                //_gc.moveTo(event.getX(), event.getY());
            	_gc.rect(rectangle.getxCoord(), rectangle.getyCoord(),
                		event.getX() - rectangle.getxCoord()
                		,event.getY() - rectangle.getyCoord());
            }
        });
		
		_canvas.removeEventHandler(MouseEvent.MOUSE_DRAGGED, EditorController.actualDragEventHandler);
         
        
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
        
        
 
        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>(){
 
            @Override
            public void handle(MouseEvent event) {
            	System.out.println("Mouse released");
 
            }
        });
	}

	

	@Override
	public void undo() {
		_gc.setStroke(_colorOld);
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
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
