package main;

import interfaces.IClock;

public class Clock implements IClock{

	private int _hours, _minutes, _seconds;
	private static Clock Clock = null;

    private Clock() {
        // Exists only to defeat instantiation.
    }

    public static Clock getInstance() {
        if (Clock == null) {
        	Clock = new Clock();
        }
        return Clock;
    }
	
	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}	
	
	public int getHours() {
		return _hours;
	}

	public void setHours(int _hours) {
		this._hours = _hours;
	}
	
	public int getMinutes() {
		return _minutes;
	}

	public void setMinutes(int _minutes) {
		this._minutes = _minutes;
	}
	
	public int getSeconds() {
		return _seconds;
	}

	public void setSeconds(int _seconds) {
		this._seconds = _seconds;
	}
}
