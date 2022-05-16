package com.example.transactiontest.application.product.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {
	private final String director;
	private final String actor;

	public Movie(Long id, String name, int price, int stockQuantity, String director, String actor) {
		super(id, name, price, stockQuantity);
		this.director = director;
		this.actor = actor;
	}

	@Override
	public String toString() {
		return "[제목:" + getName() + " 감독:" + director + " 배우:" + actor + "]";
	}
}
