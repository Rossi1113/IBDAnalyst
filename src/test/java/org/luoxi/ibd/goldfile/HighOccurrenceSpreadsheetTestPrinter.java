package org.luoxi.ibd.goldfile;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;
import org.luoxi.ibd.printer.HighOccurrenceSpreadsheetPrinter;
import org.luoxi.ibd.printer.PrinterUtil;

/**
 *
 * Test implementation of {@link HighOccurrenceSpreadsheetPrinter}.
 * Major purpose here is to override the output file directory.
 *
 * @author luoxi
 *
 */
public class HighOccurrenceSpreadsheetTestPrinter extends HighOccurrenceSpreadsheetPrinter {

	@Override
	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		String fileName = ConfigFactory.get().getPropertiesProvider().getValue("path.test.result") + "high_occurrence.xls";

		PrinterUtil.generateResultSpreadsheet(outputSpreadsheet, fileName);
	}

}
