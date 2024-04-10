package designpattern.strategy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	List<Item> items;

	public ShoppingCart() {
		this.items = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		this.items.remove(item);
	}

	public int getTotalPrice() {

		int totalPrice = 0;
		for (Item item : items) {
			totalPrice += item.getPrice();
		}

		return totalPrice;

	}

	public void pay(PaymentStrategy paymentStrategy) {
		int amount = getTotalPrice();
		paymentStrategy.pay(amount);
	}

}
