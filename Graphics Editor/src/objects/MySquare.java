package objects;

import interfaces.Drawable;
import interfaces.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MySquare extends Rectangle implements IShape,Drawable,Cloneable {
	
	double xCoord,yCoord;

	public MySquare(){
	    
	}

	@Override
	public void draw(Pane pane) {
	    System.out.println("Draw Square");
	    pane.getChildren().add(this);
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

	public void setSquareWidth(double a) {
		this.setWidth(a);
		this.setHeight(a);
	}
	
	public void setSquareHeight(double a) {
		this.setWidth(a);
		this.setHeight(a);
	}
	
	public double getxCoord() {
		return xCoord;
	}

	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}

	public double getyCoord() {
		return yCoord;
	}

	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}
	
	@Override
	public void setHandler() {
		this.setOnMouseClicked(new EventHandler<MouseEvent>(){
		 
		            @Override
		            public void handle(MouseEvent event) {
		            	if(event.getButton().toString().equals("SECONDARY")){
		            		
		            	}else{
			            	if(MySquare.this.isFocused()){
			            		MySquare.this.setStrokeWidth(0);
			            		MySquare.this.setFocused(false);
			            	}else{
			            		MySquare.this.setStroke(new Color(1, 0, 0, 1));
			            		MySquare.this.setStrokeWidth(2);
			            		MySquare.this.setFocused(true);
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
		            		int newWidth = (int) ((event.getX() - MySquare.this.getX() + event.getY() - MySquare.this.getX())/2);
		            		MySquare.this.resize(newWidth, newWidth);
		            		MySquare.this.setWidth(newWidth);
		            		MySquare.this.setHeight(newWidth);
		            		
		            		System.out.println("width= "+MySquare.this.getWidth() +" hieght = "+MySquare.this.getHeight());
		            		
		            	}else{
		            		MySquare.this.setX(event.getX());
			            	MySquare.this.setY(event.getY());
		            	}
		            }
		        });
	}
	
}
