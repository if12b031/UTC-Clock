package commands;

import main.EditorController;
import objects.ObjectList;
import objects.Triangle;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintTriangle implements ICommand {
	
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld;
	Triangle triangle;

	public ComPaintTriangle(Canvas canvas, Paint color, Paint colorOld) {
		_canvas = canvas;
		_color = color;
		_colorOld = colorOld;
		_gc = canvas.getGraphicsContext2D();
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
		            	//Rectangle triangle = new Rectangle();
		            	triangle = (Triangle) ObjectList.createShape("Triangle");//klone ein rechteck!
		            	triangle.setxCoord(event.getX());
		            	triangle.setyCoord(event.getY());
		                //_gc.beginPath();
		                //_gc.moveTo(event.getX(), event.getY());
		            	_gc.rect(triangle.getxCoord(), triangle.getyCoord(),
		                		event.getX() - triangle.getxCoord()
		                		,event.getY() - triangle.getyCoord());
		            	//_gc.fillPolygon(xPoints, yPoints, 3); easy get the 3 points xD
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);

				EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	_gc.rect(triangle.getxCoord(), triangle.getyCoord(),
		                		event.getX() - triangle.getxCoord()
		                		,event.getY() - triangle.getyCoord());
		            	_gc.stroke();
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,EditorController.actualDragEventHandler);
		        
		        EditorController.actualReleaseEventHandler =
		                new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//on Click markier es
		            	ObjectList.add(triangle);
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,EditorController.actualReleaseEventHandler);
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
