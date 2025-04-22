class Solution {
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        int answer = 0;
        for (int employee = 0; employee < timelogs.length; employee++) {
            
            int limit = getLimit(schedules[employee]);
            int count = 0;
                
            for (int day = 0; day < timelogs[employee].length; day++) {
                
                int curDay = (day + startday) % 7;
                
                // 토요일, 일요일인 경우 검사 제외
                if (6 != curDay && 0 != curDay) {
                    if (timelogs[employee][day] <= limit){
                        count++;
                    }
                }    
            
            }
            
            if (count == 5) {
                answer++;
            }

        }
 
        return answer;
        
    }
    
    public static int getLimit(int time) {
        int limit = time + 10;
        if ((limit / 10) % 10 == 6) {
            limit += 100;
            limit -= 60;
        }
        
        return limit;
    }
    
}