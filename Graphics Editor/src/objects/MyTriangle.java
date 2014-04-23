package objects;

import interfaces.Drawable;
import interfaces.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class MyTriangle extends Polygon implements IShape,Drawable,Cloneable {
	
	double xCoord,yCoord,x,y;

	public MyTriangle(){
	
	}

	@Override
	public void draw(Pane pane) {
	    System.out.println("draw Triangle");
	    
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

	@Override
	public void setHandler() {
	
			this.setOnMouseClicked(new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {
			            	
			            	MyTriangle.this.setStroke(new Color(1, 0, 0, 1));
			            	MyTriangle.this.setStrokeWidth(2);
			            	
			            	System.out.println("Triangle OnClick");
			            	
			            }
			        });
			
			this.setOnMouseDragged(new EventHandler<MouseEvent>(){
			 
			            @Override
			            public void handle(MouseEvent event) {	
			            	MyTriangle.this.setX(event.getX());
			            	MyTriangle.this.setY(event.getY());
			            }
			        });
		
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
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
