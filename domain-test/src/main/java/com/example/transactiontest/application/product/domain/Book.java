package com.example.transactiontest.application.product.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

	private String author;
	private String isbn;

	protected Book() {}

	private Book(Long id, String name, int price, int stockQuantity, String author, String isbn) {
		super(id, name, price, stockQuantity);
		this.author = author;
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "[제목:" + getName() + " 저자:" + author + "]";
	}

	static class Builder {
		private final Long id;
		private String name;
		private int price;
		private int stockQuantity;

		private String author;
		private String isbn;

		public Builder(Long id) {
			this.id = id;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder price(int price) {
			this.price = price;
			return this;
		}

		public Builder stockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
			return this;
		}

		public Builder author(String author) {
			this.author = author;
			return this;
		}

		public Builder isbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		public Book build() {
			return new Book(id, name, price, stockQuantity, author, isbn);
		}
	}
}
