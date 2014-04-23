package objects;

import java.util.Hashtable;

public class ObjectList {
	
	public static final int MAX_DIFF_SHAPES = 6;//different shapes (circle,rectangle....)
	
	private static Hashtable<Integer, Shape> shapeMap  = new Hashtable<Integer, Shape>();

	public static Shape getShape(int shapeId) {
		Shape currentShape = shapeMap.get(shapeId);
		return currentShape;
	}
	
	private static void initializeShapes(){
			
		shapeMap.put(0, new Rectangle());
		shapeMap.put(1, new Square());
		shapeMap.put(2, new Ellipse());
		shapeMap.put(3, new Circle());
		shapeMap.put(4, new Triangle());
		shapeMap.put(5, new CompositeGraphic());
	}
	
	
	
	public static Shape createShape(String type) {//klont die form die geklont werden soll(param)
		if(shapeMap.size() == 0){
			initializeShapes();
			System.out.println("init Shapes");
		}
		
		for(int i = 0;i <= MAX_DIFF_SHAPES; i++){
			if(shapeMap.get(i).getType().equals(type)){
				System.out.println("ShapeNumber(Rec 0;Square 1; Elipse 2; Circle 3; Triangle 4;) = "+ i );
				Shape clonedShape = (Shape) shapeMap.get(i).klone();
				return clonedShape;
			}
		}
		return null;
	}
	
	public static void add(Shape shape){
		shape.setId(shapeMap.size());
	    shapeMap.put(shape.getId(),shape);
	    System.out.println("current Shapes = " + (shapeMap.size()-MAX_DIFF_SHAPES));
	    return;
	}
	
	public static void remove(Shape shape){
		shapeMap.remove(shape.getId());
		return;
	}
}
