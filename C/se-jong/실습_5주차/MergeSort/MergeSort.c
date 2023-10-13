#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {

	int key;
	struct __node * next;

} Node;

typedef struct __list {
	
	Node * head;
	int numOfData;

} List;

List * initList() {

	List *list = (List *)malloc(sizeof(List));

	list->head = (Node *)malloc(sizeof(Node));
	list->head->next = NULL;
	list->numOfData = 0;

	return list;

}

Node * createNode(int key) {

	Node *newNode = (Node *)malloc(sizeof(Node));
	newNode->key = key;
	newNode->next = NULL;

	return newNode;

}

void addLast(List * list, int key) {

	Node * pos = list->head;
	Node *newNode = createNode(key);

	while (pos->next) {
		pos = pos->next;
	}

	pos->next = newNode;
	(list->numOfData)++;

}

int removeFirst(List * list) {

	Node *removeNode = list->head->next;
	int removeKey = removeNode->key;

	list->head->next = removeNode->next;
	(list->numOfData)--;

	free(removeNode);

	return removeKey;

}

int get(List * list) {
	return list->head->next->key;
}

int size(List * list) {
	return list->numOfData;
}

bool isEmpty(List * list) {
	
	if (list->numOfData == 0) {
		return true;
	}

	return false;

}

void print(List * list) {

	Node *pos = list->head->next;

	for (int i = 0; i < list->numOfData; i++) {
		printf(" %d", pos->key);
		pos = pos->next;
	} printf("\n");

}

void input(List * list, int n) {
	
	int key;
	
	for (int i = 0; i < n; i++) {
		scanf("%d", &key);
		addLast(list, key);
	}

}

void mergeSort(List *list);
void merge(List * L, List * L1, List * L2);
List * partition(List *list, int n);

int main() {

	List *list = initList();

	int n, key;
	scanf("%d", &n);

	input(list, n);
	mergeSort(list);
	print(list);

	return 0;

}

void merge(List * L, List * L1, List * L2) {

	while (!isEmpty(L1) && !isEmpty(L2)) {
		
		if (get(L1) <= get(L2)) {
			addLast(L, removeFirst(L1));
		} else {
			addLast(L, removeFirst(L2));
		}
	}

	while (!isEmpty(L1)) {
		addLast(L, removeFirst(L1));
	}
	

	while (!isEmpty(L2)) {
		addLast(L, removeFirst(L2));
	}	

}

List * partition(List *list, int n) {

	List *L = initList();

	for (int i = 0; i < n; i++) {
		addLast(L, removeFirst(list));
	}

	return L;

}

void mergeSort(List * list) {
	
	List * L1;
	List * L2;

	if (size(list) > 1) {
		L1 = partition(list, list->numOfData / 2);
		L2 = partition(list, list->numOfData);
		mergeSort(L1);
		mergeSort(L2);
		merge(list, L1, L2);
	}

	return;

}