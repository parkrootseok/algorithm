#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int * initArray(int);
void input(int n, int *dic);
int binarySearch(int * dic, int start, int end, int target);

int main() {

    int *dic;
    int n, target;
    
    scanf("%d %d", &n, &target);
    
    dic = initArray(n);
    input(n, dic);

    int find = binarySearch(dic, 0, n - 1, target);
    if (find == n) {
        find = n;        
    }
    
    printf(" %d\n", find);
    return 0;

}

int * initArray(int n) {
    return (int *)malloc(sizeof(int) * n);
}

void input(int n, int * dic) {

    int key;

    for (int i = 0; i < n; i++) {
        scanf("%d", &key);
        dic[i] = key;
    }

}

int binarySearch(int * dic, int start, int end, int target) {

    while (start <= end) {

        int mid = (start + end) / 2;

        if (target == dic[mid]) {
            return mid;
        } else if (target < dic[mid]) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }

    }

    return start;

}