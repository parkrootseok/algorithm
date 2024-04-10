package designpattern.strategy;

public class KaKaoPay implements PaymentStrategy {

	private String requestId;
	private String KakaoId;

	public KaKaoPay(String requestId, String kakaoId) {
		this.requestId = requestId;
		this.KakaoId = kakaoId;
	}

	@Override
	public void pay(int amount) {
		System.out.println("RequestID: " + requestId);
		System.out.println("Amout: " + amount);
		System.out.println("Payment Options: KaKaoPay");
	}

}
