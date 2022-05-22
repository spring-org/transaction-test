package com.example.transactiontest.application.order.domain;

import com.example.transactiontest.application.product.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

	@Id
	@Setter
	@Column(name = "ORDER_ITEM_ID")
	private Long id;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private Item item;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;

	@Setter
	private int orderPrice;
	@Setter
	private int count;

	protected OrderItem() {
	}

	// 생성 메서드
	public static OrderItem createOrderItem(Long id, Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(id);
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);

		item.removeStock(count);
		return orderItem;
	}

	// business logic
	public void cancel() {
		getItem().addStock(count);
	}

	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderItem)) return false;
		OrderItem orderItem = (OrderItem) o;
		return Objects.equals(id, orderItem.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "OrderItem{" +
				"id=" + id +
				", item=" + item +
				", orderPrice=" + orderPrice +
				", count=" + count +
				'}';
	}
}
