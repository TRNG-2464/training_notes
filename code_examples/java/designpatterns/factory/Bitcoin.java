package com.revature.designpatterns.factory;

public class Bitcoin implements Currency {

	@Override
	public String getSymbol() {
		return "BTC";
	}

}
