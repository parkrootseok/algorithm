import java.util.*;

/**
 * PG_광고삽입
 * @author parkrootseok
 */
class Solution {
    
    public String solution(String play_time, String adv_time, String[] logs) {
        
        String answer = "";
        
        // 총 시간 : (99 * 3600) + (59 * 60) + 59 = 359_999
        int[] seconds = new int[360_000];
        for (String log : logs) {
            String[] partOfLog = log.split("-");
            seconds[convertToInt(partOfLog[0])]++;
            seconds[convertToInt(partOfLog[1])]--;
        }
        
        // 누적합 계산
        for (int second = 1; second < seconds.length ; second++) {
            seconds[second] += seconds[second - 1];
        }
        
        int playTime = convertToInt(play_time);
        int advTime = convertToInt(adv_time);
        
        // 00:00:00 ~ (adv_time - 1)까지 누적 시간 계산
        long sum = 0;
        for (int second = 0; second < advTime; second++) {
            sum += seconds[second];
        }
        
        // adv_time 이후 누적 시간 계산
        int maxSecond = 0;
        long maxSum = sum;
        for (int second = advTime; second < playTime; second++) {
            
            // 윈도우 이동
            // s1 ~ s2 -> (s1 + 1) ~ (s2 + 1)
            sum -= seconds[second - advTime];
            sum += seconds[second];
            
            // 윈도우를 이동한 후 누적합이 더 크다면
            if (maxSum < sum) {
                // 누적합을 갱신하고
                maxSum = sum;
                // 현재 시작 시간을 기록
                maxSecond = second - advTime + 1;
            }
            
        }
        
        return convertToString(maxSecond);
        
    }
    
    public int convertToInt(String time) {
        String[] partOfTime = time.split(":");
        int hour = Integer.valueOf(partOfTime[0]);
        int minute = Integer.valueOf(partOfTime[1]);
        int second = Integer.valueOf(partOfTime[2]);
        return (hour * 3600) + (minute * 60) + second;
    }
    
    public String convertToString(int time) {
        int hour = time / 3600;
        int minute = (time % 3600) / 60;
        int second = time % 3600 % 60;
        
        return new StringBuilder()
            .append(hour < 10 ? "0" + hour : hour).append(":")
            .append(minute < 10 ? "0" + minute : minute).append(":")
            .append(second < 10 ? "0" + second : second)
            .toString();
    }
    
}