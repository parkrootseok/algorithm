#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {
    int element;
    struct __node *next;
} Node;

typedef struct __stack {
    int * element;
    int top;
} Stack;

typedef struct __vertex {

    int name;
    Node * outEdges;

} Vertex;

typedef struct __edge {
    char name;
    int from;
    int to;    
} Edge;

typedef struct __graph {
    Vertex * vertices;
    Edge * edges;
    int numVertices;
    int numEdges;
} Graph;

void addFirst(Node *H, int i);

void push(Stack *S, int e);
int pop(Stack *S);
bool isEmpty(Stack *S);

Graph * buildGraph(int * n, int * m);

void insertVertex(Graph * G, int i);
void insertDirectedEdge(Graph * G, int from, int to, int i);

void topologicalSortDFS(Graph *G);

int main() {

    Graph * G;
    int n, m;

    G =buildGraph(&n, &m);
    topologicalSortDFS(G);

    free(G->edges);
    free(G->vertices);
    free(G);
    return 0;
}

/* List ADT */
void addFirst(Node * H, int i) {

    Node * node = (Node *)malloc(sizeof(Node));
    node->element = i;
    node->next = H->next;
    H->next = node;

    return;
    
}

/* Stack ADT */
void push(Stack * S, int e) {
    (S->top)++;
    S->element[S->top] = e;
}

int pop(Stack * S) {

    if(isEmpty(S)) {
        return -1;
    }
    
    return S->element[(S->top)--];
}

int peek(Stack * S) {

    return S->element[S->top];

}

bool isEmpty(Stack * S) {

    if(S->top == -1) {
        return true;
    }

    return false;

}

/* Graph ADT */
Graph * buildGraph(int * n, int * m) {
    
    Graph *G;

    scanf("%d %d", n, m);
    getchar();

    G = (Graph *)  malloc(sizeof(Graph));
    G->numVertices = *n;
    G->numEdges = *m;

    G->vertices = (Vertex *)malloc(sizeof(Vertex) * ((*n) + 1));

    for (int i = 1; i <= (*n); i++) {
        insertVertex(G, i);
    }

    G->edges = (Edge *)malloc(sizeof(Edge) * (*m));

    int from, to;
    for (int i = 1; i <= (*m); i++) {
        scanf("%d %d", &from, &to);
        getchar();
        insertDirectedEdge(G, from, to, i);
    }

    return G;

}

void insertVertex(Graph * G, int i) {

    G->vertices[i].name = i;
    G->vertices[i].outEdges = (Node *)malloc(sizeof(Node));
    G->vertices[i].outEdges->next = NULL;

}

void insertDirectedEdge(Graph * G, int from, int to, int i) {

    G->edges[i].from = from;
    G->edges[i].to = to;

    addFirst(G->vertices[from].outEdges, i);
}

void topologicalSortDFS(Graph * G) {

    Stack * S = (Stack *)malloc(sizeof(Stack));
    S->element = (int *)malloc(sizeof(int) * (G->numVertices + 1));
    S->top = -1;

    bool *visited;
    visited = (bool *)malloc(sizeof(bool) * (G->numVertices + 1)); 

    for (int v = 1; v <= G->numVertices; v++) {
        
        if (!visited[v]) {

            push(S, v);

            while (!isEmpty(S)) {
                
                bool allVisit = true;
                visited[v] = true;
                int v = peek(S);    // 가장 최근에 방문한 노드
                Node *outEdge = G->vertices[v].outEdges->next; // 최근에 방문한 정점에 대한 진출 간선

                while (outEdge != NULL) {

                    int w = G->edges[outEdge->element].to;

                    if (!visited[w]) {  // v의 반대 정점인 w를 방문한 적 없다면
                        visited[w] = true;  // 방문 표시를 하고
                        allVisit = false;   // 방문하지 않은 정점이 있는지 확인
                        push(S, w); // 스택에 삽입(최근에 방문한 스택을 초기화 후 종료)
                        break;
                    }

                    outEdge = outEdge->next;

                }

                if (allVisit) { // 방문할 노드가 없다면 출력
                    printf("%d\n", pop(S));
                }
                
            }

            while (!isEmpty(S)) {   // 조회 탐색이 종료되면 스택에 있는 원소를 출력(최근에 방문한 순서대로 출력)
                printf("%d\n", pop(S));
            }

        }

    }

    for (int i = 1; i <= G->numVertices; i++) {
        if (!visited[i]) {
            printf("%d\n", i);
        }
    }           

    free(S->element);
    free(S);
    free(visited);
    return;

}

// 5 9    
// 3 1
// 5 3
// 2 4
// 3 4
// 1 2
// 5 4
// 3 2
// 1 4
// 5 2