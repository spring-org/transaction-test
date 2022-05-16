package com.example.transactiontest.application.product.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
	private String author;
	private String isbn;

	@Override
	public String toString() {
		return "[제목:" + getName() + " 저자:" + author + "]";
	}
}
