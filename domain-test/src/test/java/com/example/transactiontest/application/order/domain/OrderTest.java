package com.example.transactiontest.application.order.domain;

import com.example.transactiontest.application.member.domain.Member;
import com.example.transactiontest.application.product.domain.Album;
import com.example.transactiontest.application.product.domain.Item;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.transactiontest.application.member.stub.StubMember.address;
import static com.example.transactiontest.application.member.stub.StubMember.makeMember;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

	@DisplayName("주문 등록")
	@Test
	void testCase1() {
		Member member = makeMember(1L, address("서울시", "논현로", "11길"));
		Item album = new Album.Builder(2L).stockQuantity(10).price(1000)
				.artist("작곡자").etc("팝송")
				.build();

		// 주문상품에 대해서 생성할 때 기존 상품에 대한 재고에 대해서 고려해야 함
		OrderItem orderItem = OrderItem.createOrderItem(album, album.getPrice(), 2);

		Delivery delivery = new Delivery(member.getAddress());
		Order order = Order.createOrder(member, delivery, Arrays.array(orderItem));

		assertThat(order.getMember()).isEqualTo(member);
		assertThat(order.getOrderItems()).contains(orderItem);
		assertThat(order.getDelivery()).isEqualTo(delivery);
	}
}