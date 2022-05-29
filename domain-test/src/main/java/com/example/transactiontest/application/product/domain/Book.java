package com.example.transactiontest.application.product.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

	private String author;
	private String isbn;

	protected Book() {
	}

	private Book(Builder builder) {
		super(builder.name, builder.price, builder.stockQuantity);
		this.author = builder.author;
		this.isbn = builder.isbn;
	}

	public static class Builder {
		private String name;
		private int price;
		private int stockQuantity;

		private String author;
		private String isbn;

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
			return new Book(this);
		}
	}

	@Override
	public String toString() {
		return "Book{" +
				"id='" + getId() + '\'' +
				", author='" + author + '\'' +
				", isbn='" + isbn + '\'' +
				'}';
	}
}
