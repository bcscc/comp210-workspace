package assn04;


import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
    private T _element;
    private BST<T> _left;
    private BST<T> _right;

    public NonEmptyBST(T element) {

        _left = new EmptyBST<T>();
        _right = new EmptyBST<T>();
        _element = element;
    }

    // TODO: insert
    @Override
    public BST<T> insert(T element) {
        if (element.compareTo(_element) < 0) {
            _left = _left.insert(element);
        }
        else {
            _right = _right.insert(element);
        }
        return this;
    }

    // TODO: remove
    @Override
    public BST<T> remove(T element) {
        if (this.isEmpty()) {
            BST<T> empty = new EmptyBST<>();
            return empty;
        }
        else if (element.compareTo(_element) < 0) {
            _left = _left.remove(element);
        }
        else if (element.compareTo(_element) > 0) {
            _right = _right.remove(element);
        }
        else {
            if (_left.isEmpty()) {
                return _right;
            }
            else if (_right.isEmpty()) {
                return _left;
            }
            _element = successor(_right);
            _right = _right.remove(_element);
        }
        return this;
    }

    T successor(BST<T> bst) {
        T min = bst.getElement();
        while (!(bst.getLeft().isEmpty())) {
            min = bst.getLeft().getElement();
            bst = bst.getLeft();
        }
        return min;
    }

    boolean search(BST<T> bst, T element) {
        while (!(bst.isEmpty())) {
            if (bst.getElement() == element) {
                return true;
            }
            if (element.compareTo(bst.getElement()) < 0) {
                bst = bst.getLeft();
            } else {
                bst = bst.getRight();
            }
        }
        return false;
    }

        // TODO: printPreOrderTraversal
    @Override
    public void printPreOrderTraversal() {
        if (this.isEmpty()) {
            return;
        }
        System.out.print(_element + " ");
        if (!_left.isEmpty()) {
            _left.printPreOrderTraversal();
        }
        if (!_right.isEmpty()) {
            _right.printPreOrderTraversal();
        }
    }

    // TODO: printPostOrderTraversal
    @Override
    public void printPostOrderTraversal() {
        if (this.isEmpty()) {
            return;
        }
        if (!_left.isEmpty()) {
            _left.printPostOrderTraversal();
        }
        if (!_right.isEmpty()) {
            _right.printPostOrderTraversal();
        }
        System.out.print(_element + " ");
    }

    // TODO: printBreadthFirstTraversal
    @Override
    public void printBreadthFirstTraversal() {
        Queue<BST<T>> queue = new LinkedList<>();
        queue.add(this);
        while (!(queue.isEmpty())) {
            BST<T> current = queue.poll();
            System.out.print(current.getElement() + " ");
            if (!(current.getLeft().isEmpty())) {
                queue.add(current.getLeft());
            }
            if (!(current.getRight().isEmpty())) {
                queue.add(current.getRight());
            }
        }
    }

    @Override
    public int getHeight() {
        return Math.max(_left.getHeight(), _right.getHeight())+1;
    }


    @Override
    public BST<T> getLeft() {
        return _left;
    }

    @Override
    public BST<T> getRight() {
        return _right;
    }

    @Override
    public T getElement() {
        return _element;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
