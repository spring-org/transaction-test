package com.example.transactiontest.application.category.domain;

import com.example.transactiontest.application.product.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private final List<Category> child = new ArrayList<>();

	protected Category() {
	}

	private Category(String name) {
		this.name = name;
	}

	public static Category of(String name) {
		return new Category(name);
	}

	public Category addChildCategory(Category child) {
		this.child.add(child); // parent에 sub 카테고리 추가
		child.setParent(this); // sub 카테고리에 상위 카테고리 연결
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Category)) return false;
		Category category = (Category) o;
		return Objects.equals(id, category.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", parent=" + parent +
				'}';
	}
}
