package commands;

import main.Clock;
import interfaces.ICommand;

public class ComSet implements ICommand {
	
	private static Clock singeltonClock;
	private int _h,_m,_s;
	private int _oldH, _oldM, _oldS;
	
	public ComSet(int h, int m, int s) {
		_h = h;
		_m = m;
		_s = s;
	}

	@Override
	public void execute() {
		singeltonClock = Clock.getInstance();
		_oldH = singeltonClock.getHours();
		_oldM = singeltonClock.getMinutes();
		_oldS = singeltonClock.getSeconds();
		singeltonClock.setHours(_h);
    	singeltonClock.setMinutes(_m);
    	singeltonClock.setSeconds(_s);
		}

	@Override
	public void undo() {
		
		singeltonClock.setHours(_oldH);
    	singeltonClock.setMinutes(_oldM);
    	singeltonClock.setSeconds(_oldS);
		
	}

}
