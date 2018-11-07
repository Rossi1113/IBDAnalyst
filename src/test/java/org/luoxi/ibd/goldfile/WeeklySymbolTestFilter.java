package org.luoxi.ibd.goldfile;

import java.io.File;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.filter.WeeklySymbolFilter;

public class WeeklySymbolTestFilter extends WeeklySymbolFilter {

	@Override
	protected String getFilteringCriteriaFile() {
		File root = new File((String)ConfigFactory.get().getPropertiesProvider().getValue("path.test.root"));
		File[] files = root.listFiles();

		for (File file : files) {
			if (file.isFile() && file.getName().contains("WEEKLY")) {
				return ConfigFactory.get().getPropertiesProvider().getValue("path.test.root") + file.getName();
			}
		}

		return null;
	}

}
