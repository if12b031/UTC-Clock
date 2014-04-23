package commands;


import main.EditorController;
import objects.ObjectList;
import objects.MyTriangle;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComPaintTriangle implements ICommand {
	
	Canvas _canvas;
	GraphicsContext _gc;
	Paint _color,_colorOld,_white = new Color(1, 1, 1, 1);
	MyTriangle triangle;
	Pane _pane;
	double[] xPoints;
	double[] yPoints;

	public ComPaintTriangle(Pane pane, Paint color, Paint colorOld) {
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
		            	System.out.println("mouse pressed");
		            	triangle = (MyTriangle) ObjectList.createShape("Triangle");//klone ein dreieck!
		            	triangle.setyCoord(event.getY());
		            	triangle.setxCoord(event.getX());
		            	triangle.setX(event.getY());
		            	triangle.setY(event.getX());
		            	
		                xPoints = new double[] {((event.getX()- triangle.getxCoord())/2)+triangle.getxCoord(), triangle.getxCoord(), event.getX()};
		                yPoints = new double[] {triangle.getyCoord(),event.getY(),event.getY()};
		               
		            	_gc.fillPolygon(xPoints, yPoints, 3);
		            
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,EditorController.actualPressEventHandler);

				EditorController.actualDragEventHandler =   new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	_gc.setFill(_white);
		            	xPoints[1] -= 2;
		            	xPoints[2] += 2;//just to delete the painted triangle on the canvas
		            	xPoints[0] -= 1;
		            	yPoints[0] -= 2;
		            	_gc.fillPolygon(xPoints, yPoints, 3);
		            	_gc.setFill(_color);
		                xPoints = new double[] {((event.getX()- triangle.getxCoord())/2)+triangle.getxCoord(), triangle.getxCoord(), event.getX()};
		                yPoints = new double[] {triangle.getyCoord(),event.getY(),event.getY()};
		               
		            	_gc.fillPolygon(xPoints, yPoints, 3);
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,EditorController.actualDragEventHandler);
		        
		        EditorController.actualReleaseEventHandler =
		                new EventHandler<MouseEvent>(){
		            @Override
		            public void handle(MouseEvent event) {
		            	//on Click markier es
		            	triangle.getPoints().addAll(new Double[]{
		            		    ((event.getX()- triangle.getxCoord())/2)+triangle.getxCoord(),//x
		            		    triangle.getyCoord(),//y
		            		    triangle.getxCoord(),//x
		            		    event.getY(),//y
		            		    event.getX(),//x
		            		    event.getY()//y 
		            		    });
		            	_gc.setFill(_white);
		            	xPoints[1] -= 2;
		            	xPoints[2] += 2;//just to delete the painted triangle on the canvas
		            	xPoints[0] -= 1;
		            	yPoints[0] -= 2;
		            	_gc.fillPolygon(xPoints, yPoints, 3);
		            	ObjectList.add(triangle);
		            	triangle.setFill(_color);
		            	triangle.draw(_pane);//draw defined rectangle and add it as child to pane		        
		            	triangle.setHandler();
	            		
		            }
		        };
		        _canvas.addEventHandler(MouseEvent.MOUSE_RELEASED,EditorController.actualReleaseEventHandler);
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
