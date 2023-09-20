package baekjoon.class2.p10828;

/**
 * 스택
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

class CustomStack {

    Node head;
    int size;

    public CustomStack() {
        this.head = null;
        this.size = 0;
    }

}

public class Main {

    public static void push (int x, CustomStack stack) {

        Node newNode = new Node(x);

        if (empty(stack) == 1) {
            stack.head = newNode;
        }

        newNode.next = stack.head;
        stack.head = newNode;
        stack.size++;

    }

    public static int pop(CustomStack stack) {

        Node removeNode;
        int removeData;

        if (empty(stack) == 1) {
            return -1;
        }

        removeNode = stack.head;
        removeData = removeNode.data;

        if (size(stack) > 1) {
            stack.head = removeNode.next;
        } else {
            stack.head = null;
        }

        stack.size--;
        return removeData;

    }

    public static int top (CustomStack stack) {
        if (empty(stack) == 1) {
            return -1;
        }

        return stack.head.data;
    }

    public static int size (CustomStack stack) {
        return stack.size;
    }

    public static int empty (CustomStack stack) {
        return stack.size == 0 ? 1 : 0;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        CustomStack stack = new CustomStack();
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
                    push(data, stack);
                    break;
                case "pop":
                    System.out.println(pop(stack));
                    break;
                case "top":
                    System.out.println(top(stack));
                    break;
                case "size":
                    System.out.println(size(stack));
                    break;
                case "empty":
                    System.out.println(empty(stack));
                    break;
                default:
                    return;
            }

        }

    }

}