package objects;


public class Rectangle extends Shape implements Cloneable {
	
	double xCoord,yCoord,a,b;
	
	public Rectangle(){
	    type = "Rectangle";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Rectangle::draw() method.");
	    
	}

	
	//Setters & Getters
	
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

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

}
