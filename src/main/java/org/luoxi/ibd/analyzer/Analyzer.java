package org.luoxi.ibd.analyzer;

import java.util.List;

import org.luoxi.ibd.model.InputSpreadsheet;
import org.luoxi.ibd.model.OutputSpreadsheet;

/**
 *
 * Interface for any analyze strategy.
 * Any strategy to analyze the stock list data needs to implement this interface.
 *
 * @author luoxi
 *
 */
public interface Analyzer {

	/**
	 * Analyze a list of {@link InputSpreadsheet} and generate specific result based on the strategy.
	 * @return A {@link OutputSpreadsheet} that can be used to print out a single result spreadsheet.
	 */
	public OutputSpreadsheet analyze(List<InputSpreadsheet> inputSpreadsheets);
}
