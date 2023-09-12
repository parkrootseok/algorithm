#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void insertionSort(int size, long * arr);

int main() {

    clock_t finish, start;  
    double duration;
    srand(time(NULL));

    long n;
    scanf("%ld", &n);

    long * arr = (long *)malloc(sizeof(long) * n);
    for (int i = 0; i < n; i++) {
        *(arr + i) = rand() % 100;
    }

    start = clock();
    insertionSort(n, arr);
    finish = clock();

    duration = (double)(finish-start)/CLOCKS_PER_SEC;
	printf("걸린 시간 : %lf\n",duration);
    for (int i = 0; i < n; i++) {
        printf("%ld ", *(arr + i));
    } printf("\n");

    return 0;
}

void insertionSort(int size, long *arr) {

    int i, j;
    long cur;

    for (i = 1; i < size; i++)
    {

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