package objects;


public class Circle extends Ellipse {

	public Circle(){
	    type = "Circle";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Circle::draw() method.");
	}

}
