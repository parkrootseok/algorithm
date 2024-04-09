package designpattern.singleton;

/**
 * 단순 메서드 호출 + Synchronized Keyword
 *
 * 로직
 * 1. getInstance() 메서드 호출
 * 2. 최초 호출 스레드가 아닌 다른 스레드가 접근하지 못하도록 잠금
 * 3. 인스턴스가 존재하는지 확인
 *  3-1. 존재하지 않으면 생성 작업 실행
 *  3-2. 존재하면 별도의 작업 미실행
 * 4. 인스턴스 반환
 *
 * 단점
 * - 메서드를 호출할 때 마다 lock이 걸려 성능저하 우려
 *
 */
public class SingletonMethodSync {

	private static SingletonMethodSync instance;

	private SingletonMethodSync() {}

	public static synchronized SingletonMethodSync getInstance() {

		if (instance == null) {
			instance = new SingletonMethodSync();
		}

		return instance;

	}

}
