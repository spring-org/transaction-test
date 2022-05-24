package com.example.transactiontest.application.order.domain;

import com.example.transactiontest.application.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@Setter
	@Column(name = "ORDER_ID")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private final List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "DELIVERY_ID")
	private Delivery delivery;

	@Setter
	private LocalDateTime orderDate;

	@Setter
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	// 연관관계 메서드
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}

	// business logic
	public static Order createOrder(Long id, Member member, Delivery delivery, OrderItem... orderItems) {
		Order order = new Order();
		order.setId(id);
		order.setMember(member);
		order.setDelivery(delivery);

		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}

		order.setStatus(OrderStatus.ORDER);
		order.setOrderDate(LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ISO_DATE_TIME));
		return order;
	}

	public Order cancel() {
		if (delivery.getStatus() == DeliveryStatus.COMP) {
			throw new RuntimeException("이미 배송완료된 상품은 취소가 불가능합니다.");
		}
		this.setStatus(OrderStatus.CANCEL);
		for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
		return this;
	}

	// 조회
	public int getTotalPrice() {
		int totalPrice = 0;
		for (OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}

		return totalPrice;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Order)) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", member=" + member +
				", orderItems=" + orderItems +
				", delivery=" + delivery +
				", orderDate=" + orderDate +
				", status=" + status +
				'}';
	}
}
