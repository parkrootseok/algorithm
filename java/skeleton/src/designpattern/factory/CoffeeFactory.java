package designpattern.factory;

/**
 * Factory Class를 사용하여 생성 로직 분리
 */
public class CoffeeFactory {

	public static Coffee createCoffee(CoffeeType type) throws IllegalAccessException {

		// CoffeeType을 받아 이에 해당하는 객체를 생성하여 반환
		switch (type) {

			case LATTE -> {
				return new Latte();
			}

			case ESPRESSO -> {
				return new Espresso();
			}

			default -> {
				throw new IllegalAccessException("Invalid Coffee Type: " + type);
			}

		}

	}

}
