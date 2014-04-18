package objects;

public class Ellipse extends Shape {

	public Ellipse(){
	    type = "Ellipse";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Ellipse::draw() method.");
	}

}
