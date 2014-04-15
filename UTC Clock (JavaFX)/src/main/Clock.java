package main;

import java.util.ArrayList;
import java.util.List;

import interfaces.IClock;
import interfaces.Observer;

public class Clock  implements IClock {
	
	public static Clock instance;
	public MyTimer myTimer;
	private int _hours, _minutes, _seconds;

	private List<Observer> windowsOpen = new ArrayList<Observer>();
    
    @Override
	public void startTimer() {
    	
    	myTimer = new MyTimer();
		
		this.setHours(12);
		this.setMinutes(59);
		this.setSeconds(55);
		
		myTimer.setClock(this);
		myTimer.start();
    	
	}
    
    public void addWindow(DigitalController cw){
    	
    	windowsOpen.add((Observer) cw);
    	return;
    }
    
    public void removeWindow(DigitalController cw){
    	
    	windowsOpen.remove(cw);
    	return;
    }
    
    protected Clock(){
    	
    }
    
    public static Clock getInstance() {
		
		if (instance == null) {
            synchronized (Clock .class){
            				if (instance == null) {
            							instance = new Clock ();
            				}
            }
		}
	return instance;
		
	}
    
    
	@Override
	public void notifyObservers() {
		
		for(Observer o : windowsOpen ){
			o.update();
		}
		
	}
	
	//SETTERS bzw. GETTERS
	
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

	public void incrementTime(int h, int m, int s) {
		
		_hours += h;
		_minutes += m;
		_seconds += s;
		return;		
	}

	public void decrementTime(int h, int m, int s) {
		
		_hours -= h;
		_minutes -= m;
		_seconds -= s;
		return;
	}
}
