package commands;

import java.util.List;

import interfaces.ICommand;
import interfaces.IShape;
import objects.ObjectList;
import objects.CompositeGraphic;

public class ComGroupShapes implements ICommand {
	
	List<IShape> _shapesToGroup;
	CompositeGraphic compositum;
	
	public ComGroupShapes(List<IShape> shapesToGroup){
		_shapesToGroup = shapesToGroup;
	}
	
	@Override
	public void execute() {
		
		compositum = (CompositeGraphic) ObjectList.createShape("CompositeGraphic");//klone ein rechteck!
		for (IShape s : _shapesToGroup){//for each construct
			compositum.add((javafx.scene.shape.Shape) s);
		}
	}

	@Override
	public void undo() {
		
		for (IShape s : _shapesToGroup){//for each construct
			compositum.remove((javafx.scene.shape.Shape) s);
		}
	}
	
	
}
