package org.luoxi.ibd.printer;

import org.luoxi.ibd.model.OutputSpreadsheet;

/**
 * Interface defining generating the final result spreadsheet.
 *
 * @author luoxi
 *
 */
public interface SpreadsheetPrinter {

	/**
	 * Generate a spreadsheet that contains final the analyze result
	 * @param stockListAnalyzeResult
	 */
	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet);
}
