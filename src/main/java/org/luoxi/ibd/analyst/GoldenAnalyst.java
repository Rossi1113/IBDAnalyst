package org.luoxi.ibd.analyst;

import java.util.List;

import org.apache.log4j.Logger;
import org.luoxi.ibd.analyzer.Analyzer;
import org.luoxi.ibd.analyzer.IBD50AndSectorLeaderHistoryAnalyzer;
import org.luoxi.ibd.enrich.ContinuityEnricher;
import org.luoxi.ibd.enrich.Enricher;
import org.luoxi.ibd.enrich.QuotePerformanceEnricher;
import org.luoxi.ibd.filter.DefaultFilter;
import org.luoxi.ibd.filter.Filter;
import org.luoxi.ibd.printer.IBD50AndSectorLeaderHistorySpreadsheetPrinter;
import org.luoxi.ibd.printer.SpreadsheetPrinter;
import org.luoxi.ibd.scanner.IBD50AndSectorLeaderResultScanner;
import org.luoxi.ibd.scanner.SpreadsheetScanner;

import com.google.common.collect.Lists;

/**
 *
 * Golden analyst analyze based on the ibd50_plus_sector_leader result.
 * The major goal for this is to see which stock stays on these two lists most frequently and
 * has the best continuity in a certain time period.
 *
 * Attention : this analyst needs to run after {@link BasicAnalyst} gets results
 *
 * @author luoxi
 *
 */
public class GoldenAnalyst extends Analyst {

	private static final Logger logger = Logger.getLogger(GoldenAnalyst.class);

	public GoldenAnalyst(SpreadsheetScanner scanner, Analyzer analyzer, List<Filter> filters, List<Enricher> enrichers, SpreadsheetPrinter printer) {
		super(scanner, analyzer, filters, enrichers, printer);
	}

	@Override
	protected List<String> getInputSpreadsheetPaths() {
		return AnalystUtil.getIBD50AndSectorLeaderResultSpreadsheets();
	}

	public static void main(String[] args) {
		logger.info("Running GoldenAnalyst......");

		Filter defaultFilter = new DefaultFilter();
		Enricher performanceEnricher = new QuotePerformanceEnricher();
		Enricher continuityEnricher = new ContinuityEnricher();

		Analyst analyst = new GoldenAnalyst(new IBD50AndSectorLeaderResultScanner(),
				new IBD50AndSectorLeaderHistoryAnalyzer(),
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(performanceEnricher, continuityEnricher),
				new IBD50AndSectorLeaderHistorySpreadsheetPrinter());
		analyst.brainstorm();
	}
}
