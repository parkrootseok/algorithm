import java.util.*;

/**
 * PG_표편집
 * @author parkrootseok
 */
class Solution {
    
    public String solution(int n, int k, String[] cmd) {
    
        int size = n;
        int pointer = k;
        Stack<Integer> removedRow = new Stack<>();
        for (String c : cmd) {
            
            switch (c.charAt(0)) {
                case 'U':
                    // 현재 선택된 행에서 X칸 아래로 이동
                    pointer -= Integer.parseInt(c.split(" ")[1]);
                    break;
                case 'D':
                    // 현재 선택된 행에서 X칸 위로 이동
                    pointer += Integer.parseInt(c.split(" ")[1]);
                    break;
                case 'C':       
                    removedRow.push(pointer);
                    if (pointer == --size) {
                        // 마지막 행이라면, 포인터를 아래로 이동 
                        pointer--;
                    }
                    break;         
                case 'Z':
                    if (removedRow.pop() <= pointer) {
                        // 복구된 행이, 현재 위치보다 작다면 포인터 증가
                        pointer++;
                    }
                    // 사이즈 증가
                    size++;
                    break;            
            }

        }
        
        StringBuilder answer = new StringBuilder();
        for (int index = 0; index < size; index++) {
            answer.append("O");
        }
        
        while(!removedRow.isEmpty()) {
            answer.insert(removedRow.pop(), "X");
        }

        return answer.toString();
        
    }
    
}