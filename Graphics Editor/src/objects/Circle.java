package objects;

import interfaces.IPrototyp;

public class Circle extends Ellipse implements IPrototyp {

	@Override
	public Object clone(){
		
		return super.clone();
	}

}
