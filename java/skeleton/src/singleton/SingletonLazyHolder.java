package singleton;

/**
 * 정적(static) 멤버 + Lazy Holder(중첩 클래스)
 * - 내부 클래스를 하나 더 생성하여 Singleton 클래스가 최초에 로딩되더라도 함께 초기화가 되지 않음
 *
 * 로직
 * 1. 메서드 호출 시 singletonInstanceHolder 클래스가 로딩되어 인스턴스 생성 후 반환
 *
 *
 */
public class SingletonLazyHolder {

	private static class singletonInstanceHolder {
		private static final SingletonLazyHolder instance = new SingletonLazyHolder();
	}

	public static SingletonLazyHolder getInstance() {
		return singletonInstanceHolder.instance;
	}

}