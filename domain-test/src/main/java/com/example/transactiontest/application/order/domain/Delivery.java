package com.example.transactiontest.application.order.domain;

import com.example.transactiontest.application.member.domain.Address;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;

	@OneToOne(mappedBy = "delivery")
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	public void setOrder(Order order) {

	}

}
