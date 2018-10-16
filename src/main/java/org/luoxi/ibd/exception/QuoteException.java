package org.luoxi.ibd.exception;

import org.luoxi.ibd.services.QuoteService;

/**
 * Exception that is related to {@link QuoteService}
 *
 * @author luoxi
 *
 */
public class QuoteException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuoteException(Exception e) {
		super(e);
	}

}
