package tdd;

import model.CircularQueueImpl;
import model.MinMaxStackImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    CircularQueue queue;

    private final static int FIRST_PUSH = 1;
    private final static int FIRST_PUSH_NUM_ELEM = 1;

    private final static int EMPTY_STACK = 0;
    private final static String EMPTY_STACK_MESSAGE = "Empty queue.";

    @BeforeEach
    void beforeEach(){
        queue = new CircularQueueImpl();
    }

    @Test
    public void addElem() {
        this.queue.enqueue(FIRST_PUSH);
        assertEquals(FIRST_PUSH_NUM_ELEM, queue.size());
    }
    @Test
    public void addElemsMoreThanTheSize() {
        var vaules = FIRST_PUSH;
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        assertEquals(this.queue.front(), FIRST_PUSH + 1);
    }
    @Test
    public void removeElem() {
        this.queue.enqueue(FIRST_PUSH);
        this.queue.dequeue();
        assertEquals(EMPTY_STACK, queue.size());
    }

    @Test
    public void removeFromEmptyQueue() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            this.queue.dequeue();
        });
        assertEquals(EMPTY_STACK_MESSAGE, exception.getMessage());
    }

    @Test
    public void getFirstElem() {
        var vaules = FIRST_PUSH;
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        this.queue.enqueue(vaules++);
        assertEquals(this.queue.front(), FIRST_PUSH);
    }

    @Test
    void testIsEmpty() {
        assertTrue(this.queue.isEmpty());

        this.queue.enqueue(FIRST_PUSH);
        assertFalse(queue.isEmpty());

        this.queue.dequeue();
        assertTrue(queue.isEmpty());
    }


}
