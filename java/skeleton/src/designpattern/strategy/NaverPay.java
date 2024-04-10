package designpattern.strategy;

public class NaverPay implements PaymentStrategy {

	private String requestId;
	private String password;

	public NaverPay(String requestId, String password) {
		this.requestId = requestId;
		this.password = password;
	}

	@Override
	public void pay(int amount) {
		System.out.println("RequestID: " + requestId);
		System.out.println("Amout: " + amount);
		System.out.println("Payment Options: NaverPay");
	}

}
