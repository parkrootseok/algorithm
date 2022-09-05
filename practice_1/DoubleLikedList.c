#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

// 노드 및 리스트 정의

typedef struct DoubleLinkedListNode {
	
	int r;			// 순서
	char elem;			// 원소
	struct DoubleLinkedListNode* prev;	// 이전 노드
	struct DoubleLinkedListNode* next;	// 다음 노드

} node; // 이중연결리스트 노드 


typedef struct DoubleLinkedList{

	int size;	// 리스트 크기
	node* H;	// 헤더
	node* T;	// 트레일러

} list; // 이중연결리스트(헤더, 트레일러 보유)

// 메소드 정의

void init(list* list);
node* newNode(int r, char elem);
void ADD(list* list, int r, char e);
void DELETE(list* list, int r);
void GET(list* list, int r);
void print(list* list);

int main() {

	list list; init(&list);
	int opernum, r;
	char oper, elem;

	scanf("%d", &opernum);
	getchar();

	for (int i = 0; i < opernum; i++) {
		scanf("%c", &oper);
		getchar();

		switch (oper) {
		case 'A' :
			scanf("%d %c", &r, &elem);
			getchar();
			ADD(&list, r, elem);
			break;
		case 'D':
			scanf("%d", &r);
			getchar();
			DELETE(&list, r);
			break;
		case 'G':
			scanf("%d", &r);
			getchar();
			GET(&list, r);
			break;
		case 'P':
			print(&list);
			break;
		default:
			break;
		}
	}

	return;
}


// 메소드 구현

void init(list* list) {

	node* header = (node *)malloc(sizeof(node));
	node* trailer = (node*)malloc(sizeof(node));

	list->H = header; header->next = trailer; header->prev = NULL;
	list->T = trailer; trailer->prev = header; trailer->next = NULL;
	list->size = 0;

} // DoublelinkedList 생성

node* newNode(int r, char elem) {

	node* newnode = (node*)malloc(sizeof(node));

	newnode->next = NULL; newnode->prev = NULL;
	
	newnode->r = r;
	newnode->elem = elem;

	return newnode;

} // Node 생성

void ADD(list* list, int r, char e) {

	node* newnode = newNode(r, e);
	node* node = list->H;

	// 노드 위치 이동 후 노드 추가
	for (int i = 0; i < r; i++) {
		node = node->next;
	} 
	
	// node.prev <- new -> node 
	newnode->next = node;
	newnode->prev = node->prev;

	// node -> new
	node->prev = newnode;

	// newnode <- node
	node = newnode->prev;
	node->next = newnode;

	list->size++;

}

void DELETE(list* list, int r) {

	node* node, *p, *q;
	node = list->H;

	if (r > list->size) {
		printf("invalid position\n");
		return;
	}

	// 노드 이동
	for (int i = 0; i < r; i++) node = node->next;
	
	// node.prev -> node.next, node.prev <- node.next
	p = node->prev;
	q = node->next;
	p->next = q;
	q->prev = p;

	free(node);
	list->size--;

}
void GET(list* list, int r) {

	node* node = list->H;

	if (r > list->size) {
		printf("invalid position\n");
		return;
	}

	for (int i = 0; i < r; i++) {
		node = node->next;
	}

	printf("%c\n", node->elem);

}

void print(list* list) {

	node* node = list->H;

	for (int i = 0; i < list->size; i++) {
		node = node->next;
		printf("%c", node->elem);
	} printf("\n");

}