import java.util.*;

class Solution {
    
    static final int PILLAR = 0;
    static final int BEAM = 1;
    static final int DELETE = 0;
    static final int CREATE = 1;
    
    static boolean[][] pillar;
    static boolean[][] beam;
    
    public int[][] solution(int n, int[][] build_frame) {
        
        pillar = new boolean[n + 1][n + 1];
        beam = new boolean[n + 1][n + 1];
        
        int count = 0;
        for (int r = 0; r < build_frame.length; r++) {
            
            int x = build_frame[r][0];
            int y = build_frame[r][1];
            int type = build_frame[r][2];
            int command = build_frame[r][3];
            
            if (type == PILLAR) {
                if (command == CREATE) {
                    if (isPossibleOfPillar(x, y)) {
                        pillar[x][y] = true;
                        count++;
                    }
                } else {
                    pillar[x][y] = false;
                    if (!isDeleted(n)) {
                        pillar[x][y] = true;    
                    } else {
                        count--;
                    }
                 }
                
            } else {
                if (command == CREATE) {
                    if (isPossibleOfBeam(x, y)) {
                        beam[x][y] = true;
                        count++;
                    }
                } else {
                    beam[x][y] = false;
                    if (!isDeleted(n)) {
                        beam[x][y] = true;    
                    } else {
                        count--;
                    }
                }
                
            }
            
        }
        
        int id = 0;
        int[][] answer = new int[count][3];
        for (int r = 0; r <= n; r++) {
            for (int c = 0; c <= n; c++) {
                if (pillar[r][c]) {
                    answer[id][0] = r;
                    answer[id][1] = c;
                    answer[id++][2] = PILLAR;
                }
                
                if (beam[r][c]) {
                    answer[id][0] = r;
                    answer[id][1] = c;
                    answer[id++][2] = BEAM;
                }
            }
        }
        
        return answer;
        
    }
    
    public boolean isDeleted(int n) {
        // 기둥이나 보를 삭제한 후, 현재 상태가 유효하지 않다면 삭제 불가
        for (int r = 0; r <= n; r++) {
            for (int c = 0; c <= n; c++) {
                if (pillar[r][c] && !isPossibleOfPillar(r, c)) {
                    return false;
                }
                else if (beam[r][c] && !isPossibleOfBeam(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isPossibleOfPillar(int x, int y) {
        // 바닥에 설치하는 경우
        if (y == 0) {
            return true;
        }
        // 아래 기둥이 있는 경우
        else if (0 < y && pillar[x][y - 1]) {
            return true;
        }
        // 보와 연결된 경우
        else if (0 < x && beam[x - 1][y] || beam[x][y]) {
            return true;
        }
        return false;
    }
    
    public boolean isPossibleOfBeam(int x, int y) {
        // 1. 한 쪽 끝에 기둥이 있는 경우
        if (0 < y && pillar[x][y - 1] || pillar[x + 1][y - 1]) {
            return true;
        }
        // 2. 양 쪽 끝 부분이 보와 연결된 경우
        else if (0 < x && beam[x - 1][y] && beam[x + 1][y]) {
            return true;
        }
        return false;
    }
    
}