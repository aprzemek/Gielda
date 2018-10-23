package com.sda.gielda;

public class Main {
	public static void main(String[] args) {
		final StockMarket sm = new StockMarket();
		new Thread(sm).start();


	}
}
