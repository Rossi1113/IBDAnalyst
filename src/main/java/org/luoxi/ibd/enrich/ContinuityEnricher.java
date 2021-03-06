package org.luoxi.ibd.enrich;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;
import org.luoxi.ibd.model.output.IBD50AndSectorLeaderStockAnalyzeResult;
import org.luoxi.ibd.model.output.IBD50AndSectorLeaderStockListAnalyzeResult;

import com.google.common.collect.Lists;

/**
 *
 * Enricher responsible to add continuity indicator to the output spreadsheet.
 *
 * @author luoxi
 *
 */
public class ContinuityEnricher implements Enricher {

	public OutputSpreadsheet enrich(OutputSpreadsheet outputSpreadsheet) {
		// Update the context first
		EnricherUtil.updateContext(this.getClass().getSimpleName());

		IBD50AndSectorLeaderStockListAnalyzeResult result = (IBD50AndSectorLeaderStockListAnalyzeResult)outputSpreadsheet;

		Iterator<Entry<String, IBD50AndSectorLeaderStockAnalyzeResult>> iterator = result.getResult().entrySet().iterator();
		while(iterator.hasNext()) {
			IBD50AndSectorLeaderStockAnalyzeResult singleStockResult = iterator.next().getValue();
			singleStockResult.setContinuity(calculateContinuity(singleStockResult.getInvolvedDates(), getAllDates()));

		}

		return result;
	}

	/**
	 * If hit one date, we mark *
	 * If miss one date, we mark /
	 *
	 * @param involvedDates
	 * @param allDates
	 * @return
	 */
	private String calculateContinuity(Set<String> involvedDates, String[] allDates) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < allDates.length; i++) {
			if (involvedDates.contains(allDates[i])) {
				sb.append("*");
			} else {
				sb.append("/");
			}
		}

		return sb.toString();
	}

	// Return all sorted dates
	private String[] getAllDates() {
		List<String> allDates = Lists.newArrayList();

		File root = new File((String)ConfigFactory.get().getPropertiesProvider().getValue("path.result"));
		File[] files = root.listFiles();

		for (File file : files) {
			if (file.isFile() && file.getName().contains("ibd50_plus_sector_leader")) {
				allDates.add(file.getName().substring(0, 8).replaceAll("_", "/"));
			}
		}

		String[] resultAllDates = new String[allDates.size()];
		allDates.toArray(resultAllDates);
		Arrays.sort(resultAllDates);

		return resultAllDates;
	}
}
