#include <stdio.h>
#include <stdlib.h>

void swap(int * a, int * b) {

    int tmp = * a;
    * a = * b;
    * b = tmp;

}

int * input(int N) {

    int elem;
    int * arr = (int *)malloc(sizeof(int) * N);
    
    for (int i = 0; i < N; i++) {
        scanf("%d", &elem);
        arr[i] = elem;
    }

    return arr;

}

void print(int * arr, int N) {


    for (int i = 0; i < N; i++) {
        printf("%d ", arr[i]);
    }
    
    printf("\n");

}

void inPlaceQuickSort(int *L, int l, int r);
int inPlacePartition(int *L, int l, int r, int k);

int main() {

    int *arr;
    int N;

    scanf("%d", &N);
    arr = input(N);
    inPlaceQuickSort(arr, 0, N - 1);
    print(arr, N);

    return 0;

}

void inPlaceQuickSort(int *L, int l, int r) {

    int k, a;

    if (l >= r) {
        return;
    }

    k = (l + r) / 2;
    a = inPlacePartition(L, l, r, k);
    inPlaceQuickSort(L, l, a - 1);
    inPlaceQuickSort(L, a + 1, r);
    
}

int inPlacePartition(int *L, int l, int r, int k) {

    int p, i, j;

    p = L[k];
    swap(&L[k], &L[r]);

    i = l;
    j = r - 1;

    while (i <= j) {

        while (i <= j && L[i] <= p) {
            i++;
        }

        while (i <= j && L[j] >= p) {
            j--;
        }

        if (i < j) {
            swap(&L[i], &L[j]);
        }
        
    }

    swap(&L[i], &L[r]);
    return i;

}