package designpattern.singleton;
/**
 * 이중 확인 잠금(Double Checked Locking)
 * - 인스턴스 생성 여부를 싱글톤 패턴 잠금 전에 한 번 객체 생성 전에 한 번 총 2번 체크하여 존재하지 않을 때만 잠금
 *
 * volatile
 * - JAVA는 메인 메모리가 아닌 캐시 메모리에서 변수에 대한 주소를 가져오는데 volatile 키워드를 사용하면 메인 메모리에서 가져오도록 할 수 있음
 *
 * 로직
 * 1. getInstance() 메서드 호출
 * 2. 인스턴스가 존재하는지 확인
 *  3-1. 존재하지 않으면 잠금 후 인스턴스를 생성
 *  3-2. 존재하면 별도의 작업 미실행
 * 4. 인스턴스 반환
 *
 */
public class SingletonDCL {

	private volatile SingletonDCL instance;

	private SingletonDCL() {

	}

	public SingletonDCL getInstance() {

		// 1차 확인
		if (instance == null) {

			// 2차 확인
			synchronized (SingletonDCL.class) {

				if (instance == null) {
					instance = new SingletonDCL();
				}

			}

		}

		return instance;

	}

}
