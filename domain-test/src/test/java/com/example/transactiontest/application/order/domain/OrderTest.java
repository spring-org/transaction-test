package com.example.transactiontest.application.order.domain;

import com.example.transactiontest.application.member.domain.Member;
import com.example.transactiontest.application.product.domain.Item;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.example.transactiontest.application.member.stub.StubMember.address;
import static com.example.transactiontest.application.member.stub.StubMember.makeMember;
import static com.example.transactiontest.application.product.stub.ItemStub.album;
import static com.example.transactiontest.application.product.stub.ItemStub.book;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

	private Member member;
	private Item album;
	private Delivery delivery;
	private Item book;

	@BeforeEach
	void setUp() {
		member = makeMember(1L, address("서울시", "논현로", "11길"));
		album = album(2L, 1000, 10, "앨범명", "아티스트", "팝");
		book = book(3L, 500, 5, "서적명", "저자", "ISBN");
		delivery = new Delivery(member.getAddress());
	}

	@Nested
	@DisplayName("주문 등록 관련")
	class CreateOrderTest {

		@DisplayName("주문 등록 테스트")
		@Test
		void testCase1() {
			// 주문상품에 대해서 생성할 때 기존 상품에 대한 재고에 대해서 고려해야 함
			OrderItem[] array = Arrays.array(
					OrderItem.createOrderItem(4L, album, album.getPrice(), 2)
					, OrderItem.createOrderItem(5L, book, book.getPrice(), 4)
			);
			int totalPrice = java.util.Arrays.stream(array)
					.mapToInt(OrderItem::getTotalPrice)
					.sum();
			Order order = Order.createOrder(member, delivery, array);

			assertThat(order.getMember()).isEqualTo(member);
			assertThat(order.getOrderItems()).contains(array);
			assertThat(order.getDelivery()).isEqualTo(delivery);
			assertThat(order.getTotalPrice()).isEqualTo(totalPrice);
		}
	}

	@Nested
	@DisplayName("주문 등록 및 취소 상태 확인")
	class OrderAndCancelTest {

		@DisplayName("주문 최종 금액 조회 테스트")
		@Test
		void testCase2() {
			OrderItem[] array = Arrays.array(
					OrderItem.createOrderItem(4L, album, album.getPrice(), 2)
					, OrderItem.createOrderItem(5L, book, book.getPrice(), 4)
			);

			Order order = Order.createOrder(member, delivery, array);
			assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDER);

			order.cancel();
			assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
		}
	}
}