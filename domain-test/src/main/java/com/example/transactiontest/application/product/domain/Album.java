package com.example.transactiontest.application.product.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

	private final String artist;
	private final String etc;

	@Builder
	private Album(Long id, String name, int price, int stockQuantity, String artist, String etc) {
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
}
