#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {
    int element;
    struct __node *next;
} Node;

typedef struct  __queue{

    Node * head;
    int numOfData;

} Queue;

typedef struct __vertex {

    char name;
    Node * inEdges;
    Node * outEdges;
    int inDegree;

} Vertex;

typedef struct __edge {
    char name;
    int from;
    int to;    
} Edge;

typedef struct __graph {
    Vertex * vertices;
    Edge * edges;
} Graph;

void addFirst(Node *H, int i);

Queue * initQueue();
void offer(Queue * q, int element);
int foll(Queue * q);
bool isEmpty(Queue *q);

void buildGraph();

void insertVertex(char vName, int i);
void insertDirectedEdge(char to, char from, int i);
int getIndex(char vName);

void topologicalSort();

Graph * G;

int n, m;
int * topOrder;

int main() {

    buildGraph();

    topologicalSort();

    if (topOrder[0] == 0) {
        printf("0\n");
    } else {
        for (int i = 1; i <= n; i++) {
            printf("%c ", G->vertices[topOrder[i]].name);
        }
        printf("\n");
    }

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

/* Queue ADT */
Queue * initQueue() {

    Queue * q = (Queue *)malloc(sizeof(Queue));
    q->head = (Node *)malloc(sizeof(Node));
    q->head->next = NULL;

    q->numOfData = 0;
    return q;

}

void offer(Queue * q, int element) {

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->element = element;
    newNode->next = NULL;

    if(q->head->next == NULL) {
    
        q->head->next = newNode;
    
    } else {
        
        Node *cur = q->head->next;
        
        while (cur->next != NULL) {
            cur = cur->next;
        }
        
        cur->next = newNode;

    }

    (q->numOfData)++;
    return;

}

int poll(Queue * q) {

    Node *removeNode = q->head->next;
    int removeData = removeNode->element;

    q->head->next = removeNode->next;
    (q->numOfData)--;

    free(removeNode);

    return removeData;

}

bool isEmpty(Queue * q) {

    if (q->numOfData == 0) {
        return true;
    }

    return false;

}



/* Graph ADT */

void buildGraph() {

    G = (Graph *)malloc(sizeof(Graph));

    scanf("%d", &n);
    getchar();

    topOrder = (int *)malloc(sizeof(int) * (n + 1));
    G->vertices = (Vertex *)malloc(sizeof(Vertex) * (n + 1));

    char vName;
    for (int i = 1; i <= n; i++) {

        scanf("%c", &vName);
        getchar();
        insertVertex(vName, i);

    }

    scanf("%d", &m);
    getchar();

    G->edges = (Edge *)malloc(sizeof(Edge) * m);

    char from, to;
    for (int i = 0; i < m; i++) {
        scanf("%c %c", &from, &to);
        getchar();
        insertDirectedEdge(from, to, i);
    }

    return;

}

void insertVertex(char vName, int i) {

    G->vertices[i].name = vName;
    G->vertices[i].inEdges = (Node *)malloc(sizeof(Node));
    G->vertices[i].inEdges->next = NULL;
    G->vertices[i].outEdges = (Node *)malloc(sizeof(Node));
    G->vertices[i].outEdges->next = NULL;
    G->vertices[i].inDegree = 0;
}

void insertDirectedEdge(char from, char to, int i) {

    int u, w;

    u = getIndex(from);
    w = getIndex(to);

    G->edges[i].from = u;
    G->edges[i].to = w;

    addFirst(G->vertices[u].outEdges, i);
    addFirst(G->vertices[w].inEdges, i);

    (G->vertices[w].inDegree)++;

}

int getIndex(char vName) {

    for (int i = 1; i <= n; i++) {
        if (G->vertices[i].name == vName) {
            return i;
        }
    }

    return 0;

}

void topologicalSort() {

    int t;
    int in[n + 1];
    Queue * q = initQueue();

    for (int i = 1; i <= n; i++) {

        in[i] = G->vertices[i].inDegree;
        if(in[i] == 0) {
            offer(q, i);
        }

    }

    t = 1;
    int u, w;
    while (!isEmpty(q)) {

        u = poll(q);
        topOrder[t] = u;
        t++;

        Node *e = G->vertices[u].outEdges->next;
        while (e != NULL) {

            w = G->edges[e->element].to;
            in[w] = in[w] - 1;
            if (in[w] == 0) {
                offer(q, w);
            }

            e = e->next;

        }

    }

    if (t <= n) {
        topOrder[0] = 0;
    } else {
        topOrder[0] = 1;
    }

    return;

}