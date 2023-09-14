#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/**
i 24
i 17
i 33
p
d
i 50
p
q
*/

#define SIZE 101

int Heap[SIZE];
int numOfData = 0;

bool Insert(int key);
void upHeap(int idx);
int Remove();
void downHeap(int root);
void print();

int main() {

    int key;
    char oper;

    while (1) {

        scanf("%c", &oper);
        getchar();

        switch (oper) {
        case 'i':
            scanf(" %d", &key);
            getchar();
            if (Insert(key)) {
                printf("0\n");
            }
            break;
        case 'd':
            printf("%d\n", Remove());
            break;
        case 'p':
            print();
            break;
        case 'q':
            return 0;
        }

    }
}

bool Insert(int key) {

    if (numOfData > SIZE - 1) {
        return false;
    }

    Heap[++numOfData] = key;
    upHeap(numOfData);

    return true;

}

void upHeap(int idx) {

    int parent = idx / 2;

    if (idx == 1) {
        return;
    }

    if (Heap[idx] <= Heap[parent]) {
        return;
    }

    int tmp = Heap[parent];
    Heap[parent] = Heap[idx];
    Heap[idx] = tmp;

    upHeap(parent);

}

int Remove() {

    int key = Heap[1];
    Heap[1] = Heap[numOfData];
    numOfData--;
    downHeap(1);
    return key;

}

void downHeap(int root) {

    
    int left = root * 2;
    int right = left + 1;

    if (left > numOfData && right > numOfData) {
        return;
    }

    int bigger = right;
    if (Heap[left] > Heap[right]) {
        bigger = left;
    }

    int tmp = Heap[bigger];
    Heap[bigger] = Heap[root];
    Heap[root] = tmp;

    downHeap(bigger);

}

void print() {

    for (int i = 1; i <= numOfData; i++) {
        printf(" %d", Heap[i]);
    } printf("\n");
}