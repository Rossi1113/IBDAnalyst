package org.luoxi.ibd.scanner;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.context.Context;

public class ScannerUtil {

	public static void updateContext(String className) {
		Context context = ConfigFactory.get().getContextProvider().getContext();
		context.setScannerName(className);
		ConfigFactory.get().getContextProvider().establishOrUpdateContext(context);
	}
}
