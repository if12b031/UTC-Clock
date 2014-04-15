package objects;

	import interfaces.IGraphic;

	import java.util.List;
	import java.util.ArrayList;
	
	class CompositeGraphic implements IGraphic {
	 
	    //Collection of child graphics.
	    private List<IGraphic> childGraphics = new ArrayList<IGraphic>();
	 
	 
	    //Adds the graphic to the composition.
	    public void add(IGraphic graphic) {
	        childGraphics.add(graphic);
	    }
	 
	    //Removes the graphic from the composition.
	    public void remove(IGraphic graphic) {
	        childGraphics.remove(graphic);
	    }
	}
