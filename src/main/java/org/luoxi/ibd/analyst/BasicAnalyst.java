package org.luoxi.ibd.analyst;

import java.util.List;

import org.apache.log4j.Logger;
import org.luoxi.ibd.analyzer.Analyzer;
import org.luoxi.ibd.analyzer.FullAnalyzer;
import org.luoxi.ibd.analyzer.HighOccurrenceAnalyzer;
import org.luoxi.ibd.analyzer.IBD50AndSectorLeaderAnalyzer;
import org.luoxi.ibd.enrich.DefaultEnricher;
import org.luoxi.ibd.enrich.Enricher;
import org.luoxi.ibd.filter.DefaultFilter;
import org.luoxi.ibd.filter.Filter;
import org.luoxi.ibd.filter.WeeklySymbolFilter;
import org.luoxi.ibd.printer.FullSpreadsheetPrinter;
import org.luoxi.ibd.printer.HighOccurrenceSpreadsheetPrinter;
import org.luoxi.ibd.printer.IBD50AndSectorLeaderSpreadsheetPrinter;
import org.luoxi.ibd.printer.SpreadsheetPrinter;
import org.luoxi.ibd.scanner.RatingScanner;
import org.luoxi.ibd.scanner.SpreadsheetScanner;

import com.google.common.collect.Lists;

/**
 * Basic Analyst to get the basic result which include :
 * - full results
 * - IBD50 + Sector Leader
 * - High Occurrence
 *
 * @author luoxi
 *
 */
public class BasicAnalyst extends Analyst {

	private static final Logger logger = Logger.getLogger(BasicAnalyst.class);

	public BasicAnalyst(SpreadsheetScanner scanner, Analyzer analyzer, List<Filter> filters, List<Enricher> enrichers, SpreadsheetPrinter printer) {
		super(scanner, analyzer, filters, enrichers, printer);
	}

	@Override
	protected List<String> getInputSpreadsheetPaths() {
		return AnalystUtil.getIBDRawSpreadsheets();
	}

	public static void main(String[] args) {
		logger.info("Running BasicAnalyst......");

		SpreadsheetScanner ratingScanner = new RatingScanner();

		Analyzer fullAnalyzer = new FullAnalyzer();
		Analyzer ibd50AndSectorLeaderAnalyzer = new IBD50AndSectorLeaderAnalyzer();
		Analyzer highOccurrenceAnalyzer = new HighOccurrenceAnalyzer();

		Filter defaultFilter = new DefaultFilter();
		Filter weeklyFilter = new WeeklySymbolFilter();

		Enricher defaultEnricher = new DefaultEnricher();

		SpreadsheetPrinter fullPrinter = new FullSpreadsheetPrinter();
		SpreadsheetPrinter ibd50AndSectorLeaderPrinter = new IBD50AndSectorLeaderSpreadsheetPrinter();
		SpreadsheetPrinter highOccurrencePrinter = new HighOccurrenceSpreadsheetPrinter();

		Analyst analyst = new BasicAnalyst(ratingScanner,
				fullAnalyzer,
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(defaultEnricher),
				fullPrinter);
		analyst.brainstorm();

		analyst = new BasicAnalyst(ratingScanner,
				ibd50AndSectorLeaderAnalyzer,
				Lists.newArrayList(weeklyFilter),
				Lists.newArrayList(defaultEnricher),
				ibd50AndSectorLeaderPrinter);
		analyst.brainstorm();

		analyst = new BasicAnalyst(ratingScanner,
				highOccurrenceAnalyzer,
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(defaultEnricher),
				highOccurrencePrinter);
		analyst.brainstorm();
	}
}
