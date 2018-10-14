package org.luoxi.ibd.enrich;

import org.luoxi.ibd.model.OutputSpreadsheet;

/**
 *
 * Interface providing the hook to add additional data on analyze result.
 *
 * @author luoxi
 *
 */
public interface Enricher {

	/**
	 * Enrich the analyze result by additional data based on certain criteria
	 * @param outputSpreadsheet - input analyze result
	 * @return {@link OutputSpreadsheet} - a filtered analyze result
	 */
	public OutputSpreadsheet enrich(OutputSpreadsheet outputSpreadsheet);
}
