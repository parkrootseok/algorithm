package designpattern.factory;

public class FactoryPattern {

	public static void main(String[] args) {

		try {

			// Factory Class를 사용하여 Coffee 객체를 생성
			Coffee coffee = CoffeeFactory.createCoffee(CoffeeType.LATTE);
			System.out.println(coffee.getName());

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
