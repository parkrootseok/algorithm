package singleton;

/**
 * 단순 메서드 호출
 *
 * 로직
 * 1. getInstance() 메서드 호출
 * 2. 인스턴스가 존재하는지 확인
 *  2-1. 존재하지 않으면 생성 작업 실행
 *  2-2. 존재하면 별도의 작업 미실행
 * 3. 인스턴스 반환
 *
 * 단점
 * - 원자성 결여
 *  - 2개의 Thread에서 동시에 메서드를 호출하면 총 2개의 인스턴스가 생성될 수 있음
 *  - 즉, 멀티 쓰레드 환경에서 사용하기에 부적절함
 *
 */
public class SingletonMethod {

	private static SingletonMethod instance;

	private SingletonMethod() {}

	public static SingletonMethod getInstance() {

		if (instance == null) {
			instance = new SingletonMethod();
		}

		return instance;

	}

}
