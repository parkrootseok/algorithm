#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {
    int elment;
    struct __node * next;
} Node;

int hashFunction(int x);
int getNextBucket(int x, int v, int i);

void insertItem(int x);
bool findElement(int x);
void printHashTable();

Node **ht;
int M, n, q;

int main() {

    scanf("%d %d %d", &M, &n, &q);
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
            if (!findElement(x)) {
                printf("-1\n");
            }
            getchar();
            break;
        case 'p':
            printHashTable();
            break;
        case 'e':
            printHashTable();
            return 0;
        }
    }
}

int hashFunction(int x) {
    return x % M;
}

int doubleHashFunction(int x) {
    return q - (x % q);
}

int getNextBucket(int x, int v, int i) {
    return (v + i * doubleHashFunction(x)) % M;
}

void insertItem(int x) {

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->elment = x;
    newNode->next = NULL;

    int v = hashFunction(x);
    int i, b;

    i = 0;
    while (i < M) {
        b = getNextBucket(x, v, i);
        if(ht[b]->next == NULL) {
            ht[b]->next = newNode;
            printf("%d\n", b);
            return;
        } else {
            printf("C");
            i++;
        }
    }

}

bool findElement(int x) {

    int v = hashFunction(x);
    int i, b;

    i = 0;
    while (i < M) {
        b = getNextBucket(x, v, i);
        if(ht[b]->next == NULL) {
            return false;
        } else  if (ht[b]->next->elment == x) {
            printf("%d %d\n", b, ht[b]->next->elment);
            return true;
        } else {
            i++;
        }
    }

    return false;

}

void printHashTable() {

    for (int i = 0; i < M; i++) {

        Node *cur = ht[i]->next;
        
        if (cur == NULL) {
            printf(" 0");
        }

        while (cur != NULL) {
            printf(" %d", cur->elment);
            cur = cur->next;
        }

    }
    
    printf("\n");

}