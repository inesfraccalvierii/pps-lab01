package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     * Adds an integer onto the queue.
     *
     * @param value The integer to add.
     */
    void enqueue(int value);

    /**
     * removes the first elem.
     *
     */
    void dequeue();

    /**
     * Gets the number of elements currently in the queue.
     *
     * @return The size of the queue.
     */
    int size();


    /**
     * Gets first elem of the queue.
     *
     * @return The fist elem of the queue.
     */
    int front();

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();


}