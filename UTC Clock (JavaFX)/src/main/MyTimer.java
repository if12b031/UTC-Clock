package main;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {

	private TimerTask _timerTask;
	private Timer _timer;
	private Clock _clock;
	
	public void start() {
		_timer = new Timer();
		_timerTask = new TimerTask() {
		    @Override
		    public void run () {
		        updateClock();
		    }
		};
		//schedule timer to execute every second
		_timer.scheduleAtFixedRate(_timerTask, 0, 1000);
	}
	
	private void updateClock() {
		int currentHours = _clock.getHours();
		int currentMinutes = _clock.getMinutes();
		int currentSeconds = _clock.getSeconds();
		
		//count one second up
		_clock.setSeconds((currentSeconds + 1) % 60);
		
		//if 60 seconds passed -> count one minute up
		if((currentSeconds + 1) % 60 == 0) {
			_clock.setMinutes((currentMinutes + 1) % 60);
			
			//if 60 minutes passed -> count one hour up (24 hour format)
			if((currentMinutes + 1) % 60 == 0) {
				_clock.setHours((currentHours + 1) % 24);
			}
		}	
		
		System.out.println("It is: " + _clock.getHours() + ":"
				+ _clock.getMinutes() + ":"
				+ _clock.getSeconds() + " o'clock");
	}
	
	public void pause() {
		_timer.cancel();
	}
	
	public void setClock(Clock _clock) {
		this._clock = _clock;
	}
	
	public Clock getClock() {
		return _clock;
	}
}
