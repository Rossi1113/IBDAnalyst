package org.luoxi.ibd.goldfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.luoxi.ibd.analyst.Analyst;
import org.luoxi.ibd.analyst.GoldenAnalyst;
import org.luoxi.ibd.analyzer.Analyzer;
import org.luoxi.ibd.analyzer.IBD50AndSectorLeaderHistoryAnalyzer;
import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.enrich.ContinuityEnricher;
import org.luoxi.ibd.enrich.Enricher;
import org.luoxi.ibd.filter.DefaultFilter;
import org.luoxi.ibd.filter.Filter;
import org.luoxi.ibd.printer.SpreadsheetPrinter;
import org.luoxi.ibd.scanner.IBD50AndSectorLeaderResultScanner;
import org.luoxi.ibd.scanner.SpreadsheetScanner;

import com.google.common.collect.Lists;

/**
 *
 * Test implementation of {@link GoldenAnalyst}.
 * Major purpose here is to override the scan file directory.
 *
 * @author luoxi
 *
 */
public class GoldenTestAnalyst extends GoldenAnalyst {

	public GoldenTestAnalyst(SpreadsheetScanner scanner, Analyzer analyzer, List<Filter> filters,
			List<Enricher> enrichers, SpreadsheetPrinter printer) {
		super(scanner, analyzer, filters, enrichers, printer);
	}

	@Override
	protected List<String> getInputSpreadsheetPaths() {
		return getGoldIBD50AndSectorLeaderResultSpreadsheets();
	}

	private List<String> getGoldIBD50AndSectorLeaderResultSpreadsheets() {
		List<String> spreadsheets = Lists.newArrayList();

		File root = new File((String)ConfigFactory.get().getPropertiesProvider().getValue("path.result"));
		File[] files = root.listFiles();

		for (File file : files) {
			if (file.isFile() && file.getName().contains("ibd50_plus_sector_leader")) {
				spreadsheets.add((String)ConfigFactory.get().getPropertiesProvider().getValue("path.result") + file.getName());
			}
		}

		return spreadsheets;
	}

	public static void generateTestResult() throws IOException {
		Filter defaultFilter = new DefaultFilter();
		// No performance enricher here
		Enricher continuityEnricher = new ContinuityEnricher();

		Analyst analyst = new GoldenAnalyst(new IBD50AndSectorLeaderResultScanner(),
				new IBD50AndSectorLeaderHistoryAnalyzer(),
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(continuityEnricher),
				new IBD50AndSectorLeaderHistorySpreadsheetTestPrinter());
		analyst.brainstorm();
	}
}
