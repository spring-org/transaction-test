package com.example.transactiontest.application.product.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

	private String director;
	private String actor;

	protected Movie() {}

	private Movie(Long id, String name, int price, int stockQuantity, String director, String actor) {
		super(id, name, price, stockQuantity);
		this.director = director;
		this.actor = actor;
	}

	@Override
	public String toString() {
		return "[제목:" + getName() + " 감독:" + director + " 배우:" + actor + "]";
	}

	static class Builder {
		private final Long id;
		private String name;
		private int price;
		private int stockQuantity;

		private String director;
		private String actor;
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

		public Builder director(String director) {
			this.director = director;
			return this;
		}

		public Builder actor(String actor) {
			this.actor = actor;
			return this;
		}

		public Movie build() {
			return new Movie(id, name, price, stockQuantity, director, actor);
		}
	}
}
