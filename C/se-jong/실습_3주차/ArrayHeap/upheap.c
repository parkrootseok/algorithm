#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define SIZE 101

int Heap[SIZE];
int numOfData = 0;

void rbuildHeap(int root);
void buildHeap();
void downHeap(int root);
void printHeap();

int main() {

    int num;
    scanf("%d", &num);
    getchar();

    int key;
    for (int i = 1; i <= num; i++) {

        scanf("%d", &key);
        Heap[i] = key;
        numOfData++;
        
    }

    buildHeap();
    // rbuildHeap(1);
    printHeap();
}

void rbuildHeap(int i) {    // 재귀식

    if (i > numOfData) {
        return;
    }

    rbuildHeap(i * 2);
    rbuildHeap(i * 2 + 1);
    downHeap(i);

    return;
}

void buildHeap() {  // 비재귀식

    for (int i = numOfData / 2 ; i >= 1 ; i--) {
        downHeap(i);
    }

    return;

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

    if (Heap[root] >= Heap[bigger]) {
        return;
    }

    int tmp = Heap[bigger];
    Heap[bigger] = Heap[root];
    Heap[root] = tmp;

    downHeap(bigger);

}

void printHeap() {

    if (numOfData < 1) {
		return;
    }

    for (int i = 1; i <= numOfData; i++) {
        printf(" %d", Heap[i]);
    } printf("\n");

}