package tdd;

import model.SmartDoorLockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl smartDoorLock;
    private final static String EXAMPLE_PIN = "1234";
    private final static String WRONG_EXAMPLE_PIN = "12c446";
    private final static int MAX_ATTEMPTS = 5;
    private final static String SET_PIN_LOCKED_SMARTDOOR = "The smartDoor is not unlocked.";
    private final static String LOCK_UNSET_PIN = "Smartdoors's pin is not set.";

    @BeforeEach
    void beforeEach(){
      smartDoorLock = new SmartDoorLockImpl();
    }

    @Test
    public void initialSmartDoorLockState() {
        assertEquals(smartDoorLock.isLocked(), false);
    }

    @Test
    public void setNewPinUnlockedSmartDoorLock() {
        smartDoorLock.setPin(EXAMPLE_PIN);
        assertEquals(smartDoorLock.getPin(), EXAMPLE_PIN);
    }

    @Test
    public void setNewPinLockedSmartDoorLock() {
        smartDoorLock.setPin(EXAMPLE_PIN);
        assertEquals(smartDoorLock.getPin(), EXAMPLE_PIN);
        smartDoorLock.lock();
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            smartDoorLock.setPin(EXAMPLE_PIN);
        });
        assertEquals(SET_PIN_LOCKED_SMARTDOOR, exception.getMessage());
    }
    @Test
    public void lockUnlockedSmartDoor() {
        smartDoorLock.setPin(EXAMPLE_PIN);
        smartDoorLock.lock();
        assertEquals(smartDoorLock.getState(), SmartDoorLockImpl.SmartDoorLockState.LOCKED);
    }

    @Test
    public void lockSmartDoorWithUnsetPin() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            smartDoorLock.lock();
        });
        assertEquals(LOCK_UNSET_PIN, exception.getMessage());
    }

    @Test
    public void setNewWrongPin() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            smartDoorLock.setPin(WRONG_EXAMPLE_PIN);
        });
        assertEquals("More than 4 digit.", exception.getMessage());
    }

    @Test
    public void unlock(){
        smartDoorLock.unlock(EXAMPLE_PIN);
        assertEquals(false, smartDoorLock.isLocked());

    }
    @Test
    public void unlockFaild(){
        smartDoorLock.setPin(EXAMPLE_PIN);
        smartDoorLock.lock();
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        var attempts = 1;
        assertEquals(attempts, smartDoorLock.getFailedAttempts());
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    public void neverBlocked(){
        assertFalse( smartDoorLock.isBlocked());
    }

    @Test
    public void blockedByFailingAllTheAttempts(){
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        assertTrue(smartDoorLock.isBlocked());
    }

    @Test
    public void tryUnlockForMaxAttemptsCheckIsBlocked(){
        int attempts = 1;
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        assertFalse(smartDoorLock.isBlocked());
        assertEquals(attempts++, smartDoorLock.getFailedAttempts());
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        attempts++;
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        attempts++;
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        attempts++;
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        assertEquals(attempts++, smartDoorLock.getFailedAttempts());
        assertEquals(MAX_ATTEMPTS, smartDoorLock.getFailedAttempts());
        smartDoorLock.unlock(WRONG_EXAMPLE_PIN);
        assertEquals(true, smartDoorLock.isBlocked());
    }


}
