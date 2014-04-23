package objects;

import interfaces.Drawable;
import interfaces.IShape;
import javafx.scene.layout.Pane;
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
		// TODO Auto-generated method stub
		
	}
	

}
