#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#pragma warning(disable:4996)


int main() {

	int a, b, m, rep, result;
	char *ans;

	scanf("%d %d %d", &a, &b, &rep);

	ans = (char*)malloc(sizeof(char) * (rep + 1));
	scanf("%s", ans);

	for (int i = 0; i < rep; i++) {

		m = (a + b) / 2;
	
		if (ans[i] == 'Y') a = m + 1;
		else b = m;
		
		if (a == b)	result = a;
	}
	printf("%d\n", result);

	free(ans);
	return;
}
