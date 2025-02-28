package model;
import tdd.SmartDoorLock;

public class SmartDoorLockImpl implements SmartDoorLock {

    private String pin;
    private int attempts;
    private final static int MAX_ATTEMPTS = 5;
    private final static int RESET_ATTEMPTS = 0;

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
        this.attempts = RESET_ATTEMPTS;
    }

    @Override
    public void setPin(String pin) {
        if(this.state != SmartDoorLockState.UNLOCKED){
            throw new IllegalStateException("The smartDoor is not unlocked.");
        }
        if(isValidFourDigitString(pin)){
            this.pin = pin;
        }

    }

    @Override
    public void unlock(String pin) {
        if (pin.equals( this.pin)){
            this.state = SmartDoorLockState.UNLOCKED;
        }
        else {
            if(getFailedAttempts() == MAX_ATTEMPTS){
                this.state = SmartDoorLockState.BLOCKED;
            }
            else {
                this.attempts++;
            }
        }
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
        return this.state == SmartDoorLockState.LOCKED;
    }

    @Override
    public boolean isBlocked() {
        return this.state == SmartDoorLockState.BLOCKED;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {
        this.pin = "";
        this.state = SmartDoorLockState.UNLOCKED;
        this.attempts = RESET_ATTEMPTS;
    }

    public enum SmartDoorLockState {
        UNLOCKED,
        LOCKED,
        BLOCKED;
    }

    private static boolean isValidFourDigitString(String pin) {
        if (pin.length() != 4) throw new IllegalStateException("More than 4 digit.");
        for (char c : pin.toCharArray()) {
            if (!Character.isDigit(c)) throw new IllegalStateException("Set pin with only digit.");;
        }
        return true;
    }
}
