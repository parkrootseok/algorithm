#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {
    int data;
    struct __node * next;
} Node;

typedef struct __list {
    Node *head;
    int numOfData;
} List;

Node * makeNode() {

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->next = NULL;

    return newNode;
}

List * initList() {
    
    List *list = (List *)malloc(sizeof(List));
    list->head = makeNode();
    list->numOfData = 0;

    return list;

}

// swap(int *a, int *b)
// {

//     int tmp = * a;
//     * a = * b;
//     * b = tmp;
// }

void input(List *list);
void addLast(List *list, int data);
int removeFirst(List *list);
int get(List *list, int k);
int isSize(List *list);
bool isEmpty(List *list);
void inPlaceQuickSort(int *arr, int l, int r);
int inPlacePartition(int * arr, int l, int r, int k);
void print(List * list);

void quickSort(List *L);
void partition(List *L, List * LT, List * EQ, List *GT, int k);
void merge(List *L, List *LT, List *EQ, List *GT);
int main()
{

    List *list = initList();

    input(list);
    quickSort(list);
    print(list);

    return 0;
}

void addLast(List *list, int data) {

    Node *cur = list->head;
    Node *node = makeNode();
    node->data = data;

    while (cur->next != NULL) {
        cur = cur->next;
    }

    cur->next = node;
    (list->numOfData)++;

}

int removeFirst(List *list) {

    Node *rNode = list->head->next;
    int rData = rNode->data;

    list->head->next = rNode->next;
    (list->numOfData)--;

    free(rNode);
    return rData;

}

int get(List *list, int k) {

    Node *cur = list->head;

    if(isSize(list) < k) {
        return false;
    }

    for (int i = 0; i <= k; i++) {
        cur = cur->next;
    }

    return cur->data;
}

int isSize(List *list) {
    return list->numOfData;
}

bool isEmpty(List *list) {

    if(isSize(list) == 0) {
        return true;
    }

    return false;

}

void input(List * list) {

    int data;

    while (true) {
        
        scanf("%d", &data);
        getchar();

        if (data == -1) {
            return;
        }

        addLast(list, data);
        
    }

}

void print(List * list) {

    Node * cur = list->head;

    for (int i = 0; i < list->numOfData; i++) {
        cur = cur->next;
        printf(" %d", cur->data);
    }

    printf("\n");

}

void quickSort(List *L) {

    List *LT = initList(), *EQ = initList(), *GT = initList();
    int k;

    if (isSize(L) > 1) {
        k = L->numOfData / 2;
        partition(L, LT, EQ, GT, k);
        quickSort(LT);
        quickSort(GT);
        merge(L, LT, EQ, GT);
    }

}

void partition(List *L, List * LT, List * EQ, List *GT, int k) {

    int data, pivot;

    pivot = get(L, k);
    while(!isEmpty(L)) {
        data = removeFirst(L);
        if (pivot > data) {
            addLast(LT, data);
        } else if (pivot == data) {
            addLast(EQ, data);
        } else {
            addLast(GT, data);
        }
    }

    return;

}
void merge(List *L, List *LT, List *EQ, List *GT) {

    while(!isEmpty(LT)) {
        addLast(L, removeFirst(LT));
    }
    
    while(!isEmpty(EQ)) {
        addLast(L, removeFirst(EQ));
    }

    while(!isEmpty(GT)) {
        addLast(L, removeFirst(GT));
    }
}