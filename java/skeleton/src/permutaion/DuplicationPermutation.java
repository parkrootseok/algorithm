package permutaion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Duplication Permutaion (중복 순열)
 * @author parkrootseok
 **/

public class DuplicationPermutation {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;

    static int elementNumber = 4;
    static int selectNumber = 2;

    static int[] elements = {1, 2, 3, 4};
    static int[] permutation;
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        permutation = new int[selectNumber];
        isUsed = new boolean[elementNumber + 1];
        permutation(0);

        bw.write(sb.toString());
        bw.close();

    }

    /**
     * 중복 원소를 포함하는 순열
     * @param level -> 현재 순열의 원소가 들어갈 자리
     */
    public static void permutation(int level) {

        /**
         * 기저 조건
         * 1. 현재 레벨이 총 선택할 수 와 같다면 종료
         * -> 모든 자리에 사용할 원소가 삽입이 되었음을 의미
         */
        if (level == selectNumber) {

            // 만들어진 수열을 출력 후 종료
            for (int pNumber : permutation) {
                sb.append(pNumber).append(" ");
            }
            sb.append("\n");

            return;
        }

        // 이전 기저 조건에서 종료가 되지 않았음은 아직 수열에 사용한 원소가 모두 삽입되지 않았음을 의미 하므로 현재 레벨에 원소를 삽입하는 과정을 진행
        for (int elementIdx = 0; elementIdx < elementNumber; elementIdx++) {

            /**
             * 전처리 로직
             * 1. 현재 level에 사용할 원소를 삽입
             * 2. 사용한 원소에 대한 사용 여부를 표시
             */
            permutation[level] = elements[elementIdx];
            isUsed[elements[elementIdx]] = true;

            /**
             * 재귀 함수 호출
             * 1. 현재 level + 1을 파라미터로 재귀 호출
             * -> 현재 level에는 이미 원소가 삽입되어 있으므로 하나 증가 시켜야함
             */
            permutation(level + 1);

            /**
             * 후처리 로직
             * 1. 이전에 사용했던 원소를 다음에 만들어질 수열에서 사용해야 하기 때문에 사용 여부를 초기화
             */
            isUsed[elements[elementIdx]] = false;

        }

    }

}