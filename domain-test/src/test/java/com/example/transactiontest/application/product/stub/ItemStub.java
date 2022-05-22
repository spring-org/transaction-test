package com.example.transactiontest.application.product.stub;

import com.example.transactiontest.application.product.domain.Album;
import com.example.transactiontest.application.product.domain.Book;
import com.example.transactiontest.application.product.domain.Item;
import com.example.transactiontest.application.product.domain.Movie;

public final class ItemStub {

	public static Item album(Long id, int price, int stock, String name, String artist, String etc) {
		return new Album.Builder(id)
				.name(name).artist(artist).etc(etc)
				.price(price)
				.stockQuantity(stock)
				.build();
	}

	public static Item book(Long id, int price, int stock, String name, String author, String isbn) {
		return new Book.Builder(id)
				.price(price).stockQuantity(stock).name(name)
				.author(author).isbn(isbn)
				.build();
	}

	public static Item movie(Long id, int price, int stock, String name, String director, String actor) {
		return new Movie.Builder(id)
				.stockQuantity(stock).price(price).name(name)
				.actor(actor).director(director)
				.build();
	}
}
