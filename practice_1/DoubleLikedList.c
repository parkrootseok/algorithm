#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

// 노드, 리스트 정의

typedef struct _node {

	char e;
	struct _node* prev;
	struct _node* next;

} Node;

typedef struct _DoubleLinkedList {

	Node* H, * T;
	int size;

} DoubleLinkedList;

// 메소드 정의

void initList(DoubleLinkedList* list);
Node* newNode(char e);
void add(DoubleLinkedList* list, int r, char e);
void delete(DoubleLinkedList* list, int r);
void get(DoubleLinkedList* list, int r);
void print(DoubleLinkedList* list);

int main() {

	DoubleLinkedList* list = (DoubleLinkedList*)malloc(sizeof(DoubleLinkedList));
	int num, r;
	char oper, e;

	initList(list);

	scanf("%d", &num); getchar();
	for (int i = 0; i < num; i++) {

		scanf("%c", &oper); getchar();

		switch (oper) {

		case 'A':
			scanf("%d %c", &r, &e); getchar();
			add(list, r, e);
			break;

		case 'D':
			scanf("%d", &r); getchar();
			delete(list, r);
			break;
		case 'G':
			scanf("%d", &r); getchar();
			get(list, r);
			break;
		case 'P':
			print(list);
			break;

		}
	}

	return 0;

}

void initList(DoubleLinkedList* list) {

	Node* H, * T;

	H = (Node*)malloc(sizeof(Node));
	T = (Node*)malloc(sizeof(Node));

	list->H = H; H->next = T; H->prev = NULL;
	list->T = T; T->prev = H; T->next = NULL;

	list->size = 0;

}

Node* newNode(char e) {

	Node* new = (Node*)malloc(sizeof(Node));

	new->prev = new->next = NULL;
	new->e = e;

	return new;

}

void add(DoubleLinkedList* list, int r, char e) {

	Node* new;
	Node* pos = list->H;

	// 순위 정보 유지
	// 4순위 입력 후 6순위 입력 불가. 반드시 1, 2, 3, 4, 5 , ... 순서 유지.
	if (list->size + 1 < r) {
		printf("invalid position\n");
		return;
	}

	new = newNode(e);

	// 노드 이동
	for (int i = 0; i < r; i++) pos = pos->next;

	// 노드 연결
	pos->prev->next = new;
	new->prev = pos->prev;

	pos->prev = new;
	new->next = pos;

	pos = new;
	list->size++;

}

void delete(DoubleLinkedList* list, int r) {

	Node* pos = list->H;

	if (list->size < r) {
		printf("invalid position\n");
		return;
	}

	// 노드 이동
	for (int i = 0; i < r; i++) pos = pos->next;

	// 현재 노드의 전, 후 노드 연결
	pos->prev->next = pos->next;
	pos->next->prev = pos->prev;

	// 노드 삭제
	free(pos);
	list->size--;
}

void get(DoubleLinkedList* list, int r) {

	Node* pos = list->H;

	if (list->size < r) {
		printf("invalid position\n");
		return;
	}

	// 노드 이동
	for (int i = 0; i < r; i++) pos = pos->next;
	printf("%c\n", pos->e);

}

void print(DoubleLinkedList* list) {

	Node* node = list->H;
	int size = list->size;

	for (int i = 0; i < size; i++) {
		node = node->next;
		printf("%c", node->e);
	} printf("\n");

}