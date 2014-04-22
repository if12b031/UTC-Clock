package objects;


public class Circle extends Ellipse implements Cloneable {	
	
	public Circle(){
	    type = "Circle";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Circle::draw() method.");
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
	
	public void setA(double w) {
		super.w = w;
		super.h = h;
	}
	
	public void setB(double h) {
		super.h = h;
		super.w = w;
	}
	

}
