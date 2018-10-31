package org.luoxi.ibd.goldfile;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.OutputSpreadsheet;
import org.luoxi.ibd.printer.IBD50AndSectorLeaderHistorySpreadsheetPrinter;

/**
 *
 * Test implementation of {@link IBD50AndSectorLeaderHistorySpreadsheetPrinter}.
 * Major purpose here is to override the output file directory.
 *
 * @author luoxi
 *
 */
public class IBD50AndSectorLeaderHistorySpreadsheetTestPrinter extends IBD50AndSectorLeaderHistorySpreadsheetPrinter {

	@Override
	public void generateResultSpreadsheet(OutputSpreadsheet outputSpreadsheet) {
		String fileName = ConfigFactory.get().getPropertiesProvider().getValue("path.test.result") + "Golden.xls";

		generateGoldenSpreadsheet(outputSpreadsheet, fileName);
	}

}
