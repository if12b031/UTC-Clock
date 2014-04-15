package objects;

import interfaces.IPrototyp;

public class Rectangle implements IPrototyp{

	@Override
	public Object clone() {
		
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
