#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {

    int vertex;
    struct __node *next;

} Node;

typedef struct __list {

    Node *head;

} List ;

List *graph;
int n, m, s;
bool * visited;

void initGraph();
bool isInvalid(int vertex);
void insertEdge(int to, int from);
void dfs(int s);

int main() {

    initGraph();
    dfs(s);

    return 0;

}

void initGraph() {

    scanf("%d %d %d", &n, &m, &s);
    getchar();

    graph = (List *)malloc(sizeof(List) * (n + 1));
    for (int i = 0; i <= n; i++) {
        graph[i].head = (Node *)malloc(sizeof(Node));
        graph[i].head->next = NULL;
    }

    visited = (bool *)malloc(sizeof(bool) * n);

    int to, from;
    for (int i = 0; i < m; i++) {

        scanf("%d %d", &to, &from);
        getchar();

        insertEdge(to, from);
        insertEdge(from, to);

    }

}

bool isInvalid(int vertex) {

    if (!(1 <= vertex && vertex <= n)) {
        return true;
    }

    return false;

}

void insertEdge(int to, int from) {

    if (isInvalid(to) || isInvalid(from)) {
        printf("-1\n");
        return;
    }

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->vertex = from;
    newNode->next = NULL;

    if (graph[to].head->next == NULL) {
        graph[to].head->next = newNode;
        return;
    }

    Node * cur = graph[to].head;
    while (cur->next != NULL && cur->next->vertex < from) {
        cur = cur->next;
    }  

    newNode->next = cur->next;
    cur->next = newNode;

}


void dfs(int s) {

    Node *cur = graph[s].head->next;

    while (cur != NULL) {

        if(!visited[s]) {
            visited[s] = true;
            printf("%d\n", s);
        }

        if (!visited[cur->vertex]) {
            dfs(cur->vertex);
        }

        cur = cur->next;

    }

    return;

}