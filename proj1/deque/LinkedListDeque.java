package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private static class Node<U> {

        // It is just a node but has two fields
        // which one is for its previous node and one is for its next node.
        private Node<U> prev;
        private final U item;
        private Node<U> next;

        Node(Node<U> p, U item, Node<U> n) {
            this.prev = p;
            this.item = item;
            this.next = n;
        }

        private U getItem(int index) {
            if (index == 0) {
                return this.item;
            }
            return this.next.getItem(index - 1);
        }
    }

    private final Node<T> sentinel;
    private int size;

    // Constructor -- No parameter passed.
    public LinkedListDeque() {
        //T t = null;
        this.sentinel = new Node<>(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;

    }


    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<>(null, item, null);

        // let the original first node's previous to be the new node.
        this.sentinel.next.prev = node;
        // let node's next to be the original first node
        node.next = this.sentinel.next;

        //let sentinel's next to be the new node.
        this.sentinel.next = node;
        // let node's previous to be the sentinel.
        node.prev = this.sentinel;

        this.size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> node = new Node<>(null, item, null);

        this.sentinel.prev.next = node;
        node.prev = this.sentinel.prev;

        this.sentinel.prev = node;
        node.next = this.sentinel;

        this.size++;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        Node<T> node = this.sentinel.next;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(node.item.toString() + " ");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.size() == 0) {
            return null;
        }

        Node<T> removedFirst = this.sentinel.next;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        this.size--;
        return removedFirst.item;
    }

    @Override
    public T removeLast() {
        if (this.size() == 0) {
            return null;
        }

        Node<T> removedLast = this.sentinel.prev;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size--;
        return removedLast.item;
    }

    @Override
    public T get(int index) {
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


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o instanceof Deque) {

            if (this.size() != ((Deque<?>) o).size()) {
                return false;
            }

            for (int i = 0; i < this.size(); i++) {
                if (!Objects.equals(((Deque<?>) o).get(i), this.get(i))) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }


    public T getRecursive(int index) {
        if (this.size() == 0) {
            return null;
        }

        if (index < 0 || index > this.size() - 1) {
            return null;
        }

//        Node<T> n = this.sentinel.next;
//
//        if (index == 0) {
//            return n.item;
//        } else {
//            n = n.next;
//            return getRecursive(index - 1);
//        }

        return this.sentinel.next.getItem(index);

    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int iteratorIndex;

        LinkedListDequeIterator() {
            this.iteratorIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return this.iteratorIndex < size();
        }

        @Override
        public T next() {
            T next = get(iteratorIndex);
            iteratorIndex += 1;
            return next;
        }

    }


}
