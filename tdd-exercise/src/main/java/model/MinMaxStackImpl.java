package model;

import tdd.MinMaxStack;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {
    private final List<Integer> stack;
    private int numElements;

    private final static int EMPTY_STACK = 0;
    public MinMaxStackImpl() {
        this.stack = new ArrayList<>();
        numElements = EMPTY_STACK;
    }

    @Override
    public void push(int value) {
        this.stack.add(value);
        numElements++;
    }

    @Override
    public int pop() {
        if(isEmpty()){
            throw new IllegalStateException("Empty stack.");
        }
        numElements--;
        return stack.remove(numElements);
    }

    @Override
    public int peek() {
        if(isEmpty()){
            throw new IllegalStateException("Empty stack.");
        }
        return stack.remove(--numElements);
    }

    @Override
    public int getMin() {
        if(isEmpty()){
            throw new IllegalStateException("Empty stack.");
        }
        int min = this.stack.get(--numElements);

        for (int valStack: this.stack) {
            if(valStack < min)
                min = valStack;
        }
        return min;
    }

    @Override
    public int getMax() {
        if(isEmpty()){
            throw new IllegalStateException("Empty stack.");
        }
        int max = this.stack.get(--numElements);

        for (int valStack: this.stack) {
            if(valStack > max)
                max = valStack;
        }
        return max;
    }

    @Override
    public boolean isEmpty() {
        return this.numElements == EMPTY_STACK;
    }

    @Override
    public int size() {
        return this.numElements;
    }
}
