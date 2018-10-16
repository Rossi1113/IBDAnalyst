package org.luoxi.ibd.enrich;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.context.Context;

public class EnricherUtil {

	public static void updateContext(String className) {
		Context context = ConfigFactory.get().getContextProvider().getContext();
		context.addEnricherName(className);
		ConfigFactory.get().getContextProvider().establishOrUpdateContext(context);
	}
}
