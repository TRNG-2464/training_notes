package com.revature.oop.polymorphism.methods;

public class Comic extends Book {

	// String name;
	// int pageCount;
	public String author;
	String illustrator;

	public Comic(String name, int pageCount, String author, String illustrator) {
		super(name, pageCount);
		this.author = author;
		this.illustrator = illustrator;
		// TODO Auto-generated constructor stub
	}

	public static void main(String... args) {
		Comic c = new Comic("Iron Man Vol 1", 40, "Stan Lee", "Stan Lee");
		c.name = "Iron Man Vol 3";
		c.pageCount = 45;
		c.read();
	}
}
