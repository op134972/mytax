package cn.ustb.test;

import java.util.Timer;


public class MyTimer {
	public static void main(String[] args) {
		MyTimerTask mt = new MyTimerTask();
		Timer timer = new Timer();
		//延迟1s执行，执行完后等待1s执行。
		timer.schedule(mt, 1000,1000);
	}
}
