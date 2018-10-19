package org.luoxi.ibd.printer;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;

/**
 * Printer for IBD50 + Sector Leader analyze result.
 *
 * @author luoxi
 *
 */
public class IBD50AndSectorLeaderSpreadsheetPrinter implements SpreadsheetPrinter {

	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		// Update the context first
		PrinterUtil.updateContext(this.getClass().getSimpleName());

		String fileName = (String)ConfigFactory.get().getPropertiesProvider().getValue("path.result") +
				PrinterUtil.getPrintDate("ibd50_plus_sector_leader") + "_ibd50_plus_sector_leader.xls";

		PrinterUtil.generateResultSpreadsheet(outputSpreadsheet, fileName);
	}

}
