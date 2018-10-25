package org.luoxi.ibd.analyst;

import java.util.List;

import org.apache.log4j.Logger;
import org.luoxi.ibd.analyzer.Analyzer;
import org.luoxi.ibd.analyzer.IPOAnalyzer;
import org.luoxi.ibd.enrich.Enricher;
import org.luoxi.ibd.enrich.QuotePerformanceEnricher;
import org.luoxi.ibd.filter.DefaultFilter;
import org.luoxi.ibd.filter.Filter;
import org.luoxi.ibd.printer.IPOSpreadsheetPrinter;
import org.luoxi.ibd.printer.SpreadsheetPrinter;
import org.luoxi.ibd.scanner.RatingScanner;
import org.luoxi.ibd.scanner.SpreadsheetScanner;

import com.google.common.collect.Lists;

/**
 * Analyst for IPO stocks
 *
 * @author luoxi
 *
 */
public class IPOAnalyst extends Analyst {

	private static final Logger logger = Logger.getLogger(IPOAnalyst.class);

	public IPOAnalyst(SpreadsheetScanner scanner, Analyzer analyzer, List<Filter> filters, List<Enricher> enrichers,
			SpreadsheetPrinter printer) {
		super(scanner, analyzer, filters, enrichers, printer);
	}

	@Override
	protected List<String> getInputSpreadsheetPaths() {
		return Lists.newArrayList(AnalystUtil.getIPOSpreadsheet());
	}

	public static void main(String[] args) {
		logger.info("Running IPOAnalyst......");

		Filter defaultFilter = new DefaultFilter();
		// Don't apply IBD50 right now, it filters too much
//		Filter ibd50Filter = new IBD50SymbolFilter();
		Enricher quotePerformanceEnricher = new QuotePerformanceEnricher();

		Analyst analyst = new IPOAnalyst(new RatingScanner(),
				new IPOAnalyzer(),
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(quotePerformanceEnricher),
				new IPOSpreadsheetPrinter());
		analyst.brainstorm();
	}

}
