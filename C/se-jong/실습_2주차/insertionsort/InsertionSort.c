#include <stdio.h>
#include <stdlib.h>

void insertionSort(int size, int * arr);

int main()
{

    int n;
    scanf("%d", &n);

    int * arr = (int *)malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) {
        scanf("%d", arr + i);
    }

    insertionSort(n, arr);

    for (int i = 0; i < n; i++) {
        printf("%d ", *(arr + i));
    }

    return 0;
}

void insertionSort(int size, int *arr) {

    int i, j, cur;

    for (i = 1; i < size ; i++) {

        cur = *(arr + i);

        for (j = i - 1; 0 <= j; j--) {

            if (*(arr + j) < cur) {
                break;
            }

              *(arr + (j + 1)) = *(arr + j);

        }

        *(arr + (j + 1)) = cur;

    }

}