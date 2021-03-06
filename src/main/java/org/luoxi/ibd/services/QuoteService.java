package org.luoxi.ibd.services;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.luoxi.ibd.exception.QuoteException;
import org.luoxi.ibd.model.TimePeriod;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * A service to retrieve stock quote.
 *
 * @author luoxi
 *
 */
public class QuoteService {

	// TODO Some issue with this method
	public double getQuote(String symbol, int year, int month, int day) {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.set(year, month, day);
		to.set(year, month, day);

		Stock stock = YahooFinance.get(symbol, true);
		List<HistoricalQuote> histQuotes = stock.getHistory(from, to, Interval.DAILY);

		if (histQuotes.isEmpty()) {
			return Double.NaN;
		} else {
			return histQuotes.get(0).getClose().doubleValue();
		}
	}

	public Pair<Double, Double> getHistoryQuotes(String symbol, TimePeriod timePeriod) throws QuoteException {
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.WEEK_OF_YEAR, -timePeriod.getNumberOfWeeks());

		final Stock stock;
		try {
			stock = YahooFinance.get(symbol, true);
		} catch (Exception e) {
			throw new QuoteException(e);
		}
		// History is starting one previous day, so here even to is supposed to today, but it's actually yesterday TODO Re-test this during weekends
		final List<HistoricalQuote> historyQuotes = stock.getHistory(from, to, Interval.DAILY);

		return new Pair<Double, Double>() {

			private static final long serialVersionUID = 1L;

			public Double setValue(Double value) {
				return null;
			}

			@Override
			public Double getLeft() {
				return stock.getQuote().getPrice().doubleValue() > 0 ?
						stock.getQuote().getPrice().doubleValue() : historyQuotes.get(0).getClose().doubleValue();
			}

			@Override
			public Double getRight() {
				return historyQuotes.get(historyQuotes.size()-1).getClose().doubleValue();
			}
		};
	}
}
