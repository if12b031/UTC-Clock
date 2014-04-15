package commands;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import interfaces.ICommand;

public class ComSetLineColor implements ICommand {
	
	GraphicsContext _gc;
	Paint _color,_colorOld;

	public ComSetLineColor(GraphicsContext gc, Paint color, Paint colorOld) {
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
		_gc.setStroke(_colorOld);		
	}
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
