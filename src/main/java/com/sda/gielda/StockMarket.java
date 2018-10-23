package com.sda.gielda;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


public class StockMarket implements Runnable {

	private static final Random RANDOM_GENERATOR = new Random();

	private Map<Stock, Double> stocksMap;

	public StockMarket() {
		this.stocksMap = new ConcurrentHashMap<>();

		this.stocksMap.put(Stock.GOOGLE, 1249.10);
		this.stocksMap.put(Stock.APPLE, 208.88);
		this.stocksMap.put(Stock.ORACLE, 48.54);
		this.stocksMap.put(Stock.IBM, 144.77);
	}


	@Override
	public void run() {
		while (!Thread.interrupted()) {
			for (Stock stock : stocksMap.keySet()) {
				stocksMap.compute(stock, (k, v) -> changeStockPrice(v));
			}
			System.out.println("GIEŁDA: Nowe ceny akcji: " + stocksMap);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ignored) {
			}
		}
	}

	public double getCurrentPrice(Stock stock) {
		return stocksMap.get(stock);
	}

	private static Double changeStockPrice(Double currentPrice) {
		double newPrice = currentPrice != null ? currentPrice : 0.0;
		final int action = RANDOM_GENERATOR.nextInt(6);
		switch (action) {
			case 0:
				newPrice *= 0.95;
				break;
			case 1:
				newPrice *= 1.05;
				break;
			case 2:
				newPrice += RANDOM_GENERATOR.nextInt(5);
				break;
			case 3:
				newPrice -= RANDOM_GENERATOR.nextInt(2);
				break;
			case 4:
				newPrice += RANDOM_GENERATOR.nextInt(100) / 100.0;
				break;
			case 5:
				newPrice -= RANDOM_GENERATOR.nextInt(100) / 100.0;
				break;
		}
		newPrice = Math.max(newPrice, 1);
		newPrice = Math.min(newPrice, 2000);
		return newPrice;
	}


}
