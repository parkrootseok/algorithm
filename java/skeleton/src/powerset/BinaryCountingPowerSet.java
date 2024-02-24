package powerset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BinaryCountingPowerSet(부분 집합)
 * @author parkrootseok
 **/

public class BinaryCountingPowerSet {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;

    static int elementNumber = 4;
    static int[] elements = {1, 2, 3, 4};

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        powerSet();

        bw.write(sb.toString());
        bw.close();

    }

    public static void powerSet() {

        // 1. 1을 원소 갯수만큼 왼쪽 shift하여 원소 갯수만큼의 bit를 가지는 비트열을 생성
        int bitSequence = 1 << elementNumber;

        // 2. 비트열 탐색
        for(int curBitSequence = 0; curBitSequence < bitSequence; curBitSequence++) {

            // 3. 원소수의 개수를 가지는 비트열 탐색
            for(int curBit = 0; curBit < elementNumber; curBit++) {

                // 3. 현재 bit가 0이 아니라면
                if ((curBitSequence & (1 << curBit)) != 0) {

                    // 집합에 포함
                    sb.append(elements[curBit]).append(" ");

                }

            }

            sb.append("\n");

        }

    }

}