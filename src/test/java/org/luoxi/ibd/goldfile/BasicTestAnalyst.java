package org.luoxi.ibd.goldfile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.luoxi.ibd.analyst.Analyst;
import org.luoxi.ibd.analyst.BasicAnalyst;
import org.luoxi.ibd.analyzer.Analyzer;
import org.luoxi.ibd.analyzer.FullAnalyzer;
import org.luoxi.ibd.analyzer.HighOccurrenceAnalyzer;
import org.luoxi.ibd.analyzer.IBD50AndSectorLeaderAnalyzer;
import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.enrich.DefaultEnricher;
import org.luoxi.ibd.enrich.Enricher;
import org.luoxi.ibd.filter.DefaultFilter;
import org.luoxi.ibd.filter.Filter;
import org.luoxi.ibd.printer.SpreadsheetPrinter;
import org.luoxi.ibd.scanner.RatingScanner;
import org.luoxi.ibd.scanner.SpreadsheetScanner;

import com.google.common.collect.Lists;

/**
 *
 * Test implementation of {@link BasicAnalyst}.
 * Major purpose here is to override the scan file directory.
 *
 * @author luoxi
 *
 */
public class BasicTestAnalyst extends BasicAnalyst {

	public BasicTestAnalyst(SpreadsheetScanner scanner, Analyzer analyzer, List<Filter> filters,
			List<Enricher> enrichers, SpreadsheetPrinter printer) {
		super(scanner, analyzer, filters, enrichers, printer);
	}

	@Override
	protected List<String> getInputSpreadsheetPaths() {
		return getGoldFileTestInputSpreadsheets();
	}

	private List<String> getGoldFileTestInputSpreadsheets() {
		List<String> spreadsheets = Lists.newArrayList();

		File root = new File((String)ConfigFactory.get().getPropertiesProvider().getValue("path.test.root"));
		File[] files = root.listFiles();

		for (File file : files) {
			if (file.isFile() && !file.getName().equals("result.xls") && file.getName().endsWith(".xls")) {
				spreadsheets.add(ConfigFactory.get().getPropertiesProvider().getValue("path.test.root") + file.getName());
			}
		}

		return spreadsheets;
	}

	public static void generateTestResult() throws IOException {
		SpreadsheetScanner ratingScanner = new RatingScanner();

		Analyzer fullAnalyzer = new FullAnalyzer();
		Analyzer ibd50AndSectorLeaderAnalyzer = new IBD50AndSectorLeaderAnalyzer();
		Analyzer highOccurrenceAnalyzer = new HighOccurrenceAnalyzer();

		Filter defaultFilter = new DefaultFilter();
		Filter weeklyFilter = new WeeklySymbolTestFilter();

		Enricher defaultEnricher = new DefaultEnricher();

		SpreadsheetPrinter fullPrinter = new FullSpreadsheetTestPrinter();
		SpreadsheetPrinter ibd50AndSectorLeaderPrinter = new IBD50AndSectorLeaderSpreadsheetTestPrinter();
		SpreadsheetPrinter highOccurrencePrinter = new HighOccurrenceSpreadsheetTestPrinter();

		Analyst analyst = new BasicTestAnalyst(ratingScanner,
				fullAnalyzer,
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(defaultEnricher),
				fullPrinter);
		analyst.brainstorm();

		analyst = new BasicTestAnalyst(ratingScanner,
				ibd50AndSectorLeaderAnalyzer,
				Lists.newArrayList(weeklyFilter),
				Lists.newArrayList(defaultEnricher),
				ibd50AndSectorLeaderPrinter);
		analyst.brainstorm();

		analyst = new BasicTestAnalyst(ratingScanner,
				highOccurrenceAnalyzer,
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(defaultEnricher),
				highOccurrencePrinter);
		analyst.brainstorm();
	}
}
