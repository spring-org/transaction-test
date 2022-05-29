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

	protected Movie() {
	}

	private Movie(Builder builder) {
		super(builder.name, builder.price, builder.stockQuantity);
		this.director = builder.director;
		this.actor = builder.actor;
	}

	public static class Builder {
		private String name;
		private int price;
		private int stockQuantity;

		private String director;
		private String actor;

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
			return new Movie(this);
		}
	}

	@Override
	public String toString() {
		return "Movie{" +
				"id='" + getId() + '\'' +
				", director='" + director + '\'' +
				", actor='" + actor + '\'' +
				'}';
	}
}
