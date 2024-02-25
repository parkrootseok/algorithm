package search.graph;

import java.util.ArrayList;
import java.util.List;
import search.graph.BFS.AdjacentVertex;

/**
 * DFS for Graph
 * @author parkrootseok
 *
 * 1. 현재 방문한 정점 방문처리
 * 2. 현재 방문한 정점의 인접 정점 탐색
 *  2-1. 방문한 정점이라면 스킵
 *  2-2. 방문하지 않은 정점이라면 방문
 **/
class DFS {

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
        dfs(1);

    }

    public static void dfs(int fromVertex) {

        // 1. 현재 방문한 정점 방문처리
        System.out.println(fromVertex);
        isVisited[fromVertex] = true;

        // 2. 현재 방문한 정점의 인접 정점 탐색
        for(AdjacentVertex toVertex : graph.vertices[fromVertex].adjacentVertices) {

            // 2-1. 방문한 정점이라면 스킵
            if (isVisited[toVertex.name]) {
                continue;
            }

            // 2-2. 방문하지 않은 정점이라면 방문
            dfs(toVertex.name);

        }

    }

}
