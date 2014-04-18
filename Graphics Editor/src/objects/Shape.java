package objects;

import interfaces.Cloneable;

public abstract class Shape implements Cloneable {

	private int id;
	protected String type;
	   
	abstract void draw();
	   
	public String getType(){
		return type;
	}
	   
	public int getId() {
		return id;
	}
	   
	public void setId(int id) {
	   	this.id = id;
	}
	
	@Override
	public Object klone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
