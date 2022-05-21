package com.example.transactiontest.application.order.domain;

import com.example.transactiontest.application.member.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
public class Delivery {

	@Id
	@Column(name = "DELIVERY_ID")
	private Long id;

	@Setter
	@OneToOne(mappedBy = "delivery")
	private Order order;

	@Embedded
	private Address address;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	protected Delivery() {
	}

	public Delivery(Address address) {
		this.address = address;
		this.status = DeliveryStatus.READY;
	}
}
