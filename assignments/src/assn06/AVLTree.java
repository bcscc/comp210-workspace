package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;

    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     *
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateLeft() {
        // You should implement left rotation and then use this
        // method as needed when fixing imbalances.
        // TODO
        AVLTree<T> rightChild = _right;
        _right = rightChild._left;
        rightChild._left = this;
        rightChild._size = _size;
        _size = _left.size() + _right.size()+1;
        updateHeight(this);
        updateHeight(rightChild);

        return rightChild;
    }

    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */

    private AVLTree<T> rotateRight() {
        // You should implement right rotation and then use this
        // method as needed when fixing imbalances.
        // TODO
        AVLTree<T> leftChild = _left;
        _left = leftChild._right;
        leftChild._right = this;
        leftChild._size = _size;
        _size = _left.size() + _right.size()+1;
        updateHeight(this);
        updateHeight(leftChild);

        return leftChild;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() { return _height; }

    @Override
    public int size() { return _size; }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        // TODO
        if (_value == null) {
            this._value = element;
            this._left = new AVLTree<>();
            this._right = new AVLTree<>();
            this._size = 1;
            this._height = 0;
            return this;
        }
        if (element.compareTo(this._value) < 0) {
            this._left = (AVLTree<T>) this._left.insert(element);
            _size += 1;
        } else if (element.compareTo(this._value) > 0){
            this._right = (AVLTree<T>) this._right.insert(element);
            _size += 1;
        } else {
            return this;
        }
        updateHeight(this);
        return rebalance(this);
    }
    void updateHeight(AVLTree<T> node) {
        int leftChildHeight = node._left.height();
        int rightChildHeight = node._right.height();
        node._height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    int balanceFactor(AVLTree<T> node) {
        return node._right.height() - this._left.height();
    }

    AVLTree<T> rebalance(AVLTree<T> node) {
        int balanceFactor = balanceFactor(node);

        if (balanceFactor < -1) {
            if (_left._right.height() > _left._left.height()) {
                node._left = node._left.rotateLeft();
            }
            return node.rotateRight();
        } else if (balanceFactor > 1) {
            if (_right._left.height() > _right._right.height()) {
                node._right = node._right.rotateRight();
            }
            return node.rotateLeft();
        } else {
            return node;
        }
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
        // TODO
        if (this.isEmpty()) {
            AVLTree<T> empty = new AVLTree<>();
            return empty;
        }
        else if (element.compareTo(_value) < 0) {
            _left = (AVLTree<T>) _left.remove(element);
            _size -= 1;
        }
        else if (element.compareTo(_value) > 0) {
            _right = (AVLTree<T>) _right.remove(element);
            _size -= 1;
        }
        else {
            if (_left.isEmpty()) {
                return _right;
            }
            else if (_right.isEmpty()) {
                return _left;
            }
            _value = successor(_right);
            _right = (AVLTree<T>) _right.remove(_value);
        }
        updateHeight(this);
        return rebalance(this);
    }

    private T successor(AVLTree<T> node) {
        T min = node._value;
        while (node._left._value != null) {
            min = node._left._value;
            node = node._left;
        }
        return min;
    }

    @Override
    public T findMin() {
        if (this.isEmpty()) {
            return null;
        } else if (!_left.isEmpty()){
            return _left.findMin();
        } else {
            return _value;
        }
    }

    @Override
    public T findMax() {
        if (this.isEmpty()) {
            return null;
        } else if (!_right.isEmpty()){
            return _right.findMax();
        } else {
            return _value;
        }
    }

    @Override
    public boolean contains(T element) {
        AVLTree<T> node = this;
        while (!(node.isEmpty())) {
            if (node._value == element) {
                return true;
            }
            if (element.compareTo(node._value) < 0) {
                node = (AVLTree<T>) node._left;
            } else {
                node = (AVLTree<T>) node._right;
            }
        }
        return false;
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

        return _right;
    }

    public String toString() {
        return (_left != null ? "{" + _left + "}" : "") + _value + (_right != null ? "(" + _right + ")" : "");
    }
}