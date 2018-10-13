package org.luoxi.ibd.filter;

import org.luoxi.ibd.model.OutputSpreadsheet;

/**
 * Default filter - just silly return the input outputSpreadsheet without any filtering
 *
 * @author luoxi
 *
 */
public class DefaultFilter implements Filter {

	public OutputSpreadsheet filtrate(OutputSpreadsheet outputSpreadsheet) {
		// Update context first
		FilterUtil.updateContext(this.getClass().getSimpleName());

		return outputSpreadsheet;
	}

}
