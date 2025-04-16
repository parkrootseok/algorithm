import java.util.*;

class Solution {
    
    static final char EMPTY = '-';
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static char[][] map;
    static int N;
    static int M;
    static Queue<int[]> emptySpace;
    
    public int solution(String[] storage, String[] requests) {
    
        createMap(storage);
        int count = 0;
        emptySpace = new ArrayDeque<>();
        for (String request : requests) {
        
            char container = request.charAt(0);
            
            // 지게차 출고
            if (1 == request.length()) {
                for (int[] pos : bfs(container)) {
                    if (map[pos[0]][pos[1]] != EMPTY) {
                        count++;
                        map[pos[0]][pos[1]] = EMPTY;
                    }
                }
            } 
            
            // 크레인 출고
            else {
                for (int row = 0; row < N; row++) {            
                    for (int col = 0; col < M; col++) {
                        if (map[row][col] == container) {
                            count++;
                            map[row][col] = EMPTY;
                        }
                    }
                }
            }
            
        }
        
        return (N - 2) * (M - 2) - count;
        
    }
    
    public List<int[]> bfs(char container) {
        
        boolean[][] isVisited = new boolean[N][M];
        List<int[]> willRemove = new ArrayList<>();
        
        for (int row = 0; row < N; row++) {
            emptySpace.offer(new int[]{row, 0});
            emptySpace.offer(new int[]{row, M - 1});
        }
        
        for (int col = 0; col < M; col++) {
            emptySpace.offer(new int[]{0, col});
            emptySpace.offer(new int[]{N - 1, col});
        }
        
        while (!emptySpace.isEmpty()) {
            
            int[] space = emptySpace.poll();
            int r = space[0];
            int c = space[1];
        
            if (isVisited[r][c]) {
                continue;
            }
            
            isVisited[r][c] = true;
            
            for (int dir = 0; dir < dr.length; dir++) {
                
                int nr = r + dr[dir];
                int nc = c + dc[dir];
                
                if (outRange(nr, nc)) {
                    continue;
                }
                
                if (map[nr][nc] == EMPTY) {
                    emptySpace.offer(new int[]{nr, nc});
                }
                
                if (map[nr][nc] == container) {
                    willRemove.add(new int[]{nr, nc});
                }
                
            }    
            
        }
        
        return willRemove;
        
    }
    
    public boolean outRange(int r, int c) {
        return r < 0 || N <= r || c < 0 || M <= c;
    }
    
    public void createMap(String[] s) {
        
        N = s.length + 2; 
        M = s[0].length() + 2;
        map = new char[N][M];
        
        for (int row = 0; row < N; row++) {
            map[row][0] = EMPTY;
            map[row][M - 1] = EMPTY;
        }
        
        for (int col = 0; col < M; col++) {
            map[0][col] = EMPTY;
            map[N - 1][col] = EMPTY;
        }
        
        for (int row = 1; row <= N - 2; row++) {
            char[] containers = s[row - 1].toCharArray();
            for (int col = 1; col <= M - 2; col++) {
                map[row][col] = containers[col - 1];
            }
        }
    }
    
}