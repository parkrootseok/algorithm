package baekjoon.class2.p10845;

/**
 * 큐
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

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

}

class CustomQueue {

    Node head;
    Node tail;
    int size;

    public CustomQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

}

public class Main {

    public static void push(int x, CustomQueue queue) {

        Node newNode = new Node(x);

        if (empty(queue) == 1) {
            queue.head = newNode;
            queue.tail = newNode;
        }

        queue.tail.next = newNode;
        queue.tail = newNode;

        queue.size++;

    }

    public static int pop(CustomQueue queue) {

        Node removeNode;
        int removeData;

        if (empty(queue) == 1) {
            return -1;
        }

        removeNode = queue.head;
        removeData = removeNode.data;
        queue.head = removeNode.next;

        queue.size--;
        return removeData;

    }

    public static int front(CustomQueue queue) {

        if (empty(queue) == 1) {
            return -1;
        }

        return queue.head.data;
    }

    public static int back(CustomQueue queue) {

        if (empty(queue) == 1) {
            return -1;
        }

        return queue.tail.data;
    }

    public static int size(CustomQueue queue) {
        return queue.size;
    }

    public static int empty(CustomQueue queue) {
        return queue.size == 0 ? 1 : 0;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        CustomQueue queue = new CustomQueue();
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
                case "push":
                    push(data, queue);
                    break;
                case "pop":
                    System.out.println(pop(queue));
                    break;
                case "front":
                    System.out.println(front(queue));
                    break;
                 case "back":
                    System.out.println(back(queue));
                    break;
                case "size":
                    System.out.println(size(queue));
                    break;
                case "empty":
                    System.out.println(empty(queue));
                    break;
                default:
                    return;
            }

        }

    }

}