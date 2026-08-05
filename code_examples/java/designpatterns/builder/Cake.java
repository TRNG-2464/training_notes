package com.revature.designpatterns.builder;

public class Cake {
	
	private final double sugar;
	private final double bakingSoda;
	private final double vegOil;
	private final double vanilla;
	private final double flour;
	private final double almondMilk;

	// Nested Builder Class - used to create Cake Objects
	public static class Builder {
		private double sugar;
		private double bakingSoda;
		private double vegOil;
		private double vanilla;
		private double flour;
		private double almondMilk;
		
		public Builder sugar(double cups) {
			this.sugar = cups;
			return this;
		}
		
		public Builder bakingSoda(double teaspoons) {
			this.bakingSoda = teaspoons;
			return this;
		}
		
		public Builder vegOil(double cups) {
			this.vegOil = cups;
			return this;
		}
		
		public Builder vanilla(double teaspoons) {
			this.vanilla = teaspoons;
			return this;
		}
		
		public Builder flour(double cups) {
			this.flour = cups;
			return this;
		}
		
		public Builder almondMilk(double cups) {
			this.almondMilk = cups;
			return this;
		}
		
		public Cake build() {
			return new Cake(this);
		}
	}
	
	public Cake(Builder builder) {
		this.sugar = builder.sugar;
		this.bakingSoda = builder.bakingSoda;
		this.vegOil = builder.vegOil;
		this.vanilla = builder.vanilla;
		this.flour = builder.flour;
		this.almondMilk = builder.almondMilk;
	}

	@Override
	public String toString() {
		return "Cake [sugar=" + sugar + ", bakingSoda=" + bakingSoda + ", vegOil=" + vegOil + ", vanilla=" + vanilla
				+ ", flour=" + flour + ", almondMilk=" + almondMilk + "]";
	}

}
