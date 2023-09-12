#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void selectionSort(int size, long * arr);

int main()
{

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
    selectionSort(n, arr);
    finish = clock();

    duration = (double)(finish-start) / CLOCKS_PER_SEC;
	printf("걸린 시간 : %lf\n",duration);
    for (int i = 0; i < n; i++) {
        printf("%ld ", *(arr + i));
    } printf("\n");

    return 0;
}

void selectionSort(int size, long *arr) {

    int tmp, idx;
    for (int i = 0; i < size ; i++) {

        idx = i;
        for (int j = i + 1; j < size; j++) {

            if (*(arr + idx) > *(arr + j)) {
                idx = j;
            }

        }

        tmp = *(arr + i);
        *(arr + i) = *(arr + idx);
        *(arr + idx) = tmp;
    }

}