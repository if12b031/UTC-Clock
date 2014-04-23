package objects;

import interfaces.Drawable;
import interfaces.IShape;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
	
	public class CompositeGraphic extends Shape implements Drawable,IShape{
		
		public CompositeGraphic(){

		}
	 
	    //Collection of child graphics.
	    private List<Shape> childGraphics = new ArrayList<Shape>();
	 
	 
	    //Adds the graphic to the composition.
	    public void add(Shape graphic) {
	        childGraphics.add(graphic);
	    }
	 
	    //Removes the graphic from the composition.
	    public void remove(Shape graphic) {
	        childGraphics.remove(graphic);
	    }

		@Override
		public	void draw(Pane pane) {
			// TODO Auto-generated method stub	
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
		public com.sun.javafx.geom.Shape impl_configShape() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setHandler() {
			// TODO Auto-generated method stub
		}
	}
