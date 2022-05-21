package com.example.transactiontest.application.member.domain;

import com.example.transactiontest.application.order.domain.Order;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.transactiontest.core.exception.utils.ArgumentUtils.existsParam;

@Getter
@Entity
public class Member {
	@Id
	@Column(name = "MEMBER_ID")
	private Long id;

	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member")
	private final List<Order> orders = new ArrayList<>();

	protected Member() {
	}

	private Member(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.address = builder.address;
	}

	public Member update(String name, Address address) {
		this.name = existsParam(name, this.name);
		this.address = existsParam(address, this.address);
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Member)) return false;
		Member member = (Member) o;
		return Objects.equals(id, member.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public static class Builder {
		private final Long id;
		private String name;
		private Address address;

		public Builder(Long id) {
			this.id = id;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder address(Address address) {
			this.address = address;
			return this;
		}

		public Member build() {
			return new Member(this);
		}
	}
}
