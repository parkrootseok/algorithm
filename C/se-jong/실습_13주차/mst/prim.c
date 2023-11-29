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
    Incidence * incidenceList;
} Vertex;

typedef struct __graph {
    Vertex * vertices;
    Edge * edges;
} Graph;


typedef struct __node {
    Vertex * vertex;
    struct __node *next;
} Node;

typedef struct __priorityqueue {
    Node * head;
    int size;
} Queue;

Queue *initQueue();
void offer(Queue * q, Vertex * Vertex);
int foll(Queue * q);
bool isEmpty(Queue *q);

void buildGraph();
void insertEdge(int from, int to, int weigth, int i);
void addIncidence(int v, int i);
Vertex * opposite(int u, int e);

void mst();

Graph * graph;
int n, m;

int main() {

    buildGraph();
    mst();

    int sum = 0;
    for (int i = 1; i <= n; i++) {
        sum += graph->vertices[i].cost;
    }

    printf("\n%d\n", sum);

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

void offer(Queue * q, Vertex * vertex) {

    Node * newNode = (Node *)malloc(sizeof(Node));
    newNode->vertex = vertex;
    
    if (q->head->next == NULL) {

        q->head->next = newNode;
        (q->size)++;
        return;
        
    } else {

        Node *cur = q->head;

        while (cur->next != NULL  && cur->next->vertex->cost <= newNode->vertex->cost) {
            cur = cur->next;
        }

        newNode->next = cur->next;
        cur->next = newNode;
    }

    (q->size)++;

    return;

}

Vertex * poll(Queue * q) {

    Node *removeNode = q->head->next;
    Vertex * removeData = removeNode->vertex;

    q->head->next = removeNode->next;

    free(removeNode);
    (q->size)--;

    return removeData;

}

void replace(Queue * q, Vertex * v) {

    Node *cur = q->head;

    while (cur != NULL) {

        if (cur->next->vertex->name == v->name) {
            cur->next = cur->next->next;
            (q->size)--;
            offer(q, v);
            break;
        }

        cur = cur->next;

    }

}

bool contains(Queue * q, Vertex * v) {

    Node *cur = q->head->next;

    while (cur != NULL) {
        if (cur->vertex->name == v->name) {
            return true;
        }

        cur = cur->next;
    }

    return false;

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
        graph->vertices[i].incidenceList = NULL;
    }

    int from, to, weight;
    graph->edges = (Edge *)malloc(sizeof(Edge) * (m + 1));
    for (int i = 1; i <= m; i++) {
        
        scanf("%d %d %d", &from, &to, &weight);
        getchar();

        insertEdge(from, to, weight, i);

    }

}

void insertEdge(int from, int to, int weigth, int i) {

    graph->edges[i].from = from;
    graph->edges[i].to = to;
    graph->edges[i].wegiht = weigth;

    addIncidence(from, i);
    addIncidence(to, i);

}

void addIncidence(int v, int i) {

    Incidence *incidence = (Incidence *)malloc(sizeof(Incidence));
    incidence->idx = i;
    incidence->next = NULL;

    if (graph->vertices[v].incidenceList == NULL) {
        graph->vertices[v].incidenceList = incidence;
        return;
    }

    Incidence *cur = graph->vertices[v].incidenceList;
    
    while (cur->next != NULL) {
        cur = cur->next;
    }
    cur->next = incidence;

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

void mst() {

    Queue *q = initQueue();

    for (int i = 1; i <= n; i++) {
        graph->vertices[i].cost = MAX_VALUE;
    }

    graph->vertices[1].cost = 0;
    for (int i = 1; i <= n; i++) {
        offer(q, &(graph->vertices[i]));
    }

    while (!isEmpty(q)) {

        Vertex *u = poll(q);
        Incidence * incidenceList = u->incidenceList;

        printf(" %d", u->name);
        while (incidenceList != NULL)
        { // u와 연결된 모든 정점에 대해 조회

            Vertex * z = opposite(u->name, incidenceList->idx);  // u와 연결된 정점 z    
            Edge *e = &(graph->edges[incidenceList->idx]);  // (u, z) 간선 정보
            
            if (contains(q, z) && e->wegiht < z->cost) {
                z->cost = e->wegiht;
                z->parent = incidenceList->idx;
                replace(q, z);
            }

            incidenceList = incidenceList->next;

        }

    }

    return;
    
}
