package org.luoxi.ibd.analyzer;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.context.Context;

public class AnalyzerUtil {

	public static void updateContext(String className) {
		Context context = ConfigFactory.get().getContextProvider().getContext();
		context.setAnalyzerName(className);
		ConfigFactory.get().getContextProvider().establishOrUpdateContext(context);
	}
}
