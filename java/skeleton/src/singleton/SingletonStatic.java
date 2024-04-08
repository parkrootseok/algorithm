package singleton;

/**
 * 정적(static) 멤버
 * - 정적 멤버나 블록은 런타임이 아닌 최초에 JVM이 클래스 로딩할 때 미리 생성하는 것을 이용
 *
 * 로직
 * 0. static 키워드의 특징으로 인해 클래스 로딩과 동시에 싱글톤 인스턴스 생성
 * 1. 메서드 호출 시 생성된 인스턴스를 반환
 *
 * 단점
 * - 불필요한 자원 낭비
 *  - 싱글톤 인스턴스가 필요없는 경우도 무조건 생성
 *
 */

public class SingletonStatic {

	// 정적 멤버 방식
	private final static SingletonStatic instance = new SingletonStatic();

	// 정적 블록 방식
	// private static SingletonStatic instance = null;
	// static {
	// 	instance = new SingletonStatic();
	// }

	private SingletonStatic() {}

	public static SingletonStatic getInstance() {

		return instance;

	}

}
