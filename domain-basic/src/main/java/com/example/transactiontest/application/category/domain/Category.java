package com.example.transactiontest.application.category.domain;

import com.example.transactiontest.application.product.domain.Item;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(
			name = "CATEGORY_ITEM",
			joinColumns = @JoinColumn(name = "CATEGORY_ID"),
			inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
	)
	private final List<Item> items = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private final List<Category> child = new ArrayList<>();

	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(this);
	}

	private void setParent(Category category) {
		this.id = category.id;
	}
}
