package tdd;

import model.MinMaxStackImpl;
import model.SmartDoorLockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Task 2 - TDD for Min Max Stack
 * A stack that supports retrieving the minimum and maximum values in constant time.
 * A stack is a data structure that allows adding and removing elements in a last-in-first-out (LIFO) manner.
 * Therefore, giving a stack [1, 2, 3], the first element to be removed is 3, then 2, and finally 1.
 * When adding elements, like 4, the stack becomes [1, 2, 3, 4].
 * NB!! You should not call Collections.min or Collections.max to get the min/max values.
 */

class MinMaxStackImplTest {
    MinMaxStack stack;

    private final static int FIRST_PUSH = 1;
    private final static int FIRST_PUSH_NUM_ELEM = 1;

    private final static int EMPTY_STACK = 0;
    private final static String EMPTY_STACK_MESSAGE = "Empty stack.";

    @BeforeEach
    void beforeEach(){
        stack = new MinMaxStackImpl();
    }
    @Test
    public void push() {
        stack.push(FIRST_PUSH);
        assertEquals(FIRST_PUSH_NUM_ELEM, stack.size());
    }
    @Test
    public void popElem() {
        stack.push(FIRST_PUSH);
        stack.pop();
        assertEquals(EMPTY_STACK, stack.size());
    }

    @Test
    public void popEmptyStack() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            stack.pop();
        });
        assertEquals(EMPTY_STACK_MESSAGE, exception.getMessage());
    }


    @Test
    public void peekElem() {
        stack.push(FIRST_PUSH);
        assertEquals(FIRST_PUSH, stack.peek());
    }

    @Test
    public void peekEmptyStack() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            stack.peek();
        });
        assertEquals(EMPTY_STACK_MESSAGE, exception.getMessage());
    }

    @Test
    public void minEmptyStack() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            stack.getMin();
        });
        assertEquals(EMPTY_STACK_MESSAGE, exception.getMessage());
    }

    @Test
    public void maxEmptyStack() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            stack.getMax();
        });
        assertEquals(EMPTY_STACK_MESSAGE, exception.getMessage());
    }


    @Test
    public void minStack() {
        int numToPush = FIRST_PUSH;
        stack.push(numToPush++);
        stack.push(numToPush++);
        stack.push(numToPush++);
        stack.push(numToPush);
        assertEquals(FIRST_PUSH, stack.getMin());
    }

    @Test
    public void maxStack() {
        int numToPush = FIRST_PUSH;
        stack.push(numToPush++);
        stack.push(numToPush++);
        stack.push(numToPush++);
        stack.push(numToPush);
        assertEquals(numToPush, stack.getMax());
    }
}