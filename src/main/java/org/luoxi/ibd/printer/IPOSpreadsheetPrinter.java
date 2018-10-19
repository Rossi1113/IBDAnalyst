package org.luoxi.ibd.printer;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;

public class IPOSpreadsheetPrinter implements SpreadsheetPrinter {

	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		// Update the context first
		PrinterUtil.updateContext(this.getClass().getSimpleName());

		String fileName = (String)ConfigFactory.get().getPropertiesProvider().getValue("path.result")
				+ PrinterUtil.getPrintDate("ipo") + "_ipo.xls";

		PrinterUtil.generateResultSpreadsheet(outputSpreadsheet, fileName);
	}

}
