package algorithm.combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Duplication Combination(중복 조합)
 * @author parkrootseok
 */

public class DuplicationCombination {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;

    static int elementNumber = 4;
    static int selectNumber = 2;

    static int[] elements = {1, 2, 3, 4};
    static int[] combination;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        combination = new int[selectNumber];
        combination(0, 0);

        bw.write(sb.toString());
        bw.close();

    }

    public static void combination(int insertIdx, int startElementIdx) {

        /**
         * 기저 조건
         * 1. 삽입할 위치가 선택할 갯수와 같다면 조합이 완성
         */
        if (insertIdx == selectNumber) {

            // 만들어진 조합을 출력 후 종료
            for (int cNumber : combination) {
                sb.append(cNumber).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int elementIdx = startElementIdx; elementIdx < elementNumber; elementIdx++) {

            /**
             * 전처리 로직
             * 1. 현재 insertIdx에 원소를 삽입
             */
            combination[insertIdx] = elements[elementIdx];

            /**
             * 재귀 호출
             * 1. insertIdx + 1(원소를 삽입할 자리를 변경), elmentIdx(다음 조합에 사용할 원소의 시작점)을 파라미터로 사용하여 재귀 호출
             * -> 중복을 방지하기 위해 다음에 사용할 원소를 현재 사용한 원소 + 1을 해주었지만, 중복 조합은 현재 사용한 원소를 그대로 재귀 호출 함수에 전달
             */
            combination(insertIdx + 1, elementIdx);

        }

    }

}