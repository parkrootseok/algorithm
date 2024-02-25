package search.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BFS for Tree (트리 BFS 탐색)
 * @author parkrootseok
 *
 * 1. 큐에 첫 방문을 진행할 노드를 삽입
 * 3. 큐에 노드가 존재하지 않을 때 까지 반복
 *  3-1. 현재 가장 처음에 존재하는 노드를 꺼내고
 *  3-2. 큐에서 꺼낸 노드와 연결된 모든 노드를 탐색
 *   3-2-2. 큐에 삽입
 **/
public class BFS {

    static int linkedNode = 7;

    public static void main(String[] args) {

    }

    public void bfs() {

        Queue<Integer> queue = new ArrayDeque<Integer>();

        // 1. 큐에 첫 방문을 진행할 노드를 삽입
        queue.add(1);

        // 3. 큐에 노드가 존재하지 않을 때 까지 반복
        while (!queue.isEmpty()) {

            // 3-1. 현재 가장 처음에 존재하는 노드를 꺼내고
            int firstInsertionNode = queue.poll();

            // 3-2. 큐에서 꺼낸 노드와 연결된 모든 노드를 탐색
            for(int curNode = 0; curNode < linkedNode; curNode++) {

                // 3-2-2. 큐에 삽입
                queue.add(curNode);

            }

        }

    }

}
