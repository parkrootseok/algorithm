package designpattern.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class Shopping {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		ShoppingCart shoppingCart = new ShoppingCart();

		Item chicken = new Item("지코바", 15000);
		Item cola = new Item("코카콜라", 1500);

		shoppingCart.addItem(chicken);
		shoppingCart.addItem(cola);

		// 컨텍스트 안에서 여러 결제 방식 전략들에 대한 교체 가능
		System.out.print("결제 방식을 입력하세요(1. NaverPay, 2. TossPay, 3. KaKaoPay: ");
		String payment = input.readLine().trim();

		switch (payment) {
			case "1" -> {
				shoppingCart.pay(new NaverPay(UUID.randomUUID().toString(), "1030"));
			}

			case "2" -> {
				shoppingCart.pay(new TossPay(UUID.randomUUID().toString(), "111-111-111"));
			}

			case "3" -> {
				shoppingCart.pay(new KaKaoPay(UUID.randomUUID().toString(), "parkrootseok"));

			}

		}

	}

}
