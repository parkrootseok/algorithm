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
    int distance;
    Incidence *incidenceList;
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

void bellmanford();

Graph * graph;
int n, m, s;

int main() {

    buildGraph();
    bellmanford();
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

        while (cur->next != NULL  && cur->next->vertex->distance <= newNode->vertex->distance) {
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

    scanf("%d %d %d", &n, &m, &s);
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

void bellmanford() {

    int *result = (int *)malloc(sizeof(int) * (n + 1));
    Queue *q = initQueue();

    for (int i = 1; i <= n; i++) {
        graph->vertices[i].distance = MAX_VALUE;
    }
    
    graph->vertices[s].distance = 0;

    for (int i = 1; i <= n; i++) {

        Edge * edges = graph->edges;

        for (int j = 1 ; j <= m; j++) {

            Vertex *u = &(graph->vertices[edges[j].from]);
            Vertex *z = opposite(u->name, j);
            Edge *e = &(edges[j]);

            if (u->distance != MAX_VALUE) {
                if (u->distance + e->wegiht < z->distance) {
                    z->distance = u->distance + e->wegiht;
                }
            }

        }
        
    }

    for (int i = 1; i <= n; i++) {
        
        Vertex *v = &(graph->vertices[i]);

        if (i != s && v->distance != MAX_VALUE) {
            printf("%d %d\n", i, v->distance);
        }

    }

    return;
    
}

// 5 7 1
// 1 2 1
// 1 4 5
// 5 1 10
// 3 5 3
// 4 3 1
// 3 1 2
// 2 3 2