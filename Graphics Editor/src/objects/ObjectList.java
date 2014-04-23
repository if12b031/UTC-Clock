package objects;

import interfaces.IShape;

import java.util.Hashtable;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class ObjectList {
	
	public static final int MAX_DIFF_SHAPES = 6;//different shapes (circle,rectangle....)
	
	private static Hashtable<String, IShape> shapeMap  = new Hashtable<String, IShape>();

	public static IShape getShape(int shapeId) {
		IShape currentShape = shapeMap.get(shapeId);
		return currentShape;
	}
	
	private static void initializeShapes(){
			
		shapeMap.put(Integer.toString(0), new MyRectangle());
		shapeMap.put(Integer.toString(1), new MySquare());
		shapeMap.put(Integer.toString(2), new MyEllipse());
		shapeMap.put(Integer.toString(3), new MyCircle());
		shapeMap.put(Integer.toString(4), new MyTriangle());
		shapeMap.put(Integer.toString(5), new CompositeGraphic());
	}
	
	
	
	public static IShape createShape(String type) {//klont die form die geklont werden soll(param)
		if(shapeMap.size() == 0){
			initializeShapes();
			System.out.println("init Shapes");
		}
		System.out.println("ShapeNumber(Rec 0;Square 1; Elipse 2; Circle 3; Triangle 4; Composite 5) = "+ getShapeNumber(type) );
		IShape clonedShape = (IShape) shapeMap.get(getShapeNumber(type)).klone();
		return clonedShape;

	}
	
	private static Object getShapeNumber(String type) {
		switch (type) {
        case "Rectangle":  type = Integer.toString(0);
                break;
        case "Square":  type = Integer.toString(1);
        		break;
        case "Ellipse":  type = Integer.toString(2);
        		break;
        case "Circle":  type = Integer.toString(3);
        		break;
        case "Triangle":  type = Integer.toString(4);
				break;
        case "Composite":  type = Integer.toString(5);
				break;
        default: type = "Invalid Shape";
        		System.out.println("Invalid Shape - Shape instance not found");
                break;
    }
		return type;
	}

	public static void add(IShape shape){
		((Node) shape).setId(Integer.toString(shapeMap.size()));
	    shapeMap.put(((Node) shape).getId(),shape);
	    System.out.println("current Shapes = " + (shapeMap.size()-MAX_DIFF_SHAPES));
	    return;
	}
	
	public static void remove(Shape shape){
		shapeMap.remove(shape.getId());
		return;
	}
	
	
	
	

}
