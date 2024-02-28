import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * SWEA_1952_수영장
 * @author parkrootseok
 * 
 * - 가장 적은 비용으로 수영장을 이용하는 것이 목표
 * - 수영장 판매 이용권 종류
 *  - 1일 이용권
 *  - 1달 이용권
 *  - 3달 이용권(단, 11월 12월 다음년 1월은 불가)
 *  - 1년 이용권
 * - 이용권의 금액과 이용 계획을 토대로 가장 적은 비용으로 이용할 수 있는 방법을 찾자
 * 
 *  1. 테스트 케이스 횟수 입력
 *  2. 이용권 가격 입력
 *  3. 이용 계획 입력
 *  4. 최소 비용을 구한다.
 *   4-1. 현재 달에서 일권으로 계산한 가격과 전달 요금의 합
 *   4-2. 현재 달에서 달권으로 계산한 가격과 전달 요금의 합
 *   4-3. 3월 이후부터는 3개월권 가격도 비교
 *   4-4. 12월 이후부터는 12개월권 가격도 비교
 **/

public class Solution {
	
	static final int TOTAL_MONTH = 12;
	static final int TOTAL_CUPON_NUMBER = 4;
	static final int DAY = 0;
	static final int MONTH_1 = 1;
	static final int MONTH_3 = 2;
	static final int YEAR = 3;
	
	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;
	static String input;

	static int testNumber;
	static int[] price;
	static int[] schedule;
	static int totalPrice;
	static int[] monthPrice;

	public static void main(String[] args) throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 테스트 케이스 횟수 입력.
		testNumber = Integer.parseInt(br.readLine().trim());

		for (int curTest = 1; curTest <= testNumber; curTest++) {
			
			price = new int[TOTAL_CUPON_NUMBER];
			schedule = new int[TOTAL_MONTH + 1];
			
			// 2. 이용권 가격 입력
			inputs = br.readLine().trim().split(" ");
			for (int idx = 0; idx < inputs.length; idx++) {
				price[idx] = Integer.parseInt(inputs[idx]);
			}
			
			// 3. 이용 계획 입력
			inputs = br.readLine().trim().split(" ");
			for (int idx = 1; idx <= TOTAL_MONTH; idx++) {
				schedule[idx] = Integer.parseInt(inputs[idx - 1]);
			}
			
			// 4. 최소 비용을 구한다.
			monthPrice = new int[TOTAL_MONTH + 1];
			
			for(int month = 1; month <= TOTAL_MONTH; month++) {
				
				// 4-1. 현재 달에서 일권으로 계산한 가격과 전달 요금의 합
				monthPrice[month] = monthPrice[month - 1] + (schedule[month] * price[DAY]);
				
				// 4-2. 현재 달에서 달권으로 계산한 가격과 전달 요금의 합
				monthPrice[month] = Math.min(monthPrice[month], monthPrice[month - 1] + (price[MONTH_1]));
				
				// 4-3. 3월 이후부터는 3개월권 가격도 비교
				if (3 <= month) {
					monthPrice[month] = Math.min(monthPrice[month], monthPrice[month - 3] + (price[MONTH_3]));
				}
				
				// 4-4. 12월 이후부터는 12개월권 가격도 비교
				if (12 <= month) {
					monthPrice[month] = Math.min(monthPrice[month], monthPrice[month - 12] + (price[YEAR]));
				}
				
			}
			
			sb.append("#").append(curTest).append(" ").append(monthPrice[TOTAL_MONTH]).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

}