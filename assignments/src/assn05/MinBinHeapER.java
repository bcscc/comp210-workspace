package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MinBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO: overloaded constructor
    public MinBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = Arrays.asList(initialEntries);
        for (int i = (_heap.size()/2) - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO: enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        _heap.add(patient);
        bubbleUp(_heap.size() - 1);
    }

    // TODO: enqueue
    public void enqueue(V value) {
        Patient patient = new Patient(value);
        _heap.add(patient);
        bubbleUp(_heap.size() - 1);
    }

    int bubbleUp(int index){
        if (index == 0) {
            return(index);
        }
        else {
            Prioritized child = _heap.get(index);
            Prioritized parent = _heap.get((index-1)/2);
            if (child.getPriority().compareTo(parent.getPriority()) < 0){
                _heap.set((index-1)/2, child);
                _heap.set(index, parent);
                return(bubbleUp((index-1)/2));
            }
            else{
                return(index);
            }
        }
    }

    // TODO: dequeue
    @Override
    public V dequeue() {
        if (_heap.size() == 0) {
            return null;
        } else if (_heap.size() == 1) {
            V returnValue = _heap.get(0).getValue();
            _heap.remove(0);
            return (returnValue);
        } else {
            V returnValue = _heap.get(0).getValue();
            _heap.set(0, _heap.get(_heap.size() - 1));   // move last element to the top
            _heap.remove(_heap.size() - 1);
            bubbleDown(0);      // bubbleDown the top element
            return (returnValue);
        }
    }

    void bubbleDown(int index){
        Prioritized parent = _heap.get(index);
        if (!hasLeftChild(index) && !hasRightChild(index)){
            return;
        }
        else if (!hasRightChild(index)) {
            Prioritized leftChild = _heap.get(leftChildInd(index));
            if (leftChild.getPriority().compareTo(parent.getPriority()) < 0) {
                _heap.set(index, leftChild);
                _heap.set(leftChildInd(index), parent);
                bubbleDown(leftChildInd(index));
            }
            else {return;}
        }
        else {      // has 2 children
            Prioritized leftChild = _heap.get(leftChildInd(index));
            Prioritized rightChild = _heap.get(rightChildInd(index));
            if ((leftChild.getPriority().compareTo(parent.getPriority()) < 0) || (rightChild.getPriority().compareTo(parent.getPriority()) < 0)) {
                if (leftChild.getPriority().compareTo(rightChild.getPriority()) < 0) {
                    _heap.set(index, leftChild);
                    _heap.set(leftChildInd(index), parent);
                    bubbleDown(leftChildInd(index));
                } else {
                    _heap.set(index, rightChild);
                    _heap.set(rightChildInd(index), parent);
                    bubbleDown(rightChildInd(index));
                }
            }
        }
    }

    boolean hasLeftChild(int index){
        return(validIndex(leftChildInd(index)));}

    boolean hasRightChild(int index){
        return(validIndex(rightChildInd(index)));}

    static int leftChildInd(int index){
        return (2*index+1);}

    static int rightChildInd(int index){
        return (2*index+2);}

    boolean validIndex(int index){
        if ((index >= 0) && (index <= _heap.size()-1)){
            return true;}
        else {return false;}}


    // TODO: getMin
    @Override
    public V getMin() {
        if (_heap.size() == 0) {
            return null;
        } else {
            return _heap.get(0).getValue();
        }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }

}