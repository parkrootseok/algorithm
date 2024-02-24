package search;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * DFS
 * @author parkrootseok
 *
 * 1. 현재 방문한 노드에 대한 방문처리
 * 2. 현재 방문한 노드와 연결된 노드를 탐색
 *  2-1. 방문한 노드라면 스킵
 *  2-2. 방문하지 않은 노드라면 방문
 **/
public class DFS {

    static int TOTAL_NODE_NUMBER = 5;
    static boolean[] isVisited = new boolean[TOTAL_NODE_NUMBER];

    public static void main(String[] args) {

    }

    public void dfs(int visitNode) {

        // 1. 현재 방문한 노드에 대한 방문처리
        isVisited[visitNode] = true;

        // 2. 현재 방문한 노드와 연결된 노드를 탐색
        for(int nextNode = 0; nextNode < likedNode; nextNode++) {

            // 2-1. 방문한 노드라면 스킵
            if (isVisited[nextNode]) {
                continue;
            }

            // 2-2. 방문하지 않은 노드라면 방문
            dfs(nextNode);

        }

    }

}
