package com.group.kindergarten.costMoney.util;

import java.util.Timer;
import java.util.TimerTask;

public class Intervalometer {//��ʱ����ÿ��һ��ͽ������ڵ��жϣ�����ǽڼ��վͽ���

	public static void main(String[] args) {
		// java.util.Timer���µ� schedule(TimerTask task, long delay, long period)
		/**
		 * ����һ taskΪ��ʱ���񣬸���ҵ����Ҫ��дTimerTask��run�������ɡ� ������ delayΪ��ʱ��������λ���롣 ������
		 * periodΪ�������һ�Σ���λ���롣
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
