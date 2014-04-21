package commands;

import java.util.List;

import interfaces.ICommand;
import objects.ObjectList;
import objects.Shape;
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
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}
	
	
}
