#include <stdio.h>
#include <stdlib.h>

int main() {
    
    char ans;
    int a, b, n, mid;
    
    scanf("%d %d %d", &a, &b, &n);
    getchar();


    for (int i = 0; i < n; i++) {
        
        mid = (a + b) / 2;
        scanf("%c", &ans);
        
        if(ans == 'Y') {
            a = mid + 1;
        }
        else {
            b = mid;
        }

        if (a == b) {
            printf("%d\n", a);
            return 0;
        }

    }

}