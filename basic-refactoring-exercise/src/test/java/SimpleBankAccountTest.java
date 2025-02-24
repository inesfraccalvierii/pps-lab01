import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    private final static double DEPOSIT_AMOUNT = 100;
    private final static double WITHDRAW_AMOUNT = 70;
    private final static String INSUFFICIENT_FOUND_MESSAGE = "Insufficient funds.";
    private final static String WRONG_ID_MESSAGE = "Wrong Id.";

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() throws Exception {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() throws Exception {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        Exception exception = assertThrows(Exception.class, () -> {
            bankAccount.deposit(2, WITHDRAW_AMOUNT);
        });
        assertEquals(WRONG_ID_MESSAGE, exception.getMessage());
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() throws Exception {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAW_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() throws Exception {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        Exception exception = assertThrows(Exception.class, () -> {
            bankAccount.withdraw(2, WITHDRAW_AMOUNT);
        });
        assertEquals(WRONG_ID_MESSAGE, exception.getMessage());
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }


    @Test
    void testWithdrawWithExcessiveAmount() throws Exception {
        bankAccount.deposit(accountHolder.getId(), DEPOSIT_AMOUNT);
        Exception exception = assertThrows(Exception.class, () -> {
            bankAccount.withdraw(accountHolder.getId(), DEPOSIT_AMOUNT + 30);
        });
        assertEquals(INSUFFICIENT_FOUND_MESSAGE, exception.getMessage());
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

}
