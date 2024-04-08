package algorithm.search.tree;

/**
 * DFS for Tree
 * @author parkrootseok
 *
 * 1. 현재 방문한 노드와 연결된 노드를 탐색
 *  1-1. 방문하지 않은 노드 방문
 **/
public class DFS {

    static int linkedNode = 7;

    public static void main(String[] args) {

    }

    public void dfs(int visitNode) {

        // 1. 현재 방문한 노드와 연결된 노드를 탐색
        for(int nextNode = 0; nextNode < linkedNode; nextNode++) {

            // 1-1. 노드 방문
            dfs(nextNode);

        }

    }

}
