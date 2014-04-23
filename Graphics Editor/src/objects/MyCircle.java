package objects;

import interfaces.Drawable;
import interfaces.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class MyCircle extends Circle implements IShape,Drawable,Cloneable {	
	
	public MyCircle(){
		
	}

	@Override
	public void draw(Pane pane) {
	    System.out.println("Inside Circle::draw() method.");
	}
	
	@Override
	public Object klone() {
	      Object clone = null;
	      try {
	         clone = super.clone();
	      } catch (CloneNotSupportedException e) {
	         e.printStackTrace();
	      }
	      return clone;
	   }

	@Override
	public void setHandler() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
		 
		            @Override
		            public void handle(MouseEvent event) {
		            	if(event.getButton().toString().equals("SECONDARY")){
		            		
		            	}else{
			            	if(MyCircle.this.isFocused()){
			            		MyCircle.this.setStrokeWidth(0);
			            		MyCircle.this.setFocused(false);
			            	}else{
			            		MyCircle.this.setStroke(new Color(1, 0, 0, 1));
			            		MyCircle.this.setStrokeWidth(2);
			            		MyCircle.this.setFocused(true);
			            	}
		            	}
		            	
		            	System.out.println("Rectangle OnClick");
		            	
		            }
		        });
		
		this.setOnMouseDragged(new EventHandler<MouseEvent>(){
		 
		            @Override
		            public void handle(MouseEvent event) {	
		            	if(event.getButton().toString().equals("SECONDARY")){
		            		System.out.println("rechte maustaste clicked");
		            		int newRadius = (int) ((event.getX() - MyCircle.this.getCenterX() + event.getY() - MyCircle.this.getCenterY())/2);
		            		MyCircle.this.setRadius(newRadius);
		            		
		            		System.out.println("new Radius: "+newRadius);
		            		
		            	}else{
		            		MyCircle.this.setCenterX(event.getX());
			            	MyCircle.this.setCenterY(event.getY());
		            	}
		            }
		        });
	}
	

}
