#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int **graph;
int N = 6;

void initGraph() {

    graph = (int **)malloc(sizeof(int *) * (N + 1));
    for (int i = 0; i <= N; i++) {
        graph[i] = (int *)malloc(sizeof(int) * (N + 1));
    }

    graph[1][2] = 1; graph[2][1] = 1;
    graph[1][3] = 1; graph[3][1] = 1;
    graph[1][4] = 1; graph[4][1] = 1;
    graph[1][6] = 2; graph[6][1] = 2;

    graph[2][3] = 1; graph[3][2] = 1;

    graph[3][5] = 4; graph[5][3] = 4;

    graph[5][5] = 4;
    graph[5][6] = 3; graph[6][5] = 3;

}

void printGraph(int to);
void modifyEdge(int to, int from, int w);

int main() {

    initGraph();

    while (true) {

        int to, from, w;
        char oper;
        scanf("%c", &oper);

        switch (oper) {
        case 'a':
            scanf("%d", &to);
            printGraph(to);
            getchar();
            break;
        case 'm':
            scanf("%d %d %d", &to, &from, &w);
            modifyEdge(to, from, w);
            getchar();
            break;
        case 'q':
            getchar();
            return 0;
        }
    }

    return 0;
}

void printGraph(int to) {

    if (!(1 <= to && to <= N)) {
        printf("-1\n");
        return;
    }

    for (int i = 1; i <= N; i++) {
        if (graph[to][i] != 0) {
            printf(" %d %d", i, graph[to][i]);
        }
    }
    printf("\n");
    
    return;

}

void modifyEdge(int to, int from, int w) {

    if (!(1 <= to && to <= N) || !(1 <= from && from <= N)) {
        printf("-1\n");
        return;
    }

    if(w == 0) {
        graph[to][from] = 0;
        graph[from][to] = 0;
    } else {
        graph[to][from] = w;
        graph[from][to] = w;
    }

    return;

}