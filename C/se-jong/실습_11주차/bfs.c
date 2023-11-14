#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {

    int vertex;
    struct __node * next;

} Node;

typedef struct  __queue{

    Node * head;
    int numOfData;

} Queue;

Queue * q;

int **graph;
int n, m, s;
bool * visited;

void initQueue();
void qOffer(int vertext);
int qRemove();

void initGraph();
void bfs(int s);

int main() {

    initGraph();
    bfs(s);

    return 0;

}

void initQueue() {

    q = (Queue *)malloc(sizeof(Queue));
    q->head = (Node *)malloc(sizeof(Node));
    q->head->next = NULL;

    q->numOfData = 0;

}

void qOffer(int vertext) {

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->vertex = vertext;
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

int qRemove() {

    Node *removeNode = q->head->next;
    int removeData = removeNode->vertex;

    q->head->next = removeNode->next;
    (q->numOfData)--;

    free(removeNode);

    return removeData;

}

bool isEmpty() {

    if (q->numOfData == 0) {
        return true;
    }

    return false;

}

void initGraph() {

    scanf("%d %d %d", &n, &m, &s);
    getchar();

    graph = (int **)malloc(sizeof(int *) * (n + 1));
    for (int i = 0; i <= n; i++) {
        graph[i] = (int *)malloc(sizeof(int) * (n + 1));
    }

    visited = (bool *)malloc(sizeof(bool) * (n + 1));

    int to, from;
    for (int i = 0; i < m; i++) {

        scanf("%d %d", &to, &from);
        getchar();

        graph[to][from] = 1;
        graph[from][to] = 1;
        
    }

}

void bfs(int s) {

    initQueue();

    qOffer(s);
    visited[s] = true;
    while (!isEmpty()) {

        int cur = qRemove();
        printf("%d\n", cur);

        for (int i = 1; i <= n; i++) {

            if (!visited[i] && graph[cur][i] == 1) {
                visited[i] = true;
                qOffer(i);
            }

        }

    }

    return;

}