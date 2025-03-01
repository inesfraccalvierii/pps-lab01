package model;

import tdd.CircularQueue;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    int[] queue;
    private int numElements;
    private int positionOldestElem;
    private final static int START = 0;
    private final static int MAX_ELEM = 5;
    private int front, rear;
    public CircularQueueImpl() {
        this.queue = new int[MAX_ELEM];
        this.numElements = START;
        this.positionOldestElem = START;
        this.rear = START;
        this.front = START;
    }

    @Override
    public void enqueue(int value) {
        if (numElements == MAX_ELEM) {
            front = (front + 1) % MAX_ELEM;
            numElements--;
        }
        this.queue[rear] = value;
        rear = (rear + 1) % MAX_ELEM;
        numElements++;
    }
    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty queue.");
        }
        int elem = this.queue[front];
        front = (front + 1) % MAX_ELEM;
        numElements--;
    }

    @Override
    public int size() {
        return numElements;
    }

    @Override
    public int front() {
        if (isEmpty()) {
            throw new RuntimeException("Errore: coda vuota!");
        }
        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        return numElements == START;
    }

}
