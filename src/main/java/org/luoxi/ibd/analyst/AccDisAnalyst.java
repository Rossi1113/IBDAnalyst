package org.luoxi.ibd.analyst;

import java.util.List;

import org.apache.log4j.Logger;
import org.luoxi.ibd.analyzer.AccDisAnalyzer;
import org.luoxi.ibd.analyzer.Analyzer;
import org.luoxi.ibd.enrich.DefaultEnricher;
import org.luoxi.ibd.enrich.Enricher;
import org.luoxi.ibd.filter.DefaultFilter;
import org.luoxi.ibd.filter.Filter;
import org.luoxi.ibd.printer.AccDisSpreadsheetPrinter;
import org.luoxi.ibd.printer.SpreadsheetPrinter;
import org.luoxi.ibd.scanner.RatingScanner;
import org.luoxi.ibd.scanner.SpreadsheetScanner;

import com.google.common.collect.Lists;

/**
 *
 * Analyst for accumulation and distribution.
 *
 * @author luoxi
 *
 */
public class AccDisAnalyst extends Analyst {

	private static final Logger logger = Logger.getLogger(AccDisAnalyst.class);

	public AccDisAnalyst(SpreadsheetScanner scanner, Analyzer analyzer, List<Filter> filters, List<Enricher> enrichers,
			SpreadsheetPrinter printer) {
		super(scanner, analyzer, filters, enrichers, printer);
	}

	@Override
	protected List<String> getInputSpreadsheetPaths() {
		return AnalystUtil.getIBDRawSpreadsheets();
	}

	public static void main(String[] args) {
		logger.info("Running AccDisAnalyst......");

		SpreadsheetScanner ratingScanner = new RatingScanner();
		Analyzer accDisAnalyzer = new AccDisAnalyzer();
		Filter defaultFilter = new DefaultFilter();
		Enricher defaultEnricher = new DefaultEnricher();
		SpreadsheetPrinter accDisSpreadsheetPrinter = new AccDisSpreadsheetPrinter();

		Analyst analyst = new AccDisAnalyst(ratingScanner,
				accDisAnalyzer,
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(defaultEnricher),
				accDisSpreadsheetPrinter);

		analyst.brainstorm();
	}
}
