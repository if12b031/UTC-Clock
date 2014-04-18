package objects;


public class Triangle extends Shape {

	public Triangle(){
	    type = "Triangle";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Triangle::draw() method.");
	}

}
