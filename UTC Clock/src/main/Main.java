package main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clock myClock = Clock.getInstance();		
		MyTimer myTimer = new MyTimer();
		
		myClock.setHours(12);
		myClock.setMinutes(59);
		myClock.setSeconds(55);
		
		myTimer.setClock(myClock);
		myTimer.start();
	}

}
