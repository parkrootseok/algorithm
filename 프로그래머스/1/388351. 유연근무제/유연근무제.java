class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        int answer = 0;
        
        // 1. 시작날로 부터 일주일 동안 목표 시간에 출근했는지 확인
        // 2. 단, 토요일, 일요일 출근 시간은 제외
        
        for (int employee = 0; employee < timelogs.length; employee++) {
            
            int limit = getLimit(schedules[employee]);
            int count = 0;
            int offset = 0;   
                
            for (int day = 0; day < timelogs[employee].length; day++) {
                
                int curDay = (day + startday) % 7;                
                if (6 != curDay && 0 != curDay) {
                    if (timelogs[employee][day] <= limit){
                        count++;
                    }
                }    
            
            }
            
            if (count == 5) {
                answer++;
            }

            // System.out.println();

        }
 
        System.out.println(answer);
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
    
    public static String convertToString(int day) {
        
        switch (day) {
            case 1:
                return "월";
            case 2:
                return "화";
            case 3:
                return "수";
            case 4:
                return "목";
            case 5:
                return "금";   
            case 6:
                return "토";    
        }
        
        return "일";
        
    }
}