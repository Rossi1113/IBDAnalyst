package org.luoxi.ibd.analyzer;

import java.util.List;

import org.luoxi.ibd.model.InputSpreadsheet;
import org.luoxi.ibd.model.OutputSpreadsheet;
import org.luoxi.ibd.model.input.Stock;
import org.luoxi.ibd.model.input.StockList;
import org.luoxi.ibd.model.output.StockListAnalyzeResult;

/**
 *
 * Analyzer for IPO data.
 *
 * @author luoxi
 *
 */
public class IPOAnalyzer implements Analyzer {

	/**
	 * Right now it just map the java pojo.
	 * It's pretty much same as FullAnalyzer, but later we can add additional analyze here.
	 */
	public OutputSpreadsheet analyze(List<InputSpreadsheet> inputSpreadsheets) {
		// First update the context
		AnalyzerUtil.updateContext(this.getClass().getSimpleName());

		StockListAnalyzeResult result = new StockListAnalyzeResult();

		for (InputSpreadsheet inputSpreadsheet : inputSpreadsheets) {
			StockList stockList = (StockList)inputSpreadsheet;
			for (Stock stock : stockList.getStocks()) {
				result.addStockAnalyzeResult(stock, stockList.getName());
			}
		}

		return result;
	}

}
