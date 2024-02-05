import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * SWEA_5215_햄버거다이어트(subset)
 * @author parkrootseok
 * 
 * - 정해진 칼로리를 넘지 않고, 선호도가 높은 햄버거를 제작
 * - 해당하는 햄버거의 점수를 출력
 * 
 * 1. 테스트 케이스 횟수 입력
 * 2. 재료 갯수와 제한 칼로리 입력
 * 3. 재료에 대한 정보 입력
 * 4. 재료에 대한 부분집합을 만들고
 *  4-1. 부분집합에 포함된 재료들의 칼로리를 계산
 *  4-2. 제한 칼로리를 넘지 않는다면
 *  4-3. 계산한 점수가 최대값이라면 초기화
 */

class Ingredient {

    int score;
    int cal;

    public Ingredient(String score, String cal) {
        this.score = Integer.parseInt(score);
        this.cal = Integer.parseInt(cal);
    }


}

class Solution {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static String[] inputs;

    static int ingredientNumber;
    static int limitKcal;
    static int ANSWER;
    static int tcNumber;
    static Ingredient[] ingredients;

    public static void main(String args[]) throws Exception {
    	
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
    	
		// 1. 테스트 케이스 횟수 입력
        tcNumber = Integer.parseInt(br.readLine().trim());
        

        for (int curTC = 1; curTC <= tcNumber; curTC++) {

        	// 2. 재료 갯수와 제한 칼로리 입력
            inputs = br.readLine().trim().split(" ");
            ingredientNumber = Integer.parseInt(inputs[0]);
            limitKcal = Integer.parseInt(inputs[1]);

            // 3. 재료에 대한 정보 입력
            ingredients = new Ingredient[ingredientNumber];
            for (int idx = 0 ; idx < ingredientNumber ; idx++) {
                inputs = br.readLine().split(" ");
                ingredients[idx] =  new Ingredient(inputs[0], inputs[1]);
            }
            
            // 4. 재료에 대한 부분집합을 만들고
            ANSWER = 0;
            for(int subset = 1; subset < (1 << ingredientNumber); subset++) {
            	
            	int curCal = 0;
            	int curScore = 0;
            	
            	// 4-1. 부분집합에 포함된 재료들의 칼로리를 계산
            	for(int bit = 0; bit < ingredientNumber; bit++) {
            		
            		if((subset & 1 << bit) != 0) {
            			curCal += ingredients[bit].cal;
            			curScore += ingredients[bit].score;
            		}
            		
            	}
            	
            	// 4-2. 제한 칼로리 보다 이하라면 
            	if(curCal <= limitKcal) {
            		
            		// 4-3. 계산한 점수가 최대값이라면 초기화
            		ANSWER = Math.max(ANSWER, curScore);
            		
            	}
            	
            }

            
            sb.append("#").append(curTC).append(" ").append(ANSWER).append("\n");

        }

        bw.write(sb.toString());
        bw.close();

    }

}