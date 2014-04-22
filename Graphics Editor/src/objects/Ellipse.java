package objects;

public class Ellipse extends Shape implements Cloneable {
	
	double xCoord,yCoord,w,h;

	public Ellipse(){
	    type = "Elipse";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Ellipse::draw() method.");
	}
	
	//SETTERS & GETTERS
	
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

	public double getW() {
		return w;
	}

	public void setW(double w) {
		this.w = w;
	}

	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}


}
