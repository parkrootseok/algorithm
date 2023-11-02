#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {
    int elment;
    struct __node * next;
} Node;

int hashFunction(int x);
void insertItem(int x);
int findElement(int x);
int removeElement(int x);

void printHashTable();

Node **ht;
int M;

int main() {

    scanf("%d", &M);
    getchar();
    
    ht = (Node **)malloc(sizeof(Node *) * M);
    for (int i = 0; i < M; i++) {
        ht[i] = (Node *)malloc(sizeof(Node));
        ht[i]->elment = 0;
        ht[i]->next = NULL;
    }
    
    while (true) {
        
        char oper;
        int x;

        scanf("%c", &oper);
        getchar();

        switch (oper) {
        case 'i':
            scanf("%d", &x);
            insertItem(x);
            getchar();
            break;
        case 's':
            scanf("%d", &x);
            printf("%d\n", findElement(x));
            getchar();
            break;
        case 'd':
            scanf("%d", &x);
            printf("%d\n", removeElement(x));
            getchar();
            break;
        case 'p':
            printHashTable();
            break;
        case 'e':
            return 0;
        }
    }
}

int hashFunction(int x) {
    return x % M;
}

void insertItem(int x) {

    int v = hashFunction(x);

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->elment = x;
    newNode->next = NULL;

    if (ht[v]->next == NULL) {
        ht[v]->next = newNode;
    } else {
        newNode->next = ht[v]->next;
        ht[v]->next = newNode;
    }

}

int findElement(int x) {

    int v = hashFunction(x);
    Node *cur = ht[v]->next;

    if (cur == NULL) {
        return 0;
    } else {
        int cnt = 1;
        while (cur != NULL) {
            if (cur->elment == x) {
                return cnt;
            }

            cur = cur->next;
            cnt++;
        }
    }

    return 0;

}

int removeElement(int x) {

    int v = hashFunction(x);
    Node *cur = ht[v];

    if (cur == NULL) {
        return 0;
    } else {
        int cnt = 1;
        while (cur->next != NULL) {

            if (cur->next->elment == x) {
                cur->next = cur->next->next;
                return cnt;
            }

            cur = cur->next;
            cnt++;

        }
    }

    return 0;

}

void printHashTable() {

    for (int i = 0; i < M; i++) {

        Node *cur = ht[i]->next;

        while (cur != NULL) {
            printf(" %d", cur->elment);
            cur = cur->next;
        }

    }
    
    printf("\n");

}