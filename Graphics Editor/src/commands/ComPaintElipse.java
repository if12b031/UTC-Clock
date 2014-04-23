package commands;

import main.EditorController;
import objects.MyEllipse;
import objects.ObjectList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintElipse implements ICommand {
	
	Pane _pane;
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld;
	MyEllipse elipse;

	public ComPaintElipse(Pane pane, Canvas canvas, Paint color, Paint colorOld) {
		_pane = pane;
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
		removeEventHandler();
		_pane.getChildren().remove(elipse);
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
		            	//Rectangle elipse = new Rectangle();
		            	elipse = (MyEllipse) ObjectList.createShape("Elipse");//klone eine Elipse!
		            	elipse.setCenterX(event.getX());
		            	elipse.setCenterY(event.getY());
		                //_gc.beginPath();
		                //_gc.moveTo(event.getX(), event.getY());
		            	_gc.strokeOval(elipse.getCenterX(), elipse.getCenterY(),
		                		event.getX() - elipse.getCenterX()
		                		,event.getY() - elipse.getCenterY());
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);

				EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	_gc.strokeOval(elipse.getCenterX(), elipse.getCenterY(),
		                		event.getX() - elipse.getCenterX()
		                		,event.getY() - elipse.getCenterY());
		            	_gc.stroke();
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,EditorController.actualDragEventHandler);
		        
		        EditorController.actualReleaseEventHandler = 
		                new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//on Click markier es
		            	ObjectList.add(elipse);
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,EditorController.actualReleaseEventHandler);
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
