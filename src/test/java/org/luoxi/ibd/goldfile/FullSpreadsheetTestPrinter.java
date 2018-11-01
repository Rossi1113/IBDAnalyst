package org.luoxi.ibd.goldfile;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;
import org.luoxi.ibd.printer.FullSpreadsheetPrinter;
import org.luoxi.ibd.printer.PrinterUtil;

/**
 *
 * Test implementation of {@link FullSpreadsheetPrinter}.
 * Major purpose here is to override the output file directory.
 *
 * @author luoxi
 *
 */
public class FullSpreadsheetTestPrinter extends FullSpreadsheetPrinter {

	@Override
	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		String fileName = ConfigFactory.get().getPropertiesProvider().getValue("path.test.result") + "full.xls";

		PrinterUtil.generateResultSpreadsheet(outputSpreadsheet, fileName);
	}

}
