package commands;

import java.util.List;

import javafx.scene.shape.Shape;
import interfaces.ICommand;
import objects.ObjectList;
import objects.CompositeGraphic;

public class ComGroupShapes implements ICommand {
	
	List<Shape> _shapesToGroup;
	CompositeGraphic compositum;
	
	public ComGroupShapes(List<Shape> shapesToGroup){
		_shapesToGroup = shapesToGroup;
	}
	
	@Override
	public void execute() {
		
		compositum = (CompositeGraphic) ObjectList.createShape("CompositeGraphic");//klone ein rechteck!
		for (Shape s : _shapesToGroup){//for each construct
			compositum.add((javafx.scene.shape.Shape) s);
		}
	}

	@Override
	public void undo() {
		
		for (Shape s : _shapesToGroup){//for each construct
			compositum.remove((javafx.scene.shape.Shape) s);
		}
	}
	
	
}
