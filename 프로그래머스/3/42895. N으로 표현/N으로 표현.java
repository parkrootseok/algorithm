import java.util.*;

class Solution {
    
    public static List<Set<Integer>> numbers;
        
    public int solution(int N, int number) {

        if (N == number) {
            return 1;
        }
        
        numbers = new ArrayList<>();
        for (int count = 0; count <= 8; count++) {
            numbers.add(new HashSet<>());
        }
        
        numbers.get(1).add(N);
		
        for (int prev = 2; prev <= 8; prev++) {
         
            Set<Integer> curNumbers = numbers.get(prev);
            curNumbers.add(Integer.parseInt(String.valueOf(N).repeat(prev)));
            
            for (int post = 1; post <= prev; post++) {
                Set<Integer> prevNumbers = numbers.get(post);
                Set<Integer> nextNumbers = numbers.get(prev - post);    
                
                for (int prevNumber : prevNumbers) {
                    for (int nextNumber : nextNumbers) {
                            
                        curNumbers.add(prevNumber + nextNumber);
                        curNumbers.add(prevNumber - nextNumber);
                        curNumbers.add(prevNumber * nextNumber);
                        if (prevNumber != 0 && nextNumber != 0) {
                            curNumbers.add(prevNumber / nextNumber);
                        }

                    }
                }
                
            }                    
            
        }
        
        for (Set<Integer> s : numbers) {
            if (s.contains(number)) {
                return numbers.indexOf(s);
            }
                
        }
        
        return -1;
        
    }
    
}