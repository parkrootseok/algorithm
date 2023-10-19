#include <stdio.h>

#define SIZE 101

int HEAP[SIZE];
int numOfData = 0;

void rbuildHeap(int i);
void downHeap(int root);
void inPlaceHeapSort();
void printHeap();

int main() {

    int rep, key;

    scanf("%d", &rep);
    getchar();
    for (int i = 0; i < rep; i++) {

        scanf("%d", &key);
        getchar();

        HEAP[++numOfData] = key;

    }

    inPlaceHeapSort();
    numOfData = rep;
    printHeap();

    return 0;

}

void rbuildHeap(int i) {

    if (i > numOfData) {
        return;
    }

    rbuildHeap(i * 2);
    rbuildHeap(i * 2 + 1);
    downHeap(i);

    return;
}

void downHeap(int root) {

    int left = root * 2;
    int right = left + 1;

    if (left > numOfData && right > numOfData) {
        return;
    }

    int bigger = left;
    if (right <= numOfData) {
        if (HEAP[left] <= HEAP[right]) {
            bigger = right;
        }
    } 

    if (HEAP[root] >= HEAP[bigger]) {
        return;
    }

    int tmp = HEAP[root];
    HEAP[root] = HEAP[bigger];
    HEAP[bigger] = tmp;

    downHeap(bigger);

}

void inPlaceHeapSort() {
    
    rbuildHeap(1);

    for (int i = numOfData; 1 < i; i--) {

        int tmp = HEAP[1];
        HEAP[1] = HEAP[i];
        HEAP[i] = tmp;

        numOfData--;

        downHeap(1);
    
    }

}

void printHeap() {

    if (numOfData < 1) {
		return;
    }

    for (int i = 1; i <= numOfData; i++) {
        printf(" %d", HEAP[i]);
    } printf("\n");

}