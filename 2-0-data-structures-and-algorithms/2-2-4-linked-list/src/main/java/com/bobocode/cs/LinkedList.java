package com.bobocode.cs;


import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        LinkedList<T> list = new LinkedList<>();

        Arrays.stream(elements).forEach(list::add);
        return list;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            Node<T> temp = tail;
            temp.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        isValidIndex(index, index > size);
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            size++;
            return;
        }
        Node<T> temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        isValidIndex(index, index >= size);
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        isValidIndex(index, index >= size);
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.element;
    }

    private void isValidIndex(int index, boolean index1) {
        if (index < 0 || index1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        return tail.element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        isValidIndex(index, index > size);
        if (index == 0) {
            T element = head.element;
            head = head.next;
            size--;
            return element;
        }
        Node<T> indexNode = head;
        for (int i = 0; i < index - 1; i++) {
            indexNode = indexNode.next;
        }

        T element = indexNode.next.element;
        indexNode.next = indexNode.next.next;
        size--;
        return element;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> indexNode = head;
        while (indexNode != null) {
            if (indexNode.element == element) {
                return true;
            }
            indexNode = indexNode.next;
        }

        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }
}
