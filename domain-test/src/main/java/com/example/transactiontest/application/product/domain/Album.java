package com.example.transactiontest.application.product.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

	private String artist;
	private String etc;

	protected Album() {
	}

	private Album(Builder builder) {
		super(builder.name, builder.price, builder.stockQuantity);
		this.artist = builder.artist;
		this.etc = builder.etc;
	}

	public static class Builder {
		private String name;
		private int price;
		private int stockQuantity;
		private String artist;
		private String etc;

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

		public Builder artist(String artist) {
			this.artist = artist;
			return this;
		}

		public Builder etc(String etc) {
			this.etc = etc;
			return this;
		}

		public Album build() {
			return new Album(this);
		}

	}

	@Override
	public String toString() {
		return "Album{" +
				"id='" + getId() + '\'' +
				", artist='" + artist + '\'' +
				", etc='" + etc + '\'' +
				'}';
	}
}
