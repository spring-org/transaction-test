package com.example.transactiontest.application.member.stub;

import com.example.transactiontest.application.member.domain.Address;
import com.example.transactiontest.application.member.domain.Member;

public final class StubMember {

	private StubMember() {}

	public static Address address(String city, String street, String zipcode) {
		return new Address.Builder(city, street, zipcode).build();
	}

	public static Member makeMember(Long id, Address address) {
		return new Member.Builder(id)
				.address(address)
				.build();
	}
}
