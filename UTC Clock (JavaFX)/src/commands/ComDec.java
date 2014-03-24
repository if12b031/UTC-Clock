package commands;

import main.Clock;
import interfaces.ICommand;

public class ComDec implements ICommand {
	
	private static Clock singeltonClock;
	int _h,_m,_s;

	public ComDec(int h, int m, int s) {
		_h = h;
		_m = m;
		_s = s;
	}

	@Override
	public void execute() {
		singeltonClock = Clock.getInstance();
		
		singeltonClock.decrementTime(_h, _m, _s);
		
	}

	@Override
	public void undo() {
		
		singeltonClock.incrementTime(_h, _m, _s);
		
	}

}
