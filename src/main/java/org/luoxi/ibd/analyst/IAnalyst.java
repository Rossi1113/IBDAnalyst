package org.luoxi.ibd.analyst;

/**
 *
 * @author luoxi
 *
 */
public interface IAnalyst {

	/**
	 * Entry method - ideally when you call this method, all the stuff will be done for you.
	 * Most case, this includes :
	 * - scan input spreadsheet data
	 * - analyze data
	 * - print output spreadsheet
	 */
	public void brainstorm();
}
