package objects;

import java.util.Hashtable;

public class ObjectList {
	
	public static final int MAX_DIFF_SHAPES = 5;//different shapes (circle,rectangle....)
	
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
		
	}
	
	
	
	public static Shape createShape(String type) {//klont die form die geklont werden soll(param)
		
		if(shapeMap.isEmpty() == true){
			initializeShapes();
		}
		
		for(int i = 0;i >= MAX_DIFF_SHAPES; i++){
			if(shapeMap.get(i).getType().equals(type)){
				Shape clonedShape = (Shape) shapeMap.get(i).klone();
				add(clonedShape);
				return clonedShape;
			}
		}
		return null;
	}
	
	private static void add(Shape shape){
		shape.setId(shapeMap.size());
	    shapeMap.put(shape.getId(),shape);
	    return;
	}
	
	public static void remove(Shape shape){
		shapeMap.remove(shape.getId());
		return;
	}
	
	
	
	

}
