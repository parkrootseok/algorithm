#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

/*
5 
A 1 S 
A 2 t 
A 3 r 
A 3 a 
P

9 
A 1 D 
A 2 a 
A 3 y 
D 1
P 
G 3 
A 1 S
P
G 3 
*/

typedef struct __node {

    char elem;
    struct __node *prev;
    struct __node *next;

} Node;

typedef struct __doublelinkedlist {

    Node *head;
    Node *tail;
    int numOfData;

} DBLinkedList;

void InitDBLinkedList(DBLinkedList * list);
void LInsert(DBLinkedList * list, int position, char elem);
void LDelete(DBLinkedList * list, int position);
void LGet(DBLinkedList * list, int position);
void LPrint(DBLinkedList * list);

int main() {

    DBLinkedList * list = (DBLinkedList *)malloc(sizeof(DBLinkedList));
    InitDBLinkedList(list);

    int n, p;
    char cmd, elem;
    
    scanf("%d", &n);
    getchar();

    for (int i = 0; i < n; i++)   {

        scanf("%c", &cmd);
        getchar();

        switch (cmd) {
        case 'A':
            scanf("%d %c", &p, &elem);
            getchar();
            LInsert(list, p, elem);
            break;

        case 'D':
            scanf("%d", &p);
            getchar();
            LDelete(list, p);
            break;

        case 'G':
            scanf("%d", &p);
            getchar();
            LGet(list, p);
            break;

        case 'P':
            LPrint(list);
            break;
        }


    }

    return 0;

}

void InitDBLinkedList(DBLinkedList * list) {

    list->head = (Node *)malloc(sizeof(Node));
    list->tail = (Node *)malloc(sizeof(Node));

    list->head->next = list->tail;
    list->head->prev = NULL;

    list->tail->next = NULL;
    list->tail->prev = list->head;

    list->numOfData = 0;

}

void LInsert(DBLinkedList * list, int position, char elem) {

    Node * newNode = (Node *)malloc(sizeof(Node));
    Node * cur = list->head;

    newNode->elem = elem;
    newNode->next = NULL;
    newNode->prev = NULL;

    if (list->numOfData < position - 1) {
        printf("invalid position\n");
        return;
    }

    // 노드를 삽입할 위치로 이동
    for (int i = 0; i < position; i++) {
        cur = cur->next;
    }

    // 가장 앞의 노드 - newNode
    cur->prev->next = newNode;
    newNode->prev = cur->prev;

    // newNode - tail
    cur->prev = newNode;
    newNode->next = cur;

    (list->numOfData)++;

}

void LDelete(DBLinkedList * list, int position) {

    Node *cur = list->head;

    if (list->numOfData < position) {
        printf("invalid position\n");
        return;
    }

    // 마지막 노드로 이동
    for (int i = 0; i < position; i++) {
        cur = cur->next;
    }

    // 1 - 2 - 3 -> 1 - 3
    cur->prev->next = cur->next;
    cur->next->prev = cur->prev;

    (list->numOfData)--;
    free(cur);

}

void LGet(DBLinkedList * list, int position) {

    Node *cur = list->head;

    if (list->numOfData < position) {
        printf("invalid position\n");
        return;
    }

    for (int i = 0; i < position; i++) {
        cur = cur->next;
    }

    printf("%c\n", cur->elem);

}

void LPrint(DBLinkedList * list) {

    Node *cur = list->head;

    for (int i = 0; i < list->numOfData; i++) {
        cur = cur->next;
        printf("%c", cur->elem);
    } printf("\n");

}