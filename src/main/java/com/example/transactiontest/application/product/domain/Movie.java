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

	@Override
	public String toString() {
		return "[제목:" + getName() + " 감독:" + director + " 배우:" + actor + "]";
	}
}
