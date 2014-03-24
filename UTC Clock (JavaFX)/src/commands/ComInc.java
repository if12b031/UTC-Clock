package commands;

import main.Clock;
import interfaces.ICommand;

public class ComInc implements ICommand {

	
	private static Clock singeltonClock;
	int _h,_m,_s;
	
	public ComInc(int h, int m, int s) {
		_h = h;
		_m = m;
		_s = s;
	}
	
	@Override
	public void execute() {
		singeltonClock = Clock.getInstance();
		
		singeltonClock.incrementTime(_h, _m, _s);
	}

	@Override
	public void undo() {
		
		singeltonClock.decrementTime(_h, _m, _s);
		
	}

}
