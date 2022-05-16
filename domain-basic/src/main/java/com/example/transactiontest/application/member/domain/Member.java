package com.example.transactiontest.application.member.domain;

import com.example.transactiontest.application.order.domain.Order;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Member {
	@Id
	@GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;

	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member")
	private final List<Order> orders = new ArrayList<>();
}
