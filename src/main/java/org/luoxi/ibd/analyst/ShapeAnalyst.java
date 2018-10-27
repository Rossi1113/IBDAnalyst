package org.luoxi.ibd.analyst;

import java.util.List;

import org.apache.log4j.Logger;
import org.luoxi.ibd.analyzer.Analyzer;
import org.luoxi.ibd.analyzer.ShapeAnalyzer;
import org.luoxi.ibd.enrich.DefaultEnricher;
import org.luoxi.ibd.enrich.Enricher;
import org.luoxi.ibd.filter.DefaultFilter;
import org.luoxi.ibd.filter.Filter;
import org.luoxi.ibd.printer.ShapeSpreadsheetPrinter;
import org.luoxi.ibd.printer.SpreadsheetPrinter;
import org.luoxi.ibd.scanner.RatingScanner;
import org.luoxi.ibd.scanner.SpreadsheetScanner;

import com.google.common.collect.Lists;

/**
 * Analyst used for choosing the right shape of stocks.
 *
 * 04/06/17 - read ibd 50 and filter out with the stocks with potential cup shape
 *
 * @author luoxi
 *
 */
public class ShapeAnalyst extends Analyst {

	private static final Logger logger = Logger.getLogger(ShapeAnalyst.class);

	public ShapeAnalyst(SpreadsheetScanner scanner, Analyzer analyzer, List<Filter> filters, List<Enricher> enrichers,
			SpreadsheetPrinter printer) {
		super(scanner, analyzer, filters, enrichers, printer);
	}

	@Override
	protected List<String> getInputSpreadsheetPaths() {
		return Lists.newArrayList(AnalystUtil.getIBD50ResultSpreadsheets());
	}

	public static void main(String[] args) {
		logger.info("Running ShapeAnalyst......");

		Filter defaultFilter = new DefaultFilter();
		Enricher defaultEnricher = new DefaultEnricher();

		Analyst analyst = new ShapeAnalyst(new RatingScanner(),
				new ShapeAnalyzer(),
				Lists.newArrayList(defaultFilter),
				Lists.newArrayList(defaultEnricher),
				new ShapeSpreadsheetPrinter());
		analyst.brainstorm();
	}

}
