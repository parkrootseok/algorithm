import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.accessibility.AccessibleTable;
import javax.swing.border.EtchedBorder;

/***
 * BOJ_15961_회전초밥
 * @author parkrootseok
 * 
 * - 벨트의 임의의 한 위치부터 k개를 연속으로 먹을 경우 할인된 정액 가격 제공
 * - 행사에 참가할 경우 쿠폰에 적혀진 초밥 하나를 무료 제공(없다면 만들어서 제공)
 * - 가능한 다양한 종류의 초밥을 먹을거임
 * - 음식 종류, 메뉴에 있는 초밥 가짓수, 연속해서 먹는 접시의 개수, 쿠폰 번호가 주어지고 가장 최대로 먹을 수 있는 가짓수는?
 * 
 * 1. 필요한 정보를 받는다
 * 2. 벨트를 초기화.
 * 3. eatableContinuosNumber수 만큼 크기를 잘라 Sliding하여 먹을 수 있는 가짓수를 구한다.
 *  3-1. 쿠폰으로 먹을 수 있는 초밥을 처리
 *  3-2. 초기값을 위해 eatableContinuosNumber만큼 먹을 수 있는 초밥수를 카운트
 *  3-3. 가장 처음에 선택한 초밥에 대한 카운트를 감소시키고 0이라면 가짓 수를 줄인다.
 *  3-4. 다음으로 고를 초밥이 없는 초밥이라면 가짓수를 증가
 * 
 */

public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int dishNumber;
	static int sushiNumber;
	static int eatableContinuosNumber;
	static int cuponNumber;

	static int[] dishes;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 필요한 정보를 받는다
		// 벨트에 놓인 접시 수, 초밥 가짓수, 연속해서 먹는 접시 수, 쿠본 번호 
		inputs = br.readLine().trim().split(" ");
		dishNumber = Integer.parseInt(inputs[0]);
		sushiNumber = Integer.parseInt(inputs[1]);
		eatableContinuosNumber = Integer.parseInt(inputs[2]);
		cuponNumber = Integer.parseInt(inputs[3]);

		// 2. 벨트 초기화
		dishes = new int[dishNumber];
		for (int beltIdx = 0; beltIdx < dishes.length; beltIdx++) {
			dishes[beltIdx] = Integer.parseInt(br.readLine().trim());
		}

		// 3. eatableContinuosNumber수 만큼 크기를 잘라 Sliding하여 먹을 수 있는 가짓수를 구한다.
		
		int[] eatableSushi = new int[sushiNumber + 1];
		
		// 3-1. 쿠폰으로 먹을 수 있는 초밥을 처리
		int eatableCount = 1;
		eatableSushi[cuponNumber]++;
		
		// 3-2. 초기값을 위해 eatableContinuosNumber만큼 먹을 수 있는 초밥수를 카운트
		for (int beltIdx = 0; beltIdx < eatableContinuosNumber; beltIdx++) {

			// 현재 먹을 수 있는 초밥이아니라면
			if (eatableSushi[dishes[beltIdx]] == 0) {

				// 먹을 수 있는 초밥 개수를 카운트
				eatableCount++;

			}
			
			// 가지고 있는 초밥 개수를 카운트
			eatableSushi[dishes[beltIdx]]++;

		}

		int maxEatableCount = eatableCount;
		for (int curDish = 0; curDish < dishNumber; curDish++) {
			

			//  3-3. 가장 처음에 선택한 초밥에 대한 카운트를 감소시키고 0이라면 가짓 수를 줄인다.
			
			int firstInSushi = dishes[curDish];
			
			// 가지고 있는 초밥 개수를 감소
			eatableSushi[firstInSushi]--;
			
			if(eatableSushi[firstInSushi] == 0) {
				// 가짓수 감소
				eatableCount--;
			}
			

			// 3-4. 다음으로 고를 초밥이 없는 초밥이라면 가짓수를 증가
			int lastInSushi = dishes[(curDish + eatableContinuosNumber) % dishNumber];
			if (eatableSushi[lastInSushi] == 0) {

				// 가짓수 증가
				eatableCount++;

			}
			
			// 가지고 있는 초밥 개수 증가
			eatableSushi[lastInSushi]++;

			
			maxEatableCount = Math.max(maxEatableCount, eatableCount);
			
		}

		sb.append(maxEatableCount);
		bw.write(sb.toString());
		bw.close();

	}

}