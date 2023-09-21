package baekjoon.class2.p10866;

/**
 * Dequeue
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Node {

    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}

class CustomDequeue {

    Node head;
    Node tail;
    int size;

    public CustomDequeue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

}

public class Main {

    public static void push_front(int x, CustomDequeue dq) {

        Node newNode = new Node(x);
        newNode.next = dq.head;

        if (empty(dq) == 1) {
            dq.tail = newNode;
        } else {
            dq.head.prev = newNode;
        }

        dq.head = newNode;
        dq.size++;

    }
    public static void push_back(int x, CustomDequeue dq) {

        Node newNode = new Node(x);
        newNode.prev = dq.tail;

        if (empty(dq) == 1) {
            dq.head = newNode;
        } else {
            dq.tail.next = newNode;
        }

        dq.tail = newNode;
        dq.size++;

    }

    public static int pop_front(CustomDequeue dq) {

        Node removeNode;
        int removeData;

        if (empty(dq) == 1) {
            return -1;
        }

        removeNode = dq.head;
        removeData = removeNode.data;

        dq.head = removeNode.next;

        if (dq.head == null) {
            dq.tail = null;
        } else {
            dq.head.prev = null;
        }

        dq.size--;
        return removeData;

    }

    public static int pop_back(CustomDequeue dq) {

        Node removeNode;
        int removeData;

        if (empty(dq) == 1) {
            return -1;
        }

        removeNode = dq.tail;
        removeData = removeNode.data;

        dq.tail = removeNode.prev;

        if (dq.tail == null) {
            dq.head = null;
        } else {
            dq.tail.next = null;
        }

        dq.size--;
        return removeData;

    }

    public static int front(CustomDequeue dq) {

        if (empty(dq) == 1) {
            return -1;
        }

        return dq.head.data;
    }

    public static int back(CustomDequeue dq) {

        if (empty(dq) == 1) {
            return -1;
        }

        return dq.tail.data;
    }

    public static int size(CustomDequeue dq) {
        return dq.size;
    }

    public static int empty(CustomDequeue dq) {
        return dq.size == 0 ? 1 : 0;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        CustomDequeue dq = new CustomDequeue();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < n ; i++) {

            String cmd;
            int data = -1;

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = st.nextToken();
            if (st.hasMoreTokens()) {
                data = Integer.parseInt(st.nextToken());
            }

            switch (cmd) {
                case "push_front":
                    push_front(data, dq);
                    break;
                case "push_back":
                    push_back(data, dq);
                    break;
                case "pop_front":
                    System.out.println(pop_front(dq));
                    break;
                case "pop_back":
                    System.out.println(pop_back(dq));
                    break;
                case "front":
                    System.out.println(front(dq));
                    break;
                 case "back":
                    System.out.println(back(dq));
                    break;
                case "size":
                    System.out.println(size(dq));
                    break;
                case "empty":
                    System.out.println(empty(dq));
                    break;
                default:
                    return;
            }

        }

    }

}