package netapi.mgkim.com.testDemo.java;

import java.util.NoSuchElementException;

public class LinkedList<E> implements ILinkedList<E>, IStack<E>, IQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    @Override
    public void addFirst(E data) {
        Node curNode = new Node(data);
        if(size == 0) {
            head = curNode;
            tail = curNode;
        } else {
            curNode.next = head;
            head = curNode;
        }
        size++;
    }

    @Override
    public void add(E data) {
        if(size == 0) {
            addFirst(data);
            return;
        } else {
            Node curNode = new Node(data);
            tail.next = curNode;
            tail = curNode;
            size++;
        }
    }

    @Override
    public void add(int index, E data) {
        if(index == 0) {
            addFirst(data);
            return;
        } else if(index == size - 1) {
            add(data);
            return;
        }
        Node newNode = new Node(data);
        Node prevNode = findNode(index - 1);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
        size++;
    }

    @Override
    public E peekFirst() {
        if(size == 0) {
            throw new NullPointerException();
        }
        return head.data;
    }

    @Override
    public E peekLast() {
        if(size == 0) {
            throw new NullPointerException();
        }
        return tail.data;
    }

    @Override
    public E pop() {
        if(size == 0) {
            throw new NullPointerException();
        } else if (size == 1) {
            return poll();
        }
        E tempData = tail.data;
        Node prevNode = findNode(size - 2);
        prevNode.next = null;
        tail = prevNode;
        size--;
        return tempData;
    }

    @Override
    public E poll() {
        if(size == 0) {
            throw new NullPointerException();
        }
        E tempData = head.data;
        head = head.next;
        size--;
        if(size == 0) {
            tail = null;
        }
        return tempData;
    }

    @Override
    public E getData(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return findNode(index).data;
    }

    @Override
    public int isSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator Iterator() {
        return new Iterator(head);
    }

    private Node<E> findNode(int index) {
        if(index >= size) {
            throw new NoSuchElementException();
        }
        Node curNode = head;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    class Node<E> {
        private E data;
        private Node next;

        public Node(E data) {
            this.data = data;
        }
    }

    public class Iterator {
        private Node<E> curNode;
        private Node<E> nextNode;
        private int nextIndex;

        private Iterator(Node node) {
            nextNode = node;
            this.nextIndex = 0;
        }

        E next() {
            curNode = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return curNode.data;
        }
        boolean hasNext() {
            return nextIndex < size;
        }
    }
}

interface ILinkedList<E> {
    void add(E data);
    void addFirst(E data);
    void add(int index, E data);
    E pop();
    E poll();
    E peekFirst();
    E peekLast();
    E getData(int index);
    int isSize();
    boolean isEmpty();
}

interface IStack<E> {
    void add(E data);
    E pop();
    E peekLast();
    int isSize();
    boolean isEmpty();
}

interface IQueue<E> {
    void add(E data);
    E poll();
    E peekFirst();
    int isSize();
    boolean isEmpty();
}


