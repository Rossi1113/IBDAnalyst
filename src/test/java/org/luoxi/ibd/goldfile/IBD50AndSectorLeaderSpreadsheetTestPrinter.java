package org.luoxi.ibd.goldfile;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;
import org.luoxi.ibd.printer.IBD50AndSectorLeaderSpreadsheetPrinter;
import org.luoxi.ibd.printer.PrinterUtil;

/**
 *
 * Test implementation of {@link IBD50AndSectorLeaderSpreadsheetPrinter}.
 * Major purpose here is to override the output file directory.
 *
 * @author luoxi
 *
 */
public class IBD50AndSectorLeaderSpreadsheetTestPrinter extends IBD50AndSectorLeaderSpreadsheetPrinter {

	@Override
	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		String fileName = ConfigFactory.get().getPropertiesProvider().getValue("path.test.result") + "ibd50_plus_sector_leader.xls";

		PrinterUtil.generateResultSpreadsheet(outputSpreadsheet, fileName);
	}

}
