package commands;

import main.EditorController;
import objects.ObjectList;
import objects.Square;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintSquare implements ICommand {
	
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld;
	Square square;
	double max,min,w,h;

	public ComPaintSquare(Canvas canvas, Paint color, Paint colorOld) {
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
		            	//Rectangle square = new Rectangle();
		            	square = (Square) ObjectList.createShape("Square");//klone ein quadrat!
		            	square.setxCoord(event.getX());
		            	square.setyCoord(event.getY());
		                //_gc.beginPath();
		                //_gc.moveTo(event.getX(), event.getY());
		            	//x und y koordinate linke obere ecke , width,height
		            	w = event.getX() - square.getxCoord();
		            	h = event.getY() - square.getyCoord();
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
		            	_gc.rect(square.getxCoord(), square.getyCoord(),
		                		w
		                		,h);
		            }
		        };
				
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);
			
				EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//System.out.println(Math.abs(event.getX() - square.getxCoord()));
		            	w = event.getX() - square.getxCoord();
		            	h = event.getY() - square.getyCoord();
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
		            	_gc.rect(square.getxCoord(), square.getyCoord(),
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
