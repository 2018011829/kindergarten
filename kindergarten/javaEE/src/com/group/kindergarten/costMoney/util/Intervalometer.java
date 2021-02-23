package com.group.kindergarten.costMoney.util;

import java.util.Timer;
import java.util.TimerTask;

public class Intervalometer {//定时器：每过一天就进行日期的判断，如果是节假日就进行

	public static void main(String[] args) {
		// java.util.Timer包下的 schedule(TimerTask task, long delay, long period)
		/**
		 * 参数一 task为定时任务，根据业务需要重写TimerTask的run方法即可。 参数二 delay为延时启动，单位毫秒。 参数三
		 * period为多久运行一次，单位毫秒。
		 */
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					// do Something
					System.out.println("hello world!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, 2000);
	}

}
