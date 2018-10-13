package org.luoxi.ibd.filter;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.context.Context;

public class FilterUtil {

	public static void updateContext(String className) {
		Context context = ConfigFactory.get().getContextProvider().getContext();
		context.addFilterName(className);
		ConfigFactory.get().getContextProvider().establishOrUpdateContext(context);
	}
}
