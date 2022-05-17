package com.example.transactiontest.application.product.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
	private final String author;
	private final String isbn;

	public Book(Long id, String name, int price, int stockQuantity, String author, String isbn) {
		super(id, name, price, stockQuantity);
		this.author = author;
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "[제목:" + getName() + " 저자:" + author + "]";
	}
}
