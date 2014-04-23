package objects;


import interfaces.Drawable;
import interfaces.IShape;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends Rectangle implements IShape,Drawable,Cloneable {
	
	double xCoord,yCoord;
	
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

	public MyRectangle(){
	    
	}

	@Override
	public void draw(Pane pane) {
		System.out.println("Drawing a Rectangle");
	    //this.relocate(xCoord, yCoord);
		pane.getChildren().add(this);
		
	}
	
	@Override
	public Object klone(){
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
			            	if(MyRectangle.this.isFocused()){
			            		MyRectangle.this.setStrokeWidth(0);
			            		MyRectangle.this.setFocused(false);
			            	}else{
			            		MyRectangle.this.setStroke(new Color(1, 0, 0, 1));
			            		MyRectangle.this.setStrokeWidth(2);
			            		MyRectangle.this.setFocused(true);
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
		            		MyRectangle.this.resize(event.getX() - MyRectangle.this.getX(), event.getY() - MyRectangle.this.getY());
		            		MyRectangle.this.setWidth(event.getX() - MyRectangle.this.getX());
		            		MyRectangle.this.setHeight(event.getY() - MyRectangle.this.getY());
		            		
		            		System.out.println("width= "+MyRectangle.this.getWidth() +" hieght = "+MyRectangle.this.getHeight());
		            		
		            	}else{
		            		MyRectangle.this.setX(event.getX());
			            	MyRectangle.this.setY(event.getY());
		            	}
		            }
		        });
	}

}
