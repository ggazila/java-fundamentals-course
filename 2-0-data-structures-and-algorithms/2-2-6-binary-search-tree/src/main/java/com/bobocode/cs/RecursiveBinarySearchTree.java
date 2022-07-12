package com.bobocode.cs;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> searchTree = new RecursiveBinarySearchTree<>();
        Arrays.stream(elements).forEach(searchTree::insert);
        return searchTree;
    }

    @Override
    public boolean insert(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        } else {
            return insertInternal(root, element);
        }
    }

    private boolean insertInternal(Node<T> current, T value) {
        if (value.compareTo(current.value) < 0) {
            if (current.left == null) {
                current.left = new Node<>(value);
                size++;
                return true;
            } else {
                return insertInternal(current.left, value);
            }
        } else if (value.compareTo(current.value) > 0) {
            if (current.right == null) {
                current.right = new Node<>(value);
                size++;
                return true;
            } else {
                return insertInternal(current.right, value);
            }

        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        return containsInternal(root, element);
    }

    private boolean containsInternal(Node<T> current, T element) {
        if (current == null) {
            return false;
        }

        if (element.compareTo(current.value) < 0) {
            return containsInternal(current.left, element);
        } else if (element.compareTo(current.value) > 0) {
            return containsInternal(current.right, element);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if (root == null) {
            return 0;
        }
        return depthInternal(root, 0) - 1;
    }

    private int depthInternal(Node<T> current, int i) {
        if (current == null) {
            return 0;
        } else {
            return 1 + Math.max(depthInternal(current.left, i), depthInternal(current.right, i));
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversalInternal(root, consumer);
    }

    private void inOrderTraversalInternal(Node<T> current, Consumer<T> consumer) {
        if (current != null) {
            inOrderTraversalInternal(current.left, consumer);
            consumer.accept(current.value);
            inOrderTraversalInternal(current.right, consumer);
        }
    }

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }
}
