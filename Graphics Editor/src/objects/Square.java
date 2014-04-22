package objects;

public class Square extends Rectangle {
	

	public Square(){
	    type = "Square";
	}

	@Override
	public void draw() {
	    System.out.println("Inside Square::draw() method.");
	}

	public void setA(double a) {
		super.a = a;
		super.b = b;
	}
	
	public void setB(double b) {
		super.b = b;
		super.a = b;
	}
	
}
