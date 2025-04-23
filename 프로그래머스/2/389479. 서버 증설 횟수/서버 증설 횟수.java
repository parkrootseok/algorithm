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
            // S시 증설 서버는
            this.start = start;
            // S+5 까지 운영
            this.end = start + K;
        }
        
    }
    
    static int M;
    static int K;
    
    public int solution(int[] players, int m, int k) {

        M = m;
        K = k;
        
        int answer = 0;
        Queue<Server> servers = new ArrayDeque();
        for (int hour = 0; hour < 24; hour++) {
            
            // 현재 들어온 플레이어를
            int player = players[hour];
            
            // 사용 불가능한 서버 제거
            while(!servers.isEmpty() && servers.peek().end <= hour) {
                servers.poll();
            }
            
            // 현재 서버로 감당할 수 있는지 확인 필요
            if (isPossible(player, servers.size())) {
                continue;
            }
            
            // 감당할 수 없다면 서버 증설
            System.out.println("현재 시간[" + hour + "] " + "서버 증설 수행");
            int needServerCount = (player / M) - servers.size();
            for (int count = 0; count < needServerCount; count++) {
                answer++;
                servers.offer(new Server(hour));
            }
        
        }
        
        return answer;
        
    }
    
    // 현재 서버로 소화할 수 있는지에 대한 여부
    // - 현재 용량 : (size + 1) * M
    public boolean isPossible(int player, int size) {
        return player < (size + 1) * M;
    }
    
}