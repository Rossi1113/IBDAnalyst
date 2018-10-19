package org.luoxi.ibd.printer;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;

/**
 * Printer for high occurrence analyze result.
 *
 * @author luoxi
 *
 */
public class HighOccurrenceSpreadsheetPrinter implements SpreadsheetPrinter {

	// TODO Generate the implementation here into util
	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		// Update the context first
		PrinterUtil.updateContext(this.getClass().getSimpleName());

		String fileName = (String)ConfigFactory.get().getPropertiesProvider().getValue("path.result") +
				PrinterUtil.getPrintDate("high_occurence") + "_high_occurence.xls";

		PrinterUtil.generateResultSpreadsheet(outputSpreadsheet, fileName);
	}

}
