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

	protected Album() {}

	private Album(final Long id, final String name, final int price, final int stockQuantity, final String artist, final String etc) {
		super(id, name, price, stockQuantity);
		this.artist = artist;
		this.etc = etc;
	}

	@Override
	public String toString() {
		return "Album{" +
				"artist='" + artist + '\'' +
				", etc='" + etc + '\'' +
				'}';
	}

	public static class Builder {
		private final Long id;
		private String name;
		private int price;
		private int stockQuantity;

		private String artist;
		private String etc;

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

		public Builder artist(String artist) {
			this.artist = artist;
			return this;
		}

		public Builder etc(String etc) {
			this.etc = etc;
			return this;
		}

		public Album build() {
			return new Album(id, name, price, stockQuantity, artist, etc);
		}
	}
}
