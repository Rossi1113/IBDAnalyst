package org.luoxi.ibd.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.luoxi.ibd.config.ConfigFactory;
import org.luoxi.ibd.model.TimePeriod;

import com.google.common.collect.Lists;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class QuoteServiceTest {

	public static void main(String[] args) throws Exception {
//		((QuoteService)ConfigFactory.get().getBean("quoteService")).getHistoryQuotes("^IXIC", TimePeriod.ONE_WEEK);
		((QuoteService)ConfigFactory.get().getBean("quoteService")).getHistoryQuotes("CRM", TimePeriod.ONE_WEEK);

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.WEEK_OF_YEAR, -52);

		Stock stock = YahooFinance.get("cohr", true);
		final List<HistoricalQuote> historyQuotes = stock.getHistory(from, to, Interval.DAILY);

		System.out.println(historyQuotes);
		System.out.println(stock.getSymbol() + " is in cup shape ? " + isCupShape(historyQuotes));
	}

	/**
	 * Detect whether the stock is a good cup shape given the historical quotes.
	 *
	 * The way to detect this :
	 * 1. Find the highest quote in history data
	 * 2. Detect current price is lower but close to history high
	 * 3. Detect the very first price has a gap with history high
	 * 4. Time gap between current price and history high should NOT be too short
	 *
	 *
	 * @param quotes - history quote ordering from latest to oldest
	 * @return
	 */
	private static boolean isCupShape(List<HistoricalQuote> quotes) {
		HistoricalQuote high = null, low = null, current = quotes.get(0);
		Double highPrice = Double.MIN_VALUE, lowPrice = Double.MAX_VALUE, currentPrice = current.getClose().doubleValue();

		for (HistoricalQuote quote : quotes) {
			if (quote.getClose().doubleValue() > highPrice) {
				highPrice = quote.getClose().doubleValue();
				high = quote;
			}

			if (quote.getClose().doubleValue() < lowPrice) {
				lowPrice = quote.getClose().doubleValue();
				low = quote;
			}
		}

		// If it's just past the highest recently, we want to see whether there is a "similar" high price point before
		List<HistoricalQuote> highCandidates = Lists.newArrayList();
		if (current.getClose().doubleValue() == highPrice ||
				((highPrice - current.getClose().doubleValue())/highPrice < 0.05 && TimeUnit.DAYS.convert(Math.abs(high.getDate().getTimeInMillis() - current.getDate().getTimeInMillis()), TimeUnit.MILLISECONDS) < 10)) {
			for (HistoricalQuote quote : quotes) {
				if ((highPrice-quote.getClose().doubleValue())/highPrice < 0.05 &&
						TimeUnit.DAYS.convert(Math.abs(quote.getDate().getTimeInMillis() - current.getDate().getTimeInMillis()), TimeUnit.MILLISECONDS) >20) {
					highCandidates.add(quote);
				}
			}
		}

		// If not empty, update high quote to the previous similar one which has the highest price,
		// instead of recently real highest price quote
		if (!highCandidates.isEmpty()) {
			high = highCandidates.get(0);
			highPrice = highCandidates.get(0).getClose().doubleValue();
			for (HistoricalQuote quote : highCandidates) {
				if (quote.getClose().doubleValue() > highPrice) {
					high = quote;
					highPrice = quote.getClose().doubleValue();
				}
			}
		}

		// Print out adjuested information
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Highest quote is " + highPrice + " on " + format.format(high.getDate().getTime()));
		System.out.println("Lowest quote is " + lowPrice + " on " + format.format(low.getDate().getTime()));
		System.out.println("Current quote is " + currentPrice + " on " + format.format(current.getDate().getTime()));
		System.out.println("Price change is " + (highPrice - currentPrice) / currentPrice * 100 + "%");
		System.out.println("Time period is " + TimeUnit.DAYS.convert(Math.abs(high.getDate().getTimeInMillis() - current.getDate().getTimeInMillis()), TimeUnit.MILLISECONDS) + " days");


		// ------------------------------- Apply some rules below -------------------------------

		// Rule 1 :
		// Make sure the current price is within +/- 10% of high price
		// And gap between two dates is more than 20 days
		if (Math.abs((highPrice - current.getClose().doubleValue())/highPrice) > 0.1 ||
				TimeUnit.DAYS.convert(Math.abs(high.getDate().getTimeInMillis() - current.getDate().getTimeInMillis()), TimeUnit.MILLISECONDS) < 20) {
			System.out.println("FAILING REASON ********** Not in a good relationship with highest price : "
					+ "Current prices is " + current.getClose().doubleValue()
					+ "; highest prices is " + highPrice
					+ "; Difference is " + (highPrice - current.getClose().doubleValue())/highPrice
					+ "; time gap is " + TimeUnit.DAYS.convert(Math.abs(high.getDate().getTimeInMillis() - current.getDate().getTimeInMillis()), TimeUnit.MILLISECONDS));
			return false;
		}

		// Rule 2 :
		// Make sure current trend in on the right side instead of the left side
		// The cup should at least more than 10% down
		int highIndex = quotes.indexOf(high);
		double lowestWithinRangePrice = highPrice;
		HistoricalQuote lowestWithinRange = null;
		for (int i = 0; i < highIndex; i++) {
			if (quotes.get(i).getClose().doubleValue() < lowestWithinRangePrice) {
				lowestWithinRangePrice = quotes.get(i).getClose().doubleValue();
				lowestWithinRange = quotes.get(i);
			}
		}

		if (lowestWithinRangePrice == current.getClose().doubleValue() ||
				(current.getClose().doubleValue() - lowestWithinRangePrice) < (highPrice - lowestWithinRangePrice)/2 ||
				((highPrice-lowestWithinRangePrice)/highPrice < 0.1)) {
			System.out.println("FAILING REASON ********** Not on the right up side or cup shape is not deep enough : "
					+ "Current prices is " + current.getClose().doubleValue()
					+ "; highest prices is " + highPrice
					+ "; lowest price between two is " +lowestWithinRangePrice);
			return false;
		}

		return true;
	}

}
