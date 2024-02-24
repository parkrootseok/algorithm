package powerset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * PowerSet(부분 집합)
 * @author parkrootseok
 **/

public class PowerSet {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;

    static int elementNumber = 4;
    static int[] elements = {1, 2, 3, 4};
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        isUsed = new boolean[elementNumber];
        powerSet(0);

        bw.write(sb.toString());
        bw.close();

    }

    public static void powerSet(int elementIdx) {

        /**
         * 기저 조건
         * 1. 시작할 원소의 인덱스가 원소의 갯수랑 같다면 부분집합이 완성
         */
        if (elementIdx == elementNumber) {

            // 부분 집합에 포함하는 원소를 출력 후 종료
            for (int idx = 0; idx < isUsed.length; idx++) {

                if (isUsed[idx]) {
                    sb.append(elements[idx]).append(" ");
                }

            }
            sb.append("\n");

            return;
        }

        /**
         * 전처리 로직
         * 1. 현재 원소를 사용한다.
         */
        isUsed[elementIdx] = true;

        /**
         * 재귀 호출
         * 1. 다음에 집합에 포함할 원소에 대한 인덱스를 전달
         * -> 이때 전달되는 isUsed는 현재 사용할 원소 인덱스를 사용하여 전달됨
         */
        powerSet(elementIdx + 1);


        /**
         * 전처리 로직
         * 1. 현재 원소를 사용하지 않는다.
         */
        isUsed[elementIdx] = false;

        /**
         * 재귀 호출
         * 1. 다음에 집합에 포함할 원소에 대한 인덱스를 전달
         * -> 이때 전달되는 isUsed는 현재 사용할 원소 인덱스에 대해서 사용하지 않고 전달
         */
        powerSet(elementIdx + 1);

    }

}