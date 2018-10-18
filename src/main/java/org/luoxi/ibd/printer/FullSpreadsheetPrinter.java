package org.luoxi.ibd.printer;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;

/**
 * Printer for full analyze result.
 *
 * @author luoxi
 *
 */
public class FullSpreadsheetPrinter implements SpreadsheetPrinter {

	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		// Update the context first
		PrinterUtil.updateContext(this.getClass().getSimpleName());

		String fileName = (String)ConfigFactory.get().getPropertiesProvider().getValue("path.result")
				+ PrinterUtil.getPrintDate("") + ".xls";

		PrinterUtil.generateResultSpreadsheet(outputSpreadsheet, fileName);
	}

}
