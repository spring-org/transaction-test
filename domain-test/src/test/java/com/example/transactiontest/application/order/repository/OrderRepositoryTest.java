package com.example.transactiontest.application.order.repository;

import com.example.transactiontest.application.member.domain.Member;
import com.example.transactiontest.application.order.domain.*;
import com.example.transactiontest.application.product.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.example.transactiontest.application.product.stub.ItemStub.album;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Order 클래스")
@DataJpaTest
class OrderRepositoryTest {

	@Autowired
	protected OrderRepository orderRepository;
	private Order orderEntity;
	private OrderItem orderItem;
	private Delivery delivery;
	private Member member;

	@Nested
	@DisplayName("주문 등록 관련")
	class OrderTest {

		@BeforeEach
		void setUp() {
			member = new Member.Builder().build();
			delivery = new Delivery(member.getAddress());
			Item item = album(1000, 5, "앨범", "아티스트", "팜");
			orderItem = OrderItem.createOrderItem(item, item.getPrice(), 2);
			orderEntity = Order.createOrder(member, delivery, orderItem);
		}

		@DisplayName("주문 등록 시 주문상태, 배달상태 정보 확인 테스트")
		@Test
		void testCase1() {
			Order order = orderRepository.save(orderEntity);
			Delivery orderDelivery = order.getDelivery();

			assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);
			assertThat(orderDelivery.getStatus()).isEqualTo(DeliveryStatus.READY);
		}

		@DisplayName("주문 등록 및 취소 시 상태 정보 확인 테스트")
		@Test
		void testCase2() {
			Order order = Order.createOrder(member, delivery, orderItem);
			orderRepository.save(order);
			Delivery orderDelivery = order.getDelivery();

			assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);
			assertThat(orderDelivery.getStatus()).isEqualTo(DeliveryStatus.READY);

			order.cancel();

			assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
			assertThat(order.getDelivery().getStatus()).isEqualTo(DeliveryStatus.READY);
		}
	}
}