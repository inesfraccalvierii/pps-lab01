package tdd;

import model.SmartDoorLockImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl smartDoorLock;
    private final static String EXAMPLE_PIN = "1234";

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

    /**
     * Sets a new PIN for the door lock.
     * You can set only when the system is open (not locked or blocked).
     */
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
}
