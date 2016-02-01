package org.jboss.tools.examples.service;

import org.jboss.as.clustering.singleton.SingletonService;
import org.jboss.msc.service.DelegatingServiceContainer;
import org.jboss.msc.service.ServiceActivator;
import org.jboss.msc.service.ServiceActivatorContext;
import org.jboss.msc.service.ServiceController;

public class HATimerServiceActivator implements ServiceActivator
{
	@Override
	public void activate(ServiceActivatorContext context)
	{
		IvrAsteriskRecordingsFeed service = new IvrAsteriskRecordingsFeed();
		
		SingletonService<String> singleton = new SingletonService<String>(service, IvrAsteriskRecordingsFeed.SINGLETON_SERVICE_NAME);
		
		singleton.build(new DelegatingServiceContainer(context.getServiceTarget(), context.getServiceRegistry()))
			.setInitialMode(ServiceController.Mode.ACTIVE).install();
	
	}

}
