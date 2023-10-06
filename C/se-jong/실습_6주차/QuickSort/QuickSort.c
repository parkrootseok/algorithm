#include <stdio.h>
#include <stdlib.h>

void swap(int * a, int * b) {

    int tmp = * a;
    * a = * b;
    * b = tmp;

}

int * input(int N) {

    int elem;
    int *arr = (int *)malloc(sizeof(int) * N);
    for (int i = 0; i < N; i++) {
        scanf("%d", &elem);
        arr[i] = elem;
    }

    return arr;

}

void print(int N, int * arr) {


    for (int i = 0; i < N; i++) {
        printf("%d ", arr[i]);
    }
    
    printf("\n");

}

void inPlaceQuickSort(int * arr, int l, int r);
int inPlacePartition(int * arr, int l, int r, int k);

int main() {

    int N;
    scanf("%d", &N);
    
    int *arr = input(N);

    inPlaceQuickSort(arr, 0, N - 1);

    print(N, arr);
    return 0;

}

void inPlaceQuickSort(int * arr, int l, int r) {

    int k, a, b;

    if (l >= r) {
        return;
    }

    k = (l + r) / 2;
    a = inPlacePartition(arr, l, r, k);
    b = a + 1;

    while (arr[b] == arr[b + 1]) {  // 중복된 원소는 정렬에서 제외
        b++;
    }

    inPlaceQuickSort(arr, l, a - 1);
    inPlaceQuickSort(arr, a + 1, r);

}

int inPlacePartition(int * arr, int l, int r, int k) {

    int pivot, i, j;

    // pivot 숨기기
    pivot = arr[k];
    swap(&arr[k], &arr[r]);
    i = l;
    j = r - 1;

    while (i <= j) {
        
        // pivot보다 작으면 이동
        while (i <= j && pivot >= arr[i]) { 
            i++;
        }

        // pivot보다 크면 이동
        while (j >= i && pivot <= arr[j]) {
            j--;
        }

        // i는 피봇보다 큰수이고 j는 피봇보다 작은수이므로 둘을 교환
        // 단, i와 j가 교차하지 않았을 때 수행
        if (i < j) {
            swap(&arr[i], &arr[j]);
        }

    }

    // 피봇을 다시 가져옴
    swap(&arr[i], &arr[r]);

    return i;
    
}