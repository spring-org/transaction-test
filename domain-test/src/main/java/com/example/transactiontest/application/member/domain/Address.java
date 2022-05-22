package com.example.transactiontest.application.member.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Embeddable
public class Address {
	private String city;
	private String street;
	private String zipcode;

	protected Address() {
	}

	private Address(Builder builder) {
		this.city = builder.city;
		this.street = builder.street;
		this.zipcode = builder.zipcode;
	}

	public static class Builder {
		private final String city;
		private final String street;
		private final String zipcode;

		public Builder(String city, String street, String zipcode) {
			this.city = city;
			this.street = street;
			this.zipcode = zipcode;
		}

		public Address build() {
			return new Address(this);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Address)) return false;
		Address address = (Address) o;
		return Objects.equals(city, address.city)
				&& Objects.equals(street, address.street)
				&& Objects.equals(zipcode, address.zipcode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, street, zipcode);
	}

	@Override
	public String toString() {
		return "Address{" +
				"city='" + city + '\'' +
				", street='" + street + '\'' +
				", zipcode='" + zipcode + '\'' +
				'}';
	}
}
