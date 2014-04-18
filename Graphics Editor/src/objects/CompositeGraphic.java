package objects;

import objects.Shape;
import java.util.List;
import java.util.ArrayList;
	
	class CompositeGraphic extends Shape {
	 
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
		void draw() {
			// TODO Auto-generated method stub
			
		}
	}
