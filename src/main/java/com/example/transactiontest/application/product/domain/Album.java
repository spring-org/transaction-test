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

	@Override
	public String toString() {
		return "Album{" +
				"artist='" + artist + '\'' +
				", etc='" + etc + '\'' +
				'}';
	}
}
