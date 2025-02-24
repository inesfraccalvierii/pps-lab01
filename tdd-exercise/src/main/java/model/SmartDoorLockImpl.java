package model;
import tdd.SmartDoorLock;

public class SmartDoorLockImpl implements SmartDoorLock {

    private String pin;

    public String getPin() {
        return pin;
    }

    public SmartDoorLockState getState() {
        return state;
    }

    private SmartDoorLockState state;

    public SmartDoorLockImpl() {
        this.pin = "";
        this.state = SmartDoorLockState.UNLOCKED;
    }

    @Override
    public void setPin(String pin) {
        if(this.state != SmartDoorLockState.UNLOCKED){
            throw new IllegalStateException("The smartDoor is not unlocked.");
        }
        this.pin = pin;
    }

    @Override
    public void unlock(String pin) {

    }

    @Override
    public void lock() {
        if(this.pin.isEmpty()){
            throw new IllegalStateException("Smartdoors's pin is not set.");
        }
        this.state = SmartDoorLockState.LOCKED;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }

    public enum SmartDoorLockState {
        UNLOCKED,
        LOCKED,
        BLOCKED;
    }
}
