package org.luoxi.ibd.analyzer;

import java.util.List;

import org.luoxi.ibd.model.InputSpreadsheet;
import org.luoxi.ibd.model.OutputSpreadsheet;
import org.luoxi.ibd.model.input.IBD50AndSectorLeaderStockList;
import org.luoxi.ibd.model.output.IBD50AndSectorLeaderStockListAnalyzeResult;
import org.luoxi.ibd.model.output.StockAnalyzeResult;

/**
 *
 * A history analyzer for all IBD50 + Sector Leader existing analyze result.
 * The purpose of this analyzer is to show which stock is most frequently shown on this results
 * and what the continuity it is.
 *
 * @author luoxi
 *
 */
public class IBD50AndSectorLeaderHistoryAnalyzer implements Analyzer {

	private IBD50AndSectorLeaderStockListAnalyzeResult result;

	public IBD50AndSectorLeaderHistoryAnalyzer() {
		super();
		result = new IBD50AndSectorLeaderStockListAnalyzeResult();
	}

	public OutputSpreadsheet analyze(List<InputSpreadsheet> stockLists) {
		// First update the context
		AnalyzerUtil.updateContext(this.getClass().getSimpleName());

		for (int i = 0; i < stockLists.size(); i++) {
			IBD50AndSectorLeaderStockList stockList = (IBD50AndSectorLeaderStockList)(stockLists.get(i));
			for (StockAnalyzeResult stock : stockList.getStocks()) {
				result.addStockAnalyzeResult(stock, stockList.getName());
			}
		}

		return result;
	}
}
