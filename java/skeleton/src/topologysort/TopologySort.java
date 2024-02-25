package topologysort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 위상 정렬
 * @author parkrootseok
 *
 * - 방향 그래프이면서 사이클이 존재하지 않는 DAG에서만 정의 가능
 * - 모든 정점들을 변의 방향을 거스르지 않고 나열하는 것
 *
 * 1. 진입 차수가 0인 정점들을 모두 큐에 삽입
 * 2. 큐에 삽입된 정점들을 탐색
 *  2-1. 현재 가장 처음에 존재하는 정점을 꺼내고
 *  2-2. 큐에서 꺼낸 정점과 인접한 정점을 탐색
 *   2-2-1. 인접 정점의 차수를 감소
 *   2-2-2. 감소된 인접 정점의 차수가 0이라면 큐에 삽입
 */
public class TopologySort {

    static class Vertex {

        int name;
        int inDegree;
        List<AdjacentVertex> adjacentVertices;

        Vertex(int name) {
            this.name = name;
            this.inDegree = 0;
            this.adjacentVertices = new ArrayList<>();
        }

    }

    static class AdjacentVertex {

        int name;
        int weight;

        AdjacentVertex(int name, int weight) {
            this.name = name;
            graph.vertices[name].inDegree++;
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
        graph.vertices[0].adjacentVertices.add(new AdjacentVertex(2, 1));
        graph.vertices[0].adjacentVertices.add(new AdjacentVertex(3, 1));

        graph.vertices[1].adjacentVertices.add(new AdjacentVertex(3, 1));
        graph.vertices[1].adjacentVertices.add(new AdjacentVertex(4, 1));

        graph.vertices[2].adjacentVertices.add(new AdjacentVertex(3, 1));
        graph.vertices[2].adjacentVertices.add(new AdjacentVertex(5, 1));

        graph.vertices[3].adjacentVertices.add(new AdjacentVertex(5, 1));

        graph.vertices[4].adjacentVertices.add(new AdjacentVertex(5, 1));

        // 3. 위상 정렬
        isVisited = new boolean[TOTAL_VERTEX_NUMBER + 1];
        topologySort();

    }

    public static void topologySort() {

        Queue<Vertex> queue = new ArrayDeque<>();

        // 1. 진입 차수가 0인 정점들을 모두 큐에 삽입
        for (Vertex vertex : graph.vertices) {

            if(vertex.inDegree == 0) {
                queue.add(vertex);
            }

        }

        // 2. 큐에 삽입된 정점들을 탐색
        while (!queue.isEmpty()) {

            // 2-1. 현재 가장 처음에 존재하는 정점을 꺼내고
            Vertex fromVertex = queue.poll();
            System.out.println(fromVertex.name);

            // 2-2. 큐에서 꺼낸 정점과 인접한 정점을 탐색
            for(AdjacentVertex toVertex : fromVertex.adjacentVertices) {

                // 2-2-1. 인접 정점의 차수를 감소
                graph.vertices[toVertex.name].inDegree--;

                // 2-2-2. 감소된 인접 정점의 차수가 0이라면 큐에 삽입
               if (graph.vertices[toVertex.name].inDegree == 0) {
                   queue.add(graph.vertices[toVertex.name]);
               }

            }

        }

    }

}
