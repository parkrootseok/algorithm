import java.util.*;
import java.io.*;

/**
 * PG_충돌위험 찾기
 * @author parkrootseok
 */
class Solution {
    
    static class Robot {   
        Queue<Integer> targets;
        int row;
        int col;
        
        Robot() {
            this.targets = new ArrayDeque<>();
        }
    }
    
    int SIZE = 100;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static Map<Integer, int[]> positions;

    public int solution(int[][] points, int[][] routes) {
        
        // 포인트의 위치 정보를 담는 map 만들기
        positions = new HashMap<>();
        for (int index = 0; index < points.length; index++) {
            int row = points[index][0];
            int col = points[index][1];
            positions.put(index + 1, new int[]{row, col});
        }
        
        // 로봇 경로 입력
        int[] counts = new int[points.length + 1];
        Robot[] robots = new Robot[routes.length];
        for (int id = 0; id < routes.length; id++) {
            
            robots[id] = new Robot();
            
            for (int index = 0; index < routes[id].length; index++) {
                
                int target = routes[id][index];
                
                if (index == 0) {
                    // 첫 번째 포인트는 출발지로 설정
                    counts[target]++; 
                    int[] position = positions.get(target);
                    robots[id].row = position[0];
                    robots[id].col = position[1];
                    continue;
                }
                
                robots[id].targets.offer(target);   
                
            }
            
        }
        
        // 시작 위치에서 충돌이 발생했으면 이를 반영 후 시뮬레이션 시작
        int count = 0;
        for (int index = 0; index < counts.length; index++) {
            if (2 <= counts[index]) {
                count++;
            }
        }
        
        return simulation(robots, count);
        
    }
    
    public int simulation(Robot[] robots, int offset) {
      
        int count = offset;
        Map<String, Integer> positionOfCount = new HashMap<>();
        while (isPossible(robots)) {
            
            int[] nextDir = new int[robots.length];
            positionOfCount.clear();
            
            for (int id = 0; id < robots.length; id++) {
                
                if (!isMovable(robots[id])) {
                    continue;
                }

                // 로봇이 이동할 방향을 기록  
                nextDir[id] = selectDirection(robots[id]);
                int nRow = robots[id].row + dr[nextDir[id]];
                int nCol = robots[id].col + dc[nextDir[id]];
                
                // 다음 이동할 위치에서 발생할 충돌 횟수 카운트
                String key = nRow + "," + nCol;
                positionOfCount.put(key, positionOfCount.getOrDefault(key, 0) + 1);
                
            }

            
            for (String key : positionOfCount.keySet()) {
                // 충돌 횟수가 2를 넘어가면
                if (positionOfCount.get(key) >= 2) {
                    // 카운팅
                    count++;
                }
            }
            
            for (int id = 0; id < robots.length; id++) {
                
                if (!isMovable(robots[id])) {
                    continue;
                }
                
                // 다음 위치로 이동
                nextDir[id] = selectDirection(robots[id]);
                robots[id].row += dr[nextDir[id]];
                robots[id].col += dc[nextDir[id]];
                
                // 현재 목적지에 도착했는지 확인
                int[] position = positions.get(robots[id].targets.peek());
                if (robots[id].row == position[0] && robots[id].col == position[1]) {
                    robots[id].targets.poll();
                }

            }

        }
        
        return count;
      
    }
    
    public boolean isMovable(Robot r) {
        return !r.targets.isEmpty();
    }
    
    public boolean isPossible(Robot[] robots) {
        for (int id = 0; id < robots.length; id++) {
            // 하나라도 도착하지 못한 로봇이 있다면 계속 진행
            if (isMovable(robots[id])) {
                return true;
            }
        }
        return false;
    }
    
    public int selectDirection(Robot r) {
        
        int minDistance = Integer.MAX_VALUE;
        int nDir = 0;
        for (int dir = 0; dir < dr.length; dir++) {
            
            int nRow = r.row + dr[dir];
            int nCol = r.col + dc[dir];
            
            // 격자를 벗어나면 이동 불가
            if (outRange(nRow, nCol)) {
                continue;
            }
            
            // 현재 목표와의 거리
            int[] position = positions.get(r.targets.peek());
            int dist = getDistance(nRow, nCol, position[0], position[1]);
            // 가 현재 최소 거리보다 작으면
            if (dist < minDistance) {
                // 해당 위치로 이동 가능하므로 기록
                minDistance = dist;
                nDir = dir;
            }
            
        }
        
        return nDir;
        
    }
    
    public int getDistance(int cRow, int cCol, int tRow, int tCol) {
        return (int) (Math.abs(cRow - tRow) + Math.abs(cCol - tCol)); 
    }
    
    public boolean outRange(int row, int col) {
       return row <= 0 || SIZE < row || col <= 0 || SIZE < col; 
    }
    
}