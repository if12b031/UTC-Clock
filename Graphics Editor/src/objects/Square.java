package objects;

public class Square extends Rectangle {

	public Square(){
	    type = "Square";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Square::draw() method.");
	}

}
