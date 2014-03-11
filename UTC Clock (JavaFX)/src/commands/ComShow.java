package commands;

import main.ClockWindow;
import interfaces.ICommand;

public class ComShow implements ICommand {
	
	private String _display;
	private int _x,_y,_timezone;
	
	public ComShow(String display, int timezone, int x, int y){
		_display = display;
		_timezone = timezone;
		_x = x;
		_y = y;
	}

	@Override
	public void execute() {
		
		ClockWindow window = new ClockWindow(_display, _timezone, _x, _y);
		window.show();
		
	}
	
}
