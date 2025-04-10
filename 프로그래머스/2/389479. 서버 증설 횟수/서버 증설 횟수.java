import java.util.*;

/**
 * PG_서버증설횟수
 * @author parkrootseok
 */
class Solution {
    
    static class Server {
        
        int start;
        int end;
        
        Server(int start) {
            this.start = start;
            this.end = start + K;
        }
        
    }
    
    static int M;
    static int K;
    
    public int solution(int[] players, int m, int k) {
        
        M = m;
        K = k;
        
        int answer = 0;
        Queue<Server> servers = new ArrayDeque<>();
        
        for (int hour = 0; hour <= 23; hour++) {
            
            // 증설된 서버 종료
            while(!servers.isEmpty()) {
                // 가장 먼저 증설된 서버의 종료 시각이 현재 시간보다 큰 경우
                if (hour < servers.peek().end) {
                    // 반복문 종료
                    break;
                }
                // 그렇지 않으면 현재 서버 종료
                servers.poll();
            }
            
            
            // 현재 증설된 서버로
            int player = players[hour];
            if (isRunnable(player, servers.size())) {
                // 실행 가능하면 스킵
                continue;
            }
             
            // 실행 불가능하면 서버 증설 필요
            int size = servers.size();
            int needServer = (int) Math.ceil(player / M);
            for (int server = 0; server < needServer - size; server++) {
                answer++;
                servers.offer(new Server(hour));
            }
            
        }
        
        return answer;
        
    }
    
    public boolean isRunnable(int player, int size) {
        return player < (size + 1) * M;
    }
    
}