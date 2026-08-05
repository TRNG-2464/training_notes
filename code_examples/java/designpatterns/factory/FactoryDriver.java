package com.revature.designpatterns.factory;

public class FactoryDriver {

	public static void main(String[] args) {
		
		String country;
		
		country = "USA";
		Currency usa = CurrencyFactory.createCurrency(country);
		System.out.println("USA currency: " + usa.getSymbol());
		
		country = "Italy";
		Currency italy = CurrencyFactory.createCurrency(country);
		System.out.println("Italy currency: " + italy.getSymbol());
		
		country = "China";
		Currency china = CurrencyFactory.createCurrency(country);
		System.out.println("China currency: " + china.getSymbol());
		
		country = "Venezuela";
		Currency venezuela = CurrencyFactory.createCurrency(country);
		System.out.println("Venezuela currency: " + venezuela.getSymbol());
	}
}
