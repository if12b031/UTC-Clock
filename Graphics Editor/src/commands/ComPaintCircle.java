package commands;

import main.EditorController;
import objects.MyCircle;
import objects.ObjectList;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintCircle implements ICommand {
	
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld;
	MyCircle circle;
	double max,min,w,h;

	public ComPaintCircle(Canvas canvas, Paint color, Paint colorOld) {
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
		            	//Rectangle circle = new Rectangle();
		            	circle = (MyCircle) ObjectList.createShape("Circle");//klone ein quadrat!
		            	circle.setCenterX(event.getX());
		            	circle.setCenterY(event.getY());
		                //_gc.beginPath();
		                //_gc.moveTo(event.getCenterX(), event.getCenterY());
		            	//x und y koordinate linke obere ecke , width,height
		            	w = event.getX() - circle.getCenterX();
		            	h = event.getY() - circle.getCenterY();
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
		            	_gc.fillOval(circle.getCenterX(), circle.getCenterY(),
		                		w
		                		,h);
		            }
		        };
				
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);
		        
		        EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//System.out.println(Math.abs(event.getCenterX() - circle.getCenterX()));
		            	w = event.getX() - circle.getCenterX();
		            	h = event.getY() - circle.getCenterY();
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
		            	_gc.fillOval(circle.getCenterX(), circle.getCenterY(),
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
		            	ObjectList.add(circle);
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,EditorController.actualReleaseEventHandler);
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
