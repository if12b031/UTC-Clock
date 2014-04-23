package objects;

import interfaces.Drawable;
import interfaces.IShape;
import javafx.scene.layout.Pane;
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

	@Override
	public void setHandler() {
		// TODO Auto-generated method stub
		
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
	
}
