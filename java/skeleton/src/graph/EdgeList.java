package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph - Edge List
 * @author parkrootseok
 *
 * - 간선 리스트를 이용한 그래프 구현 (간선 중심) 1. 간선 정보를 리스틀 활용하여 구현
 */
public class EdgeList {

    static class Vertex {

        int name;

        Vertex(int name) {
            this.name = name;
        }

    }

    static class Edge {

        int to;
        int from;

    }

    static class Graph {

        Vertex[] vertices;

        /**
         * 간선 정보를 저장하기 위한 리스트
         * - 무방향일 경우 최대 간선은 V * (V - 1) / 2
         * - 방향일 경구 최대 간선은 V * (V - 1)
         */
        Edge[] edges;

        public Graph(int vertexNumber) {
            this.vertices = new Vertex[vertexNumber];
            this.edges = new Edge[vertexNumber * (vertexNumber - 1)];
        }

    }

}
