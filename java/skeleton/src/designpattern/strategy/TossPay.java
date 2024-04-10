package designpattern.strategy;

public class TossPay implements PaymentStrategy {

	private String requestId;
	private String accountNumber;

	public TossPay(String requestId, String accountNumber) {
		this.requestId = requestId;
		this.accountNumber = accountNumber;
	}

	@Override
	public void pay(int amount) {
		System.out.println("RequestID: " + requestId);
		System.out.println("Amout: " + amount);
		System.out.println("Payment Options: TossPay");
	}

}
