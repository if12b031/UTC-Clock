package commands;

import main.EditorController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import interfaces.ICommand;

public class ComSetLineColor implements ICommand {
	
	GraphicsContext _gc;
	Color _color,_colorOld;

	public ComSetLineColor(GraphicsContext gc, Color color, Color colorOld) {
		_gc = gc;
		_color = color;
		_colorOld = colorOld;
	}

	@Override
	public void execute() {
		_gc.setStroke(_color);
	}	

	@Override
	public void undo() {
		EditorController.updateColorpicker(_colorOld);
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
