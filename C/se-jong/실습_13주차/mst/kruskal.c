#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_VALUE 0x7FFFFFFF

typedef struct __egde {

    int from;   // 출발
    int to;     // 도착
    int wegiht; // 가중치

} Edge;

typedef struct __incidence {
    int idx;
    struct __incidence *next;
} Incidence;

typedef struct __vertex {
    int name;
    int cost;
    int parent;
} Vertex;

typedef struct __graph {
    Vertex * vertices;
    Edge * edges;
} Graph;


typedef struct __node {
    Edge * e;
    struct __node *next;
} Node;

typedef struct __priorityqueue {
    Node * head;
    int size;
} Queue;

Queue * initQueue();
void offer(Queue * q, Edge * e);
Edge * foll(Queue * q);
bool isEmpty(Queue *q);

void buildGraph();
void insertEdge(Graph * T, int from, int to, int weigth, int i);
void insertEdgeForMST(Graph *T, int from, int to, int weigth, int i);
void addIncidence(int v, int i);
Vertex * opposite(int u, int e);

Graph * mst();

int Find(int * unf, int v) {
    if(v == unf[v])
        return v;

    return unf[v] = Find(unf, unf[v]);
}

void Union(int * unf, int a, int b) {
    int fa = Find(unf, a);
    int fb = Find(unf, b);
    if(fa != fb)
        unf[fa] = fb;
}

Graph * graph;
int n, m;

int main() {

    buildGraph();
    Graph * T = mst();

    // int sum = 0;
    // for (int i = 1; i <= n; i++) {
    //     sum += graph->vertices[i].cost;
    // }

    // printf("\n%d\n", sum);

    return 0;

}

/* Priority Queue ADT */
Queue * initQueue() {

    Queue * q = (Queue *)malloc(sizeof(Queue));
    q->head = (Node *)malloc(sizeof(Node));
    q->head->next = NULL;
    q->size = 0;

    return q;

}

void offer(Queue * q, Edge * e) {

    Node * newNode = (Node *)malloc(sizeof(Node));
    newNode->e = e;
    
    if (q->head->next == NULL) {

        q->head->next = newNode;
        (q->size)++;
        return;
        
    } else {

        Node *cur = q->head;

        while (cur->next != NULL  && cur->next->e->wegiht <= newNode->e->wegiht) {
            cur = cur->next;
        }

        newNode->next = cur->next;
        cur->next = newNode;
    }

    (q->size)++;

    return;

}

Edge * poll(Queue * q) {

    Node *removeNode = q->head->next;
    Edge * removeData = removeNode->e;

    q->head->next = removeNode->next;

    free(removeNode);
    (q->size)--;

    return removeData;

}

bool isEmpty(Queue * q) {

    if (q->size == 0) {
        return true;
    }

    return false;

}

void buildGraph() {

    graph = (Graph *)malloc(sizeof(Graph));

    scanf("%d %d", &n, &m);
    getchar();

    graph->vertices = (Vertex *)malloc(sizeof(Vertex) * (n + 1));
    for (int i = 1; i <= n; i++) {
        graph->vertices[i].name = i;
    }

    int from, to, weight;
    graph->edges = (Edge *)malloc(sizeof(Edge) * (m + 1));
    for (int i = 1; i <= m; i++) {
        
        scanf("%d %d %d", &from, &to, &weight);
        getchar();

        insertEdge(graph, from, to, weight, i);

    }

}

void insertEdge(Graph * T, int from, int to, int weigth, int i) {

    T->edges[i].from = from;
    T->edges[i].to = to;
    T->edges[i].wegiht = weigth;

}

Vertex * opposite(int u, int e) {

    if(graph->edges[e].from == u) {
        return &(graph->vertices[graph->edges[e].to]);
    }

    if(graph->edges[e].to == u) {
        return &(graph->vertices[graph->edges[e].from]);
    }

    return NULL;

}

Graph * mst() {

    int *unf = (int *)malloc(sizeof(int) * (n + 1));
    for (int i = 1; i <= n; i++){
        unf[i] = graph->vertices[i].name;
    }

    Queue *q = initQueue();

    for (int i = 1; i <= m; i++) {
        offer(q, &(graph->edges[i]));
    }

    Graph * T = (Graph *)malloc(sizeof(Graph));
    T->edges = (Edge *)malloc(sizeof(Edge) * (n + 1));

    int u, v, w;
    int rep = 0, sum = 0;
    while (rep < n - 1) {

        Edge * e = poll(q);
        u = e->from;
        v = e->to;
        w = e->wegiht;

        if (Find(unf, u) != Find(unf, v)) {
            insertEdge(T, u, v, w, rep);
            printf(" %d", w);
            sum += w;
            Union(unf, u, v);
            rep++;
        }

    }

    printf("\n%d\n", sum);

    return T;
    
}

// 6 9
// 1 2 3
// 1 3 20 
// 2 4 25 
// 2 5 17 
// 3 4 34 
// 3 5 1
// 3 6 12 
// 4 5 5
// 5 6 37

// 5 7
// 1 2 75
// 1 4 95
// 1 3 51
// 2 4 9
// 4 3 19
// 4 5 42
// 3 5 31