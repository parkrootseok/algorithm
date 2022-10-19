#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

/* 노드 및 연결 리스트 정의*/
typedef struct _node {

	int data;
	struct _node* next;

} Node;

typedef struct _linkedlist {

	Node* H;
	int size;

} linkedList;


/* 메소드 정의 */
linkedList* initlinkedList();
Node* getNode();
void Insert(linkedList * L, int data);
int removeFirst(linkedList* L);
int isEmpty(linkedList * L);
void show(linkedList* L);
linkedList* mergeSort(linkedList* L, int n);
linkedList* merge(linkedList** L1, linkedList** L2);
void partition(linkedList* L, linkedList ** L1, linkedList** L2, int k);


int main() {

	linkedList * L = initlinkedList();

	int n, data;

	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		scanf("%d", &data);
		Insert(L, data);
	}

	//removeFirst(L);
	mergeSort(L, n);
	//show(L);

}

linkedList* initlinkedList() {

	linkedList* L = (linkedList*)malloc(sizeof(linkedList));

	L->H = NULL;
	L->size = 0;

	return L;
}

Node * getNode() {

	Node * newNode = (Node*)malloc(sizeof(Node));

	newNode->next = NULL;

	return newNode;
}

int isEmpty(linkedList* L) {

	return L->H == NULL;

}

void Insert(linkedList * L, int data) {

	Node* node = L->H;
	Node* newNode = getNode();
	newNode->data = data;

	if (L->H == NULL) {
		L->H = newNode;	
	} else {
		while (node->next != NULL) {
			node = node->next;
		} node->next = newNode;

	}
	
	L->size++;
	return;
}

int removeFirst(linkedList* L) {

	Node* cur = L->H;
	int result;

	if (L->H == NULL) return;

	result = cur->data;
	L->H = cur->next;
	cur->next = NULL;
	
	free(cur);
	L->size--;

	return result;
}

void show(linkedList* L) {

	Node* node = L->H;

	for (int i = 0; i < L->size; i++) {
		printf(" %d", node->data);
		node = node->next;
	} printf("\n");

}

linkedList * mergeSort(linkedList* L,int n) {

	linkedList* result, * L1, * L2;

	if (n > 1) {
		partition(L, &L1, &L2, n);
		L1 = tmp.L1;
		L2 = tmp.L2;
		printf("size : %d\n", size);
		show(L1);
		show(L2);
		//mergeSort(L1);
		//mergeSort(L2);
		//result = merge(L1, L2);
	}

}

linkedList* merge(linkedList* L1, linkedList* L2) {

}

void partition(linkedList* L, linkedList** L1, linkedList** L2, int k) {

	Node* p = L->H;

	*L1 = L;
	for (int i = 0; i < (k / 2) - 1; i++) {
		p = p->next;
	}
	*L2 = p->next;
	p->next = NULL;

}