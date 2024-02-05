import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
 * 4. 재료에 대한 조합을 만들고
 *  4-2. 제한 칼로리를 넘으면 종료
 *  4-3. 제한 칼로리 이하라면 최대값 초기화
 *  4-4. 모든 재료를 사요했다면 종료
 *  
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
    
    public static void comibnation(int level, int start, int score, int kcal) {
    	
    	// 4-2. 제한 칼로리를 넘으면 종료
    	if(kcal > limitKcal) {
    		return;
    	}
    	
    	// 4-3. 제한 칼로리 이하라면 최대값 초기화
    	if(kcal <= limitKcal) {
    		ANSWER = Math.max(ANSWER, score);
    	}
    	
    	// 4-4. 모든 재료를 사요했다면 종료
    	if(level == ingredientNumber) {
    		return;
    	}
    	
    	// 조합 생성
    	for(int index = start; index < ingredientNumber; index++) {
    		
    		comibnation(level + 1, index + 1, score + ingredients[index].score, kcal + ingredients[index].cal);
    		
    	}
    	
    }

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
            
            ANSWER = 0;
            comibnation(0, 0, 0, 0);
          

            
            sb.append("#").append(curTC).append(" ").append(ANSWER).append("\n");

        }

        bw.write(sb.toString());
        bw.close();

    }

}