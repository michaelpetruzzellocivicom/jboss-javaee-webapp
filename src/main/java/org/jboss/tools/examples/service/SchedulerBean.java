package org.jboss.tools.examples.service;

import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
public class SchedulerBean implements Scheduler
{
	@Resource
	private TimerService timerService;
	
	@Timeout
	public void scheduler(Timer timer)
	{
		System.out.println("Scheduler timeout");
	}
	
	@Override
	public void initialize(String info)
	{
		System.out.println("Scheduler timeout");
		ScheduleExpression scheduleExpression = new ScheduleExpression();
		scheduleExpression.hour("*").minute("*").second("0/10");
		timerService.createCalendarTimer(scheduleExpression, new TimerConfig(info, false));
	}

	@Override
	public void stop()
	{
		for (Timer timer : timerService.getTimers())
		{
			timer.cancel();
		}

	}

}