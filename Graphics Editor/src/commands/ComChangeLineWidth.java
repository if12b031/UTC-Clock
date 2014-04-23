package commands;

import main.EditorController;
import javafx.scene.canvas.GraphicsContext;
import interfaces.ICommand;

public class ComChangeLineWidth implements ICommand {
	
	GraphicsContext _gc;
	double _width,_widthOld;

	public ComChangeLineWidth(GraphicsContext gc, double width, double widthOld) {
		_gc = gc;
		_width = width;
		_widthOld = widthOld;
	}

	@Override
	public void execute() {
		_gc.setLineWidth(_width);
	}

	

	@Override
	public void undo() {
		EditorController.undoLineWidth(_widthOld);		
	}
	
	//GETTERS
	public GraphicsContext get_gc() {
		return _gc;
	}

}
