#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {

    int vertex;
    int weigth;
    struct __node *next;

} Node;

typedef struct __list {

    Node *head;

} List ;

void initGraph(int N);
bool isInvalid(int vertex);
void insertEdge(int to, int from, int weight);
void deleteEdge(int to, int from);
void modifyEdge(int to, int from, int weight);
void printGraph(int vertex);

List * graph;
int N = 6;

int main() {

    initGraph(N);
    while (true)  {

        int to, from, w;
        char oper;
        scanf("%c", &oper);

        switch (oper) {
        case 'a':
            scanf("%d", &to);
            if (isInvalid(to)) {
                printf("-1\n");
            } else {
                printGraph(to);
            }
            getchar();
            break;
        case 'm':
            scanf("%d %d %d", &to, &from, &w);
            if (isInvalid(to) || isInvalid(from)) {
                printf("-1\n");
            } else {
                modifyEdge(to, from, w);
                modifyEdge(from, to, w);
            }
            getchar();
            break;
        case 'q':
            getchar();
            return 0;
        }
    }

    return 0;
}

void initGraph(int N) {

    graph = (List *)malloc(sizeof(List) * (N + 1));
    for (int i = 0; i <= N; i++) {
        graph[i].head = (Node *)malloc(sizeof(Node));
        graph[i].head->next = NULL;
    }

    insertEdge(1, 2, 1); insertEdge(2, 1, 1);
    insertEdge(1, 3, 1); insertEdge(3, 1, 1);
    insertEdge(1, 4, 1); insertEdge(4, 1, 1);
    insertEdge(1, 6, 2); insertEdge(6, 1, 2);

    insertEdge(2, 3, 1); insertEdge(3, 2, 1);

    insertEdge(3, 5, 4);  insertEdge(5, 3, 4);

    insertEdge(5, 5, 4);

    insertEdge(5, 6, 3); insertEdge(6, 5, 3);

}

bool isInvalid(int vertex) {

    if (!(1 <= vertex && vertex <= 6)) {
        return true;
    }

    return false;

}

void insertEdge(int to, int from, int weight) {

    if (isInvalid(to) || isInvalid(from)) {
        printf("-1\n");
        return;
    }

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->vertex = from;
    newNode->weigth = weight;
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

void printGraph(int vertex) {

    Node *cur = graph[vertex].head->next;

    if (cur == NULL) {
        printf("-1\n");
        return;
    }

    while (cur) {
        printf(" %d %d", cur->vertex, cur->weigth);
        cur = cur->next;
    }

    printf("\n");

}

void modifyEdge(int to, int from, int weight) {

    if (weight == 0) {
        deleteEdge(to, from);
    } else {

        Node *cur = graph[to].head->next;

        while (cur) {
            if (cur->vertex == from) {
                break;
            }
            cur = cur->next;
        }

        if (cur == NULL) {
            insertEdge(to, from, weight);
        } else {
            cur->weigth = weight;
        }

    }

}

void deleteEdge(int to, int from) {
    
    Node *cur = graph[to].head;
    Node *prev = NULL;

    while (cur != NULL) {

        if (cur->vertex == from) {
            if (prev == NULL) {
                graph[to].head = cur->next;
            } else {
                prev->next = cur->next;
            }
            free(cur);
            break; 
        }

        prev = cur;
        cur = cur->next;

    }

}