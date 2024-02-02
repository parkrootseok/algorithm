import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2961_도영이가만든맛있는음식
 * @author parkrootseok
 * 
 * - 재료 N개
 * - 재료는 신맛, 쓴맛이 존재
 * - 신맛은 곱 증가, 쓴맛은 합 증가
 * - 둘 의 차이를 작게 만들거임
 * - 재료는 적어도 하나를 만들어야 함
 * - 가장 차이가 적은 요리를 구해라
 * 
 * 1. 재료의 갯수를 입력
 * 2. 재료에 대한 신맛과 쓴맛에 대한 정보를 입력
 * 3. 재료에 대한 부분집합을 생성
 *  3-1. 완성된 집합의 재료 중 가장 차이가 적은 것을 고른다
 *  3-2. 아무것도 쓰지 않은 경우는 종료
 *  
 */

class Main {
	
	static class Ingredient  {
		
		int sour;
		int bitter;
		
		public Ingredient(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
		
	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuffer sb;
	static String[] inputs;

	static int difference;
	static int ingredientNumber;
	static Ingredient[] ingredients;

	public static void main(String args[]) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuffer();
		
		// 1. 재료의 갯수를 입력
		ingredientNumber = Integer.parseInt(br.readLine().trim());
		ingredients = new Ingredient[ingredientNumber];
		
		// 2. 재료에 대한 신맛과 쓴맛에 대한 정보를 입력
		for(int index = 0; index < ingredientNumber; index++) {
			
			inputs = br.readLine().trim().split(" ");
			ingredients[index] = new Ingredient(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
			
		}
		
		// 3. 재료에 대한 부분집합을 생성
		difference = Integer.MAX_VALUE;
		for (int numbers = 1; numbers < (1 << ingredients.length); numbers++) {
			
			int totalSour = 1;
			int totalBitter = 0;
			
			for(int bitPosition = 0; bitPosition < ingredients.length; bitPosition++) {
				
				// bitPostion에 해당하는 bit가 1이라면
				if((numbers & 1 << bitPosition) != 0) {
					
					// 맛을 추가
					totalSour *= ingredients[bitPosition].sour;
					totalBitter += ingredients[bitPosition].bitter;
					
				}
				
			}
			
			// 3-1. 완성된 집합의 재료 중 가장 차이가 적은 것을 고른다
			difference = Math.min(difference, Math.abs(totalBitter - totalSour));
			
		}
		
		sb.append(difference).append("\n");
		bw.write(sb.toString());
		bw.close();
		return;

	}

}