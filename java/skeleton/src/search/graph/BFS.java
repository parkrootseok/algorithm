package search.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BFS
 * @author parkrootseok
 *
 * 1. 큐에 첫 방문을 진행할 정점을 삽입
 * 2. 큐에 정점이 존재하지 않을 때 까지 반복
 *  2-1. 현재 가장 처음에 존재하는 정점을 꺼내고
 *  2-2. 큐에서 꺼낸 정점과 인접한 정점을 탐색
 *   2-2-1. 방문할 정점이 이미 방문한 곳이라면 스킵
 *   2-2-2. 방문하지 않은 곳이라면 큐에 삽입 후 방문 처리
 **/
public class BFS {

    static class Vertex {

        int name;
        List<AdjacentVertex> adjacentVertices;

        Vertex(int name) {
            this.name = name;
            this.adjacentVertices = new ArrayList<>();
        }

    }

    static class AdjacentVertex {

        int name;
        int weight;

        AdjacentVertex(int name, int weight) {
            this.name = name;
            this.weight = weight;
        }

    }

    static class Graph {

        Vertex[] vertices;

        public Graph(int vertexNumber) {
            this.vertices = new Vertex[vertexNumber];
        }

    }

    static int TOTAL_VERTEX_NUMBER = 5;

    static Graph graph;
    static boolean[] isVisited;

    public static void main(String[] args) {

        graph = new Graph(TOTAL_VERTEX_NUMBER + 1);

        // 1. 정점 초기화
        for(int vertex = 0; vertex <= TOTAL_VERTEX_NUMBER; vertex++) {
            graph.vertices[vertex] = new Vertex(vertex);
        }

        // 2. 각 정점들에 대한 인접 정점 초기화
        graph.vertices[1].adjacentVertices.add(new AdjacentVertex(5, 1));
        graph.vertices[1].adjacentVertices.add(new AdjacentVertex(2, 1));
        graph.vertices[1].adjacentVertices.add(new AdjacentVertex(4, 1));

        graph.vertices[2].adjacentVertices.add(new AdjacentVertex(1, 1));
        graph.vertices[2].adjacentVertices.add(new AdjacentVertex(4, 1));

        graph.vertices[3].adjacentVertices.add(new AdjacentVertex(4, 1));
        graph.vertices[3].adjacentVertices.add(new AdjacentVertex(5, 1));

        graph.vertices[4].adjacentVertices.add(new AdjacentVertex(1, 1));
        graph.vertices[4].adjacentVertices.add(new AdjacentVertex(5, 1));
        graph.vertices[4].adjacentVertices.add(new AdjacentVertex(3, 1));

        graph.vertices[5].adjacentVertices.add(new AdjacentVertex(1, 1));
        graph.vertices[5].adjacentVertices.add(new AdjacentVertex(3, 1));

        // 3. bfs 수행
        isVisited = new boolean[TOTAL_VERTEX_NUMBER + 1];
        bfs();

    }

    public static void bfs() {

        Queue<Vertex> queue = new ArrayDeque<>();

        // 1. 큐에 첫 방문을 진행할 정점을 삽입
        queue.add(graph.vertices[1]);
        isVisited[1] = true;

        // 2. 큐에 정점이 존재하지 않을 때 까지 반복
        while (!queue.isEmpty()) {

            // 2-1. 현재 가장 처음에 존재하는 정점을 꺼내고
            Vertex fromVertex = queue.poll();
            System.out.println(fromVertex.name);

            // 2-2. 큐에서 꺼낸 정점과 인접한 정점을 탐색
            for(AdjacentVertex toVertex : fromVertex.adjacentVertices) {

                // 2-2-1. 방문할 정점이 이미 방문한 곳이라면 스킵
                if(isVisited[toVertex.name]) {
                    continue;
                }

                // 2-2-2. 방문하지 않은 곳이라면 큐에 삽입 후 방문 처리
                queue.add(graph.vertices[toVertex.name]);
                isVisited[toVertex.name] = true;

            }

        }

    }

}
