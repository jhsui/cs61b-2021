package deque;

import java.util.Iterator;

//public class LinkedListDeque<T> implements Iterable<T> {

public class LinkedListDeque<T> {

    private static class Node<T> {

        // It is just a node but has two fields
        // which one is for its previous node and one is for its next node.
        private Node<T> prev;
        private T item;
        private Node<T> next;

        public Node(Node<T> p, T item, Node<T> n) {
            this.prev = p;
            this.next = n;
        }
    }

    private Node<T> sentinel;
    private int size;

    // Constructor -- No parameter passed.
    public LinkedListDeque() {
        this.sentinel = new Node<>(null, null, null);
        this.sentinel.next = this.sentinel.prev;
        this.size = 0;

    }

    // Constructor
    public LinkedListDeque(T item) {
        this.sentinel = new Node<>(null, null, null);
        Node<T> node = new Node<>(null, item, null);

        this.sentinel.next = node;
        node.next = this.sentinel.prev;

        this.size = 1;

    }

    // item is never null.
    public void addFirst(T item) {
        this.sentinel.next = new Node<T>(this.sentinel, item, this.sentinel.next);
        this.size++;
    }

    // item is never null.
    public void addLast(T item) {
        this.sentinel.prev = new Node<T>(this.sentinel.prev, item, this.sentinel);
        this.size++;
    }


    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {

        Node<T> node = this.sentinel.next;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(node.item.toString() + " ");
        }
        System.out.println();
    }

    // if it doesn't exist, return null.
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        }

        Node<T> removedFirst = this.sentinel.next;
        this.sentinel.next = this.sentinel.next.next;
        this.size--;
        return removedFirst.item;
    }

    // if it doesn't exist, return null.
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }

        Node<T> removedLast = this.sentinel.prev;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.size--;
        return removedLast.item;
    }

    public T get(int index) {

        if (this.size() == 0) {
            return null;
        }

        if (index < 0 || index > this.size() - 1) {
            return null;
        }
        // iterate from the first node.
        Node<T> node = this.sentinel.next;
        for (int i = 0; i < this.size(); i++) {
            if (i != index) {
                node = node.next;
            } else {
                return node.item;
            }
        }

        return null;
    }

//    @Override
    public Iterator<T> iterator() {


        return new Iterator<T>() {

            private Node<T> node = sentinel;


            @Override
            public boolean hasNext() {
                return node.next != node.prev;
            }


            @Override
            public T next() {
                Node<T> currentNode = node.next; // we start from the first.
                node = node.next;
                return currentNode.item;
            }

        };
    }

    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque) {
            for (int i = 0; i < this.size(); i++) {
                if (((LinkedListDeque<?>) o).get(i) == null || ((LinkedListDeque<?>) o).get(i) != this.get(i)) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    // nooooooooooooooooooooooooooooooooo
    public T getRecursive(int index) {

        if (this.size() == 0) {
            return null;
        }

        if (index < 0 || index > this.size() - 1) {
            return null;
        }

        // iterate from the first node.
        Node<T> node = this.sentinel.next;

        int i = 0;

        if (i == index) {
            return node.item;
        } else {
            i += 1;
            node = node.next;
            return getRecursive(index);
        }


    }


}
